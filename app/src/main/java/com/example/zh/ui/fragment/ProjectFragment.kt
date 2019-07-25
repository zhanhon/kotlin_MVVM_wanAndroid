package com.example.zh.ui.fragment


import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.zh.R
import com.example.zh.base.BaseFragment
import com.example.zh.ui.adapter.ProjectAdapter
import com.example.zh.ui.adapter.ProjectClassAdapter
import com.example.zh.ui.viewmodel.ProjectViewModel
import com.example.zh.utils.InjectorUtil
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

    override fun initView() {
        //项目分类
        projectClassAdapter = ProjectClassAdapter(viewModel.projectList)
        rv_project.layoutManager = LinearLayoutManager(context)
        rv_project.adapter = projectClassAdapter
        projectClassAdapter.onItemClickListener = object : BaseQuickAdapter.OnItemClickListener {
            override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
                projectClassAdapter.selectionPosition(position)

            }
        }

        //项目文章
        projectAdapter = ProjectAdapter(viewModel.articleList)
        rv_projectList.layoutManager = LinearLayoutManager(context)
        rv_projectList.adapter = projectAdapter
        projectAdapter.onItemClickListener = object : BaseQuickAdapter.OnItemClickListener{
            override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {


            }
        }


    }

    override fun initVM() {
        viewModel = ViewModelProviders.of(this, InjectorUtil.getProjectFactoty()).get(ProjectViewModel::class.java)
    }

    override fun initData() {
        getProjectList()


    }

    fun getProjectList(){
        viewModel.getProjectList().observe(this, Observer {
            viewModel.projectList.addAll(it)
            projectAdapter.notifyDataSetChanged()
        })
    }

    fun projectArticleList(pageNum: Int,cId: Int){
        viewModel.projectArticleList(pageNum,cId).observe(this, Observer {
            viewModel.articleList.addAll(it)


        })
    }





}
