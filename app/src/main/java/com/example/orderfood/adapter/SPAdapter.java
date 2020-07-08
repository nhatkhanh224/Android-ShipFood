package com.example.orderfood.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.orderfood.R;
import com.example.orderfood.activity.SanPhamMain;
import com.example.orderfood.model.SanPham;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SPAdapter extends BaseAdapter {
    private SanPhamMain context;
    private int layout;
    private List<SanPham> SPList;

    public SPAdapter(SanPhamMain context, int layout, List<SanPham> SPList) {
        this.context = context;
        this.layout = layout;
        this.SPList = SPList;
    }

    @Override
    public int getCount() {
        return SPList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder
    {
        TextView txtTenSP,txtMieuTaSP;
        ImageView imgSP;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null)
        {
            viewHolder=new ViewHolder();
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(layout,null);
            viewHolder.txtTenSP=(TextView)convertView.findViewById(R.id.txtTenSPChiTiet);
            viewHolder.txtMieuTaSP=(TextView)convertView.findViewById(R.id.txtMieuTaSP);
            viewHolder.imgSP=(ImageView) convertView.findViewById(R.id.imgSP);

            convertView.setTag(viewHolder);
        }
        else{
            viewHolder=(ViewHolder) convertView.getTag();
        }
        final SanPham sanpham=SPList.get(position);
        viewHolder.txtTenSP.setText(sanpham.getTen_sanpham());
        viewHolder.txtMieuTaSP.setText(sanpham.getMieuta());
        Picasso.with(context).load(sanpham.getUrl_sp()).placeholder(R.mipmap.ic_launcher).into(viewHolder.imgSP);
        return convertView;
    }
}
