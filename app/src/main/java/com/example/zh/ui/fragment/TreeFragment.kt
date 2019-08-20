package com.example.zh.ui.fragment


import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.zh.R
import com.example.zh.base.BaseFragment
import com.example.zh.ui.adapter.TreeAdapter
import com.example.zh.ui.viewmodel.TreeViewModel
import kotlinx.android.synthetic.main.fragment_tree.*


/**
 *体系
 */
class TreeFragment : BaseFragment() {
    lateinit var viewModle: TreeViewModel
    var treeAdapter: TreeAdapter? = null

    override fun setContent(): Int {
        return R.layout.fragment_tree
    }

    override fun initVM() {
        viewModle = ViewModelProviders.of(this).get(TreeViewModel::class.java)
    }

    override fun initView() {
        rv_tree.layoutManager = LinearLayoutManager(context)
        treeAdapter = TreeAdapter(viewModle.treeList)
        rv_tree.adapter = treeAdapter


    }

    override fun initData() {
        showLoadingDialog()
        treeSystem()

    }

    fun treeSystem(){

        viewModle.treeSystem().observe(this, Observer {
            dismissLoadingDialog()
            viewModle.treeList.clear()
            if (it != null){
                viewModle.treeList.addAll(it)
                treeAdapter?.notifyDataSetChanged()

            }
        })
    }



}
