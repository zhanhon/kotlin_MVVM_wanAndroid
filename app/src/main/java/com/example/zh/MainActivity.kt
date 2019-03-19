package com.example.zh

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.example.zh.base.BaseActivity
import com.example.zh.ui.viewmodel.MainViewModel
import com.example.zh.utils.InjectorUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    lateinit var viewModel: MainViewModel
    private lateinit var mFragment: Fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initVM()
        initView()
    }

    override fun initVM() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun initView() {
        navigation_bar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
        navigation_bar.setMode(BottomNavigationBar.MODE_FIXED)
        for (i in 0 until viewModel.mList.size){
            navigation_bar.addItem(BottomNavigationItem(viewModel.barImage[i],viewModel.barText[i]))
        }
        navigation_bar.setActiveColor(R.color.cFE6243)
        navigation_bar.setInActiveColor(R.color.c707070)

        navigation_bar.setTabSelectedListener(object : BottomNavigationBar.OnTabSelectedListener{
            override fun onTabReselected(position: Int) {
            }
            override fun onTabUnselected(position: Int) {
            }
            override fun onTabSelected(position: Int) {
                switchFragment(position)
            }
        })
        selectTab(0)

    }

    fun selectTab(position: Int){
        navigation_bar.initialise()
        navigation_bar.setFirstSelectedPosition(position)
        defaultFragment(position)
    }


    fun defaultFragment(position: Int) {
        if (viewModel.mList.size > position) {
            mFragment = viewModel.mList.get(position)
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.content, mFragment).commitAllowingStateLoss()
        }
    }

    fun switchFragment(position: Int) {
        if (viewModel.mList.size < position) {
            return
        }
        //判断当前显示的Fragment是不是切换的Fragment
        val fragment = viewModel.mList.get(position)
        if (mFragment !== fragment) {
            val fm = supportFragmentManager
            val ft = fm.beginTransaction()
            //判断切换的Fragment是否已经添加过
            if (!fragment.isAdded()) {
                //如果没有，则先把当前的Fragment隐藏，把切换的Fragment添加上
                ft.hide(mFragment).add(R.id.content, fragment).commitAllowingStateLoss()
            } else {
                //如果已经添加过，则先把当前的Fragment隐藏，把切换的Fragment显示出来
                ft.hide(mFragment).show(fragment).commitAllowingStateLoss()
            }
            mFragment = fragment
        }
    }

}
