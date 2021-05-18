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
import table.Exhibition;

public class Exhibition_Detail extends AppCompatActivity {
    private Handler handler;
    private TextView view1;
    private TextView view3;
    private TextView view4;
    private TextView view5;
    private ImageView imageView1;
    private ImageView imageView2;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exhibition__detail);
        GetExhibition ex=new GetExhibition("首都博物馆");
        view1 = findViewById(R.id.tv_2);
        view3=findViewById(R.id.tv_8);
        view4=findViewById(R.id.tv_4);
        view5=findViewById(R.id.tv_6);
        imageView1=findViewById(R.id.img1);
        imageView2=findViewById(R.id.img2);
        handler = new Handler() {
            @SuppressLint("HandlerLeak")
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        try {
                            //获取结果
                            List<Exhibition> result = (List<Exhibition>) msg.obj;
                            Exhibition t=result.get(i);
                            view1.setText("展览名称："+t.getExh_name());
                            view3.setText("展览时间："+t.getExh_time());
                            view4.setText("博物馆名称："+t.getMus_name());
                            view5.setText(t.getExh_info());
                            Glide.with(Exhibition_Detail.this).load(t.getExh_picture()).into(imageView1);
                            Glide.with(Exhibition_Detail.this).load(t.getExh_picture()).into(imageView2);

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

    public class GetExhibition
    {

        private List<Exhibition> e;

        public GetExhibition(String name) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    // 新建线程连接数据库
                    String s = PostExhibition(name);
                    System.out.println(s);
                    e=  parseJSONList_Exh(s);
                    System.out.println(e.toString());
                    Message msg = new Message();
                    msg.what = 1;
                    msg.obj=e;
                    handler.sendMessage(msg);
                }
            });
            t.start();
        }

        public List<Exhibition> getE() {
            return e;
        }
    }

    public String  PostExhibition(String name)
    {
        String s =new String();
        String url = "http://192.168.142.1/InformationServe/exhibition.php";
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
    private List parseJSONList_Exh(String jsonData)
    {
        Gson gson = new Gson();//创建Gson对象
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonElements = jsonParser.parse(jsonData).getAsJsonArray();//获取JsonArray对象
        ArrayList<Exhibition> exhibitionList = new ArrayList<>();
        for (JsonElement bean : jsonElements) {
            Exhibition bean1 = gson.fromJson(bean, Exhibition.class);//解析
            exhibitionList.add(bean1);
        }
        return exhibitionList;
    }
}