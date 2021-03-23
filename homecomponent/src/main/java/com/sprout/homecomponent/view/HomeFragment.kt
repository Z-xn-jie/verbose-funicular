package com.sprout.homecomponent.view

import android.view.View
import androidx.fragment.app.Fragment
import com.example.book.base.BaseFragment
import com.example.book.base.BasePagerAdapter
import com.google.android.material.tabs.TabLayout
import com.sprout.homecomponent.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {
    private val mTile = arrayOf("同城","关注","推荐")
    private val mFragments  = arrayListOf<Fragment>(
           CityFragment(),
            AttentionFragment(),
            AttentionFragment()
    )
    private lateinit var mPagerAdapter: BasePagerAdapter

    override fun getContentViewLayoutID(): Int = R.layout.fragment_home

    override fun onFirstVisibleToUser() {
     initView()
    }

    private fun initView() {
        mPagerAdapter = BasePagerAdapter(childFragmentManager)
        mPagerAdapter.setFragments(view_pager, mFragments)
        view_pager.adapter = mPagerAdapter
        view_pager.offscreenPageLimit = mFragments.size
        tab_layout.setViewPager(view_pager,mTile)

    }

    override fun onVisibleToUser() {

    }

    override fun onInvisibleToUser() {

    }

}