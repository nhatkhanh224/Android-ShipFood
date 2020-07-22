package com.example.orderfood.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import com.example.orderfood.R;

public class LienHe extends AppCompatActivity {
    Button btnFacebook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lien_he);

    }

    public void facebook(View view)
    {
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/nhatkhanh224"));
        startActivity(intent);
    }
    public void github(View view)
    {
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/nhatkhanh224"));
        startActivity(intent);
    }
}
