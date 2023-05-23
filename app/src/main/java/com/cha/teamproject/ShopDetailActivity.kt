package com.cha.teamproject

import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.cha.teamproject.databinding.ActivityShopDetailBinding
import org.json.JSONObject


class ShopDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityShopDetailBinding
    var dataObj = JSONObject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init(){
        webViewConfig()
        dataObj.put("brand",intent.getStringExtra("brand"))
        dataObj.put("image",intent.getStringExtra("image"))
        dataObj.put("description",intent.getStringExtra("description"))
        dataObj.put("price",intent.getStringExtra("price"))
    }

    private fun webViewConfig(){
        binding.shopDetailWv.webViewClient = WebViewClient()
        binding.shopDetailWv.webChromeClient = WebChromeClient()
        binding.shopDetailWv.settings.javaScriptEnabled = true
        var placeUrl = "http://tjdrjs0803.dothome.co.kr/TeamProject/shop_detail.html"
        binding.shopDetailWv.loadUrl(placeUrl)


    }

    override fun onResume() {
        super.onResume()
        val jsonData = dataObj.toString()

        G.HomeWebView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                binding.shopDetailWv.loadUrl("javaScript:setInfoInMobile('$jsonData')")
            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBackPressed() {
        G.HomeWebView.goBack()
        startActivity(Intent(this,MainActivity::class.java).putExtra("shopDetail","shopDetail"))
        finish()

//        G.HomeWebView.webViewClient = object : WebViewClient() {
//            override fun onPageFinished(view: WebView?, url: String?) {
//                super.onPageFinished(view, url)
//                var placeUrl2 = "javascript:openShop()"
//                G.HomeWebView.loadUrl(placeUrl2)
//                G.HomeWebView.postDelayed({
//                    finish()
//                },100)
//            }
//        }
    }
}

