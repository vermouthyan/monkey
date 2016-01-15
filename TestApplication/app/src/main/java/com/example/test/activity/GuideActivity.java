package com.example.test.activity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Point;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ViewSwitcher.ViewFactory;

import com.example.test.testapplication.R;

public class GuideActivity extends Activity {
    private ImageSwitcher mImageSwitcher;
    private int[] mImgResIds;
    private int mCurrentImageIndx;
    private Point mDownPoint = new Point();

    private Animation mRight2LeftIn,mRight2leftOut;

    private String filename = "config";
    private String firstRunning = "true";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        SharedPreferences sp = getSharedPreferences(filename, MODE_PRIVATE);
        String config = sp.getString(firstRunning, null);
        if(config != null){
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
        mImageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
        mImgResIds = new int[]{
                R.drawable.image01,
                R.drawable.image02,
                R.drawable.image03,
                R.drawable.image04

        };

        /*
         * 给使用setFactoru给mImageSwitcher添加两个子级控件
         */

        mImageSwitcher.setFactory(new ViewFactory() {

            @Override
            public View makeView() {
                // TODO Auto-generated method stub
                ImageView v = new ImageView(getApplicationContext());


                //这里是内部类所以不能用this 要用getApplicationContext()
                v.setScaleType(ScaleType.FIT_XY);
                v.setImageResource(mImgResIds[mCurrentImageIndx]);

                return v;
            }
        });
        mRight2LeftIn = AnimationUtils.loadAnimation(this, R.anim.right2left_in);
        mRight2leftOut = AnimationUtils.loadAnimation(this, R.anim.right2left_out);
    }
    /**
     * 判断手势
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownPoint.x = (int) event.getX();
                break;
            case MotionEvent.ACTION_UP:
                if(event.getX() - mDownPoint.x >10){
                    //left ->right :previous

                }
                if(mDownPoint.x - event.getX() >10){
                    // right->left :next
                    if(mCurrentImageIndx < mImgResIds.length - 1){
                        mCurrentImageIndx++;
                        ((ImageView)mImageSwitcher.getNextView()).setImageResource(mImgResIds[mCurrentImageIndx]);
                        mImageSwitcher.setInAnimation(mRight2LeftIn);
                        mImageSwitcher.setOutAnimation(mRight2leftOut);
                        mImageSwitcher.showNext();
                    /*
                     * 判断如果到最后一张图片就显示按钮
                     * */
                        if(mCurrentImageIndx == mImgResIds.length - 1){
                            findViewById(R.id.btn_go_to_main).setVisibility(View.VISIBLE);
                        }
                    }
                }
                break;

            default:
                break;
        }
        return super.onTouchEvent(event);
    }
    public void startMain(View v){
        SharedPreferences sp = getSharedPreferences(filename, MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putString(firstRunning, "false");
        editor.commit();
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
        finish();//引导界面的Activity就会退出，回退栈中也会清除该Activity，点击回退按钮也不会回退到引导界面
    }


}

