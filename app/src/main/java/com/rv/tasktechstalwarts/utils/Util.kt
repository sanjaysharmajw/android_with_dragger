package com.rv.tasktechstalwarts.utils

import android.content.Context
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.rv.tasktechstalwarts.R
import com.rv.tasktechstalwarts.common.MY_LOCAL_DATABASE
import dagger.hilt.android.qualifiers.ApplicationContext


object Util {
    fun setImage(context: Context,iv:AppCompatImageView,url: String?){
        Glide.with(context)
            .load(url)
            .placeholder(R.drawable.ic_recipes_placeholder)
            .into(iv)
    }

    fun setCircularImage(context: Context,iv:AppCompatImageView,url: String?){
        Glide.with(context)
            .load(url)
            .circleCrop()
            .placeholder(R.drawable.ic_recipes_placeholder)
            .into(iv)
    }

    fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }


    //delete database use Application context
     fun deleteDatabase(context:Context) {
     context.deleteDatabase(MY_LOCAL_DATABASE)
    }
}