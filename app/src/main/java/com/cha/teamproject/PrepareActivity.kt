package com.cha.teamproject

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import com.cha.teamproject.databinding.ActivityPrepareBinding
import com.google.android.material.navigation.NavigationBarView

class PrepareActivity : AppCompatActivity() {

    lateinit var binding: ActivityPrepareBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrepareBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }
    private fun init(){
        webViewConfig()
    }

    private fun webViewConfig(){

        binding.prepareWv.webViewClient = WebViewClient()
        binding.prepareWv.webChromeClient = WebChromeClient()
        binding.prepareWv.settings.javaScriptEnabled = true
        var placeUrl = "http://tjdrjs0803.dothome.co.kr/TeamProject/prepare_page.html"
        binding.prepareWv.loadUrl(placeUrl)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBackPressed() {
        G.HomeWebView.goBack()
        G.HomeWebView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                var placeUrl2 = "javascript:openShop()"
                G.HomeWebView.loadUrl(placeUrl2)
                G.HomeWebView.postDelayed({
                    finish()
                },100)
            }
        }
    }

}