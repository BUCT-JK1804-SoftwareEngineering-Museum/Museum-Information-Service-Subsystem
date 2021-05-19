package com.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import table.Comment;
import com.google.gson.*;

import okhttp3.*;
import java.io.IOException;

public class WComment extends AppCompatActivity {
    private Button sub;
    private String mus_name;
    private TextView view1;
    private TextView view2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wcomment);
        sub=(Button)findViewById(R.id.submit);
        Intent i = getIntent();  //直接获取传过来的intent
        mus_name = i.getStringExtra("muse_name");
        setListeners();
    }
    private void setListeners(){
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("OK");
                view1 = findViewById(R.id.wrtv_1);
                view2 = findViewById(R.id.edit_text2);
//                PutComment co=new PutComment(mus_name,"张三","太棒了！","5");
                Toast.makeText(WComment.this,"评论成功",Toast.LENGTH_SHORT).show();
//                Intent intent=new Intent(WComment.this,Museum_Detail.class);
//                intent.putExtra("muse_name", mus_name);
////                此处如何传入数据库需要补齐 默认重新跳转至博物馆详情界面
//                startActivity(intent);
            }
        });
    }

    public class PutComment {
        private String a;

        public PutComment(String mus_name,String user_name,String com_info,String com_grade) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    // 新建线程连接数据库
                    String s = PostCom(mus_name, user_name, com_info, com_grade);
                    System.out.println(s);
                    String m= parseJSONData_Com(s);
                    System.out.println(m.toString());
//                    Message msg = new Message();
//                    msg.what = 5;
//                    msg.obj=m;
//                    handler.sendMessage(msg);
                }
            });
            t.start();
        }

        public String getA() {
            return a;
        }
    }

    public String PostCom(String mus_name,String user_name,String com_info,String com_grade)
    {
        String s =new String();
        String url = "http://192.168.142.1/InformationServe/avgCommit.php";
        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody formBody = new FormBody.Builder()
                .add("mus_name", mus_name)
                .add("user_name", user_name)
                .add("com_info",com_info)
                .add("com_grade",com_grade)
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
    private String parseJSONData_Com(String jsonData) {
        Gson gson = new Gson();
        String data = gson.fromJson(jsonData, String.class);
        // data就是整个JSON数据映射的一个对象
        return data;

    }


}