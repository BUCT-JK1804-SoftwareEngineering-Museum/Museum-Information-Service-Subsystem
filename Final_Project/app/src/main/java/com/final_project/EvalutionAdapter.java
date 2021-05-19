package com.final_project;



import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;
import com.final_project.R;
import table.Comment;
import com.google.gson.*;

import okhttp3.*;
import java.io.IOException;

public class EvalutionAdapter extends BaseAdapter{

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private Handler handler;
    private List<Comment> result;
    public EvalutionAdapter(Context context)
    {
        this.mContext=mContext;
        mLayoutInflater=LayoutInflater.from(context);

    }
    @Override
    public int getCount() {
        return 20;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolder
    {
        public ImageView imageView;
        public TextView tvName,tvScore,tvComments,tvcommentstime;
    }

    private ViewHolder holder = null;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null)
        {
            convertView=mLayoutInflater.inflate(R.layout.layout_evaluation,null);
            holder=new ViewHolder();
            holder.tvComments=(TextView)convertView.findViewById(R.id.tv_comments);
            holder.tvName=(TextView)convertView.findViewById(R.id.tv_name);
            holder.tvScore=(TextView)convertView.findViewById(R.id.tv_score);
            holder.tvcommentstime=(TextView)convertView.findViewById(R.id.tv_commentstime);
            convertView.setTag(holder);
        }else
        {
            holder=(ViewHolder)convertView.getTag();
        }

        GetCom com=new GetCom("首都博物馆");

        handler = new Handler() {
            @SuppressLint("HandlerLeak")
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        try {
                            //获取结果
                            result = (List<Comment>) msg.obj;
                            holder.tvName.setText("用户id："+result.get(position).getUser_id());
                            holder.tvScore.setText("评分："+result.get(position).getCom_grade());
                            holder.tvComments.setText("评价："+result.get(position).getCom_info());
                            holder.tvcommentstime.setText("评论时间："+result.get(position).getCom_time());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        break;
                }
            }
        };
        //给控件赋值

        return convertView;
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
                    msg.what = 1;
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