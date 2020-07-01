package com.example.orderfood.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.orderfood.R;
import com.example.orderfood.activity.GioHangMain;
import com.example.orderfood.activity.MainActivity;
import com.example.orderfood.model.GioHang;
import com.example.orderfood.model.SanPham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class ChiTietFood extends AppCompatActivity {
    ImageView imgChiTietSP;
    TextView txtTenSP,txtGiaSP;
    Button btnOrder;
    Spinner spinner;
    int ID=0;
    int ID_LOAI=0;
    String SanPham="";
    int Gia=0;
    String url="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_food);
        AnhXa();
        EventSpinner();
        GetData();
        EventButton();
    }

    private void EventButton() {
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.mangGioHang.size()>=0)
                {
                    int sl=Integer.parseInt(spinner.getSelectedItem().toString());
                    boolean exit=false;
                    for (int i=0;i<MainActivity.mangGioHang.size();i++)
                    {
                        if (MainActivity.mangGioHang.get(i).getIdsp()==ID){
                            MainActivity.mangGioHang.get(i).setSoluongsp(MainActivity.mangGioHang.get(i).getSoluongsp()+sl);
                            if (MainActivity.mangGioHang.get(i).getSoluongsp()>=10){
                                MainActivity.mangGioHang.get(i).setSoluongsp(10);
                            }
                            MainActivity.mangGioHang.get(i).setGiasp(Gia*MainActivity.mangGioHang.get(i).getSoluongsp());
                            exit=true;
                        }
                    }
                    if (exit==false){
                        int soluong=Integer.parseInt(spinner.getSelectedItem().toString());

                        long GiaMoi=soluong*Gia;

                        MainActivity.mangGioHang.add(new GioHang(ID,SanPham,GiaMoi,url,soluong));
                    }

                }else{
                    int soluong=Integer.parseInt(spinner.getSelectedItem().toString());
                    long GiaMoi=soluong*Gia;
                    MainActivity.mangGioHang.add(new GioHang(ID,SanPham,GiaMoi,url,soluong));
                }
                Intent intent=new Intent(getApplicationContext(), GioHangMain.class);
                startActivity(intent);
            }



        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuGioHang:
                Intent intent=new Intent(getApplicationContext(),GioHangMain.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    private void GetData() {
        ImageView imageView=(ImageView) findViewById(R.id.imgSPChiTiet);
        com.example.orderfood.model.SanPham sanPham= (SanPham) getIntent().getSerializableExtra("thongtinsanpham");
        ID=sanPham.getId_sanpham();
        ID_LOAI=sanPham.getId_loaisp();
        SanPham=sanPham.getTen_sanpham();
        Gia=sanPham.getGia();
        url=sanPham.getUrl_sp();
        txtTenSP.setText(SanPham);
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        txtGiaSP.setText("Giá: "+decimalFormat.format(Gia)+" Đ");
        Picasso.with(getApplicationContext()).load(url).placeholder(R.mipmap.ic_launcher).into(imageView);
    }
    private void EventSpinner() {
        Integer[] soluong=new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayAdapter=new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_dropdown_item,soluong);
        spinner.setAdapter(arrayAdapter);
    }
    private void AnhXa() {
        imgChiTietSP=(ImageView) findViewById(R.id.imgSP);
        txtTenSP=(TextView) findViewById(R.id.txtTenSPChiTiet);
        txtGiaSP=(TextView) findViewById(R.id.txtGiaSPChiTiet);
        spinner=(Spinner) findViewById(R.id.spinner);
        btnOrder=(Button) findViewById(R.id.btnOrder);
    }
}
