package com.apcc.baseapp

import android.graphics.*
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.apcc.view.XButton
import com.apcc.view.XCrop
import com.apcc.view.XImage


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val crop = findViewById<XCrop>(R.id.crop)
        val image = findViewById<XImage>(R.id.imv)

        crop.initInfo()

        findViewById<XButton>(R.id.btnCheck).setOnClickListener {
            testCrop(crop, image)
        }
        findViewById<XButton>(R.id.btnReset).setOnClickListener {
            image.setImageResource(R.drawable.img_edit_example)
        }

    }

    fun testCrop(crop:XCrop, image:XImage){
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.img_edit_example)
        // using w/h
        val ratioB = bitmap.width * 1f / bitmap.height
        val ratioC = crop.width * 1f / crop.height
        var ratio = 1f
        var marginX = 0f
        var marginY = 0f
        if (ratioB > ratioC){ // margin h
            ratio = bitmap.width * 1f / crop.width
            marginY = (crop.height - bitmap.height / ratio) / 2f
        }else{ // margin w
            ratio = bitmap.height * 1f / crop.height
            marginX = (crop.width - bitmap.width / ratio) / 2f
        }


        val path = Path()
        var startX = -1f
        var startY = -1f

        var left = -1f
        var top = -1f
        var right = -1f
        var bot = -1f

        for (point in crop.mListPoint) {
            val px = (point.x - marginX ) * ratio
            val py =  (point.y - marginY ) * ratio
            if (startX == -1f && startY == -1f){
                startX = px
                startY =py
                path.moveTo(startX, startY)
            }else
                path.lineTo(px, py)

            if (left == -1f || left > px){
                left = px
            }
            if (right == -1f || right < px){
                right = px
            }
            if (top == -1f || top > py){
                top = py
            }
            if (bot == -1f || bot < py){
                bot = py
            }

        }
        //path.lineTo(startX, startY)


        val redrawImage = Bitmap.createBitmap(bitmap.width, bitmap.height, bitmap.config)
//        val redrawImage = Bitmap.createBitmap((right - left).toInt(), (bot- top).toInt(), bitmap.config)
        val paint = Paint()
        paint.isAntiAlias = true
        val canvas = Canvas(redrawImage)
        canvas.drawPath(path, paint)

        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(bitmap, 0f, 0f, paint)

//        image.setImageBitmap(redrawImage)

        crop.setImageBitmap(redrawImage)

//        val imgResult = Bitmap.createBitmap(redrawImage, left.toInt(), top.toInt(), (right-left).toInt(), (bot - top).toInt())
//        image.setImageBitmap(imgResult)
    }
}