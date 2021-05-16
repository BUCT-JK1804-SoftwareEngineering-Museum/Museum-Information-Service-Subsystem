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
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import table.Collection;
import table.Exhibition;

public class Exhibition_Detail extends AppCompatActivity {
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exhibition__detail);
        GetExhibition ex=new GetExhibition("瑷珲历史陈列馆");

        handler = new Handler() {
            @SuppressLint("HandlerLeak")
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        try {
                            //获取结果
                            Exhibition result = (Exhibition) msg.obj;

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
                    msg.what = 3;
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