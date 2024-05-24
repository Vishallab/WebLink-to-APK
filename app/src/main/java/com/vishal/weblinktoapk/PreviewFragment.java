package com.vishal.weblinktoapk;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;




public class PreviewFragment extends Fragment {

    private WebView webView;
    private TextView textViewPlaceholder;
    private Button buttonReset;
    private Button buttonDownload;
    private String apkUrl;

    public PreviewFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_preview, container, false);
        webView = view.findViewById(R.id.webView);
        textViewPlaceholder = view.findViewById(R.id.textViewPlaceholder);
        buttonReset = view.findViewById(R.id.buttonReset);

        // Initially show the placeholder message
        textViewPlaceholder.setVisibility(View.VISIBLE);
        webView.setVisibility(View.GONE);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                // Once the page finishes loading, hide the placeholder text and show the WebView
                textViewPlaceholder.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);

        // Handle reset button click
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reload the placeholder message and hide the WebView
                textViewPlaceholder.setVisibility(View.VISIBLE);
                webView.setVisibility(View.GONE);
            }
        });

        return view;
    }

    public void loadUrl(String url) {
        if (webView != null) {
            // Show placeholder text and hide WebView until the page finishes loading
            textViewPlaceholder.setVisibility(View.VISIBLE);
            webView.setVisibility(View.GONE);
            webView.loadUrl(url);
        }
    }
    public boolean canGoBack() {
        return webView != null && webView.canGoBack();
    }

    public void goBack() {
        if (webView != null) {
            webView.goBack();
        }
    }

}
