package com.iantje.studentportal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class ViewPortalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_portal);

        ((WebView)findViewById(R.id.webview)).loadUrl(getIntent().getStringExtra(MainActivity.EXTRA_URL_INTENT));
    }
}
