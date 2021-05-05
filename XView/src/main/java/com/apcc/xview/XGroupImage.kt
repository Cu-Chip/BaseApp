package com.apcc.xview

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.apcc.xview.utils.ViewHelper
import kotlin.math.min

@SuppressLint("AppCompatCustomView")
class GroupImage: ConstraintLayout, ViewInterface {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?)
            : super(context, attrs){
        parseAttributes(context, attrs)
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr){
        parseAttributes(context, attrs)
    }

    companion object{

    }

    lateinit var txtMore:XTextView
    lateinit var btnAddImage:XButton
    lateinit var ic1:XIcon
    lateinit var ic2:XIcon
    lateinit var ic3:XIcon
    lateinit var ic4:XIcon
    lateinit var ic5:XIcon
    lateinit var ic6:XIcon
    lateinit var ic7:XIcon
    lateinit var ic8:XIcon
    lateinit var ic9:XIcon
    lateinit var ic10:XIcon
    lateinit var ic11:XIcon
    lateinit var ic12:XIcon
    private var mListener:Listener? = null

    private var mImageList:MutableList<String> = ArrayList()
    private var mEnable = true

    //////////////////////////////////////////////////
    /// system
    //////////////////////////////////////////////////

    override val mStyleableID: IntArray = R.styleable.XGroupImage
    override val mLayoutID: Int = R.layout.view_group_image
    override val mViewGroup: ViewGroup = this

    override fun extraTypes(typedArray: TypedArray) {
        mEnable = ViewHelper.getBoolean(typedArray, R.styleable.XGroupImage_android_enabled, true)
    }

    override fun initView(root: View) {
        txtMore = root.findViewById(R.id.txtMore)
        btnAddImage = root.findViewById(R.id.btnAddImage)
        ic1 = root.findViewById(R.id.ic1)
        ic2 = root.findViewById(R.id.ic2)
        ic3 = root.findViewById(R.id.ic3)
        ic4 = root.findViewById(R.id.ic4)
        ic5 = root.findViewById(R.id.ic5)
        ic6 = root.findViewById(R.id.ic6)
        ic7 = root.findViewById(R.id.ic7)
        ic8 = root.findViewById(R.id.ic8)
        ic9 = root.findViewById(R.id.ic9)
        ic10 = root.findViewById(R.id.ic10)
        ic11 = root.findViewById(R.id.ic11)
        ic12 = root.findViewById(R.id.ic12)

        txtMore.setOnClickListener { mListener?.onImageClick(-1) }
        btnAddImage.setOnClickListener { mListener?.onAddImageClick() }
        ic1.setOnClickListener { mListener?.onImageClick(0) }
        ic2.setOnClickListener { mListener?.onImageClick(1) }
        ic3.setOnClickListener { mListener?.onImageClick(2) }
        ic4.setOnClickListener { mListener?.onImageClick(3) }
        ic5.setOnClickListener { mListener?.onImageClick(4) }
        ic6.setOnClickListener { mListener?.onImageClick(5) }
        ic7.setOnClickListener { mListener?.onImageClick(6) }
        ic8.setOnClickListener { mListener?.onImageClick(7) }
        ic9.setOnClickListener { mListener?.onImageClick(8) }
        ic10.setOnClickListener { mListener?.onImageClick(9) }
        ic11.setOnClickListener { mListener?.onImageClick(10) }
        ic12.setOnClickListener { mListener?.onImageClick(11) }
    }

    override fun updateView() {
        setEnable(mEnable)
    }

    fun setListener(listener: Listener){
        mListener = listener
    }

    //////////////////////////////////////////////////
    /// function support
    //////////////////////////////////////////////////

    fun setImages(list: List<String>){
        mImageList.clear()
        mImageList.addAll(list)
        autoShow()
    }

    fun getImages():MutableList<String>{
        return mImageList
    }

    /**
     * @param index: default is -1, that mean insert to last
     * image as link
     */
    fun addImage(image: String?, index: Int = -1){
        image?.let {
            if (index == -1)
                mImageList.add(image)
            else
                mImageList.add(index, image)
            autoShow()
        }
    }

    /**
     * @param index: default is -1, that mean insert to last
     */
    fun addImage(images: Collection<String>?, index: Int = -1){
        images?.let {
            if (index == -1)
                mImageList.addAll(images)
            else
                mImageList.addAll(index, images)
            autoShow()
        }
    }

    fun getImageIndex(index: Int):String?{
        if (mImageList.size > index)
            return mImageList[index]
        return null
    }

    fun removeImage(image: String?){
        image?.let {
            mImageList.remove(image)
            autoShow()
        }
    }

    fun removeImage(index:Int){
        if (index >=0 && index < mImageList.size){
            mImageList.removeAt(index)
            autoShow()
        }
    }

    fun removeImage(images: Collection<String>?){
        images?.let {
            for (image in it){
                mImageList.remove(image)
            }
        }
        autoShow()
    }

    private fun autoShow(){
        // icAddImage is the first
        val canShow = min(min((width - btnAddImage.width) / ic1.width, 12),mImageList.size) // 12 total icon view

        showImage(ic1, 0 , canShow)
        showImage(ic2,1, canShow)
        showImage(ic3,2, canShow)
        showImage(ic4,3 , canShow)
        showImage(ic5,4 , canShow)
        showImage(ic6,5 , canShow)
        showImage(ic7,6 , canShow)
        showImage(ic8,7 , canShow)
        showImage(ic9,8 , canShow)
        showImage(ic10,9, canShow)
        showImage(ic11,10, canShow)
        showImage(ic12,11, canShow)
        txtMore.visibility = if (canShow < mImageList.size) VISIBLE else GONE
        txtMore.text = "${mImageList.size - canShow}+"
    }
    private fun showImage(ic:XIcon, index:Int, canShow:Int){
        ic.visibility = if (canShow > index) VISIBLE else GONE
        ic.loadByPath(getImageIndex(index), R.drawable.ic_img)
    }

    fun setEnable(enable:Boolean){
        mEnable = enable
        txtMore.isEnabled = enable
        btnAddImage.isEnabled = enable
        ic1.isEnabled = enable
        ic2.isEnabled = enable
        ic3.isEnabled = enable
        ic4.isEnabled = enable
        ic5.isEnabled = enable
        ic6.isEnabled = enable
        ic7.isEnabled = enable
        ic8.isEnabled = enable
        ic9.isEnabled = enable
        ic10.isEnabled = enable
        ic11.isEnabled = enable
        ic12.isEnabled = enable
    }

    //////////////////////////////////////////////////
    ///
    //////////////////////////////////////////////////
    interface Listener{
        fun onImageClick(index:Int)
        fun onAddImageClick()
    }

}