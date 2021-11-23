package com.yash.techrockdesi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity {


    ProgressBar progressBar;

    WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide(); // hide the title bar in Main Activity
        setContentView(R.layout.activity_detail);

        progressBar = findViewById(R.id.progressBar);

        webView = findViewById(R.id.detailView);

        //Toast.makeText(this,getIntent().getStringExtra("url"),Toast.LENGTH_SHORT).show();

        //set the toolbar
        //setSupportActionBar(toolbar);

        // when webView generate Progressbar is hide
        webView.setVisibility(View.INVISIBLE);

        // for execution of webView javaScript
        webView.getSettings().setJavaScriptEnabled(true);

        //chromium permission
        webView.setWebChromeClient(new WebChromeClient());


        webView.setWebViewClient(new WebViewClient(){

            //When Page is Loading
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Toast.makeText(DetailActivity.this,"Page Started Loading", Toast.LENGTH_SHORT).show();
            }

            // When Page is loaded
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                // when page load progressbar is invisible
                progressBar.setVisibility(View.GONE);

                //when page load webView visible
                webView.setVisibility(View.VISIBLE);

                Toast.makeText(DetailActivity.this,"Page Loaded", Toast.LENGTH_SHORT).show();


            }
        });
        webView.loadUrl(getIntent().getStringExtra("url"));



    }
}