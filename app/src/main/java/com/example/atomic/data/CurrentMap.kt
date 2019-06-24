package com.example.atomic.data

import com.example.atomic.*
import com.example.atomic.Map
import kotlin.collections.ArrayList

class CurrentMap : Map {
    private constructor(
        listPassibility: ArrayList<ArrayList<Boolean>> = ArrayList(),
        listAtoms: ArrayList<Atom> = ArrayList(),
        listResultAtoms: ArrayList<Atom> = ArrayList(),
        wh: XY = XY(0,0)
    ) : super(listPassibility, listAtoms, listResultAtoms,wh)


    var listVector: ArrayList<Vector> = ArrayList()
    var numLevel: Int = 1


    fun isPassability(xy: XY): Boolean =
        !(!listPassibility[xy.y][xy.x] || listAtoms.find { it.xy.equals(xy) } != null)

    fun getFirstNoNPassabilityCAll(xy: XY, x: Int = 0, y: Int = 0): XY {
        for (i in 1..wh.x) {
            val iXY = XY(xy.x + i * x, xy.y + i * y)
            if (!isPassability(iXY))
                return iXY
        }
        return xy
    }


    companion object {
        private var currentMap: CurrentMap? = null

        fun getCurrentMap(): CurrentMap {
            if (currentMap != null)
                return currentMap!!
            currentMap = CurrentMap()
            ChangeMap.changeLevel(Levels.level_1)
            return currentMap!!
        }
        fun getCurrentMap(numLevel:Int) {
            val level = Levels.valueOf("level_$numLevel")
            ChangeMap.changeLevel(level)
        }

    }

    fun setMap(map: Map, level: Int){
        this.listPassibility = map.listPassibility
        this.listAtoms = map.listAtoms
        this.wh = map.wh
        listVector = ArrayList()

        for(i in map.listConnection!!){
            map.listResultAtoms.find { it == i.object1 }?.connections?.add(i)
            map.listResultAtoms.find { it == i.object2 }?.connections?.add(i)
        }
        this.listResultAtoms = map.listResultAtoms
        numLevel = level
    }
}

