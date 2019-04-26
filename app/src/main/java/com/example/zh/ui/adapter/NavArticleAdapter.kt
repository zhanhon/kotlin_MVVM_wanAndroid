package com.example.zh.ui.adapter

import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.zh.R
import com.example.zh.bean.NavArticle

class NavArticleAdapter(datas: List<NavArticle>)
    : BaseQuickAdapter<NavArticle,BaseViewHolder>(R.layout.item_list_nav, datas){
    override fun convert(holder: BaseViewHolder, item: NavArticle?) {
        val tv_title: TextView = holder.getView(R.id.tv_title);
        tv_title.text = item?.title ?: ""
    }

}