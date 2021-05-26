package com.apcc.utils

import android.content.Context
import androidx.appcompat.content.res.AppCompatResources

object ResourceHelper {
    fun getColor(context: Context, resource:Int) = AppCompatResources.getColorStateList(context, resource)

    fun getInt(context: Context, resource:Int):Int{
        return context.resources.getInteger(resource)
    }

    fun getDimension(context: Context, resource:Int) = context.resources.getDimension(resource)
}