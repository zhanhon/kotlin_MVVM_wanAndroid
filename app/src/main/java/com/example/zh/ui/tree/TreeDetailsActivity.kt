package com.example.zh.ui.tree

import android.os.Bundle
import android.util.Log
import com.example.zh.R
import com.example.zh.base.BaseActivity

class TreeDetailsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tree_details)
        initVM()
        initView()
    }

    override fun initVM() {

    }

    override fun initView() {
        val children_id = intent.getIntExtra("children_id",-1)
        Log.e("log","children_id="+children_id)

    }

}
