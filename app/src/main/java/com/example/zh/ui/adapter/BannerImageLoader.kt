package com.example.zh.ui.adapter

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.zh.bean.BannerBean
import com.youth.banner.loader.ImageLoader

class BannerImageLoader : ImageLoader() {
    override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
        val beanData = path as BannerBean
        Glide.with(context!!).load(beanData.imagePath).into(imageView!!)

    }
}
