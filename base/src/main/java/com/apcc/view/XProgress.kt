package com.apcc.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.text.TextUtils
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import com.apcc.utils.ViewHelper
import com.apcc.R

@SuppressLint("AppCompatCustomView")
class XProgress: ConstraintLayout, ViewInterface {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?)
            : super(context, attrs){
        parseAttributes(context, attrs)
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr){
        parseAttributes(context, attrs)
    }



    lateinit var lblDescription:XTextView
    lateinit var pgbProgress: ProgressBar
    lateinit var pgbProgressH: ProgressBar

    private var mText:String? = ""
    private var mProgress:Int = 0


    //////////////////////////////////////////////////
    /// system
    //////////////////////////////////////////////////

    override val mStyleableID: IntArray = R.styleable.XProgress
    override val mLayoutID: Int = R.layout.view_progress
    override val mViewGroup: ViewGroup = this

    override fun extraTypes(typedArray: TypedArray) {
        mText = ViewHelper.getString(typedArray, R.styleable.XProgress_android_text)
        mProgress = ViewHelper.getInt(typedArray, R.styleable.XProgress_android_progress)
    }

    override fun initView(root: View) {
        lblDescription = root.findViewById(R.id.apccDescription)
        pgbProgress = root.findViewById(R.id.apccProgress)
        pgbProgressH = root.findViewById(R.id.apccProgressH)
    }

    override fun updateView() {
        setText(mText)
        setProgress(mProgress)
    }

    //////////////////////////////////////////////////
    /// function support
    //////////////////////////////////////////////////

    fun setText(textContent:String?){
        mText = if (TextUtils.isEmpty(textContent)) "" else textContent
        lblDescription.text = mText

        if (TextUtils.isEmpty(textContent))
            lblDescription.visibility = View.GONE
        else
            lblDescription.visibility = View.VISIBLE
    }

    fun setProgress(progress:Int){
        mProgress = progress
        pgbProgress.visibility = if (mProgress > 0) GONE else VISIBLE
        pgbProgressH.visibility = if (mProgress > 0) VISIBLE else GONE
        pgbProgressH.max = 100
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N)
            pgbProgressH.setProgress(mProgress, true)
        else
            pgbProgressH.progress = progress

    }

    //////////////////////////////////////////////////
    /// function support
    //////////////////////////////////////////////////

}