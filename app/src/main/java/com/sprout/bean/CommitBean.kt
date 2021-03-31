package com.sprout.bean


import android.annotation.SuppressLint
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class CommitBean(
        val address: String,
        val channelid: Int,
        val lat: Int,
        val lng: Int,
        var mood: String,
        val res: List<Re>,
        val themeid: Int,
        var title: String,
        val type: Int
) : Parcelable {
    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Re(
            val tags: List<Tag>,
            var url: String
    ) : Parcelable {
        @SuppressLint("ParcelCreator")
        @Parcelize
        data class Tag(
                val id: Int,
                val name: String,
                val type: Int,
                val x: Float,
                val y: Float
        ) : Parcelable
    }
}