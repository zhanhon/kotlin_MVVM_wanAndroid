package com.example.zh.ui.adapter

import android.content.Context
import android.widget.TextView
import com.example.zh.R
import com.example.zh.bean.NavArticle
import com.zhy.adapter.recyclerview.CommonAdapter
import com.zhy.adapter.recyclerview.base.ViewHolder

class NavArticleAdapter(context: Context?, datas: List<NavArticle>)
    : CommonAdapter<NavArticle>(context, R.layout.item_list_nav, datas){
    override fun convert(holder: ViewHolder, item: NavArticle?, position: Int) {
        val tv_title: TextView = holder.getView(R.id.tv_title);
        tv_title.text = item?.title ?: ""
    }

}