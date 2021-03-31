package com.sprout.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.example.book.base.BasePagerAdapter
import com.facebook.drawee.backends.pipeline.Fresco
import com.google.android.material.tabs.TabLayout
import com.sprout.R
import com.sprout.baselibrary.base.BaseActivity
import com.sprout.baselibrary.utils.RealPathFromUriUtils
import com.sprout.bean.*
import com.sprout.homecomponent.view.HomeFragment
import com.sprout.searchcomponent.view.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_table.view.*
import kotlin.collections.ArrayList


class MainActivity : BaseActivity(), PictureTagFrameLayout.ITagLayoutCallBack {

    open lateinit var mViewPager: ViewPager
    private var mPointX: Float = 0F
    lateinit var adapter: ImageAdapter
    private var mPointY: Float = 0F
    lateinit var videoview: VideoView
    lateinit var imagess: ImageView
    private var mImageAdapter: ImagePagerAdapterForPublish? = null
    private val mTagBeansMap: Map<String, List<ITagBean>>? = null
     val uriList: ArrayList<String> = arrayListOf()
    private var imageResId: ArrayList<String> = ArrayList()
    private var reList: ArrayList<CommitBean.Re> = ArrayList()
    private var tagList: ArrayList<CommitBean.Re.Tag> = ArrayList()
    private var commitBean = CommitBean("北京",1,0,0,"",reList,1,"",1)
    private var count = 0;


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

