package com.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import table.New;

public class NewsDetail extends AppCompatActivity {
    private TextView view2;
    private TextView view3;
    private TextView view4;
    private TextView view5;
    private TextView view6;
    private TextView view7;
    private Handler handler;
    private ImageView imageView1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        view2 = findViewById(R.id.sd2);
        view3 = findViewById(R.id.sd3);
        view4 = findViewById(R.id.sd4);
        view5 = findViewById(R.id.sd5);
        view6 = findViewById(R.id.sd6);
        view7 = findViewById(R.id.sd7);
        imageView1= findViewById(R.id.img1);
        GetNew ne=new GetNew("首都博物馆");

        handler = new Handler() {
            @SuppressLint("HandlerLeak")
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        try {
                            //获取结果
                            List<New> result = (List<New>) msg.obj;
                            New f=result.get(0);
                            view2.setText( "新闻类型："+f.getNew_level());
                            view3.setText( f.getNew_title());
                            view4.setText("发布人："+ f.getNew_publisher());
                            view5.setText("发布时间："+ f.getNew_time());
                            view6.setText( f.getNew_content());
                            view7.setText( "爬取源："+f.getNew_source());
                            Glide.with(NewsDetail.this).load(f.getNew_pic()).into(imageView1);
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

    public class GetNew {
        private List<New> n;


        public GetNew(String name) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    // 新建线程连接数据库
                    String s = PostNew(name);
                    System.out.println(s);
                    n = parseJSONList_new(s);
                    System.out.println(n.toString());
                    Message msg = new Message();
                    msg.what = 1;
                    msg.obj=n;
                    handler.sendMessage(msg);
                }
            });
            t.start();
        }


        public List<New> getN() {
            return n;
        }
    }

    public String PostNew(String name)
    {
        String s =new String();
        String url = "http://192.168.142.1/InformationServe/new.php";
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
            s =response.body().string();
            Log.d("TAG", "run: " + s);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return s;
    }
    private List parseJSONList_new(String jsonData) {
        Gson gson = new Gson();//创建Gson对象
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonElements = jsonParser.parse(jsonData).getAsJsonArray();//获取JsonArray对象
        ArrayList<New> NewList = new ArrayList<>();
        for (JsonElement bean : jsonElements) {
            New bean1 = gson.fromJson(bean, New.class);//解析
            NewList.add(bean1);
        }
        return NewList;
    }

}