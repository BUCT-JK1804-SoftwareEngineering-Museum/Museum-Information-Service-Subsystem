package com.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.final_project.rank.MyAdapter;

import table.Museum;

public class Rank extends AppCompatActivity {

    private ListView mlv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        mlv1 = (ListView)findViewById(R.id.lv_1);
        MyAdapter adapter = new MyAdapter(Rank.this);//新建并配置ArrayAapeter
        mlv1.setAdapter(adapter);
        mlv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = (String) adapter.getItem(position);
                System.out.println(s);
                Intent intent = new Intent(Rank.this, Museum_Detail.class);
                intent.putExtra("muse_name", s);
                startActivity(intent);
            }
        });
    }
}