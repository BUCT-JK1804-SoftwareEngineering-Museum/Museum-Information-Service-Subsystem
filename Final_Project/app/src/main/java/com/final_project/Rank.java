package com.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import table.Comment;
import table.Museum;

public class Rank extends AppCompatActivity {
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        GetGrade gra=new GetGrade();

        handler = new Handler() {
            @SuppressLint("HandlerLeak")
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        try {
                            //获取结果
                            Comment result = (Comment) msg.obj;

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

    public class GetGrade {
        private List<Museum> g;

        public GetGrade() {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    // 新建线程连接数据库
                    String s = GetG();
                    System.out.println(s);
                    g = parseJSONData_Grade(s);
                    System.out.println(g.toString());
                    Message msg = new Message();
                    msg.what = 7;
                    msg.obj=g;
                    handler.sendMessage(msg);
                }
            });
            t.start();
        }

        public List<Museum> getG() {
            return g;
        }
    }

    public String GetG()
    {
        String s = new String();
        String url = "http://192.168.142.1/InformationServe/museumGrade.php";
        OkHttpClient okHttpClient = new OkHttpClient();

        final Request request = new Request.Builder()
                .url(url)
                .get()
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

    private List parseJSONData_Grade(String jsonData) {
        Gson gson = new Gson();//创建Gson对象
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonElements = jsonParser.parse(jsonData).getAsJsonArray();//获取JsonArray对象
        ArrayList<Museum> Gradelist = new ArrayList<>();
        for (JsonElement bean : jsonElements) {
            Museum bean1 = gson.fromJson(bean, Museum.class);//解析
            Gradelist.add(bean1);
        }
        // data就是整个JSON数据映射的一个对象
        return Gradelist;
    }
}