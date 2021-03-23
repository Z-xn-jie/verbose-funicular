package com.sprout.view

import android.view.View
import androidx.fragment.app.Fragment
import com.example.book.base.BasePagerAdapter
import com.google.android.material.tabs.TabLayout
import com.sprout.R
import com.sprout.baselibrary.base.BaseActivity
import com.sprout.homecomponent.view.HomeFragment
import com.sprout.searchcomponent.view.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_table.view.*

class MainActivity : BaseActivity() {
    /** 主菜单（图标） */
    private val mIcons = intArrayOf(
            R.drawable.home_selector,
            R.drawable.search_selector,
            R.drawable.a642c265c319e7b470fe73bdbc664040,
            R.drawable.info_selector,
            R.drawable.mine_selector)
    private val mFragments: MutableList<Fragment> = mutableListOf(
            HomeFragment(),
            SearchFragment(),
            HomeFragment(),
            SearchFragment(),
            HomeFragment()
            )
    private lateinit var mPagerAdapter: BasePagerAdapter

    override fun initMain() {
        mPagerAdapter = BasePagerAdapter(supportFragmentManager)
        mPagerAdapter.setFragments(view_pager, mFragments)
        view_pager.adapter = mPagerAdapter
        view_pager.setSmoothScroll(false)
        view_pager.setCanScroll(false)
        view_pager.offscreenPageLimit = mFragments.size
        tablayout.setupWithViewPager(view_pager)
        for (i in mIcons.indices) { // 循环添加自定义的tab
            val tab: TabLayout.Tab? = tablayout.getTabAt(i)
            tab?.customView = getTabView(i)
        }
    }

    private fun getTabView(position: Int): View? {
        layoutInflater.inflate(R.layout.item_table, tablayout, false).apply {
            // View设置属性，注意上面引用的包（import属于你们自己的包路径）
            this.tab_image.setImageResource(mIcons[position])
            return this
        }
    }

    override fun getLayoutId(): Int = R.layout.activity_main

}