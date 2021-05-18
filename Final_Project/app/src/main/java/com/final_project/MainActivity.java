package com.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import table.Museum;

public class MainActivity extends AppCompatActivity {

    private Button mHome_page;
    private Button mList;
    private Button mRank;
    private Button mDetails;
    private ListView mlv3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mList=(Button)findViewById(R.id.More);
//        mDetails=(Button)findViewById(R.id.Details);
        mRank=(Button)findViewById(R.id.Rank);
        setListeners();
        mlv3 = findViewById(R.id.mainlist);
        mlv3.setAdapter(new MainAdapter(MainActivity.this));
    }

    private void setListeners() {
        mList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ListActivity.class);
                startActivity(intent);
            }
        });
//        mDetails.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(MainActivity.this,Evaluation.class);
//                startActivity(intent);
//            }
//        });
        mRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Rank.class);
                startActivity(intent);
            }
        });
    }

//    private class onClick implements View.OnClickListener{
//        @Override
//        public void onClick(View v){
//            Intent intent=null;
//            switch(v.getId()){
//                case R.id.List:
//                    intent=new Intent(MainActivity.this,ListActivity.class);
//                    break;
//                case R.id.Details:
//                    intent=new Intent(MainActivity.this, Museum_Detail.class);
//                    break;
//                case R.id.Rank:
//                    intent=new Intent(MainActivity.this, Rank.class);
//                    break;
//            }
//            startActivity(intent);
//        }
//    }
}