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
import table.Museum;

public class Object_Detail extends AppCompatActivity {
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object__detail);
        GetCollection col=new GetCollection("瑷珲历史陈列馆");

        handler = new Handler() {
            @SuppressLint("HandlerLeak")
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        try {
                            //获取结果
                            Collection result = (Collection) msg.obj;

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

    public class GetCollection
    {

        private List<Collection> c;

        public GetCollection(String name) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    // 新建线程连接数据库
                    String s = PostCollection(name);
                    System.out.println(s);
                    c=  parseJSONList_Col(s);
                    System.out.println(c.toString());
                    Message msg = new Message();
                    msg.what = 2;
                    msg.obj=c;
                    handler.sendMessage(msg);
                }
            });
            t.start();
        }

        public List<Collection> getC() {
            return c;
        }
    }

    public String  PostCollection(String name)
    {
        String s =new String();
        String url = "http://192.168.142.1/InformationServe/Collection.php";
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
    private List parseJSONList_Col(String jsonData)
    {
        Gson gson = new Gson();//创建Gson对象
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonElements = jsonParser.parse(jsonData).getAsJsonArray();//获取JsonArray对象
        ArrayList<Collection> collectionList = new ArrayList<>();
        for (JsonElement bean : jsonElements) {
            Collection bean1 = gson.fromJson(bean, Collection.class);//解析
            collectionList.add(bean1);
        }
        return collectionList;
    }
}