        Fresco.initialize(this)
        mImageAdapter = ImagePagerAdapterForPublish(this, this)
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
        mPopupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT)  //设置高度  必须代码设置
        // 需要设置一下此参数，点击外边可消失
        mPopupWindow.setBackgroundDrawable(BitmapDrawable())
        // 设置点击窗口外边窗口消失    这两步用于点击手机的返回键的时候，不是直接关闭activity,而是关闭pop框
        mPopupWindow.setOutsideTouchable(true)
        // 设置此参数获得焦点，否则无法点击，即：事件拦截消费
        mPopupWindow.setFocusable(true)

        val text = view.findViewById<TextView>(R.id.texts)
        val button_paizhao = view.findViewById<Button>(R.id.pai_zhao)
        val button_weixin = view.findViewById<TextView>(R.id.weixin)
        val shangchuan = view.findViewById<TextView>(R.id.shang_chuan)
        val recycler = view.findViewById<RecyclerView>(R.id.recyclerView)
        shangchuan.setOnClickListener {
            if(imageResId.size == 0){
                Toast.makeText(this@MainActivity,"未選擇圖片",Toast.LENGTH_SHORT).show()
            }else {
                val intent = Intent(this@MainActivity, ShangActivity::class.java)
                intent.putExtra("uri",commitBean)
                startActivity(intent)
            }
        }
        videoview = view.findViewById<VideoView>(R.id.videoview)
        mViewPager = view.findViewById(R.id.images)
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        adapter = ImageAdapter(this@MainActivity)
        recycler.layoutManager = linearLayoutManager
        recycler.adapter = adapter
        adapter.onClickPhoneItem = {
            mViewPager.setCurrentItem(it,false);

        }
        adapter.onClickItem = {
            val intent = Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            startActivityForResult(intent, 100)
        }
        mViewPager.setAdapter(mImageAdapter)
        mViewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                count = position

            }

        })
        mViewPager.setOffscreenPageLimit(9)
        button_paizhao.setOnClickListener {
            val intents = Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            startActivityForResult(intents, 1)

        }

        button_weixin.setOnClickListener {
            startActivity(Intent(this@MainActivity, WeiActivity::class.java))
        }

        //点击“X”形图片的关闭pop框
        text.setOnClickListener(View.OnClickListener {
            mPopupWindow.dismiss()
        })

        mPopupWindow.setOnDismissListener {
            setBackgroundAlpha(1.0f, this)
        }
        setBackgroundAlpha(0.5f, this)
        val parentView: View =
                LayoutInflater.from(this).inflate(R.layout.activity_main, null)
        mPopupWindow.showAtLocation(parentView, Gravity.BOTTOM, 0, 0)

    }

    fun setBackgroundAlpha(bgAlpha: Float, mContext: Context) {
        val activity: Activity = mContext as Activity
        val lp = activity.window.attributes
        lp.alpha = bgAlpha
        activity.window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        activity.window.attributes = lp
    }

    override fun onActivityResult(
            requestCode: Int,
            resultCode: Int,
            data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 100 && data != null) {
            /*   val realPathFromUri = RealPathFromUriUtils.getRealPathFromUri(this, data.data)*/
            if (data.data == null) {
                val imageNames = data.clipData!!
                val counts = imageNames.itemCount - 1
                for (si in 0..counts) {
                    val imageUri = imageNames.getItemAt(si).uri
                    val video = RealPathFromUriUtils.getRealPathFromUri(this, imageUri)
                    if (video.substring(video.length - 3, video.length).equals("mp4")) {
                        videoview.setVideoURI(imageUri)
                        videoview.start()
                        mViewPager.visibility = View.GONE
                        videoview.visibility = View.VISIBLE
                    } else {
                        imageResId.add(imageUri.toString())
                        update()
                        adapter.addList(imageResId)
                        videoview.stopPlayback()
                        videoview.visibility = View.GONE
                        mViewPager.visibility = View.VISIBLE
                    }
                }
            } else {
                val imageUri = data.data
                val video = RealPathFromUriUtils.getRealPathFromUri(this, imageUri)
                if (video.substring(video.length - 3, video.length).equals("mp4")) {
                    videoview.setVideoURI(imageUri)
                    videoview.start()
                    mViewPager.visibility = View.GONE
                    videoview.visibility = View.VISIBLE
                } else {
                    imageResId.add(imageUri.toString())
                    adapter.addList(imageResId)
                    update()
                    videoview.stopPlayback()
                    videoview.visibility = View.GONE
                    mViewPager.visibility = View.VISIBLE
                }
            }

        }
        if (requestCode == 1 && resultCode == RESULT_OK) {

            val photo = data!!.extras!!["data"] as Bitmap
                     val uri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), photo, "", ""))
            imageResId.add(uri.toString())
            adapter.addList(imageResId)
            update()

        }
        if (resultCode == RESULT_CODE_GET_TAG) {
            if (data != null) {
                val tagBean: TagBean? = data.getParcelableExtra(BUNDLE_TAG_BEAN)
                if (tagBean != null) {
                    onTagSelected(tagBean)
                }
            }
        }
    }

    fun onTagSelected(tagBean: ITagBean?) {
        if (tagBean != null && mImageAdapter != null) {
            val itemView = mImageAdapter!!.primaryItem
            Log.e("TAG", "onTagSelected: ")
            if (itemView is PictureTagFrameLayout) {
                val arrayList = reList.get(count).tags as ArrayList
                arrayList.add(com.sprout.bean.CommitBean.Re.Tag(1,tagBean.tagName,1,tagBean.sx,tagBean.sy))
                tagBean.sx = mPointX
                tagBean.sy = mPointY
                itemView.addItem(tagBean)
            }
        }
    }

    var REQUEST_CODE_GET_TAG = 101
    var RESULT_CODE_GET_TAG = 110
    var BUNDLE_TAG_BEAN = "tag_bean"


    private fun update() {
        val mMediaTotal: MutableList<IChooserModel> = ArrayList()
        reList.clear()
        for (i in imageResId.indices) {
            val options = BitmapFactory.Options()
            options.inJustDecodeBounds = true
            val realPathFromUri = RealPathFromUriUtils.getRealPathFromUri(this@MainActivity, Uri.parse(imageResId[i]))
          reList.add(com.sprout.bean.CommitBean.Re(ArrayList(),realPathFromUri))
            BitmapFactory.decodeFile(realPathFromUri, options)
            val chooserModel = ChooserModel()
            chooserModel.setId(imageResId[i])
            chooserModel.setHeight(options.outHeight)
            chooserModel.setWidth(options.outWidth)
            mMediaTotal.add(chooserModel)
        }
        mImageAdapter!!.setData(mMediaTotal, mTagBeansMap, UIUtils.dip2Px(this, 20F).toInt())
    }

    override fun onTagViewMoving() {

    }

    override fun onTagViewStopMoving() {

    }

    override fun onSingleClick(x: Float, y: Float) {
        mPointX = x
        mPointY = y
        openTagActvty()
    }

    private fun openTagActvty() {
        val intent = Intent(this@MainActivity, TagsActivity::class.java)
        startActivityForResult(intent, 10)
    }


}



