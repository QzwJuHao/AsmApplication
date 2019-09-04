package com.example.asmapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("AsmApplication", "MainActivity - onCreate: 执行");
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_bt).setOnClickListener(this);
        Log.d("AsmApplication", "MainActivity - onCreate: 结束");
    }

    @Override
    public void onClick(View view) {
        Log.d("AsmApplication", "onClick: 点击事件执行");
        startActivity(new Intent(this,SecondActivity.class));
    }
}
