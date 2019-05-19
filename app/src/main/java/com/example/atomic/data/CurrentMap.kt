package com.example.atomic.data

import android.widget.Button
import com.example.atomic.controler.*
import com.example.atomic.controler.Vector
import com.example.atomic.interfaces.CallBack
import com.example.atomic.interfaces.InterfaceMapView
import com.example.atomic.utils.ArrayListCustom
import com.example.atomic.utils.l
import java.util.*
import kotlin.collections.ArrayList

class CurrentMap : CallBack {
    private constructor()

    override fun callBack() {
        l("CallBack")
        view?.render()
    }

    var listMap: ArrayListCustom<ArrayList<Wall>> = ArrayListCustom(this)
        set(value) {
            field = value
            view?.render()
        }
    var listAtoms: ArrayListCustom<Atom> = ArrayListCustom(this)
        set(value) {
            field = value
            view?.render()
        }
    var listVector: ArrayListCustom<Vector> = ArrayListCustom(this)
        set(value) {
            field = value
            view?.render()
        }

    var wh: XY = XY(15, 15)
    var view: InterfaceMapView? = null

//    val resalt =

//    fun getLayerPassability(): ArrayList<ArrayList<Wall>> {
//        val list = ArrayList<ArrayList<Wall>>(listMap)
////        for (i in listAtoms){
////            list[i.xy.y][i.xy.x].passability = false
////        }
//        return list
//    }

    fun getMapOneList(): ArrayList<Wall> {
        val list = ArrayList<Wall>()
        listMap.map {
            it.map {
                list.add(it)
            }
        }
        return list
    }

    fun isPassability(xy: XY): Boolean =
        !(!listMap[xy.y][xy.x].passability || listAtoms.find { it.xy.equals(xy) } != null)

    fun getFirstNoNPassabilityCAll(xy: XY, x: Int = 0, y: Int = 0): XY {
//        val x:Int = if(changingX)1 else 0
//        val y:Int = if(changingY)1 else 0
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
            return currentMap!!

        }

    }
}

