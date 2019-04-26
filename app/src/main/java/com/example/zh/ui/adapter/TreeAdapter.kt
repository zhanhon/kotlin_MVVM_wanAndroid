package com.example.zh.ui.adapter

import android.content.Intent
import android.text.Html
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.zh.R
import com.example.zh.bean.Children
import com.example.zh.bean.TreeSystemBean
import com.example.zh.ui.home.WebActivity
import com.example.zh.ui.tree.TreeDetailsActivity
import com.scwang.smartrefresh.layout.util.DensityUtil
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import com.zhy.view.flowlayout.TagFlowLayout

class TreeAdapter(data: MutableList<TreeSystemBean>?) :
        BaseQuickAdapter<TreeSystemBean, BaseViewHolder>(R.layout.item_list_tree,data) {

    override fun convert(helper: BaseViewHolder?, item: TreeSystemBean?) {
        helper?.setText(R.id.tv_tree_group_name,item?.name)
        val tagFlowLayout : TagFlowLayout = helper!!.getView(R.id.tagFlowLayout_child)

        showTagView(tagFlowLayout,item)


    }


    private fun showTagView(flowlayoutHot: TagFlowLayout, item: TreeSystemBean?) {
        flowlayoutHot.adapter = object : TagAdapter<Children>(item?.children) {
            override fun getView(parent: FlowLayout, position: Int, bean: Children): View {
                val textView = getTextView()
                textView.setText(Html.fromHtml(bean.name))
                return textView
            }
        }

        flowlayoutHot.setOnTagClickListener { view, position, parent ->
            val intent = Intent()
            intent.putParcelableArrayListExtra("children_list",item?.children)
            intent.putExtra("title_name",item?.name)
            intent.setClass(mContext,TreeDetailsActivity::class.java)
            mContext.startActivity(intent)
            true
        }
    }

    private fun getTextView(): TextView {
        val hotText = TextView(mContext)
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