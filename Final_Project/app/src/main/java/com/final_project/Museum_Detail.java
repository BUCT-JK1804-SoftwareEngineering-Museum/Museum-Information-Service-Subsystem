package com.final_project;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import java.io.IOException;
import okhttp3.*;
import table.Museum;
public class Museum_Detail extends AppCompatActivity {
    private TextView view1;
    private TextView view2;
    private TextView view3;
    private TextView view4;
    private TextView view5;
    private TextView view6;
    private TextView view7;
    private ImageView img;
    private Handler handler;
    private Button mdnews;
    private Button mdexhi;
    private Button mdobj;
    private Button mdeva;
    private Button mcom;
    private String mus_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_museum__detail);
        view1 = findViewById(R.id.det);
        view2 = findViewById(R.id.fen);
        view3 = findViewById(R.id.addr);
        view4 = findViewById(R.id.opentime);
        view5 = findViewById(R.id.phonenum);
        view6 = findViewById(R.id.curator);
        view7 = findViewById(R.id.othermessage);
        img = findViewById(R.id.img);
        mdnews = (Button) findViewById(R.id.news);
        mdexhi = (Button) findViewById(R.id.exhibition);
        mdobj = (Button) findViewById(R.id.object);
        mdeva = (Button) findViewById(R.id.wevaluation);
        mcom = (Button) findViewById(R.id.vcomment);
        setListeners();
        Intent i = getIntent();  //直接获取传过来的intent
        mus_name = i.getStringExtra("muse_name");
//        int i = 0;
//        String mus_name = "首都博物馆";
        GetMus m = new GetMus(mus_name);

        handler = new Handler() {
            @SuppressLint("HandlerLeak")
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        try {
                            //获取博物馆表结果
                            Museum result = (Museum) msg.obj;
                            view1.setText(result.getMus_name());
                            view2.setText("评分：" + result.getMus_grade());
                            view3.setText("博物馆地址:"+result.getMus_address());
                            view4.setText("博物馆开放时间:"+result.getMus_time());
                            view5.setText("博物馆电话:"+result.getMus_phone());
                            view6.setText("博物馆馆长:"+result.getMus_master());
                            view7.setText("博物馆其他信息:"+result.getMus_remark());
                            Glide.with(Museum_Detail.this).load(result.getMus_picture()).into(img);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        break;
                }
            }
        };
    }

    public class GetMus {
        private Museum m;

        public GetMus(String name) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    // 新建线程连接数据库
                    String s = PostMus(name);
                    System.out.println(s);
                    m = parseJSONData_Mus(s);
                    System.out.println(m.toString());
                    Message msg = new Message();
                    msg.what = 1;
                    msg.obj = m;
                    handler.sendMessage(msg);
                }
            });
            t.start();
        }

        public Museum getM() {
            return m;
        }
    }

    public String PostMus(String name) {
        String s = new String();
        String url = "http://192.168.142.1/InformationServe/museum.php";
        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody formBody = new FormBody.Builder()
                .add("mus_name", name)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        final Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            s = response.body().string();
            Log.d("TAG", "run: " + s);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return s;
    }

    private Museum parseJSONData_Mus(String jsonData) {
        Gson gson = new Gson();
        Museum data = gson.fromJson(jsonData, Museum.class);
        // data就是整个JSON数据映射的一个对象
        return data;
    }

    private void setListeners() {
        mdnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Museum_Detail.this, NewsDetail.class);
                intent.putExtra("muse_name", mus_name);
                startActivity(intent);
            }
        });
        mdexhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Museum_Detail.this, Exhibition_Detail.class);
                intent.putExtra("muse_name", mus_name);
                startActivity(intent);
            }
        });
        mdobj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Museum_Detail.this, Object_Detail.class);
                intent.putExtra("muse_name", mus_name);
                startActivity(intent);
            }
        });
        mdeva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Museum_Detail.this, WComment.class);
                startActivity(intent);
            }
        });
        mcom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Museum_Detail.this,Evaluation.class);
                intent.putExtra("muse_name", mus_name);
                startActivity(intent);
            }
        });
    }
}
