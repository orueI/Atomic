package com.example.atomic.utils

import android.util.Log
import com.example.atomic.view.MapView
import com.example.atomic.view.MapView.Companion.sideOfSquare
import com.example.atomic.view.MapView.Companion.widthOfFields

fun l(s:String){
    Log.v("MyTag",s)
}

fun Int.toGlobalCoordinate(): Int =
     this * (widthOfFields + sideOfSquare) + widthOfFields