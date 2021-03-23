package com.sprout.homecomponent.view

import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.sprout.baselibrary.base.BaseLazyFragment
import com.sprout.baselibrary.bean.CityBean
import com.sprout.baselibrary.contract.IHome
import com.sprout.homecomponent.ImageStageredAdapter
import com.sprout.homecomponent.R
import com.sprout.homecomponent.presenter.HomePresenter
import kotlinx.android.synthetic.main.fragment_city.*


class CityFragment() : BaseLazyFragment<HomePresenter>(), IHome.IView {
    lateinit  var adapter:ImageStageredAdapter
    var count = 1
    override fun getPresenter(): HomePresenter = HomePresenter()
    override fun initMain() {
        presenter.transfer(count)
        adapter = ImageStageredAdapter(getContext())
        recyclerView.adapter = adapter
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = staggeredGridLayoutManager
        recyclerView.setAdapter(adapter)
        refreshLayout.setOnRefreshListener {

        }
    }

    override fun getLayoutID(): Int = R.layout.fragment_city
    override fun cityResult(bean: CityBean?) {
       var data = bean!!.data
        if(count == 1) {
            adapter.reflashList(bean.data)
        }else{
            adapter.addList(bean.data)
        }

    }


}