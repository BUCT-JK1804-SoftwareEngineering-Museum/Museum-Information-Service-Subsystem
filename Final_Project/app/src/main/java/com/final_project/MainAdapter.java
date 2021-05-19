package com.final_project;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainAdapter extends BaseAdapter {

    private Context mContext;
    private final String[] names = {"中国科学技术馆","中国人民革命军事博物馆","中国航天博物馆","首都博物馆","中国人民抗日战争纪念馆"};
    private final String[] scores = {"https://cstm.cdstm.cn/","http://www.jb.mil.cn/", "http://www.casc-spacemuseum.com/",
    "http://www.capitalmuseum.org.cn/",    "http://www.1937china.com/"};
    private int[] imagesId={R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4,R.drawable.img5};

    private LayoutInflater mLayoutInflater;

    MainAdapter(Context context){
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public Object getItem(int position) {
        return names[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolder{
        public ImageView img;
        private TextView Musename;
        public TextView Musepos;
    }

    @SuppressLint("ResourceType")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = mLayoutInflater.inflate(R.layout.layout_main,null);
            holder = new ViewHolder();
            holder.img = convertView.findViewById(R.id.Mainimg);
            holder.Musename = convertView.findViewById(R.id.Mainmusename);
            holder.Musepos = convertView.findViewById(R.id.Mainmusepos);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.Musename.setText(names[position]);
        holder.Musepos.setText(scores[position]);
        holder.img.setImageResource(imagesId[position]);
        return convertView;
    }
}
