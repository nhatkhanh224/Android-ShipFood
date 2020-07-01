package com.example.orderfood.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.orderfood.R;
import com.example.orderfood.activity.GioHangMain;
import com.example.orderfood.activity.MainActivity;
import com.example.orderfood.model.GioHang;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GioHangAdapter extends BaseAdapter {
    Context context;
    ArrayList<GioHang> arrayGioHang;

    public GioHangAdapter(Context context, ArrayList<GioHang> arrayGioHang) {
        this.context = context;
        this.arrayGioHang = arrayGioHang;
    }

    @Override
    public int getCount() {
        return arrayGioHang.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayGioHang.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public class ViewHolder
    {
        public TextView txtTenGioHang,txtGiaGioHang;
        public ImageView imgGioHang;
        public Button btnCong,btnTru,btnNum;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (viewHolder==null)
        {
            viewHolder=new ViewHolder();
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.dong_giohang,null);
            viewHolder.txtTenGioHang=(TextView) convertView.findViewById(R.id.txtTenGioHang);
            viewHolder.txtGiaGioHang=(TextView) convertView.findViewById(R.id.txtGiaGioHang);
            viewHolder.imgGioHang=(ImageView) convertView.findViewById(R.id.imgGioHang);
            viewHolder.btnTru=(Button) convertView.findViewById(R.id.btnTru);
            viewHolder.btnNum=(Button) convertView.findViewById(R.id.btnNum);
            viewHolder.btnCong=(Button) convertView.findViewById(R.id.btnCong);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder= (ViewHolder) convertView.getTag();

        }
        GioHang gioHang=(GioHang) getItem(position);
        viewHolder.txtTenGioHang.setText(gioHang.getTensp());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        viewHolder.txtGiaGioHang.setText(decimalFormat.format(gioHang.getGiasp())+ "Đ");
        Picasso.with(context).load(gioHang.getHinhsp()).placeholder(R.mipmap.ic_launcher).into(viewHolder.imgGioHang);
        viewHolder.btnNum.setText(gioHang.getSoluongsp() + "");
        int sl= Integer.parseInt(viewHolder.btnNum.getText().toString());
        if (sl>10)
        {
            viewHolder.btnCong.setVisibility(View.INVISIBLE);
            viewHolder.btnTru.setVisibility(View.VISIBLE);
        }
        else if (sl<1){
            viewHolder.btnTru.setVisibility(View.INVISIBLE);
        }
        else if (sl>=1)
        {
            viewHolder.btnCong.setVisibility(View.VISIBLE);
            viewHolder.btnTru.setVisibility(View.VISIBLE);
        }
        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newSL=Integer.parseInt(finalViewHolder.btnNum.getText().toString()) +1;
                int curSL= MainActivity.mangGioHang.get(position).getSoluongsp();
                long curGia=MainActivity.mangGioHang.get(position).getGiasp();
                MainActivity.mangGioHang.get(position).setSoluongsp(newSL);
                long newGia=(curGia*newSL)/curSL;
                MainActivity.mangGioHang.get(position).setGiasp(newGia);
                DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
                finalViewHolder.txtGiaGioHang.setText(decimalFormat.format(newGia)+ "Đ");
                GioHangMain.EvenUltil();
                if (newSL>9){
                    finalViewHolder.btnCong.setVisibility(View.INVISIBLE);
                    finalViewHolder.btnTru.setVisibility(View.VISIBLE);
                    finalViewHolder.btnNum.setText(String.valueOf(newSL));
                }
                else {
                    finalViewHolder.btnCong.setVisibility(View.VISIBLE);
                    finalViewHolder.btnTru.setVisibility(View.VISIBLE);
                    finalViewHolder.btnNum.setText(String.valueOf(newSL));
                }
            }
        });
        viewHolder.btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newSL=Integer.parseInt(finalViewHolder.btnNum.getText().toString()) -1;
                int curSL= MainActivity.mangGioHang.get(position).getSoluongsp();
                long curGia=MainActivity.mangGioHang.get(position).getGiasp();
                MainActivity.mangGioHang.get(position).setSoluongsp(newSL);
                long newGia=(curGia*newSL)/curSL;
                MainActivity.mangGioHang.get(position).setGiasp(newGia);
                DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
                finalViewHolder.txtGiaGioHang.setText(decimalFormat.format(newGia)+ "Đ");
                GioHangMain.EvenUltil();
                if (newSL<2){
                    finalViewHolder.btnCong.setVisibility(View.VISIBLE);
                    finalViewHolder.btnTru.setVisibility(View.INVISIBLE);
                    finalViewHolder.btnNum.setText(String.valueOf(newSL));
                }
                else {
                    finalViewHolder.btnCong.setVisibility(View.VISIBLE);
                    finalViewHolder.btnTru.setVisibility(View.VISIBLE);
                    finalViewHolder.btnNum.setText(String.valueOf(newSL));
                }
            }

        });
        return convertView;
    }
}
