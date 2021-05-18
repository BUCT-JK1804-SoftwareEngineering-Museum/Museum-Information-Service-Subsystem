package com.final_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainAdapter extends BaseAdapter {

    private Context mContext;
    private final String[] names = {"A","瑷珲历史陈列馆","B","C","D"};
    private final String[] scores = {"5","4","3","2","1"};

    private LayoutInflater mLayoutInflater;

    MainAdapter(Context context){
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 5;
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
        return convertView;
    }
}
