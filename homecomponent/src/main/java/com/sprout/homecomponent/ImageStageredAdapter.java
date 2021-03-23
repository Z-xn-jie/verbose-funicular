package com.sprout.homecomponent;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sprout.baselibrary.base.BaseAdapter;
import com.sprout.baselibrary.bean.CityBean;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2019/7/31.
 */

public class ImageStageredAdapter extends BaseAdapter {
    private ArrayList<CityBean.DataDTO> dataList = new ArrayList<>();
        Context context;
    public ImageStageredAdapter(Context context) {
        this.context  = context;
    }


    @Override
    public int itemCount() {
        return (dataList==null)?0:dataList.size();
    }

    @Override
    public int layoutId(int viewType) {
        return R.layout.item_rv_water_fall;
    }

    @Override
    public void bindView(@NotNull BaseViewHolder holder, int position) {
       ImageView imageView = (ImageView)holder.getViewById(R.id.image);
       TextView title = (TextView) holder.getViewById(R.id.title);
       Glide.with(context).load(dataList.get(position).getRes().get(0).getUrl()).into(imageView);
       title.setText(dataList.get(position).getTitle());
    }

    public void reflashList(@NotNull List<? extends CityBean.DataDTO> data) {
        dataList.clear();
        dataList.addAll(data);
        notifyDataSetChanged();
    }
    public void addList(@NotNull List<? extends CityBean.DataDTO> data) {
        dataList.addAll(data);
        notifyDataSetChanged();
    }
}

