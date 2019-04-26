package com.example.zh.ui.adapter

import android.widget.CheckBox
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.zh.R
import com.example.zh.bean.ArticleBean
import com.example.zh.bean.TreeArticleList

class TreeTabAdapter(datas: List<TreeArticleList>)
    : BaseQuickAdapter<TreeArticleList,BaseViewHolder>(R.layout.item_list_home, datas) {

    override fun convert(holder: BaseViewHolder, item:TreeArticleList) {
        val tvMainTitle: TextView = holder.getView(R.id.tv_main_title)
        val tvAuthorName: TextView = holder.getView(R.id.tv_author_name)
        val tvTypeName: TextView = holder.getView(R.id.tv_type_name)
        val tvTime: TextView = holder.getView(R.id.tv_time)
        val vbCollect: CheckBox = holder.getView(R.id.vb_collect)
        tvMainTitle.setText(item.title)
        tvAuthorName.setText(item.author)
        tvTypeName.setText(item.superChapterName)
        tvTime.setText("时间" + item.niceDate)
    }
}
