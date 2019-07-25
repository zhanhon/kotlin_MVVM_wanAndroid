package com.example.zh.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.zh.R
import com.example.zh.bean.ProjectArticleBean

class ProjectAdapter(data: List<ProjectArticleBean>) : BaseQuickAdapter<ProjectArticleBean,BaseViewHolder>(R.layout.item_list_home,data) {
    override fun convert(helper: BaseViewHolder?, item: ProjectArticleBean?) {


    }

}