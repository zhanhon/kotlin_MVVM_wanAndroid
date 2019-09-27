package com.example.zh.ui.fragment


import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter

import com.example.zh.R
import com.example.zh.base.BaseFragment
import com.example.zh.bean.ArticleBean
import com.example.zh.ui.adapter.BannerImageLoader
import com.example.zh.ui.adapter.HomeAdapter
import com.example.zh.ui.home.WebActivity
import com.example.zh.ui.viewmodel.HomeViewModel
import com.example.zh.utils.MyLog
import com.example.zh.utils.ToastUtil
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener
import kotlinx.android.synthetic.main.fragment_home.*
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer


/**
 *首页
 */
class HomeFragment : BaseFragment(){
    private lateinit var viewModel: HomeViewModel
    private lateinit var homeAdapter: HomeAdapter

    override fun setContent(): Int {
        return R.layout.fragment_home
    }

    override fun initVM() {
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

    }


    override fun initView() {
        home_recyclerview.layoutManager = LinearLayoutManager(context)
        homeAdapter = HomeAdapter(viewModel.mList)
        home_recyclerview.adapter = homeAdapter
        homeAdapter.onItemClickListener = object : BaseQuickAdapter.OnItemClickListener{
            override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
                val intent = Intent()
                intent.setClass(context,WebActivity::class.java)
                val link = viewModel.mList.get(position).link
                intent.putExtra("url",link)
                startActivity(intent)
            }
        }

        homeAdapter.onItemChildClickListener = object : BaseQuickAdapter.OnItemChildClickListener{
            override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
                val item = viewModel.mList.get(position)
                if (!item.isCollect){
                    likeArticle(item.id,item,position)
                }else{
                    cancelArticle(item.id,item,position)
                }

            }
        }

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
                getBanner()

            }
        })

        banner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
        banner.setImageLoader(BannerImageLoader())
        banner.setBannerAnimation(Transformer.DepthPage)
        banner.setDelayTime(2000)
        banner.setIndicatorGravity(BannerConfig.RIGHT)
        banner.setOnBannerListener {

        }
        banner.start()

    }

    override fun initData() {
        getArticle(viewModel.pageNum)
        getBanner()
    }

    fun getBanner(){
        viewModel.getBanner().observe(this, Observer {
            if (it == null){
                return@Observer
            }
            viewModel.mBannerList.clear()
            viewModel.mTitleList.clear()
            viewModel.mBannerList.addAll(it)
            for (bannerBean in it) {
                viewModel.mTitleList.add(bannerBean.title)
            }
            banner.update(viewModel.mBannerList,viewModel.mTitleList)
        })
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

    fun likeArticle(id: Int,item: ArticleBean.DatasBean,position: Int){
        viewModel.likeArticle(id).observe(this, Observer {
            if (it != null){
                ToastUtil.showToast(it)
                item.isCollect = true
                homeAdapter.notifyItemChanged(position)
            }
        })
    }

    fun cancelArticle(id: Int,item: ArticleBean.DatasBean,position: Int){
        viewModel.cancelArticle(id).observe(this, Observer {
            if (it != null){
                ToastUtil.showToast(it)
                item.isCollect = false
                homeAdapter.notifyItemChanged(position)
            }
        })
    }



}
