package com.clfaa.androidnotes.utils;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by changlifei on 16/5/11.
 */
public class FileUtil {


    /**
     * 图片 flag
     */
    public static final int MEDIA_TYPE_IMAGE = 1;
    /**
     * Video flag
     */
    public static final int MEDIA_TYPE_VIDEO = 2;

    private static FileUtil SingleInstance = null;

    private Context mContext;

    private FileUtil(Context context){
        this.mContext = context;
    }

    public static void onCreate(Context context){
        if (context != null){
            SingleInstance = new FileUtil(context);
        }
    }

    public static FileUtil getInstance(){
        return SingleInstance;
    }


    public File getOutputMediaFile(int type) throws IOException {

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), (String) mContext.getPackageManager().getApplicationLabel(mContext.getApplicationInfo()));

        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("AndroidNotes", "failed to create directory");
                return null;
            }
        }

        String timeStmp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File file;
        if (type == MEDIA_TYPE_IMAGE){
            file = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStmp + ".jpg");
//            file = File.createTempFile(mediaStorageDir.getPath(), ".jpg", mediaStorageDir);
        } else if(type == MEDIA_TYPE_VIDEO) {
            file = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_"+ timeStmp + ".mp4");
        } else {
            return null;
        }
        return file;
    }
}
