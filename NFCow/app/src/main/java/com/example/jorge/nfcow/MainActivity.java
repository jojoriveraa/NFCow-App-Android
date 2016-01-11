package com.example.jorge.nfcow;

import static android.widget.Toast.LENGTH_SHORT;
import static java.lang.System.currentTimeMillis;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.myWebView = (WebView) this.findViewById(R.id.webView);

        // Enable JavaScript
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Provide a WebViewClient for your WebView
        myWebView.setWebViewClient(new WebViewClient());

        myWebView.loadUrl("http://52.89.24.244:8000/login/");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        // Check if there's history
        if (this.myWebView.canGoBack())
            this.myWebView.goBack();
        else
            super.onBackPressed();

    }

    private class MyWebViewClient extends WebViewClient {

        private long loadTime; // Web page loading time

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);

            // Save start time
            this.loadTime = currentTimeMillis();

            // Show a toast
            Toast.makeText(getApplicationContext(),
                    "A page has started loading...", LENGTH_SHORT).show();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

            // Calculate load time
            this.loadTime = currentTimeMillis() - this.loadTime;

            // Convert milliseconds to date format
            String time = new SimpleDateFormat("mm:ss:SSS", Locale.getDefault())
                    .format(new Date(this.loadTime));

            // Show a toast
            Toast.makeText(getApplicationContext(),
                    "Page has finished loading in " + time, LENGTH_SHORT)
                    .show();

        }
    }

}
