package com.clfaa.androidnotes.camera;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.clfaa.androidnotes.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 使用系统相机获取图片时，
 * 1.如果指定输出目录（takePicIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile))），在onActivityResult中返回的data == null,进而无法获取图像缩略图
 * 2.如果不指定输出目录，在onActivityResult中返回的data不为null,可以获取图像缩略图
 */
public class TakePhotoActivity extends AppCompatActivity {


    static final int REQUEST_IMAGE_CAPTURE = 1;
    @InjectView(R.id.img)
    ImageView img;
    @InjectView(R.id.button)
    Button button;
    @InjectView(R.id.button2)
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.button)
    public void tackPhotoFromCamare() {
        PackageManager packageManager = getPackageManager();
        boolean hasCamera = packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
        if (hasCamera) {
            dispatchTakePictureIntent();
        }

    }

    @OnClick(R.id.button2)
    public void tackPhotoFromCamare2() {
        PackageManager packageManager = getPackageManager();
        boolean hasCamera = packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
        if (hasCamera) {
            dispatchTakePictureNoOutputIntent();
        }

    }

    /**
     * 调用相机
     */
    private void dispatchTakePictureIntent() {
        Intent takePicIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePicIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (photoFile != null) {
                takePicIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                startActivityForResult(takePicIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    /**
     * 调用相机,不指定输出目录
     */
    private void dispatchTakePictureNoOutputIntent() {
        Intent takePicIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePicIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePicIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            galleryAddPic();
            if (data != null) {
                Bundle extras = data.getExtras();
                Bitmap bitmap = (Bitmap) extras.get("data");
                if (bitmap != null) {
                    img.setImageBitmap(bitmap);//显示缩略图
                }
            }else {
                if(TextUtils.isEmpty(mCurrentPhotoPath)) {
                    setPic();//显示原图
                }
            }
        }
    }

    String mCurrentPhotoPath;

    /**
     * 创建File，用以保存图片
     *
     * @return
     * @throws IOException
     */
    private File createImageFile() throws IOException {
        String timeStmp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStmp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }

    /**
     * 将照片添加到相册中
     */
    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File file = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(file);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    /**
     * 解码一幅缩放图片
     */
    private void setPic(){
        int targetW = img.getWidth();
        int targetH = img.getHeight();

        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath,bmOptions);

        int scaleFactor = Math.min(bmOptions.outWidth / targetW,bmOptions.outHeight /targetH);

        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath,bmOptions);
        img.setImageBitmap(bitmap);
    }
}
