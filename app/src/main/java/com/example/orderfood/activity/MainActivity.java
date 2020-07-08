package com.example.orderfood.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import android.widget.ViewFlipper;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.orderfood.adapter.MenuAdapter;
import com.example.orderfood.adapter.SPAdapter;
import com.example.orderfood.model.GioHang;
import com.example.orderfood.model.LoaiSP;
import com.example.orderfood.adapter.LoaiSPAdapter;
import com.example.orderfood.R;
import com.example.orderfood.model.Menu;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ViewFlipper simpleViewFlipper;
    int[] images = {R.drawable.slide,R.drawable.slidehai,R.drawable.slideba};
    String urlGetData="http://192.168.78.2/androidwebservice/orderfood/getdata.php";
    ArrayList<LoaiSP> arrayLoaiSP;
    ArrayList<Menu> menuArrayList;
    RecyclerView recyclerView;
    LoaiSPAdapter adapter;
    private Toolbar toolbar;
    MenuAdapter menuAdapter;
    ListView lvMenu;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    public static ArrayList<GioHang> mangGioHang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        GetData(urlGetData);
        FoodSlide();
        ActionBar();
        menuAdapter=new MenuAdapter(this,R.layout.dong_menu,menuArrayList);
        lvMenu.setAdapter(menuAdapter);
    }

    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_menu_gallery);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void AnhXa() {
        toolbar=findViewById(R.id.toolbarMain);
        navigationView=findViewById(R.id.navigationView);
        lvMenu=findViewById(R.id.lvMenu);
        drawerLayout=findViewById(R.id.drawer);
        recyclerView=(RecyclerView) findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        arrayLoaiSP=new ArrayList<>();
        adapter=new LoaiSPAdapter(arrayLoaiSP,getApplicationContext());
        recyclerView.setAdapter(adapter);

        if (mangGioHang!=null){

        }else {
            mangGioHang=new ArrayList<>();
        }
        menuArrayList=new ArrayList<>();
        menuArrayList.add(new Menu(0,"Trang chính",R.drawable.food));


    }

    private void FoodSlide() {
        // get The references of ViewFlipper
        simpleViewFlipper = (ViewFlipper) findViewById(R.id.simpleViewFlipper); // get the reference of ViewFlipper

        // loop for creating ImageView's
        for (int i = 0; i < images.length; i++) {
            // create the object of ImageView
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(images[i]); // set image in ImageView
            simpleViewFlipper.addView(imageView); // add the created ImageView in ViewFlipper
        }
        // Declare in and out animations and load them using AnimationUtils class
        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        // set the animation type's to ViewFlipper
        simpleViewFlipper.setInAnimation(in);
        simpleViewFlipper.setOutAnimation(out);
        // set interval time for flipping between views
        simpleViewFlipper.setFlipInterval(3000);
        // set auto start for flipping between views
        simpleViewFlipper.setAutoStart(true);
    }
    private void GetData(String url)
    {
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                arrayLoaiSP.clear();
                for (int i=0;i<response.length();i++)
                {
                    try {
                        JSONObject object=response.getJSONObject(i);
                        arrayLoaiSP.add(new LoaiSP(
                                object.getInt("ID"),
                                object.getString("TenLoai"),
                                object.getString("AnhLoai"),
                                object.getString("Url")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}
