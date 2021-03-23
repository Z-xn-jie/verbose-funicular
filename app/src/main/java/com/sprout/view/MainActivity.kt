package com.sprout.view

import android.app.Activity
import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import android.view.*
import android.view.animation.AnimationUtils
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.book.base.BasePagerAdapter
import com.example.book.base.MyViewPager
import com.flyco.tablayout.SlidingTabLayout
import com.google.android.material.tabs.TabLayout
import com.sprout.R
import com.sprout.baselibrary.base.BaseActivity
import com.sprout.homecomponent.view.HomeFragment
import com.sprout.searchcomponent.view.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_table.view.*

class MainActivity : BaseActivity() {
    private val mTitle = arrayOf(
            "相冊",
            "拍照",
            "拍視頻",
            "手記"
    )

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
            SearchFragment(),
            HomeFragment(),
            SearchFragment()
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
        image.setOnClickListener {
            initPopuptWindow()
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
    private fun initPopuptWindow() {
        val wm = this.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        // 获取屏幕的width和height
        val display: Display = wm.getDefaultDisplay()
        //加载pop框的视图布局view
        var view = View.inflate(this, R.layout.popup_test, null)
        // 创建一个PopupWindow
        // 参数1：contentView 指定PopupWindow的内容
        // 参数2：width 指定PopupWindow的width
        // 参数3：height 指定PopupWindow的height
        val mPopupWindow = PopupWindow(view, display.getWidth(), display.getHeight())
        mPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT) //设置宽度 布局文件里设置的没有用
        mPopupWindow.setHeight(1300)  //设置高度  必须代码设置
        // 需要设置一下此参数，点击外边可消失
        mPopupWindow.setBackgroundDrawable(BitmapDrawable())
        // 设置点击窗口外边窗口消失    这两步用于点击手机的返回键的时候，不是直接关闭activity,而是关闭pop框
        mPopupWindow.setOutsideTouchable(true)
        // 设置此参数获得焦点，否则无法点击，即：事件拦截消费
        mPopupWindow.setFocusable(true)

        val text = view.findViewById<TextView>(R.id.texts)
        val tablayout = view.findViewById<SlidingTabLayout>(R.id.tablayout)
        val viewpager = view.findViewById<MyViewPager>(R.id.view_pager)

        mPagerAdapter = BasePagerAdapter(supportFragmentManager)
        mPagerAdapter.setFragments(viewpager, mFragments)
        viewpager.adapter = mPagerAdapter
        viewpager.setSmoothScroll(false)
        viewpager.setCanScroll(false)
        viewpager.offscreenPageLimit = mFragments.size
     /*   tablayout.setupWithViewPager(view_pager)
        for (i in mTitle.indices) { // 循环添加自定义的tab
          tablayout.getTabAt(i)!!.text = mTitle.get(i)

        }*/


        //点击“X”形图片的关闭pop框
        text.setOnClickListener(View.OnClickListener {
            mPopupWindow.dismiss()
        })

        //点击pop框的其他地方也可以关闭pop框
        /*    view.setOnClickListener (View.OnClickListener() {
                mPopupWindow.dismiss()
            })*/
        mPopupWindow.setOnDismissListener {
            setBackgroundAlpha(1.0f, this)
        }
        setBackgroundAlpha(0.5f, this)
        val parentView: View =
                LayoutInflater.from(this).inflate(R.layout.activity_main, null)
        mPopupWindow.showAtLocation(parentView, Gravity.BOTTOM, 0, 50)

    }

    fun setBackgroundAlpha(bgAlpha: Float, mContext: Context) {
        val activity: Activity = mContext as Activity
        val lp = activity.window.attributes
        lp.alpha = bgAlpha
        activity.window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        activity.window.attributes = lp
    }

}