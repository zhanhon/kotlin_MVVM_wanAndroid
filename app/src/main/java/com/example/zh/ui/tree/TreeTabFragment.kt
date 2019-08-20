package com.example.zh.ui.tree


import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.example.zh.R
import com.example.zh.base.BaseFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.zh.ui.adapter.TreeTabAdapter
import com.example.zh.ui.home.WebActivity
import com.example.zh.ui.viewmodel.TreeViewModel
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener
import kotlinx.android.synthetic.main.fragment_tree_tab.*

class TreeTabFragment : BaseFragment() {
    var children_id : Int? = null
    lateinit var viewModle: TreeViewModel
    var treeTabAdapter : TreeTabAdapter? = null
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initVM()
        initView()
        initData()

    }

    override fun setContent(): Int {
        return R.layout.fragment_tree_tab
    }

    override fun initVM() {
        viewModle = ViewModelProviders.of(this).get(TreeViewModel::class.java)

    }

    override fun initView() {
        treeTabAdapter = TreeTabAdapter(viewModle.treeArticleList)
        rv_tree_tab.layoutManager = LinearLayoutManager(context)
        rv_tree_tab.adapter = treeTabAdapter
        treeTabAdapter?.onItemClickListener = BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
            val intent = Intent()
            intent.setClass(context, WebActivity::class.java)
            val link = viewModle.treeArticleList.get(position).link
            intent.putExtra("url",link)
            context?.startActivity(intent)
        }

        sr_smart_tree_tab.setRefreshFooter(ClassicsFooter(context))
        sr_smart_tree_tab.setRefreshHeader(ClassicsHeader(context))
        sr_smart_tree_tab.setOnRefreshLoadmoreListener(object: OnRefreshLoadmoreListener{
            override fun onLoadmore(refreshlayout: RefreshLayout?) {
                viewModle.numPage ++
                treeArticleList(viewModle.numPage,children_id ?: -1)
            }

            override fun onRefresh(refreshlayout: RefreshLayout?) {
                viewModle.numPage = 0
                treeArticleList(viewModle.numPage,children_id ?: -1)
            }
        })
    }

    override fun initData() {
        val bundle = arguments
        children_id = bundle?.getInt("children_id")
        treeArticleList(viewModle.numPage,children_id ?: -1)
    }



    fun treeArticleList(numPage: Int,cId: Int){
        viewModle.treeArticleList(numPage,cId).observe(this, Observer {
            sr_smart_tree_tab.finishLoadmore()
            sr_smart_tree_tab.finishRefresh()
            if (it != null){
                if (numPage == 0){
                    viewModle.treeArticleList.clear()
                }
                viewModle.treeArticleList.addAll(it.datas)
                treeTabAdapter?.notifyDataSetChanged()
            }else{
                viewModle.numPage --
            }
        })
    }


}
