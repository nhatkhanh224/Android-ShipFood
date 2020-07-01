package com.example.orderfood.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.orderfood.R;
import com.example.orderfood.model.KhachHang;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DangNhapActivity extends AppCompatActivity {
    private EditText txtUsername,txtPassword;
    private Button btnLogin,btnRegis;
    private static String urlLogin="http://192.168.78.2/androidwebservice/orderfood/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        AnhXa();
    }
    private void AnhXa() {
        txtUsername=  findViewById(R.id.txtUsername);
        txtPassword=  findViewById(R.id.txtPassword);
        btnLogin= findViewById(R.id.btnLogin);
        btnRegis=findViewById(R.id.btnRegis);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=txtUsername.getText().toString().trim();
                String password=txtPassword.getText().toString().trim();
                if (!email.isEmpty() || !password.isEmpty())
                {
                    onLogin(email,password);
                    Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(DangNhapActivity.this, "Vui long dien day du thong tin", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), DangKyActivity.class);
                startActivity(intent);
            }
        });
    }

    private void onLogin(final String email, final String password) {

        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlLogin, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String success=jsonObject.getString("success");
                    JSONArray jsonArray=jsonObject.getJSONArray("login");
                    if (success.equals("1")){
                        for (int i=0;i<jsonArray.length();i++)
                        {
                            JSONObject object=jsonArray.getJSONObject(i);
                            String name=object.getString("name").trim();
                            String email=object.getString("email").trim();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DangNhapActivity.this, "Error"+error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String,String>();
                params.put("email",email);
                params.put("password",password);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
