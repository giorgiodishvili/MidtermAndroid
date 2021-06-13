package com.android.myanimelist.extension

import android.app.Dialog
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

@BindingAdapter("app:imageUrl")
fun loadImage(view: ImageView, imageUrl: String?) {
    Glide.with(view.context)
        .load(imageUrl)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .centerCrop().into(view)
}


fun Dialog.setUp(binding: ViewBinding, color: Int, height: Int) {

    requestWindowFeature(Window.FEATURE_NO_TITLE)
    setContentView(binding.root)
    window!!.setBackgroundDrawableResource(color)
    val lp = WindowManager.LayoutParams()
    lp.copyFrom(window!!.attributes)
    lp.width = WindowManager.LayoutParams.MATCH_PARENT
    lp.height = height
    lp.gravity = Gravity.CENTER
    window!!.attributes = lp
}