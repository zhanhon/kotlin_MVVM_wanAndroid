package com.example.zh.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.zh.bean.NavTab

class MyPagerAdapter(fm: FragmentManager,var list: List<NavTab>) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return list.get(position).mFragment
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return list.get(position).title
    }

}
