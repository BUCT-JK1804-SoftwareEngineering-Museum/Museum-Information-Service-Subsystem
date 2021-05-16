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
import table.Comment;
import table.New;

public class Evaluation extends AppCompatActivity {
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation);
        GetCom com=new GetCom("瑷珲历史陈列馆");

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

    public class GetCom {
        private List<Comment> c;

        public GetCom(String name) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    // 新建线程连接数据库
                    String s = PostCom(name);
                    System.out.println(s);
                    c = parseJSONData_Comment(s);
                    System.out.println(c.toString());
                    Message msg = new Message();
                    msg.what = 6;
                    msg.obj=c;
                    handler.sendMessage(msg);
                }
            });
            t.start();
        }

        public List<Comment> getC() {
            return c;
        }
    }

    public String PostCom(String name)
    {
        String s =new String();
        String url = "http://192.168.142.1/InformationServe/comment.php";
        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody formBody = new FormBody.Builder()
                .add("user_name", name)
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

    private List parseJSONData_Comment(String jsonData) {
        Gson gson = new Gson();//创建Gson对象
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonElements = jsonParser.parse(jsonData).getAsJsonArray();//获取JsonArray对象
        ArrayList<Comment> CommentList = new ArrayList<>();
        for (JsonElement bean : jsonElements) {
            Comment bean1 = gson.fromJson(bean, Comment.class);//解析
            CommentList.add(bean1);
        }        // data就是整个JSON数据映射的一个对象
        return CommentList;
    }
}