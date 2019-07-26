package com.example.zh.ui.adapter

import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.zh.R
import com.example.zh.bean.ArticleBeanData

class ProjectAdapter(data: List<ArticleBeanData>) : BaseQuickAdapter<ArticleBeanData,BaseViewHolder>(R.layout.item_list_project_article,data) {
    override fun convert(helper: BaseViewHolder, item: ArticleBeanData) {
        val tv_title: TextView = helper.getView(R.id.tv_title)
        val tv_sub_title: TextView = helper.getView(R.id.tv_sub_title)

        tv_title.text = item.title
        tv_sub_title.text = item.desc
    }

}