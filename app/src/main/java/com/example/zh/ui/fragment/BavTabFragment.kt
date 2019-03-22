package com.example.zh.ui.fragment


import android.os.Bundle
import android.text.Html
import android.view.View

import com.example.zh.R
import com.example.zh.base.BaseFragment
import com.example.zh.bean.NavArticle
import kotlinx.android.synthetic.main.fragment_nav_tab.*
import android.view.Gravity
import com.scwang.smartrefresh.layout.util.DensityUtil
import android.widget.LinearLayout
import android.widget.TextView
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import com.zhy.view.flowlayout.TagFlowLayout

class BavTabFragment : BaseFragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initVM()
        initView()
        initData()

    }

    override fun setContent(): Int {
        return R.layout.fragment_nav_tab
    }

    override fun initVM() {


    }

    override fun initView() {

    }

    override fun initData() {
        val bundle = arguments
        val list = bundle?.getParcelableArrayList<NavArticle>("articleList")
        val cid = bundle?.getInt("cid")
        val listTemp: ArrayList<NavArticle> = ArrayList()
        list?.forEach {
            if (cid == it.chapterId){
                listTemp.add(it)
            }
        }
        showTagView(tagFlowLayout,listTemp)
    }

    private fun showTagView(flowlayoutHot: TagFlowLayout, beanList: List<NavArticle>) {
//        flowlayoutHot.removeAllViews()
        flowlayoutHot.adapter = object : TagAdapter<NavArticle>(beanList) {
            override fun getView(parent: FlowLayout, position: Int, bean: NavArticle): View {
                val textView = getTextView()
                textView.setText(Html.fromHtml(bean.title))
                return textView
            }
        }

        flowlayoutHot.setOnTagClickListener { view, position, parent -> true

        }
    }

    private fun getTextView(): TextView {
        val hotText = TextView(activity)
        val lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        hotText.layoutParams = lp
        hotText.textSize = 13f
        val left: Int
        val top: Int
        val right: Int
        val bottom: Int
        hotText.maxLines = 1
        bottom = DensityUtil.dp2px(5f)
        right = bottom
        top = right
        left = top
        hotText.setBackgroundResource(R.drawable.shape_navi_tag)
        hotText.gravity = Gravity.CENTER
        lp.setMargins(left, top, right, bottom)
        return hotText
    }



}
