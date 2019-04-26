package com.example.zh.ui.home

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import com.example.zh.R
import com.example.zh.base.BaseActivity
import android.widget.LinearLayout
import com.just.agentweb.AgentWeb
import kotlinx.android.synthetic.main.activity_web.*
import kotlinx.android.synthetic.main.layout_all_title.*


class WebActivity : BaseActivity() {
    lateinit var mAgentWeb: AgentWeb
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        initVM()
        initView()
    }

    override fun initVM() {

    }

    override fun initView() {
        tv_title.text = ""
        val url: String = intent.getStringExtra("url")
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(ll_web as LinearLayout, LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .setWebChromeClient(webChromeClient)
                .createAgentWeb()
                .ready()
                .go(url)

        image_back.setOnClickListener {
            if (!mAgentWeb.back()){
                finish()
            }

        }


    }



    override fun onBackPressed() {
        if (!mAgentWeb.back()){
            finish();
        }
    }

    val webChromeClient = object : WebChromeClient(){
        override fun onReceivedTitle(view: WebView?, title: String?) {
            super.onReceivedTitle(view, title)
            tv_title.text = title ?: ""
        }
    }
}
