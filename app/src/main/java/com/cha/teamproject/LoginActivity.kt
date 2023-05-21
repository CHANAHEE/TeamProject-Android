package com.cha.teamproject

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import com.cha.teamproject.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init(){
        webViewConfig()
    }

    private fun webViewConfig(){

        binding.loginWv.webViewClient = WebViewClient()
        binding.loginWv.webChromeClient = WebChromeClient()
        binding.loginWv.settings.javaScriptEnabled = true
        var placeUrl = "http://tjdrjs0803.dothome.co.kr/TeamProject/login.html"
        binding.loginWv.loadUrl(placeUrl)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBackPressed() {
        G.HomeWebView.goBack()
        finish()
    }
}