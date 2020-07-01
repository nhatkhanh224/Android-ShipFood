package com.example.orderfood.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.orderfood.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ThongTinKhachHang extends AppCompatActivity {
    Button btnXacNhan;
    EditText txtTenNguoiMua,txtSDT,txtDiaChi;
    String urlInsert="http://192.168.78.2/androidwebservice/orderfood/insert.php";
    String urlChiTiet="http://192.168.78.2/androidwebservice/orderfood/chitietorder.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_khach_hang);
        AnhXa();
        NhapThongTin();
    }

    private void NhapThongTin() {
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                final String name=DangNhapActivity.khachHang.getTen();
                final String ten=txtTenNguoiMua.getText().toString().trim();
                final String sdt=txtSDT.getText().toString().trim();
                final String diachi=txtDiaChi.getText().toString().trim();
                if (ten.length()>0 &&sdt.length()>0 && diachi.length()>0){
                    RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest=new StringRequest(Request.Method.POST, urlInsert, new Response.Listener<String>() {
                        @Override
                        public void onResponse(final String madonhang) {
                            Log.d("madonhang",madonhang);
                            if (Integer.parseInt(madonhang) >0)
                            {
                                RequestQueue queue=Volley.newRequestQueue(getApplicationContext());
                                StringRequest request=new StringRequest(Request.Method.POST, urlChiTiet, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        if (response.equals("1")){
                                            MainActivity.mangGioHang.clear();
                                            Toast.makeText(ThongTinKhachHang.this, "Thành công", Toast.LENGTH_SHORT).show();
                                            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                                            startActivity(intent);
                                        }
                                        else {
                                            Toast.makeText(ThongTinKhachHang.this, "Lỗi", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                }){
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        JSONArray jsonArray=new JSONArray();
                                        for (int i=0;i<MainActivity.mangGioHang.size();i++)
                                        {
                                            JSONObject jsonObject=new JSONObject();
                                            try {
                                                jsonObject.put("madonhang",madonhang);
                                                jsonObject.put("masanpham",MainActivity.mangGioHang.get(i).getIdsp());
                                                jsonObject.put("tensanpham",MainActivity.mangGioHang.get(i).getTensp());
                                                jsonObject.put("gia",MainActivity.mangGioHang.get(i).getGiasp());
                                                jsonObject.put("soluong",MainActivity.mangGioHang.get(i).getSoluongsp());
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            jsonArray.put(jsonObject);
                                        }
                                        HashMap<String,String> hashMap=new HashMap<String, String>();
                                        hashMap.put("json",jsonArray.toString());
                                        return hashMap;
                                    }
                                };
                                queue.add(request);
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String,String> hashMap=new HashMap<>();
                            hashMap.put("tenkhachhang",ten);
                            hashMap.put("sdt",sdt);
                            hashMap.put("diachi",diachi);
                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);
                }
                else{
                    Toast.makeText(ThongTinKhachHang.this, "Nhập đầy đủ dữ liệu", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void AnhXa() {
        btnXacNhan=(Button) findViewById(R.id.btnXacNhan);
        txtTenNguoiMua=(EditText) findViewById(R.id.txtTenNguoiMua);
        txtSDT=(EditText) findViewById(R.id.txtSDT);
        txtDiaChi=(EditText) findViewById(R.id.txtDiaChiNguoiMua);
    }
}
