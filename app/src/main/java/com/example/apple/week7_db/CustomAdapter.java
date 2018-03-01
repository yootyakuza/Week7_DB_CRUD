package com.example.apple.week7_db;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


/**
 * Created by Sarayut on 28/2/2018 AD.
 */

public class CustomAdapter extends BaseAdapter {
    Context mContext;
    String[] strName;
    String[] strPhone;
    String[] strSalary;

    public CustomAdapter(Context mContext, String[] strName, String[] strPhone, String[] strSalary) {
        this.mContext = mContext;
        this.strName = strName;
        this.strPhone = strPhone;
        this.strSalary = strSalary;
    }

    @Override
    public int getCount() {
        return strName.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(view == null)
            view = mInflater.inflate(R.layout.listview_row,parent,false);

        TextView textView = (TextView)view.findViewById(R.id.textView1);
        textView.setText(strName[position]);
        TextView textView1 = (TextView)view.findViewById(R.id.textView2);
        textView1.setText(strPhone[position]);
        TextView textView3 = (TextView)view.findViewById(R.id.textView3);
        textView3.setText(strSalary[position]);

        return view;
    }
}
