package com.example.zh.utils

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import jp.wasabeef.glide.transformations.RoundedCornersTransformation

import com.bumptech.glide.request.RequestOptions.bitmapTransform

/**
 * 在Activity里context上下文要使用getApplicationContext()
 */
object GlideUtils {
    //四周平角
    fun displayImage(context: Context, url: String, myImageView: ImageView) {
        Glide.with(context.applicationContext)
                .load(url)
                .thumbnail(0.1f)
                .into(myImageView)
    }


    //圆形
    fun loadCircularImg(context: Context, url: String, myImageView: ImageView, errorId: Int) {
        GlideApp.with(context.applicationContext)
                .load(url)
                .thumbnail(0.1f)
                .placeholder(errorId)
                .error(errorId)
                .apply(RequestOptions.circleCropTransform())
                .into(myImageView)
    }

    //图片加载--四周圆角
    fun loadRoundImg(context: Context, url: String, myImageView: ImageView) {
        GlideApp.with(context.applicationContext)
                .load(url)
                .thumbnail(0.1f)
                .apply(bitmapTransform(RoundedCornersTransformation(10, 0)))
                .into(myImageView)
    }

    fun loadRoundImg(context: Context, bitmap: Bitmap, myImageView: ImageView) {
        GlideApp.with(context.applicationContext)
                .load(bitmap)
                .thumbnail(0.1f)
                .apply(bitmapTransform(RoundedCornersTransformation(10, 0)))
                .into(myImageView)
    }

    //圆形--四周圆角
    fun loadRoundImg(context: Context, url: String, myImageView: ImageView, errorImge: Int) {
        GlideApp.with(context.applicationContext)
                .load(url)
                .error(errorImge)
                .placeholder(errorImge)
                .thumbnail(0.1f)
                .apply(bitmapTransform(RoundedCornersTransformation(10, 0)))
                .into(myImageView)
    }


}
