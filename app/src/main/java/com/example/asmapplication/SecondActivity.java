package com.example.asmapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("AsmApplication", "SecondActivity - onCreate: 执行");
        setContentView(R.layout.activity_second);
        Log.d("AsmApplication", "SecondActivity - onCreate: 结束");
    }
}
