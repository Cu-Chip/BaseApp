package com.apcc.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.widget.RadioButton
import android.widget.TextView
import com.apcc.utils.FormatHelper
import com.apcc.utils.TextType
import com.apcc.utils.ViewHelper
import com.apcc.R

@SuppressLint("AppCompatCustomView")
class XRadioButton:RadioButton, ViewInterface, TextInterface {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?)
            : super(context, attrs){
        parseAttributes(context, attrs)
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr){
        parseAttributes(context, attrs)
    }

    constructor(context: Context?,attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes){
        parseAttributes(context, attrs)
    }

    companion object{

    }


    private var mTextType:Int = TextType.TEXT_NORMAL
    private var mTextColorResource:Int = TextType.TEXT_NORMAL

    //////////////////////////////////////////////////
    /// system
    //////////////////////////////////////////////////

    override val mStyleableID: IntArray = R.styleable.XCheckBox

    override fun extraTypes(typedArray: TypedArray) {
        mTextType =ViewHelper.getInteger( typedArray, R.styleable.XCheckBox_textType, TextType.TEXT_NORMAL)
        mTextColorResource =ViewHelper.getResourceId( typedArray, R.styleable.XCheckBox_android_textColor, R.color.txt_tint)
    }

    override fun updateView() {
        setTextType(mTextType,mTextColorResource)
    }

    //////////////////////////////////////////////////
    /// function support
    //////////////////////////////////////////////////

    override fun setText(text: CharSequence, type:BufferType){
        when(mTextType){
            TextType.TEXT_HTML -> super.setText(FormatHelper.textToHtml(text.toString()), type)
            else -> super.setText(text, type)
        }
    }


    override fun getTextView(): TextView = this

    override fun refreshText() {
        this.text = text
    }

    override fun setTextType(@TextType textType: Int, requestColorResource: Int){
        mTextType = textType
        super.setTextType(textType, requestColorResource)
    }

    override fun setTextColorResource(colorResource: Int) {
        mTextColorResource = colorResource
        super.setTextColorResource(colorResource)
    }
}