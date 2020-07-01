package com.example.orderfood.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderfood.R;
import com.example.orderfood.activity.MainActivity;
import com.example.orderfood.activity.SanPhamMain;
import com.example.orderfood.model.LoaiSP;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
public class LoaiSPAdapter extends RecyclerView.Adapter<LoaiSPAdapter.MyViewHolder> {
    ArrayList<LoaiSP> listLoai;
    Context context;

    public LoaiSPAdapter(ArrayList<LoaiSP> listLoai, Context context) {
        this.listLoai = listLoai;
        this.context = context;
    }

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View itemView=layoutInflater.inflate(R.layout.loaisp,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final LoaiSP loaiSP=listLoai.get(position);
        holder.txtTenLoai.setText(loaiSP.getTenloai());
        Picasso.with(context).load(loaiSP.getUrl()).placeholder(R.mipmap.ic_launcher).into(holder.imgLoai);
        holder.imgLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,SanPhamMain.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id_loaisp",loaiSP.getId_loaisp());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return listLoai.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenLoai;
        ImageView imgLoai;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenLoai=(TextView) itemView.findViewById(R.id.txtTenLoai);
            imgLoai=(ImageView) itemView.findViewById(R.id.imgLoai);

        }
    }

}

