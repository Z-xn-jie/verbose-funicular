package com.sprout.view

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.sprout.R
import com.sprout.baselibrary.base.BaseAdapter

class ImageAdapter(context: Context) : BaseAdapter() {
    var list: ArrayList<String> = ArrayList()
    lateinit var context: Context
    init {
        this.context = context
    }
    override fun itemCount(): Int  =  if (list == null) 1 else list.size+1

    override fun layoutId(viewType: Int): Int  = R.layout.item_image

    override fun bindView(holder: BaseViewHolder, position: Int) {
        val viewById: ImageView = holder.getViewById(R.id.image) as ImageView
        if(position == list.size){
            Glide.with(context).load(R.drawable.abc).into(viewById)
            viewById.setOnClickListener {
                if(onClickItem != null){
                    onClickItem()
                }
            }
        }else {
            Glide.with(context).load(Uri.parse(list.get(position))).into(viewById)
            viewById.setOnClickListener {
                if(onClickPhoneItem!=null){
                    onClickPhoneItem(position)
                }
            }
        }
    }
    fun addList(list:ArrayList<String>){
        this.list.clear()
        this.list.addAll(list)

        notifyDataSetChanged()
    }
    lateinit var onClickItem:() -> Unit
    lateinit var onClickPhoneItem:(Int) -> Unit
}