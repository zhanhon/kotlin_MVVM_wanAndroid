package com.example.zh.ui.adapter

import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.zh.R
import com.example.zh.bean.ProjectData

class ProjectClassAdapter(datas: List<ProjectData>)
    : BaseQuickAdapter<ProjectData,BaseViewHolder>(R.layout.item_list_nav, datas){
    var position: Int = 0;
    override fun convert(holder: BaseViewHolder, item: ProjectData?) {
        val tv_title: TextView = holder.getView(R.id.tv_title);
        tv_title.text = item?.name ?: ""
        if (holder.layoutPosition == position){
            tv_title.setTextColor(mContext.resources.getColor(R.color.cFE6243))
        }else{
            tv_title.setTextColor(mContext.resources.getColor(R.color.c_main_title))

        }
    }

    fun selectionPosition(position: Int){
        this.position = position
        notifyDataSetChanged()
    }

}