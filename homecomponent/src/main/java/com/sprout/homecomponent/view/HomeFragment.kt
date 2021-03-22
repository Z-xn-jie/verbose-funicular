package com.sprout.homecomponent.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.book.base.BaseLazyFragment
import com.sprout.baselibrary.base.BaseModel
import com.sprout.baselibrary.base.BasePresenter
import com.sprout.baselibrary.base.BaseView
import com.sprout.homecomponent.R

class HomeFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(context).inflate(R.layout.fragment_home,null)
    }
}