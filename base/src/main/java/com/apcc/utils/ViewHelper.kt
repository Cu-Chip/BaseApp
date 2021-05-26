package com.apcc.utils

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.URLUtil
import androidx.annotation.NonNull
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.apcc.R
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import java.io.File

object ViewHelper {

    fun updateMenuTint(menu: Menu, context: Context){
        if (menu.size() > 0){
            for ( i in 0 until menu.size()){
                updateMenuTint(menu.getItem(i), context)
            }
        }
    }

    /**
     * support for menu drawable icon
     * working for API sdk < 26
     * from 26, it working auto from xml
     */
    fun updateMenuTint(menuItem: MenuItem?, context: Context){
        menuItem?.let { mn->
            mn.icon?.let { ic->
                ic.setTintList(
                    ContextCompat.getColorStateList(
                        context,
                        R.color.menu_tint
                    )
                )
                mn.icon = ic
            }
        }
    }

    fun loadImage(imgPath: String?, defaultImg: Int = 0, allowCache: Boolean = false): RequestCreator? {
        imgPath?.let { path->
            val request: RequestCreator
            if(checkExistFile(path)){
                // is local file
                request = Picasso.get().load(File(path))
            }else if (URLUtil.isValidUrl(path)){
                // is online file
                request  = Picasso.get().load(path)
            }else{
                // make online path as normal
                request = Picasso.get().load(formatFileUrl(path))
            }

            if(defaultImg > 0) request.error(defaultImg)
            if (allowCache) request.memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
            return request
        }
        return null
    }

    fun getViewBitmap(view: View): Bitmap {
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val bgDrawable = view.background
        if (bgDrawable != null) {
            bgDrawable.draw(canvas)
        } else {
            canvas.drawColor(Color.WHITE)
        }
        view.draw(canvas)
        return bitmap
    }

    fun isVectorDrawable(context:Context, resource:Int): Boolean {
        val drawable = AppCompatResources.getDrawable(context, resource)
        return if (drawable != null){
            isVectorDrawable(drawable)
        }else{
            false
        }
    }

    fun isVectorDrawable(@NonNull d: Drawable): Boolean {
        return d is VectorDrawableCompat
    }

    fun getDimension(typedArray: TypedArray, styleable:Int, defaultVal:Float):Float{
        return when {
            typedArray.hasValue(styleable) -> {
                typedArray.getDimension(styleable, defaultVal)
            }
            else -> {
                defaultVal
            }
        }
    }

    fun getResourceId(typedArray: TypedArray, styleable:Int, defaultVal:Int):Int{
        return when {
            typedArray.hasValue(styleable) -> {
                typedArray.getResourceId(styleable, defaultVal)
            }
            else -> {
                defaultVal
            }
        }
    }

    fun getBoolean(typedArray: TypedArray, styleable:Int, defaultVal:Boolean):Boolean{
        return when {
            typedArray.hasValue(styleable) -> {
                typedArray.getBoolean(styleable, defaultVal)
            }
            else -> {
                defaultVal
            }
        }
    }

    fun getInteger(typedArray: TypedArray, styleable:Int, defaultVal:Int):Int{
        return when {
            typedArray.hasValue(styleable) -> {
                typedArray.getInteger(styleable, defaultVal)
            }
            else -> {
                defaultVal
            }
        }
    }

    fun getInt(typedArray: TypedArray, styleable:Int, defaultVal:Int = 0):Int{
        return when {
            typedArray.hasValue(styleable) -> {
                typedArray.getInt(styleable, defaultVal)
            }
            else -> {
                defaultVal
            }
        }
    }

    fun getString(typedArray: TypedArray, styleable:Int):String?{
        return when {
            typedArray.hasValue(styleable) -> {
                typedArray.getString(styleable)
            }
            else -> {
                return null
            }
        }
    }

    fun getColorStateList(typedArray: TypedArray, styleable:Int): ColorStateList?{
        return when {
            typedArray.hasValue(styleable) -> {
                typedArray.getColorStateList(styleable)
            }
            else -> {
                return null
            }
        }
    }
    //////////////////////////////////////////////////////////////////////

    private fun checkExistFile(path:String?):Boolean{
        if (path != null && path.isNotEmpty()){
            val file = File(path)
            if (file.isFile && file.exists())
                return true
        }
        return false
    }

    private fun formatFileUrl(fileName: String?):String{
        if (fileName == null || fileName.isEmpty())
            return ""
        val host = Util.getHost()
        val sub = Util.getSubApi()
        val subFile = Util.getSubFile()

        var url = mergeUrl(host, sub)
        url = mergeUrl(url, subFile)
        url = mergeUrl(url, fileName)

        return url
    }

    private fun mergeUrl(line1: String, line2: String): String {
        var tempLine1 = line1
        var tempLine2 = line2
        ////
        if (tempLine1.endsWith('/') ){
            tempLine1 = tempLine1.substring(0, tempLine1.length - 1)
        }
        if (tempLine2.startsWith('/') ){
            tempLine2 = tempLine2.substring(1)
        }
        ///
        return "$tempLine1/$tempLine2"
    }
}