package com.example.atomic.controler

import com.example.atomic.XY
import com.example.atomic.controler.typeAtom.ActiveAtom
import com.example.atomic.controler.typeAtom.PassiveAtom
import com.example.atomic.data.CurrentMap
import com.example.atomic.interfaces.InterfaceLogicClicks
import com.example.atomic.interfaces.InterfaceMapView
import com.example.atomic.ui.MapView.Companion.sideOfSquare
import com.example.atomic.ui.MapView.Companion.widthOfFields
import com.example.atomic.utils.fromGlobal

class LogicClicks: InterfaceLogicClicks {

    var activeAtom:ActiveAtom
    var passiveAtom:PassiveAtom

    constructor(v: InterfaceMapView) {
        this.activeAtom = ActiveAtom(v)
        this.passiveAtom = PassiveAtom(v)
    }


    override fun onClick(xy: XY) {
        val (map, width, height) = fromGlobal(xy)
        val listAtoms = map.listAtoms
        val listVectors = map.listVector
        listAtoms.map {
            if (it.xy.equals(XY(width, height)))
                passiveAtom.clickOnAtom(it)
        }
        listVectors.map {
            if (it.xy.equals(XY(width, height)))
                activeAtom.clickOnVector(it)
        }
    }
}