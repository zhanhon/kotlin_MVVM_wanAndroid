package com.example.zh.ui.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.zh.R
import com.example.zh.base.BaseActivity
import com.example.zh.ui.adapter.HomeAdapter
import com.example.zh.ui.viewmodel.HomeViewModel
import com.example.zh.utils.InjectorUtil
import kotlinx.android.synthetic.main.activity_like.*

class LikeActivity : BaseActivity() {
    private lateinit var viewModel: HomeViewModel
    lateinit var homeAdapter: HomeAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_like)
        initVM()
        initView()
    }

    override fun initVM() {
        viewModel = ViewModelProviders.of(this, InjectorUtil.getHomeViewModelFactory()).get(HomeViewModel::class.java)
    }

    override fun initView() {
        homeAdapter = HomeAdapter(viewModel.mList)
        rv_like.layoutManager = LinearLayoutManager(this)
        rv_like.adapter = homeAdapter
        homeAdapter.onItemClickListener = object : BaseQuickAdapter.OnItemClickListener{
            override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {


            }
        }

    }
}
