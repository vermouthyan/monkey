package com.example.test.activity;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.testapplication1.R;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.lang.annotation.Annotation;
import java.util.zip.Inflater;

public class HomeActivity extends Activity {

    private final static String TAG = "HomeActivity";
    private Context mContext;

    private TextView mTextView;
    private RelativeLayout mRl;
    private Button mButton;
    private View mView;
    private LayoutInflater mInflater;
    private String test;
    private String test1;
    private String test2;
    private String test3;
    private String test5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext = this;
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_home);
        mInflater = LayoutInflater.from(mContext);
        View button = mInflater.inflate(R.layout.add_view, null);
        mRl.addView(button);

        ActivityManager manager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
        int heapSize = manager.getMemoryClass();
        Toast.makeText(this,"可分配内存" + heapSize, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        Log.e(TAG, "--执行onContentChanged()方法kkkkkkkkkkkkkkkkkkkkkkkkkk--");
        mRl = (RelativeLayout) findViewById(R.id.rl);
        mTextView = (TextView) findViewById(R.id.tv_hello);
        mButton = (Button) findViewById(R.id.btn_go_to);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mRl.scrollTo(-20,-100);
                Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.hyperspace_jump);
                mTextView.setAnimation(animation);
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.e(TAG, "--执行onWindowFocusChanged()方法--");
        Rect rect = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        int statusBarHeight = rect.top;
        Toast.makeText(this, statusBarHeight + "", Toast.LENGTH_SHORT).show();

        Rect rect1 = new Rect();
        getWindow().findViewById(Window.ID_ANDROID_CONTENT).getDrawingRect(rect1);
        Toast.makeText(this, rect1.width() + "--" + rect1.height(), Toast.LENGTH_SHORT).show();
    }

}
