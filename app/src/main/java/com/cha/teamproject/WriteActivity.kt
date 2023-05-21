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

        binding.writeWv.webViewClient = WebViewClient()
        binding.writeWv.webChromeClient = WebChromeClient()
        binding.writeWv.settings.javaScriptEnabled = true
        var placeUrl = "http://tjdrjs0803.dothome.co.kr/TeamProject/share_write.html"
        //var placeUrl = "http://tjdrjs0803.dothome.co.kr/WebInterfaceTest/index.html"
        binding.writeWv.loadUrl(placeUrl)
        var mainActivity = MainActivity()
        binding.writeWv.addJavascriptInterface(mainActivity.WebAppInterface(this),"share")
    }
}