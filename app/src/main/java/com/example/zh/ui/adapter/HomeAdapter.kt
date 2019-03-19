package com.example.zh.ui.adapter

import android.content.Context
import android.widget.CheckBox
import android.widget.TextView
import com.example.zh.R
import com.example.zh.bean.ArticleBean
import com.jakewharton.rxbinding3.view.clicks

import com.zhy.adapter.recyclerview.CommonAdapter
import com.zhy.adapter.recyclerview.base.ViewHolder

class HomeAdapter(context: Context?,datas: List<ArticleBean.DatasBean>)
    : CommonAdapter<ArticleBean.DatasBean>(context, R.layout.item_list_home, datas) {

    override fun convert(holder: ViewHolder, item: ArticleBean.DatasBean, position: Int) {
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
