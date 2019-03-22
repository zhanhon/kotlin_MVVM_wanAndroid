package com.example.zh.ui.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.zh.R
import com.example.zh.base.BaseFragment
import com.example.zh.bean.NavTab
import com.example.zh.ui.adapter.MyPagerAdapter
import com.example.zh.ui.adapter.NavAdapter
import com.example.zh.ui.adapter.NavArticleAdapter
import com.example.zh.ui.viewmodel.NavViewModel
import com.example.zh.utils.InjectorUtil
import kotlinx.android.synthetic.main.fragment_nav.*


/**
 *导航
 */
class NavFragment : BaseFragment() {
    lateinit var viewModel: NavViewModel
    var adapter: MyPagerAdapter? = null
    var navAdapter: NavAdapter? = null
    var articleAdapter: NavArticleAdapter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initVM()
        initView()
        initData()
    }

    override fun setContent(): Int {
        return R.layout.fragment_nav
    }

    override fun initVM() {
        viewModel = ViewModelProviders.of(this,InjectorUtil.getNavFactory()).get(NavViewModel::class.java)
    }

    override fun initView() {
        adapter = MyPagerAdapter(childFragmentManager,viewModel.navFragmentList)
        vp_book.adapter = adapter
        tab_book.setViewPager(vp_book)

    }

    override fun initData() {
        viewModel.getNavList().observe(this, Observer {
            viewModel.navFragmentList.clear()
            viewModel.articleList.clear()

            it.forEach {
                viewModel.articleList.addAll(it.articles)
                val bundle = Bundle()
                bundle.putParcelableArrayList("articleList",viewModel.articleList)
                bundle.putInt("cid",it.cid)
                val fragment = BavTabFragment()
                fragment.arguments = bundle
                viewModel.navFragmentList.add(NavTab(it.name,fragment))
            }
            adapter?.notifyDataSetChanged()
            tab_book.notifyDataSetChanged()
        })
    }

}
