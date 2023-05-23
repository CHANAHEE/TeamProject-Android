package com.cha.teamproject

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.cha.teamproject.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init(){
        webViewConfig()
    }

    private fun webViewConfig(){

        binding.signupWv.webViewClient = WebViewClient()
        binding.signupWv.webChromeClient = WebChromeClient()
        binding.signupWv.settings.javaScriptEnabled = true
        var placeUrl = "http://tjdrjs0803.dothome.co.kr/TeamProject/join.html"
        binding.signupWv.loadUrl(placeUrl)

        binding.signupWv.addJavascriptInterface(WebAppInterface(this),"login")

    }
    inner class WebAppInterface(private val mContext: Context) {

        @JavascriptInterface
        fun openIndexPage_m(){
            runOnUiThread { Log.i("adsfzzd","헬로") }
            runOnUiThread { this@SignupActivity.finish() }
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBackPressed() {
        G.HomeWebView.goBack()
        finish()
    }
}