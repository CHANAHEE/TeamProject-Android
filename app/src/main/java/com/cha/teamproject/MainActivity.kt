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

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initial()
    }

    private fun initial(){
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
        binding.homeWv.webViewClient = object : WebViewClient(){
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                if(intent.getStringExtra("shopDetail") == "shopDetail"){

                    binding.bnv.selectedItemId = R.id.shop_tab
                }
            }
        }

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
            //runOnUiThread { Log.i("asdfz",G.HomeWebView.url.toString()) }
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

        @JavascriptInterface
        fun selectSideMenu(id: String){
            runOnUiThread {
                when(id){
                    "home"-> {
                        if(binding.bnv.selectedItemId != R.id.home_tab) {
                            binding.bnv.selectedItemId = R.id.home_tab
                        }
                    }
                    "shop"-> {
                        if(binding.bnv.selectedItemId != R.id.shop_tab){
                            binding.bnv.selectedItemId = R.id.shop_tab
                        }
                    }
                    "share"-> {
                        Log.i("testasdfasdf","사이드메뉴 선택리스너")
                        if(binding.bnv.selectedItemId != R.id.share_tab){
                            Log.i("testasdfasdf","사이드메뉴 선택리스너 if 문 내부")
                            sideFlag = true
                            binding.bnv.selectedItemId = R.id.share_tab
                        }
                    }
                    "order"-> {
                        if(binding.bnv.selectedItemId != R.id.myinfo_tab){
                            binding.bnv.selectedItemId = R.id.myinfo_tab
                        }
                    }
                }
            }

        }
    }

    /*
    *       프래그먼트 전환 함수
    * */
    var sideFlag = false
    private fun changeFragment(item: MenuItem){

        when(item.itemId){
            R.id.home_tab -> {
                var placeUrl = "http://tjdrjs0803.dothome.co.kr/TeamProject/index.html"
                binding.homeWv.loadUrl(placeUrl)
            }
            R.id.shop_tab -> {
                Toast.makeText(this, "헬롱2222", Toast.LENGTH_SHORT).show()
                var placeUrl = "javaScript:openShop()"
                binding.homeWv.loadUrl(placeUrl)
            }
            R.id.share_tab -> {
                Log.i("testasdfasdf","바탐내비 선택리스너")
                if(sideFlag) return
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