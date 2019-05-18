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
        CurrentMap.getCurrentMap().listVector.clear()
        val map = CurrentMap.getCurrentMap()
        l("clickOnAtom PassiveAtom")

        val leftElement = XY(atom.xy.y,atom.xy.x-1)
        val rightElement = XY(atom.xy.y,atom.xy.x+1)
        val topElement = XY(atom.xy.y+1,atom.xy.x)
        val downElement = XY(atom.xy.y-1,atom.xy.x)

        if (map.isPassability(leftElement))
            CurrentMap.getCurrentMap().listVector.add(Vector(atom, 2, 12,
                leftElement
//            XY(1,5)
            ))

        if (map.isPassability(rightElement))
            CurrentMap.getCurrentMap().listVector.add(Vector(atom, 0, 12,
                rightElement
//            XY(3,5)
            ))

        if (map.isPassability(topElement))
            CurrentMap.getCurrentMap().listVector.add(Vector(atom, 0, 12,
                topElement
//            XY(2,4)
            ))
        if (map.isPassability(downElement))
            CurrentMap.getCurrentMap().listVector.add(Vector(atom, 0, 12,
                downElement
//            XY(2,6)
            ))

view.render()
    }

}