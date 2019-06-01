package com.example.atomic.utils

import android.util.Log
import com.example.atomic.XY
import com.example.atomic.view.MapView.Companion.sideOfSquare
import com.example.atomic.view.MapView.Companion.widthOfFields

fun l(s: String) {
    Log.v("MyTag", s)
}

fun Int.toGlobalCoordinate(): Int =
    this * (widthOfFields + sideOfSquare) + widthOfFields

fun XY.toGlobalCoordinateXY(): XY =
    XY(x.toGlobalCoordinate(), y.toGlobalCoordinate())

fun getArray(): ArrayList<ArrayList<Boolean>> {
    val array: ArrayList<ArrayList<Boolean>> = arrayListOf(
        arrayListOf(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true),
        arrayListOf(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true),
        arrayListOf(
            true,
            true,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            true,
            true
        ),
        arrayListOf(true, true, false, true, true, true, true, true, true, true, true, true, false, true, true),
        arrayListOf(true, true, false, true, false, false, true, true, true, false, false, true, false, true, true),
        arrayListOf(true, true, false, true, true, true, true, true, true, true, true, true, false, true, true),
        arrayListOf(true, true, false, true, false, false, false, true, false, false, false, true, false, true, true),
        arrayListOf(true, true, false, true, true, true, true, true, true, true, true, true, false, true, true),
        arrayListOf(true, true, false, true, false, false, false, true, false, false, false, true, false, true, true),
        arrayListOf(true, true, false, true, true, true, true, true, true, true, true, true, false, true, true),
        arrayListOf(true, true, false, true, false, false, true, true, true, false, false, true, false, true, true),
        arrayListOf(true, true, false, true, true, true, true, true, true, true, true, true, false, true, true),
        arrayListOf(
            true,
            true,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            true,
            true
        ),
        arrayListOf(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true),
        arrayListOf(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true)
    )

    return array
}
//val fifteenFalse =
//    false, false, false, false, false, false, false, false, false, false, false, false, false, false, false,
//val fifteenTrue =
//    true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,

// sevenFalse = false, false, false,false, false, false,false,
// sevenTrue = true, true, true,true, true, true,true,