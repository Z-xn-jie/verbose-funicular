package com.sprout.baselibrary.base

import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter:RecyclerView.Adapter<BaseAdapter.BaseViewHolder> () {

    override fun getItemCount(): Int {
        return itemCount()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(LayoutInflater.from(parent.context).inflate(layoutId(viewType), parent, false))
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        bindView(holder,position)
    }

    abstract  fun itemCount(): Int
    abstract  fun layoutId(viewType: Int): Int
    abstract  fun bindView(holder: BaseViewHolder, position: Int)

    class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var views: SparseArray<View> = SparseArray<View>()
        //查找item的view
        fun getViewById(id: Int): View? {
            var view = views.get(id) as View?
            if (view == null) {
                view = itemView.findViewById(id)
                //可能为空 error
                views.append(id, view)
            }
            return view
        }
    }
}
