package com.example.zh.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.zh.R
import com.example.zh.base.BaseActivity
import com.example.zh.bean.ArticleBean
import com.example.zh.ui.adapter.HomeAdapter
import com.example.zh.ui.viewmodel.HomeViewModel
import com.example.zh.utils.ToastUtil
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener
import kotlinx.android.synthetic.main.activity_like.*
import kotlinx.android.synthetic.main.layout_all_title.*

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
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
    }

    override fun initView() {
        image_back.setOnClickListener {
            finish()
        }
        tv_title.text = "收藏"
        homeAdapter = HomeAdapter(viewModel.mList)
        rv_like.layoutManager = LinearLayoutManager(this)
        rv_like.adapter = homeAdapter
        homeAdapter.onItemClickListener = object : BaseQuickAdapter.OnItemClickListener{
            override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
                val intent = Intent()
                intent.setClass(this@LikeActivity,WebActivity::class.java)
                val link = viewModel.mList.get(position).link
                intent.putExtra("url",link)
                startActivity(intent)
            }
        }

        homeAdapter.onItemChildClickListener = object : BaseQuickAdapter.OnItemChildClickListener{
            override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
                val item = viewModel.mList.get(position)
                cancelArticle(item.id,position)
            }
        }

        sm_like.setRefreshFooter(ClassicsFooter(this))
        sm_like.setRefreshHeader(ClassicsHeader(this))
        sm_like.setOnRefreshLoadmoreListener(object: OnRefreshLoadmoreListener {
            override fun onLoadmore(refreshlayout: RefreshLayout?) {
                viewModel.pageNum ++
                likeArticleList(viewModel.pageNum)
            }

            override fun onRefresh(refreshlayout: RefreshLayout?) {
                viewModel.pageNum = 0
                likeArticleList(viewModel.pageNum)

            }
        })

        likeArticleList(viewModel.pageNum)
    }

    fun likeArticleList(pageNum: Int){
        viewModel.likeArticleList(pageNum).observe(this, Observer {
            sm_like.finishRefresh()
            sm_like.finishLoadmore()
            if (it != null){
                if (pageNum == 0){
                    viewModel.mList.clear()
                }
                for (bean in it) {
                    bean.isCollect = true
                    viewModel.mList.add(bean)
                }
                homeAdapter.notifyDataSetChanged()
            }else{
                viewModel.pageNum --
            }
        })
    }

    fun cancelArticle(id: Int,position: Int){
        viewModel.cancelArticle(id).observe(this, Observer {
            if (it != null){
                ToastUtil.showToast(it)
                viewModel.mList.removeAt(position)
                homeAdapter.notifyDataSetChanged()
            }
        })
    }
}
