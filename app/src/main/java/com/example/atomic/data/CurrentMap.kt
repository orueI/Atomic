package com.example.atomic.data

import com.example.atomic.controler.*
import com.example.atomic.controler.Vector
import com.example.atomic.interfaces.CallBack
import com.example.atomic.interfaces.InterfaceMapView
import com.example.atomic.utils.ArrayListCustom
import com.example.atomic.utils.getArray
import com.example.atomic.utils.l
import kotlin.collections.ArrayList

class CurrentMap : CallBack {
    private constructor() {
//        setResult()
    }

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
    val listResult: ArrayListCustom<Connection> = ArrayListCustom(this)
    val listResultAtoms: ArrayListCustom<Atom> = ArrayListCustom(this)
    var wh: XY = XY(15, 15)
    var view: InterfaceMapView? = null


    fun isPassability(xy: XY): Boolean =
        !(!getArray()[xy.y][xy.x] || listAtoms.find { it.xy.equals(xy) } != null)

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
            return currentMap!!

        }

    }

    fun setResult(){
        val atomHR              =              Atom(TypeAtom.H, arrayOf(Direction.right), 0, XY(1,17))
        val atomHT              =              Atom(TypeAtom.H, arrayOf(Direction.top), 0, XY(2,18))
        val atomHD              =              Atom(TypeAtom.H, arrayOf(Direction.dawn), 0, XY(2,16))
        val atomHD2             =             Atom(TypeAtom.H, arrayOf(Direction.dawn), 0, XY(3,16))
        val atomOL              =              Atom(TypeAtom.O, arrayOf(Direction.left), 0, XY(4,17))
        val atomCAll            =            Atom(TypeAtom.C, arrayOf(Direction.left, Direction.top, Direction.right, Direction.dawn), 0, XY(2,17))
        val atomCWithautDawn    =               Atom(TypeAtom.C, arrayOf(Direction.left, Direction.top, Direction.right), 0, XY(3,17))

        lateinit var connectHRandCAll: Connection
        lateinit var connectHTandCAll: Connection
        lateinit var connectHDandCAll: Connection
        lateinit var connectCWithautDandCAll: Connection
        lateinit var connectCWithautDandHD2: Connection
        lateinit var connectCWithautDandOL: Connection


        connectHRandCAll = Connection(atomHR, atomCAll,Direction.right)
        connectHTandCAll = Connection(atomHT, atomCAll,Direction.top)
        connectHDandCAll = Connection(atomHD, atomCAll, Direction.dawn)
        connectCWithautDandCAll = Connection(atomCWithautDawn, atomCAll,Direction.left)
        connectCWithautDandHD2 = Connection(atomCWithautDawn, atomHD2,Direction.top)
        connectCWithautDandOL = Connection(atomCWithautDawn, atomOL,Direction.right)

        atomHR.connections = arrayOf(connectHRandCAll)
        atomHT.connections = arrayOf(connectHTandCAll)
        atomHD.connections = arrayOf(connectHDandCAll)
        atomHD2.connections = arrayOf(connectCWithautDandHD2)
        atomOL.connections = arrayOf(connectCWithautDandOL)
        atomCAll.connections = arrayOf(connectCWithautDandOL, connectHRandCAll, connectHTandCAll, connectHDandCAll)
        atomCWithautDawn.connections = arrayOf(connectCWithautDandCAll, connectCWithautDandHD2, connectCWithautDandOL)

//        listResult.clear()
        listResult.add(connectHRandCAll)
        listResult.add(connectHTandCAll)
        listResult.add(connectHDandCAll)
        listResult.add(connectCWithautDandCAll)
        listResult.add(connectCWithautDandHD2)
        listResult.add(connectCWithautDandOL)

        listResultAtoms.add(atomHR          )
        listResultAtoms.add(atomHT          )
        listResultAtoms.add(atomHD          )
        listResultAtoms.add(atomHD2         )
        listResultAtoms.add(atomOL          )
        listResultAtoms.add(atomCAll        )
        listResultAtoms.add(atomCWithautDawn)
    }
}

