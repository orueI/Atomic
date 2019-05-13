package com.example.atomic.data

import android.widget.Button
import com.example.atomic.controler.Atom
import com.example.atomic.controler.Cell
import com.example.atomic.controler.Vector
import com.example.atomic.controler.Wall
import com.example.atomic.controler.XY
import com.example.atomic.interfaces.CallBack
import com.example.atomic.interfaces.InterfaceMapView
import com.example.atomic.utils.ArrayListCustom
import com.example.atomic.utils.l
import java.util.*
import kotlin.collections.ArrayList

class CurrentMap:CallBack {
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

    var wh: XY = XY(10,10)
    var view: InterfaceMapView? = null


    fun getLayerPassability(): ArrayList<ArrayList<Wall>> {
        val list = ArrayList<ArrayList<Wall>>(listMap)
        for (i in listAtoms){
            list[i.xy.y][i.xy.x].passability = false
        }
        return list
    }

    fun getMapOneList(): ArrayList<Wall> {
        val list = ArrayList<Wall>()
        listMap.map {
            it.map {
                list.add(it)
            }
        }
        return list
    }

//    private var currentMap: CurrentMap? = null
//    fun getcurrentMap():CurrentMap = currentMap?:CurrentMap()
    companion object {
        private var currentMap: CurrentMap? = null

        fun getCurrentMap():CurrentMap {
            if (currentMap != null)
            return currentMap!!
                currentMap = CurrentMap()
            return currentMap!!

        }

    }






    private constructor() {
//        listMap = createListMapMock()
    }

    fun createListMapMock(): ArrayListCustom<ArrayList<Wall>> {
        val list = ArrayListCustom<ArrayList<Wall>>(object : CallBack {
            override fun callBack() {
            }
        })
        val list0 = ArrayList<Wall>()
        for (i1 in 0..9) {
            list0.add(Wall(false, 0 * 10 + i1, XY(0, i1)))
        }
        list.add(list0)
        for (i in 0..7) {
            val list1 = ArrayList<Wall>()
            for (i1 in 0..9) {
                if (i1 == 0 || i1 == 9)
                    list1.add(Wall(false, i * 10 + i1, XY(i, i1)))
                list1.add(Wall(true, i * 10 + i1, XY(i, i1)))
            }
            list.add(list1)
        }
        val list2 = ArrayList<Wall>()
        for (i1 in 0..9) {
            list2.add(Wall(false, 9 * 10 + i1, XY(9, i1)))
        }
        list.add(list2)
        return list
    }        //It's litter

}