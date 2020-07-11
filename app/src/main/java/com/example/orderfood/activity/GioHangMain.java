package com.example.orderfood.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.orderfood.R;
import com.example.orderfood.adapter.GioHangAdapter;
import com.example.orderfood.model.GioHang;

import java.text.DecimalFormat;

public class GioHangMain extends AppCompatActivity {
    ListView lvGioHang;
    static TextView TongTien;
    TextView txtThongBao;
    Button btnThanhToan,btnMuaTiep;
    Toolbar toolbarGioHang;
    GioHangAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang_main);
        AnhXa();
//        ActionToolbar();
        CheckData();
        EvenUltil();
        XoaSanPham();
        MuaTiep();
        Order();
    }

    private void Order() {
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.mangGioHang.size()>0)
                {
                    Intent intent=new Intent(getApplicationContext(),ThongTinKhachHang.class);
                    startActivity(intent);
                }else
                {
                    Toast.makeText(GioHangMain.this, "Giỏ hàng trống", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void MuaTiep() {
        btnMuaTiep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void XoaSanPham() {
        lvGioHang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view, final int position, long id) {
                AlertDialog.Builder builder=new AlertDialog.Builder(GioHangMain.this);
                builder.setTitle("Xác nhận xóa sản phẩm");
                builder.setMessage("Bạn có chắc muốn xóa sản phẩm này");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (MainActivity.mangGioHang.size()<=0)
                        {
                            txtThongBao.setVisibility(View.VISIBLE);
                        }
                        else {
                            MainActivity.mangGioHang.remove(position);
                            adapter.notifyDataSetChanged();
                            EvenUltil();
                            if (MainActivity.mangGioHang.size()<=0){
                                txtThongBao.setVisibility(View.VISIBLE);
                            }
                            else{
                                txtThongBao.setVisibility(View.INVISIBLE);
                                adapter.notifyDataSetChanged();
                                EvenUltil();
                            }
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.notifyDataSetChanged();
                        EvenUltil();
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    private void CheckData() {
        if (MainActivity.mangGioHang.size()<=0){
            adapter.notifyDataSetChanged();
            txtThongBao.setVisibility(View.VISIBLE);
            lvGioHang.setVisibility(View.INVISIBLE);
        }
        else {
            adapter.notifyDataSetChanged();
            txtThongBao.setVisibility(View.INVISIBLE);
            lvGioHang.setVisibility(View.VISIBLE);
        }
    }

    public static void EvenUltil() {
        long tongtien=0;
        for (int i=0;i<MainActivity.mangGioHang.size();i++){
            tongtien+=MainActivity.mangGioHang.get(i).getGiasp();
        }
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        TongTien.setText(decimalFormat.format(tongtien)+ "Đ");
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarGioHang);
    }

    private void setSupportActionBar(Toolbar toolbarGioHang) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarGioHang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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

    private void AnhXa() {
        txtThongBao=(TextView) findViewById(R.id.txtThongBao);
        lvGioHang=(ListView) findViewById(R.id.lvGioHang);
        TongTien=(TextView) findViewById(R.id.txtTongTien);
        btnThanhToan=(Button) findViewById(R.id.btnMuaHang);
        btnMuaTiep=(Button) findViewById(R.id.btnMuaTiep);
        toolbarGioHang=(Toolbar) findViewById(R.id.toolbarGioHang);
        adapter=new GioHangAdapter(GioHangMain.this,MainActivity.mangGioHang);
        lvGioHang.setAdapter(adapter);
    }
}
