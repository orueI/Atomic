package com.example.atomic.controler.typeAtom

import com.example.atomic.controler.Vector
import com.example.atomic.data.CurrentMap
import com.example.atomic.interfaces.InterfaceMapView

class ActiveAtom {
    var view: InterfaceMapView

    constructor(view: InterfaceMapView) {
        this.view = view
    }

    fun clickOnVector(vector: Vector):Boolean {
        val map = CurrentMap.getCurrentMap()
        val list = CurrentMap.getCurrentMap().getLayerPassability()
        if (vector.vector % 2 == 0) {
            val x = 1..map.wh.x
            val k = if (vector.vector==0)1 else-1
            for (i in x) {
                if (list[vector.xy.y][i*k].passability==false) {
                    map.listAtoms.update(vector.atom)?.xy?.x = i - 1
                    return true
                }
            }
        } else {
            val y = 1..map.wh.y
            val k = if (vector.vector==1)1 else-1
            for (i in y) {
                if (list[i][vector.xy.x*k].passability==false){
                    map.listAtoms.update(vector.atom)?.xy?.y = i-1
                    return true
                }

            }
        }
        return false
    }
}