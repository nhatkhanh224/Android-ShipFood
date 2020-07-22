package com.example.orderfood.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.orderfood.R;
import com.example.orderfood.adapter.SPAdapter;
import com.example.orderfood.model.SanPham;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SanPhamMain extends AppCompatActivity {
    String urlGetDataSP="http://192.168.78.2/androidwebservice/orderfood/getsp.php?page=";
    ArrayList<SanPham> arraySP;
    ListView lvSP;
    SPAdapter adapter;
    private Toolbar toolbar;
    int idloai=0;
    int page=1;
    View footerView;
    boolean isLoading=false;
    boolean limitData=false;
    mHandler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_san_pham);
        AnhXa();
        GetIdLoaiSP();
        GetData(page);
        LoadMoreData();
        Actionbar();
    }

    private void Actionbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public class mHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 0:lvSP.addFooterView(footerView);
                    break;
                case 1:GetData(++page);
                    isLoading=false;
                    break;

            }
            super.handleMessage(msg);
        }

    }
    public class ThreadData extends Thread{
        @Override
        public void run() {
            mHandler.sendEmptyMessage(0);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message=mHandler.obtainMessage(1);
            mHandler.sendMessage(message);
            super.run();
        }
    }

    private void LoadMoreData() {
        lvSP.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(), ChiTietFood.class);
                intent.putExtra("thongtinsanpham",arraySP.get(position));
                startActivity(intent);
            }
        });
        lvSP.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem+visibleItemCount==totalItemCount && totalItemCount !=0 && isLoading==false && limitData==false){
                    isLoading=true;
                    ThreadData threadData=new ThreadData();
                    threadData.start();
                }
            }
        });
    }

    private void AnhXa() {
        toolbar=findViewById(R.id.toolbarSP);
        lvSP=(ListView) findViewById(R.id.lvGioHang);
        arraySP=new ArrayList<>();
        adapter=new SPAdapter(this,R.layout.sp,arraySP);
        lvSP.setAdapter(adapter);
        LayoutInflater inflater= (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerView=inflater.inflate(R.layout.progressbar,null);
        mHandler=new mHandler();
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
    private void GetData(int page) {
        RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
        String duongdan=urlGetDataSP+String.valueOf(page);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int ID=0;
                int ID_LOAI=0;
                String SanPham="";
                String URL_SP="";
                String MieuTa="";
                int Gia=0;
                if (response!=null && response.length() !=2 ){
                    lvSP.removeFooterView(footerView);
                    try {
                        JSONArray jsonArray=new JSONArray(response);
                        for (int i=0;i<jsonArray.length();i++)
                        {
                            JSONObject object=jsonArray.getJSONObject(i);
                                ID=object.getInt("ID");
                                ID_LOAI=object.getInt("ID_LOAI");
                                SanPham=object.getString("SanPham");
                                URL_SP=object.getString("UrlSP");
                                MieuTa=object.getString("MieuTa");
                                Gia=object.getInt("Gia");
                                arraySP.add(new SanPham(ID,ID_LOAI,SanPham,URL_SP,MieuTa,Gia));
                                adapter.notifyDataSetChanged();

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    limitData=true;
                    lvSP.removeFooterView(footerView);

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> params=new HashMap<String,String>();
                params.put("idsanpham",String.valueOf(idloai));
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

    private void GetIdLoaiSP() {
        idloai=getIntent().getIntExtra("id_loaisp",-1);
        Log.d("giatriloaisp",idloai+"");

    }


}
