package com.example.atomic.utils

import android.util.Log
import com.example.atomic.controler.XY
import com.example.atomic.view.MapView
import com.example.atomic.view.MapView.Companion.sideOfSquare
import com.example.atomic.view.MapView.Companion.widthOfFields

fun l(s: String) {
    Log.v("MyTag", s)
}

fun Int.toGlobalCoordinate(): Int =
    this * (widthOfFields + sideOfSquare) + widthOfFields

fun XY.toGlobalCoordinateXY(): XY =
    XY(x.toGlobalCoordinate(), y.toGlobalCoordinate())


//val fifteenFalse =
//    false, false, false, false, false, false, false, false, false, false, false, false, false, false, false,
//val fifteenTrue =
//    true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,

// sevenFalse = false, false, false,false, false, false,false,
// sevenTrue = true, true, true,true, true, true,true,