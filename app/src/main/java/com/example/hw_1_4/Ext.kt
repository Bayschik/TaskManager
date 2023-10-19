package com.example.hw_1_4

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

fun Context.showToast(msg:String){
    Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
}

fun Fragment.showToast(msg:String){
    Toast.makeText(requireContext(), msg,Toast.LENGTH_SHORT).show()
}

fun ImageView.loadImage(url:String){
    Glide.with(this).load(url).into(this)
}