package com.bwie.android.zhangyaozhongview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private MyView myView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        myView = findViewById(R.id.mView);
//        myView.invalidate();//重绘，直接触发onDraw()方法，只能在主线程通知重绘
//        myView.postInvalidate();//重绘，直接触发onDraw()方法，面试题，区别，子线程可以通知重绘
    }
}
