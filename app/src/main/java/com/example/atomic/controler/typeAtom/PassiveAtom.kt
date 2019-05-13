package com.example.atomic.controler.typeAtom

import com.example.atomic.controler.Atom
import com.example.atomic.controler.Vector
import com.example.atomic.controler.Wall
import com.example.atomic.controler.XY
import com.example.atomic.data.CurrentMap
import com.example.atomic.interfaces.InterfaceMapView
import com.example.atomic.utils.ArrayListCustom
import com.example.atomic.utils.l

class PassiveAtom {
    private lateinit var view: InterfaceMapView

    constructor(view: InterfaceMapView) {
        this.view = view
    }

    fun clickOnAtom(atom: Atom) {
        CurrentMap.getCurrentMap().listVector= ArrayListCustom(null)
        val list = CurrentMap.getCurrentMap().getLayerPassability()
        l("clickOnAtom PassiveAtom")

        val leftElement = list[atom.xy.y][atom.xy.x-1]
        val rightElement = list[atom.xy.y][atom.xy.x+1]
        val topElement = list[atom.xy.y+1][atom.xy.x]
        val downElement = list[atom.xy.y-1][atom.xy.x]

        if (leftElement?.passability == true)
            CurrentMap.getCurrentMap().listVector.add(Vector(atom, 2, 12, leftElement.xy))

        if (rightElement?.passability == true)
            CurrentMap.getCurrentMap().listVector.add(Vector(atom, 0, 12, rightElement.xy))

        if (topElement?.passability == true)
            CurrentMap.getCurrentMap().listVector.add(Vector(atom, 0, 12, topElement.xy))
        if (downElement?.passability == true)
            CurrentMap.getCurrentMap().listVector.add(Vector(atom, 0, 12, downElement.xy))

//view.render()
    }

}