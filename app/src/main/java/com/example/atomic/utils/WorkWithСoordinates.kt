package com.example.atomic.utils

import com.example.atomic.XY
import com.example.atomic.data.CurrentMap
import com.example.atomic.ui.MapView

fun Int.toGlobalCoordinate(): Int =
    this * (MapView.widthOfFields + MapView.sideOfSquare) + MapView.widthOfFields

fun XY.toGlobalCoordinateXY(): XY =
    XY(x.toGlobalCoordinate(), y.toGlobalCoordinate())

fun fromGlobal(
    xy: XY
): Triple<CurrentMap, Int, Int> {
    val map = CurrentMap.getCurrentMap()
    val sAndW = MapView.sideOfSquare + MapView.widthOfFields
    val width = xy.x / sAndW
    val height = xy.y / sAndW
    return Triple(map, width, height)
}