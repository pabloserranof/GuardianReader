package com.pabloserrano.guardianreader.newsdetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.pabloserrano.guardianreader.R;
import com.pabloserrano.guardianreader.baseclasses.BaseActivity;
import com.pabloserrano.guardianreader.baseclasses.Presenter;

import butterknife.BindView;

public class NewsDetailsActivity extends BaseActivity implements Presenter.View {

    public static final String NEWS_URL = "NEWS_URL";

    @BindView(R.id.webView)
    WebView webView;

    private String newsUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeActivity();
        initializeWebView();
    }

    private void initializeActivity(){
        showLoading();
        newsUrl = getIntent().getExtras().getString(NEWS_URL, "");
    }
    private void initializeWebView() {
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageCommitVisible(WebView view, String url) {
                super.onPageCommitVisible(view, url);
                hideLoading();
            }
        });
        webView.loadUrl(newsUrl);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_news_details;
    }

    public static void open(Context context, String webUrl) {
        Intent intent = new Intent(context, NewsDetailsActivity.class);
        intent.putExtra(NEWS_URL, webUrl);
        context.startActivity(intent);
    }
}
