package com.example.atomic.controler.typeAtom

import com.example.atomic.controler.Direction
import com.example.atomic.controler.Vector
import com.example.atomic.controler.Wall
import com.example.atomic.controler.XY
import com.example.atomic.data.CurrentMap
import com.example.atomic.interfaces.InterfaceMapView
import com.example.atomic.utils.ArrayListCustom
import com.example.atomic.utils.l

class ActiveAtom {
    var view: InterfaceMapView

    constructor(view: InterfaceMapView) {
        this.view = view
    }

    fun clickOnVector(vector: Vector) {
        val map = CurrentMap.getCurrentMap()
        when (vector.vector.name){
            "right" -> {
                map.listAtoms.update(vector.atom)?.xy?.x = map.getFirstNoNPassabilityCAll(vector.atom.xy,1,0).x-1
            }
            "left" -> {
                map.listAtoms.update(vector.atom)?.xy?.x = map.getFirstNoNPassabilityCAll(vector.atom.xy,-1,0).x+1
            }
            "top" -> {
                map.listAtoms.update(vector.atom)?.xy?.y = map.getFirstNoNPassabilityCAll(vector.atom.xy,0,1).y-1
            }
            "dawn" -> {
                map.listAtoms.update(vector.atom)?.xy?.y = map.getFirstNoNPassabilityCAll(vector.atom.xy,0,-1).y+1
            }
        }
        map.listVector = ArrayListCustom(map)
        view.render()
    }
}