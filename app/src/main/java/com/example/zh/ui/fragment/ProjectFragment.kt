package com.example.zh.ui.fragment


import android.content.Intent
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.zh.R
import com.example.zh.base.BaseFragment
import com.example.zh.ui.adapter.ProjectAdapter
import com.example.zh.ui.adapter.ProjectClassAdapter
import com.example.zh.ui.home.WebActivity
import com.example.zh.ui.viewmodel.ProjectViewModel
import com.example.zh.utils.InjectorUtil
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener
import kotlinx.android.synthetic.main.fragment_project.*

/**
 *项目
 */
class ProjectFragment : BaseFragment() {
    lateinit var viewModel: ProjectViewModel
    lateinit var projectAdapter: ProjectAdapter
    lateinit var projectClassAdapter: ProjectClassAdapter
    override fun setContent(): Int {
        return R.layout.fragment_project
    }

    override fun initVM() {
        viewModel = ViewModelProviders.of(this, InjectorUtil.getProjectFactoty()).get(ProjectViewModel::class.java)
    }

    override fun initView() {
        //项目分类
        projectClassAdapter = ProjectClassAdapter(viewModel.projectList)
        rv_project.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        rv_project.adapter = projectClassAdapter
        projectClassAdapter.onItemClickListener = object : BaseQuickAdapter.OnItemClickListener {
            override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
                projectClassAdapter.selectionPosition(position)
                projectArticleList(viewModel.selectionProject(position))
            }
        }

        //项目文章
        projectAdapter = ProjectAdapter(viewModel.articleList)
        rv_projectList.layoutManager = LinearLayoutManager(context)
        rv_projectList.adapter = projectAdapter
        projectAdapter.onItemClickListener = object : BaseQuickAdapter.OnItemClickListener{
            override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
                val intent = Intent()
                intent.setClass(context, WebActivity::class.java)
                val link = viewModel.articleList.get(position).link
                intent.putExtra("url",link)
                context?.startActivity(intent)
            }
        }

        //刷新
        refresh.setRefreshFooter(ClassicsFooter(context))
        refresh.setRefreshHeader(ClassicsHeader(context))
        refresh.setOnRefreshLoadmoreListener(object: OnRefreshLoadmoreListener {
            override fun onLoadmore(refreshlayout: RefreshLayout?) {
                viewModel.pageNum ++
                projectArticleList(viewModel.cid, viewModel.pageNum)

            }
            override fun onRefresh(refreshlayout: RefreshLayout?) {
                viewModel.pageNum = 1
                projectArticleList(viewModel.cid)

            }
        })
    }

    override fun initData() {
        getProjectList()
    }

    fun getProjectList(){
        viewModel.getProjectList().observe(this, Observer {
            viewModel.projectList.addAll(it)
            projectClassAdapter.notifyDataSetChanged()

            projectArticleList(viewModel.selectionProject(0))
        })
    }

    fun projectArticleList(cId: Int,pageNum: Int = 1){
        viewModel.projectArticleList(pageNum,cId).observe(this, Observer {
            if (viewModel.pageNum == 1){
                viewModel.articleList.clear()
            }
            viewModel.articleList.addAll(it)
            projectAdapter.notifyDataSetChanged()
            refresh.finishLoadmore()
            refresh.finishRefresh()



        })
    }





}
