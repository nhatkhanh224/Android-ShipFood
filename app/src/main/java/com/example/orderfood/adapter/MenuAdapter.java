package com.example.orderfood.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.orderfood.R;
import com.example.orderfood.model.Menu;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MenuAdapter extends BaseAdapter {

    Context context;
    private int layout;
    ArrayList<Menu> arrayList;

    public MenuAdapter(Context context, int layout, ArrayList<Menu> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null ;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public class ViewHolder{
        TextView txtMenu;
        ImageView imgMenu;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder = new ViewHolder();
            holder.txtMenu = (TextView) view.findViewById(R.id.txtMenu);

            holder.imgMenu = (ImageView) view.findViewById(R.id.imgMenu);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        //gan gia tri
        Menu menu = arrayList.get(position);
        holder.txtMenu.setText(menu.getTenloaisp());
        holder.imgMenu.setImageResource(menu.getImage());

        return view;
    }
}
