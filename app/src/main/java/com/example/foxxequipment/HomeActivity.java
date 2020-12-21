package com.example.foxxequipment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class HomeActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if (checkConnection()) {
            loadWebView();
        } else {
            dialog();
        }
    }


    void loadWebView() {
        WebView webView = (WebView) findViewById(R.id.myWebView);
        webView.loadUrl("https://foxxequipdevel.wpengine.com");
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    boolean checkConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            return true;
        }
        return false;
    }

    void dialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(HomeActivity.this).create();
        alertDialog.setTitle("No Internet");
        alertDialog.setMessage("Please Check your Internent Connection!");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    mWebView = (WebView) findViewById(R.id.myWebView);
                    if (mWebView.canGoBack()) {
                        mWebView.goBack();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }
}