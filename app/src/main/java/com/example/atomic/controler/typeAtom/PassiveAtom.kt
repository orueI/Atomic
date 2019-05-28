package com.example.atomic.controler.typeAtom

import com.example.atomic.controler.*
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
        val map = CurrentMap.getCurrentMap()
        map.listVector.clear()

        val leftElement = XY(atom.xy.x-1,atom.xy.y)
        val rightElement = XY(atom.xy.x+1,atom.xy.y)
        val topElement = XY(atom.xy.x,atom.xy.y+1)
        val downElement = XY(atom.xy.x,atom.xy.y-1)

        if (map.isPassability(leftElement))
            CurrentMap.getCurrentMap().listVector.add(Vector(atom, Direction.left, 12,
                leftElement
//            XY(1,5)
            ))

        if (map.isPassability(rightElement))
            CurrentMap.getCurrentMap().listVector.add(Vector(atom, Direction.right, 12,
                rightElement
//            XY(3,5)
            ))

        if (map.isPassability(topElement))
            CurrentMap.getCurrentMap().listVector.add(Vector(atom, Direction.top, 12,
                topElement
//            XY(2,4)
            ))
        if (map.isPassability(downElement))
            CurrentMap.getCurrentMap().listVector.add(Vector(atom, Direction.dawn, 12,
                downElement
//            XY(2,6)
            ))

view.render()
    }

}