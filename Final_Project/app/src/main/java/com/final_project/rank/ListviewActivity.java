package com.final_project.rank;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.final_project.R;

public class ListviewActivity extends Activity {
    private ListView mLvt1;
    @Override
    protected  void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        mLvt1=(ListView)findViewById(R.id.lvt_1);
        mLvt1.setAdapter(new MyAdapter(ListviewActivity.this));

    }
}

