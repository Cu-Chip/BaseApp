package com.apcc.view

import android.widget.TextView
import androidx.core.widget.TextViewCompat
import com.apcc.R
import com.apcc.utils.ResourceHelper
import com.apcc.utils.TextType

interface TextInterface {
    fun getTextView(): TextView
    fun refreshText()

    fun setTextType(@TextType textType: Int, requestColorResource: Int) {
        when (textType) {
            /*normal*/
            TextType.TEXT_HTML -> {
                refreshText() // reset text to make effect change
            }
            TextType.TEXT_BIG -> {
                TextViewCompat.setTextAppearance(getTextView(), android.R.style.TextAppearance_Large)
                setTextColorResource(R.color.txt_tint, requestColorResource)
            }
            TextType.TEXT_HUGE -> {
                TextViewCompat.setTextAppearance(getTextView(), android.R.style.TextAppearance_Material_Display4)
                setTextColorResource(R.color.txt_tint, requestColorResource)
            }
            TextType.TEXT_SMALL -> {
                TextViewCompat.setTextAppearance(getTextView(), android.R.style.TextAppearance_Small)
                setTextColorResource(R.color.txt_tint, requestColorResource)
            }
            /*accent*/
            TextType.TEXT_NORMAL_ACCENT -> {
                TextViewCompat.setTextAppearance(getTextView(), android.R.style.TextAppearance_Medium)
                setTextColorResource(R.color.colorAccent, requestColorResource)
            }
            TextType.TEXT_BIG_ACCENT -> {
                TextViewCompat.setTextAppearance(getTextView(), android.R.style.TextAppearance_Large)
                setTextColorResource(R.color.colorAccent, requestColorResource)
            }
            TextType.TEXT_HUGE_ACCENT -> {
                TextViewCompat.setTextAppearance(getTextView(), android.R.style.TextAppearance_Material_Display4)
                setTextColorResource(R.color.colorAccent, requestColorResource)
            }
            TextType.TEXT_SMALL_ACCENT -> {
                TextViewCompat.setTextAppearance(getTextView(), android.R.style.TextAppearance_Small)
                setTextColorResource(R.color.colorAccent, requestColorResource)
            }
            /*success*/
            TextType.TEXT_NORMAL_SUCCESS -> {
                TextViewCompat.setTextAppearance(getTextView(), android.R.style.TextAppearance_Medium)
                setTextColorResource(R.color.colorSuccess, requestColorResource)
            }
            TextType.TEXT_BIG_SUCCESS -> {
                TextViewCompat.setTextAppearance(getTextView(), android.R.style.TextAppearance_Large)
                setTextColorResource(R.color.colorSuccess, requestColorResource)
            }
            TextType.TEXT_HUGE_SUCCESS -> {
                TextViewCompat.setTextAppearance(getTextView(), android.R.style.TextAppearance_Material_Display4)
                setTextColorResource(R.color.colorSuccess, requestColorResource)
            }
            TextType.TEXT_SMALL_SUCCESS -> {
                TextViewCompat.setTextAppearance(getTextView(), android.R.style.TextAppearance_Small)
                setTextColorResource(R.color.colorSuccess, requestColorResource)
            }
            /*important*/
            TextType.TEXT_NORMAL_IMPORTANT -> {
                TextViewCompat.setTextAppearance(getTextView(), android.R.style.TextAppearance_Medium)
                setTextColorResource(R.color.colorImportant, requestColorResource)
            }
            TextType.TEXT_BIG_IMPORTANT -> {
                TextViewCompat.setTextAppearance(getTextView(), android.R.style.TextAppearance_Large)
                setTextColorResource(R.color.colorImportant, requestColorResource)
            }
            TextType.TEXT_HUGE_IMPORTANT -> {
                TextViewCompat.setTextAppearance(getTextView(), android.R.style.TextAppearance_Material_Display4)
                setTextColorResource(R.color.colorImportant, requestColorResource)
            }
            TextType.TEXT_SMALL_IMPORTANT -> {
                TextViewCompat.setTextAppearance(getTextView(), android.R.style.TextAppearance_Small)
                setTextColorResource(R.color.colorImportant, requestColorResource)
            }
            /*disable*/
            TextType.TEXT_NORMAL_DISABLE -> {
                TextViewCompat.setTextAppearance(getTextView(), android.R.style.TextAppearance_Medium)
                setTextColorResource(R.color.colorDisable, requestColorResource)
            }
            TextType.TEXT_BIG_DISABLE -> {
                TextViewCompat.setTextAppearance(getTextView(), android.R.style.TextAppearance_Large)
                setTextColorResource(R.color.colorDisable, requestColorResource)
            }
            TextType.TEXT_HUGE_DISABLE -> {
                TextViewCompat.setTextAppearance(getTextView(), android.R.style.TextAppearance_Material_Display4)
                setTextColorResource(R.color.colorDisable, requestColorResource)
            }
            TextType.TEXT_SMALL_DISABLE -> {
                TextViewCompat.setTextAppearance(getTextView(), android.R.style.TextAppearance_Small)
                setTextColorResource(R.color.colorDisable, requestColorResource)
            }
            else -> {
                TextViewCompat.setTextAppearance(getTextView(), android.R.style.TextAppearance_Medium)
                setTextColorResource(R.color.txt_tint, requestColorResource)
            }
        }
    }

    fun setTextColorResource(colorResourceDefault: Int, requestColorResource:Int) {
        setTextColorResource(if (requestColorResource == 0) colorResourceDefault else requestColorResource )
    }
    fun setTextColorResource(colorResource: Int) {
        getTextView().setTextColor(ResourceHelper.getColor(getTextView().context, colorResource))
    }

    fun setTextHintColorResource(colorResource: Int){
        getTextView().setHintTextColor(ResourceHelper.getColor(getTextView().context, colorResource))
    }

}

