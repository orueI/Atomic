package com.example.atomic.controler

import android.view.View
import com.example.atomic.controler.typeAtom.ActiveAtom
import com.example.atomic.controler.typeAtom.PassiveAtom
import com.example.atomic.data.CurrentMap
import com.example.atomic.interfaces.InterfaceLogicClicks
import com.example.atomic.interfaces.InterfaceMapView
import com.example.atomic.utils.l
import com.example.atomic.view.MapView
import com.example.atomic.view.MapView.Companion.sideOfSquare
import com.example.atomic.view.MapView.Companion.widthOfFields

class LogicClicks: InterfaceLogicClicks {

    var activeAtom:ActiveAtom
    var passiveAtom:PassiveAtom

    constructor(v: InterfaceMapView) {
        this.activeAtom = ActiveAtom(v)
        this.passiveAtom = PassiveAtom(v)
    }


    override fun onClick(xy:XY) {
        val (map, width, height) = fromGlobal(xy)
        val listAtoms = map.listAtoms
        val listVectors = map.listVector
        listAtoms.map {
            if (it.xy.equals(XY(width,height)))
                passiveAtom.clickOnAtom(it)
        }
        listVectors.map {
            if (it.xy.equals(XY(width,height)))
                activeAtom.clickOnVector(it)
        }

//        val atom :Atom= map.listAtoms.find { it.xy.equals(XY(width,height)) }?:return
//            passiveAtom.clickOnAtom(atom)

    }

    private fun fromGlobal(
        xy: XY
    ): Triple<CurrentMap, Int, Int> {
        val map = CurrentMap.getCurrentMap()
        val sAndW = sideOfSquare + widthOfFields
        val width = xy.x / sAndW
        val height = xy.y / sAndW
        return Triple(map, width, height)
    }
}