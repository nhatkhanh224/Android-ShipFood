package com.example.orderfood.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DangKyActivity extends AppCompatActivity {
    private TextView txtName,txtPass,txtEmail,txtCoPass,txtLogin;
    private Button btnRegis;
    private static String urlRegis="http://192.168.78.2/androidwebservice/orderfood/regis.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        AnhXa();
    }

    private void AnhXa() {
        txtName=findViewById(R.id.txtName);
        txtPass=findViewById(R.id.txtPass);
        txtEmail=findViewById(R.id.txtEmail);
        txtCoPass=findViewById(R.id.txtCoPass);
        txtLogin=findViewById(R.id.txtLogin);
        btnRegis=findViewById(R.id.btnRegis);
        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Regist();
            }
        });
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DangKyActivity.this,DangNhapActivity.class);
                startActivity(intent);
            }
        });

    }
    public void Regist(){
        final String name=this.txtName.getText().toString().trim();
        final String email=this.txtEmail.getText().toString().trim();
        final String password=this.txtPass.getText().toString().trim();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlRegis, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String success=jsonObject.getString("success");
                    if (success.equals("1")){
                        Toast.makeText(DangKyActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),DangNhapActivity.class);
                        startActivity(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(DangKyActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String,String>();
                params.put("name",name);
                params.put("email",email);
                params.put("password",password);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
