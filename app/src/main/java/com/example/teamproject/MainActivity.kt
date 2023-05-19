package com.example.teamproject

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.example.teamproject.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {

    val binding:ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
    }

    private fun init(){
        webViewConfig()
        binding.bnv.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener {
            changeFragment(it)
            return@OnItemSelectedListener true
        })

    }

    private fun webViewConfig(){

        binding.wv.webViewClient = WebViewClient()
        binding.wv.webChromeClient = WebChromeClient()
        binding.wv.settings.javaScriptEnabled = true
        var placeUrl = "http://tjdrjs0803.dothome.co.kr/TeamProject/index.html"
        //var placeUrl = "http://tjdrjs0803.dothome.co.kr/WebInterfaceTest/index.html"
        binding.wv.loadUrl(placeUrl)

        binding.wv.addJavascriptInterface(WebAppInterface(this),"test")

    }

    /** Instantiate the interface and set the context  */
    class WebAppInterface(private val mContext: Context) {

        /** Show a toast from the web page  */
        @JavascriptInterface
        fun showToast(toast: String) {
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show()
        }
    }

    /*
    *       프래그먼트 전환 함수
    * */
    private fun changeFragment(item: MenuItem){

        when(item.itemId){
            R.id.home_tab -> {
                var placeUrl = "http://tjdrjs0803.dothome.co.kr/TeamProject/index.html"
                binding.wv.loadUrl(placeUrl)
            }
            R.id.shop_tab -> {
                var placeUrl = "javaScript:openShop()"
                binding.wv.loadUrl(placeUrl)
            }
            R.id.share_tab -> {
                var placeUrl = "javaScript:openShare()"
                binding.wv.loadUrl(placeUrl)
            }
            R.id.myinfo_tab -> {
                var placeUrl = "javaScript:openOrder()"
                binding.wv.loadUrl(placeUrl)
            }
        }
    }
}