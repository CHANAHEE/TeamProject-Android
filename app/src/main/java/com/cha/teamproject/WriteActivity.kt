package com.cha.teamproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import com.cha.teamproject.databinding.ActivityMainBinding
import com.cha.teamproject.databinding.ActivityWriteBinding
import com.google.android.material.navigation.NavigationBarView

class WriteActivity : AppCompatActivity() {

    val binding: ActivityWriteBinding by lazy { ActivityWriteBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
    }

    private fun init(){
        webViewConfig()

    }

    private fun webViewConfig(){

        binding.wv.webViewClient = WebViewClient()
        binding.wv.webChromeClient = WebChromeClient()
        binding.wv.settings.javaScriptEnabled = true
        var placeUrl = "http://tjdrjs0803.dothome.co.kr/TeamProject/share_write.html"
        //var placeUrl = "http://tjdrjs0803.dothome.co.kr/WebInterfaceTest/index.html"
        binding.wv.loadUrl(placeUrl)

        binding.wv.addJavascriptInterface(MainActivity.WebAppInterface(this),"share")
    }
}