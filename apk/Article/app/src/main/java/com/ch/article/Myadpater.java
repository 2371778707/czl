package com.ch.article;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class Myadpater extends BaseAdapter {
    private List list;
    private Context context;
    public Myadpater(List list,Context context){
        this.list=list;
        this.context=context;

    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

            convertView = View.inflate(context, R.layout.item_list, null);
            TextView textView = (TextView) convertView.findViewById(R.id.textView);
            textView.setText(list.get(position).toString());

        return convertView;
    }
}

