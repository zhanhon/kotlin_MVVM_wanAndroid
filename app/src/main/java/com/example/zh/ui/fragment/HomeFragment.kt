package com.example.zh.ui.fragment


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.zh.R
import com.example.zh.base.BaseFragment
import com.example.zh.ui.adapter.HomeAdapter
import com.example.zh.ui.viewmodel.HomeViewModel
import com.example.zh.utils.InjectorUtil
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter
import kotlinx.android.synthetic.main.fragment_home.*

/**
 *首页
 */
class HomeFragment : BaseFragment() , View.OnClickListener{
    private lateinit var viewModel: HomeViewModel
    private lateinit var homeAdapter: HomeAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initVM()
        initView()

    }

    override fun setContent(): Int {
        return R.layout.fragment_home
    }

    override fun initVM() {
        viewModel = ViewModelProviders.of(this, InjectorUtil.getHomeViewModelFactory()).get(HomeViewModel::class.java)

    }


    override fun initView() {
        home_recyclerview.layoutManager = LinearLayoutManager(context)
        homeAdapter = HomeAdapter(context,viewModel.mList)
        home_recyclerview.adapter = homeAdapter
        homeAdapter.setOnItemClickListener(object : MultiItemTypeAdapter.OnItemClickListener{
            override fun onItemLongClick(view: View?, holder: RecyclerView.ViewHolder?, position: Int): Boolean {
                return true //true 不会再执行onItemClick
            }
            override fun onItemClick(view: View?, holder: RecyclerView.ViewHolder?, position: Int) {
                Toast.makeText(context,"点击position="+position,Toast.LENGTH_SHORT).show()
            }

        })

        sr_smart.setRefreshFooter(ClassicsFooter(context))
        sr_smart.setRefreshHeader(ClassicsHeader(context))
        sr_smart.setOnRefreshLoadmoreListener(object: OnRefreshLoadmoreListener{
            override fun onLoadmore(refreshlayout: RefreshLayout?) {
                viewModel.pageNum ++
                getArticle(viewModel.pageNum)
            }

            override fun onRefresh(refreshlayout: RefreshLayout?) {
                viewModel.pageNum = 0
                getArticle(viewModel.pageNum)

            }
        })
        sr_smart.autoRefresh()


    }

    override fun initData() {
        getArticle(viewModel.pageNum)

    }

    fun getArticle(pageNum: Int){
        viewModel.getArticleList(pageNum).observe(this, Observer{
            sr_smart.finishRefresh()
            sr_smart.finishLoadmore()
            if (it != null){
                if (pageNum == 0){
                    viewModel.mList.clear()
                }
                viewModel.mList.addAll(it)
                homeAdapter.notifyDataSetChanged()
            }else{
                viewModel.pageNum --
            }
        })


    }


    override fun onClick(v: View?) {
        when(v?.id){

        }
    }

}
