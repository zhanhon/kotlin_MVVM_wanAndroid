package com.example.zh.ui.tree

import android.os.Bundle
import android.os.Parcelable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.zh.R
import com.example.zh.base.BaseActivity
import com.example.zh.bean.Children
import com.example.zh.bean.NavTab
import com.example.zh.ui.adapter.MyPagerAdapter
import com.example.zh.ui.fragment.BavTabFragment
import com.example.zh.ui.viewmodel.TreeViewModel
import com.example.zh.utils.InjectorUtil
import kotlinx.android.synthetic.main.activity_tree_details.*
import kotlinx.android.synthetic.main.fragment_nav.*

class TreeDetailsActivity : BaseActivity() {
    var adapter: MyPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tree_details)
        initVM()
        initView()
    }

    override fun initVM() {
    }

    override fun initView() {
        val navFragmentList: ArrayList<NavTab> = ArrayList()
        val title_name : String? = intent.getStringExtra("title_name")
        var children_list : ArrayList<Children>? = intent.getParcelableArrayListExtra("children_list")
        toolbar.title = title_name ?: "标题"
        adapter = MyPagerAdapter(supportFragmentManager,navFragmentList)
        viewpager.adapter = adapter
        tab_tree.setViewPager(viewpager)


        children_list?.forEach {
            val bundle = Bundle()
            bundle.putInt("children_id",it.id)
            val fragment = TreeTabFragment()
            fragment.arguments = bundle
            navFragmentList.add(NavTab(it.name,fragment))
        }
        adapter?.notifyDataSetChanged()
        tab_tree.notifyDataSetChanged()
    }


}
