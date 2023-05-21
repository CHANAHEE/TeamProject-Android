package com.cha.teamproject

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.cha.teamproject.R
import com.cha.teamproject.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {

    val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

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

        binding.homeWv.webViewClient = WebViewClient()
        binding.homeWv.webChromeClient = WebChromeClient()
        binding.homeWv.settings.javaScriptEnabled = true
        G.HomeWebView = binding.homeWv
        var placeUrl = "http://tjdrjs0803.dothome.co.kr/TeamProject/index.html"
        //var placeUrl = "http://tjdrjs0803.dothome.co.kr/WebInterfaceTest/index.html"
        binding.homeWv.loadUrl(placeUrl)


        binding.homeWv.addJavascriptInterface(WebAppInterface(this),"main")

    }

    /** Instantiate the interface and set the context  */
    inner class WebAppInterface(private val mContext: Context) {

        @JavascriptInterface
        fun openWrite_m() {
            mContext.startActivity(Intent(mContext,WriteActivity::class.java))
        }

        @JavascriptInterface
        fun openShopDetail_m(brand:String, image:String, description:String, price: String) {
            mContext.startActivity(Intent(mContext,ShopDetailActivity::class.java)
                .putExtra("brand",brand)
                .putExtra("image",image)
                .putExtra("description",description)
                .putExtra("price",price))
        }

        @JavascriptInterface
        fun openPrepare_m() {
            mContext.startActivity(Intent(mContext,PrepareActivity::class.java))
        }

        @JavascriptInterface
        fun showToast_m() {
            Toast.makeText(this@MainActivity, "준비중인 페이지 입니다.", Toast.LENGTH_SHORT).show()
        }
        @JavascriptInterface
        fun openSignup_m(){
            mContext.startActivity(Intent(mContext,SignupActivity::class.java))
        }
        @JavascriptInterface
        fun openSignin_m(){
            mContext.startActivity(Intent(mContext,LoginActivity::class.java))
        }
    }

    /*
    *       프래그먼트 전환 함수
    * */
    private fun changeFragment(item: MenuItem){

        when(item.itemId){
            R.id.home_tab -> {
                var placeUrl = "http://tjdrjs0803.dothome.co.kr/TeamProject/index.html"
                binding.homeWv.loadUrl(placeUrl)
            }
            R.id.shop_tab -> {
                var placeUrl = "javaScript:openShop()"
                binding.homeWv.loadUrl(placeUrl)
            }
            R.id.share_tab -> {
                var placeUrl = "javaScript:openShare()"
                binding.homeWv.loadUrl(placeUrl)
            }
            R.id.myinfo_tab -> {
                var placeUrl = "javaScript:openOrder()"
                binding.homeWv.loadUrl(placeUrl)
            }
        }
    }
}