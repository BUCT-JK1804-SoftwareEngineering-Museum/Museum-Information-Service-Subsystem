package com.final_project;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.final_project.R;

public class EvalutionAdapter extends BaseAdapter{

    private Context mContext;
    private LayoutInflater mLayoutInflater;
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
        public TextView tvName,tvScore,tvComments;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView==null)
        {
            convertView=mLayoutInflater.inflate(R.layout.layout_evaluation,null);
            holder=new ViewHolder();
            holder.imageView=(ImageView)convertView.findViewById(R.id.iv);
            holder.tvComments=(TextView)convertView.findViewById(R.id.tv_comments);
            holder.tvName=(TextView)convertView.findViewById(R.id.tv_name);
            holder.tvScore=(TextView)convertView.findViewById(R.id.tv_score);
            convertView.setTag(holder);
        }else
        {
            holder=(ViewHolder)convertView.getTag();
        }
        //给控件赋值
        holder.tvName.setText("此处应为用户名：");
        holder.tvScore.setText("评分：");
        holder.tvComments.setText("评价：");
        return convertView;
    }
}