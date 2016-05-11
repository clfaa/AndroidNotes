package com.clfaa.androidnotes.anim;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.clfaa.androidnotes.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 问题描述：
 * 1.animation-list：在一些机型上,动画不需要调用AnimationDrawable#start方法便会播放，其他机型则需要。
 * 2.将animation-list作为selector的默认状态item，selector作为ImageView的src或背景时。在animation-list默认播放的机型上,停止animation-list，当ImageView被重绘后（如变成press状态后松开），animation-list变成了播放状态。
 * <p/>
 * 机型一: 三星s4 Android4.4.2 默认播放
 * 机型二: 红米note3 Android5.0 需要调用start才会播放。
 * <p/>
 *
 *
 * @author changlifei
 * @since 2016-04-25
 */
public class FrameAnimActivity1 extends AppCompatActivity {

    @InjectView(R.id.img1)
    ImageView img1;
    @InjectView(android.R.id.button1)
    Button button1;
    @InjectView(android.R.id.text1)
    TextView text1;

    /**
     * 动画状态：true:播放状态，false:停止状态
     */
    private boolean isAnimRunning = false;

    private AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_anim1);
        ButterKnife.inject(this);

        StateListDrawable stateListDrawable = (StateListDrawable) img1.getDrawable();

        animationDrawable = (AnimationDrawable) stateListDrawable.getCurrent();

        isAnimRunning = animationDrawable.isRunning();
        setViews();

        //解决方案
        img1.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                if (!isAnimRunning){
                    animationDrawable.stop();
                }
                return true;
            }
        });
    }


    @OnClick(R.id.img1)
    public void onClick(View view) {
        //没有此方法，按下imageView没有效果
        ;
    }
    @OnClick(android.R.id.button1)
    public void stopOrRunAnim(){
        isAnimRunning = !isAnimRunning;
        setViews();
    }

    public void setViews(){
        if (!isAnimRunning) {
            animationDrawable.stop();
            button1.setText("start");
            text1.setText("Not Running");
        } else {
            animationDrawable.start();
            button1.setText("stop");
            text1.setText("Running");
        }
    }
}
