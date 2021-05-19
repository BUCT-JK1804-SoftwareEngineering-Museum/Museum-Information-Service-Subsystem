package com.final_project.rank;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.final_project.EvalutionAdapter;
import com.final_project.Museum_Detail;
import com.final_project.R;

import table.Comment;
import table.Museum;

import com.final_project.Rank;
import com.google.gson.*;

import okhttp3.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseAdapter{

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private Handler handler;
    private List<Museum> result;
    private String mus_name;
    public MyAdapter(Context context)
    {
        this.mContext=mContext;
        mLayoutInflater=LayoutInflater.from(context);
    }

    private final String[] names = {"中国科学技术馆","中国人民革命军事博物馆","中国航天博物馆","首都博物馆","中国人民抗日战争纪念馆"
    ,"中国农业博物馆","天津博物馆","天津自然博物馆","邯郸市博物馆","抗美援朝纪念馆","旅顺博物馆","大连现代博物馆","吉林省博物院","瑷珲历史陈列馆","中共一大会址纪念馆","上海科技馆","陈云纪念馆","南京市博物总馆","浙江自然博物馆","中国丝绸博物馆"};
    private int[] imagesId={R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4,R.drawable.img5,R.drawable.img6,R.drawable.img7,R.drawable.img8
    ,R.drawable.img9,R.drawable.img10,R.drawable.img11,R.drawable.img12,R.drawable.img13,R.drawable.img14,R.drawable.img15,R.drawable.img16,R.drawable.img17,R.drawable.img18
    ,R.drawable.img19,R.drawable.img20};

    @Override
    public int getCount() {
        return 20;
    }

    @Override
    public Object getItem(int position) {
        return names[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolder
    {
        public ImageView imageView;
        public TextView tvRank,tvName,tvScore;
    }
    private ViewHolder holder = null;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null)
        {
            convertView=mLayoutInflater.inflate(R.layout.layout_rank,null);
            holder=new ViewHolder();
            holder.imageView=(ImageView)convertView.findViewById(R.id.iv);
            holder.tvRank=(TextView)convertView.findViewById(R.id.tv_rank);
            holder.tvName=(TextView)convertView.findViewById(R.id.tv_name);
            holder.tvScore=(TextView)convertView.findViewById(R.id.tv_score);
            convertView.setTag(holder);
        }else
        {
            holder=(ViewHolder)convertView.getTag();
        }

//        int i = 0;
//        String mus_name = "首都博物馆";
//        GetGrade gra=new GetGrade();

        handler = new Handler() {
            @SuppressLint("HandlerLeak")
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        try {
                            //获取结果
                            result = (List<Museum>) msg.obj;
                            //给控件赋值
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        break;
                }
            }
        };
        holder.imageView.setImageResource(imagesId[position]);
        holder.tvRank.setText("No."+(position+1));
        holder.tvName.setText(names[position]);
        holder.tvScore.setText("评分："+(5-position*0.1)+"分");
        return convertView;
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
                    msg.what = 1;
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
