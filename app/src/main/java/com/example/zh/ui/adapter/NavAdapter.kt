package com.example.zh.ui.adapter

import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.zh.R
import com.example.zh.bean.NavBean

class NavAdapter(datas: List<NavBean>)
    : BaseQuickAdapter<NavBean,BaseViewHolder>(R.layout.item_list_nav, datas){
    override fun convert(holder: BaseViewHolder, item: NavBean?) {
        val tv_title: TextView = holder.getView(R.id.tv_title);
        tv_title.text = item?.name ?: ""
    }

}