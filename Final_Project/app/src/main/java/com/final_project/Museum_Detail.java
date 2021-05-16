package com.final_project;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.gson.Gson;
import java.io.IOException;
import okhttp3.*;
import table.Museum;
public class Museum_Detail extends AppCompatActivity {
    private TextView view1;
    private TextView view2;
    private ImageView img;
    private Handler handler;
    Museum mus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_museum__detail);
        view1 = findViewById(R.id.det);
        view2 = findViewById(R.id.fen);
        img = findViewById(R.id.img);
        Button button = (Button) findViewById(R.id.but);
        int i = 0;
        String mus_name = "瑷珲历史陈列馆";
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
}
