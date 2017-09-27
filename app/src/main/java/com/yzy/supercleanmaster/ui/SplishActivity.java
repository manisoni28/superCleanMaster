package com.yzy.supercleanmaster.ui;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.yzy.supercleanmaster.R;
import com.yzy.supercleanmaster.base.BaseActivity;
import com.yzy.supercleanmaster.service.CleanerService;
import com.yzy.supercleanmaster.service.CoreService;
import com.yzy.supercleanmaster.utils.SharedPreferencesUtils;

import java.util.Random;


public class SplishActivity extends BaseActivity {

    private Animation mFadeIn;
    private Animation mFadeInScale;
    private Animation mFadeOut;


    ImageView mImageView;

    public static final String ACTION_INSTALL_SHORTCUT = "com.android.launcher.action.INSTALL_SHORTCUT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splish);
        mImageView = (ImageView) findViewById(R.id.image);
        int index = new Random().nextInt(2);
        if (index == 1) {
            mImageView.setImageResource(R.drawable.entrance3);
        } else {
            mImageView.setImageResource(R.drawable.entrance2);
        }
        startService(new Intent(this, CoreService.class));
        startService(new Intent(this, CleanerService.class));


        if (!SharedPreferencesUtils.isShortCut(mContext)) {
            createShortCut();
        }

        initAnim();
        setListener();
    }

    private void createShortCut() {
        // TODO Auto-generated method stub
        Intent intent = new Intent();
        intent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "One key to speed up");
        intent.putExtra("duplicate", false);
        intent.putExtra(Intent.EXTRA_SHORTCUT_ICON, BitmapFactory.decodeResource(getResources(), R.drawable.short_cut_icon));
        Intent i = new Intent();
        i.setAction("com.yzy.shortcut");
        i.addCategory("android.intent.category.DEFAULT");
        intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, i);
        sendBroadcast(intent);
        SharedPreferencesUtils.setIsShortCut(mContext, true);
    }

    private void initAnim() {
        mFadeIn = AnimationUtils.loadAnimation(this, R.anim.welcome_fade_in);
        mFadeIn.setDuration(500);
        mFadeInScale = AnimationUtils.loadAnimation(this,
                R.anim.welcome_fade_in_scale);
        mFadeInScale.setDuration(2000);
        mFadeOut = AnimationUtils.loadAnimation(this, R.anim.welcome_fade_out);
        mFadeOut.setDuration(500);
        mImageView.startAnimation(mFadeIn);
    }


    public void setListener() {
        mFadeIn.setAnimationListener(new AnimationListener() {

            public void onAnimationStart(Animation animation) {

            }

            public void onAnimationRepeat(Animation animation) {

            }

            public void onAnimationEnd(Animation animation) {
                mImageView.startAnimation(mFadeInScale);
            }
        });
        mFadeInScale.setAnimationListener(new AnimationListener() {

            public void onAnimationStart(Animation animation) {

            }

            public void onAnimationRepeat(Animation animation) {

            }

            public void onAnimationEnd(Animation animation) {
                startActivity(MainActivity.class);
                finish();
            }
        });
        mFadeOut.setAnimationListener(new AnimationListener() {

            public void onAnimationStart(Animation animation) {

            }

            public void onAnimationRepeat(Animation animation) {

            }

            public void onAnimationEnd(Animation animation) {
            }
        });
    }
}
