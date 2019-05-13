package com.example.atomic.interfaces

import android.view.View
import com.example.atomic.controler.XY
import com.example.atomic.view.MapView

interface InterfaceLogicClicks {
    fun onClick(xy:XY,view: MapView)
}