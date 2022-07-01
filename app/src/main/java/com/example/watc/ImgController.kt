package com.example.watc

import android.app.Activity
import android.content.Intent

object ImgController {
    fun selectPhotofromGallery(activity: Activity, code: Int){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type="image/*"
        activity.startActivityForResult(intent,code)
    }
}