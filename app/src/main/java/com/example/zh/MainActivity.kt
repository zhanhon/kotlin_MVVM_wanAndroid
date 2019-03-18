package com.example.zh

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.example.zh.base.BaseActivity
import com.example.zh.ui.viewmodel.MainViewModel
import com.example.zh.utils.InjectorUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    lateinit var viewModel: MainViewModel
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
                this@MainActivity.onTabUnselected(position)
            }
            override fun onTabSelected(position: Int) {
                setFragment(position)
            }
        })
        selectTab(0)

    }

    fun selectTab(position: Int){
        navigation_bar.initialise()
        navigation_bar.setFirstSelectedPosition(position)
        setFragment(position)
    }

    fun setFragment(position: Int){
        if (viewModel.mList.size > position) {
//            val transaction = supportFragmentManager.beginTransaction()
//            transaction.replace(R.id.content, viewModel.mList.get(position)).commit()
            val fm: FragmentManager = supportFragmentManager
            val ft: FragmentTransaction  = fm.beginTransaction();
            val fragment: Fragment  = viewModel.mList.get(position);
            if (fragment.isAdded()) {
                ft.show(fragment);
            } else {
                ft.add(R.id.content, fragment);
            }
            ft.commitAllowingStateLoss();
        }
    }

    fun onTabUnselected(position: Int){
        if (viewModel.mList.size > position) {
            val fm: FragmentManager = supportFragmentManager
            val ft: FragmentTransaction  = fm.beginTransaction();
            val fragment: Fragment  = viewModel.mList.get(position);
            if (!fragment.isHidden()) {
                ft.hide(fragment);
            }
            ft.commitAllowingStateLoss();
        }
    }

}
