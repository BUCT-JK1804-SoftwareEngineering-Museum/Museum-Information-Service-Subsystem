package com.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WComment extends AppCompatActivity {
    private Button sub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wcomment);
        sub=(Button)findViewById(R.id.submit);
        setListeners();
    }
    private void setListeners(){
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WComment.this,Museum_Detail.class);
//                此处如何传入数据库需要补齐 默认重新跳转至博物馆详情界面
                startActivity(intent);
            }
        });
    }
}