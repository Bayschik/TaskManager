package com.example.hw_1_4.ui.data.local

import android.content.Context

class Pref(context:Context) {
    private val pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun isShow():Boolean{
        return pref.getBoolean(SHOWED_KEY, false)
    }

    fun onShowed(){
        pref.edit().putBoolean(SHOWED_KEY, true).apply()
    }

    fun saveName(name:String){
        pref.edit().putString(SHOW_NAME, name).apply()
    }

    fun getName():String?{
        return pref.getString(SHOW_NAME, "")
    }

    fun saveImage(imageView: String){
        pref.edit().putString(SHOW_IMAGE, imageView).apply()
    }

    fun getImage(): String? {
        return pref.getString(SHOW_IMAGE, null)
    }

    companion object{
        const val PREF_NAME = "pref.name"
        const val SHOWED_KEY = "showed.key"
        const val SHOW_NAME = "show.name"
        const val SHOW_IMAGE = "show.image"
    }
}