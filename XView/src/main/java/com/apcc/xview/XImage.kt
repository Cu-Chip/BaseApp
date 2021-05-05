package com.apcc.xview

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.text.TextUtils
import android.util.AttributeSet
import com.apcc.xview.photoView.PhotoView
import com.apcc.xview.utils.ResourceHelper
import com.apcc.xview.utils.ViewHelper


/**
 * using for show image detail
 * Image can zoom
 */
@SuppressLint("AppCompatCustomView")
class XImage : PhotoView, ViewInterface {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        parseAttributes(context, attrs)
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        parseAttributes(context, attrs)
    }

    private var mRadius = 0.0f
    private var mPath:Path? = Path()
    private var mImagePath:String? = ""
    private var mImageDefault:Int = 0
    private var mAllowCache = false
    private var mTint: ColorStateList? = null

    //////////////////////////////////////////////////
    /// system
    //////////////////////////////////////////////////
    override val mStyleableID: IntArray = R.styleable.XImage

    override fun extraTypes(typedArray: TypedArray) {
        mRadius = ViewHelper.getDimension( typedArray, R.styleable.XImage_cornerRadius, 0f)
        mImagePath = ViewHelper.getString( typedArray, R.styleable.XImage_imagePath)
        mImageDefault = ViewHelper.getResourceId( typedArray, R.styleable.XImage_imageDefault,0)
        mAllowCache = ViewHelper.getBoolean( typedArray, R.styleable.XImage_allowCache,false)
        mTint = ViewHelper.getColorStateList( typedArray, R.styleable.XImage_android_tint)
    }

    override fun updateView() {
        setScaleLevels(1f, 2f, 4f)
        loadByPath(mImagePath)
        imageTintList = mTint
    }


    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        if (mPath != null && mRadius >0){
            val rect = RectF(0f, 0f, this.width.toFloat(), this.height.toFloat())
            mPath!!.addRoundRect(rect, mRadius, mRadius, Path.Direction.CW)
            canvas.clipPath(mPath!!)
        }
        super.onDraw(canvas)
    }

    //////////////////////////////////////////////////
    /// function support
    //////////////////////////////////////////////////
    /**
     * default image: R.drawable.ic_img
     */
    fun loadByPath(path:String?, defaultImg:Int = 0){
        mImagePath = path
        if(defaultImg != 0){
            mImageDefault = defaultImg
        }
        if (TextUtils.isEmpty(path)){
            if(mImageDefault > 0){
                setImageResource(mImageDefault)
            }
        }else{
            ViewHelper.loadImage(path, mImageDefault, mAllowCache)?.into(this)?:setImageDefault(mImageDefault)
        }
    }

    fun setImageDefault(defaultImg:Int){
        mImageDefault = defaultImg
        if (TextUtils.isEmpty(mImagePath)
            && mImageDefault > 0){
            setImageResource(mImageDefault)
        }
    }

    /**
     * using float as pixel
     */
    fun setCornerRadius(radius:Float){
        mRadius = radius
    }

    /**
     * using dimen to set
     */
    fun setCornerRadius(radiusSource:Int){
        context?.let {
            mRadius = ResourceHelper.getDimension(context, radiusSource)
        }
    }



}