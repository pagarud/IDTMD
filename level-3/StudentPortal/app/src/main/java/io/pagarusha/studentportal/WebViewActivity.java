package io.pagarusha.studentportal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

public class WebViewActivity extends AppCompatActivity {

    private final String TAG = "WebViewActivity";
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        webView = findViewById(R.id.portalWebView);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        Log.d(TAG, "onCreate: " + url);
        webView.loadUrl(url);


    }

}
