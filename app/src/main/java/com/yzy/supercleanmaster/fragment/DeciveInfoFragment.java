package com.yzy.supercleanmaster.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.yzy.supercleanmaster.R;
import com.yzy.supercleanmaster.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class DeciveInfoFragment extends BaseFragment {

    @InjectView(R.id.webView)
    WebView mWebView;
    @InjectView(R.id.progress_bar)
    ProgressBar mProgressBar;
    Context mContext;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        View view = inflater.inflate(R.layout.fragment_device_info, container, false);
        ButterKnife.inject(this, view);
        mContext = getActivity();

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initWebview();
    }

    private void fillData() {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
    private void initWebview() {
        // TODO Auto-generated method stub
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webSettings.setAllowFileAccess(true);
        webSettings.setSupportZoom(true);
        webSettings.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setDefaultFontSize(20);


        mWebView.loadUrl("http://girl-atlas.com");

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                mProgressBar.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }

            // 页面加载完成
            @Override
            public void onPageFinished(WebView view, String url) {
                mProgressBar.setVisibility(View.GONE);
                super.onPageFinished(view, url);
            }

            // WebView加载的所有资源url
            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
            }

        });

        // 设置WebChromeClient
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            // 处理javascript中的alert
            public boolean onJsAlert(WebView view, String url, String message,
                                     final JsResult result) {
                return super.onJsAlert(view, url, message, result);
            };

            @Override
            // 处理javascript中的confirm
            public boolean onJsConfirm(WebView view, String url,
                                       String message, final JsResult result) {
                return super.onJsConfirm(view, url, message, result);
            };

            @Override
            // 处理javascript中的prompt
            public boolean onJsPrompt(WebView view, String url, String message,
                                      String defaultValue, final JsPromptResult result) {
                return super.onJsPrompt(view, url, message, defaultValue,
                        result);
            };

            // 设置网页加载的进度条
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                mProgressBar.setProgress(newProgress);
                super.onProgressChanged(view, newProgress);
            }

            // 设置程序的Title
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }
        });
        mWebView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK
                            && mWebView.canGoBack()) { // 表示按返回键

                        mWebView.goBack(); // 后退

                        // webview.goForward();//前进
                        return true; // 已处理
                    }
                }
                return false;
            }
        });
    }

    public boolean canBack() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
            return false;
        }
        return true;
    }

}
