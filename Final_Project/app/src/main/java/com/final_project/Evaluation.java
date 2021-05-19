package com.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.final_project.rank.MyAdapter;

public class Evaluation extends AppCompatActivity {
    private ListView mlv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation);
        mlv2 = (ListView)findViewById(R.id.lv_1);
        EvalutionAdapter adapter = new EvalutionAdapter(Evaluation.this);//新建并配置ArrayAapeter
        mlv2.setAdapter(adapter);
    }
}