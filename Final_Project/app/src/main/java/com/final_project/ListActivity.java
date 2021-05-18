package com.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

public class ListActivity extends AppCompatActivity {

    private ListView mlv1;
    private SearchView mSearchView;



    String[] names = new String[]{"A","瑷珲历史陈列馆","B","宝鸡青铜器博物院","C","成都金沙遗址博物馆","成都武侯祠博物馆","重庆红岩革命历史博物馆","重庆自然博物馆","陈云纪念馆","D","大连现代博物馆","大唐西市博物馆","E","F","G","广东省博物馆","甘肃省博物馆","古田会议纪念馆","广西民族博物馆","固原博物馆","H","邯郸市博物馆","I","J","吉林省博物馆","K","抗美援朝博物馆","L","旅顺博物馆","洛阳市博物馆",

            "M","N","南昌八一起义纪念馆","宁波博物院","南京市博物总馆","O","P","Q","青海省博物馆","泉州海外交通史博物馆","R","瑞金中央革命根据地纪念馆","S","上海科技馆","首都博物馆","T","天津博物馆","天津自然博物馆","天水博物馆",

            "U","V","W","潍坊市博物馆","温州博物馆","X","西安半坡博物馆","西安博物院","Y","烟台市博物馆","Z",

            "中国海军博物馆","中国航天博物馆","中国徽州文化博物馆","中国科学技术馆"

            ,"中国闽台缘博物馆","中国农业博物馆","中国人民革命军事博物馆","中国人民抗日战争纪念馆","自贡市盐业历史博物馆","中共一大会址纪念馆","浙江自然博物馆","遵义会议博物馆"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mSearchView = findViewById(R.id.searchView);
        mlv1 = findViewById(R.id.List1);
        mlv1.setTextFilterEnabled(true);
        ArrayAdapter a = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);
        mlv1.setAdapter(a);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!TextUtils.isEmpty(newText)){
                    mlv1.setFilterText(newText);
                }else{
                    mlv1.clearTextFilter();
                }
                return false;
            }
        });

        mlv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = (String) a.getItem(position);
                if(s == "瑷珲历史陈列馆"){
                    Intent intent = new Intent(ListActivity.this, Museum_Detail.class);
                    startActivity(intent);
                }
            }
        });
    }
}
