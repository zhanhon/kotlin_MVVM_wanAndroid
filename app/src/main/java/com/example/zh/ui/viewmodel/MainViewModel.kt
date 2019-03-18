package com.example.zh.ui.viewmodel

import androidx.fragment.app.Fragment
import com.example.zh.R
import com.example.zh.base.BaseViewModel
import com.example.zh.ui.fragment.*

class MainViewModel: BaseViewModel(){
    val barText: Array<String> = arrayOf("首页","项目","体系","导航","公众号")
    val mList: List<Fragment> = arrayListOf(
            HomeFragment(),
            ProjectFragment(),
            TreeFragment(),
            NavFragment(),
            ChapterFragment()
    )
    val barImage: Array<Int> = arrayOf(
            R.drawable.selector_tab_btn_home,
            R.drawable.selector_tab_btn_createtask,
            R.drawable.selector_tab_btn_manage,
            R.drawable.selector_tab_btn_coordinates,
            R.drawable.selector_tab_btn_select
    )



}