package com.example.atomic.view.activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.GsonBuilder
import com.example.atomic.*
import com.example.atomic.data.CurrentMap


class MainActivity : AppCompatActivity() {

    companion object {
        lateinit var act: Activity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        act = this
        setContentView(R.layout.activity_main)

        val numLevel = intent.getIntExtra("level",1)
        CurrentMap.getCurrentMap(numLevel)

//        val level = Map(
//            getArray(),
            createListAtomMock(),
            setResult(),
//            XY(15,15),
//            setResult1()
//        )
//        val builder = GsonBuilder()
//        val gson = builder.create()
//        val s = gson.toJson(level)
//        l(s)
    }

    fun setResult(): ArrayList<Atom> {
        val atomHR = Atom(
            TypeAtom.H,
            arrayOf(Direction.left),
            0,
            XY(5, 17)
        )
        val atomHT = Atom(
            TypeAtom.H,
            arrayOf(Direction.top),
            0,
            XY(2, 18)
        )
        val atomHD = Atom(
            TypeAtom.H,
            arrayOf(Direction.dawn),
            0,
            XY(2, 16)
        )
        val atomHD2 = Atom(
            TypeAtom.H,
            arrayOf(Direction.dawn),
            0,
            XY(3, 16)
        )


        lateinit var connectHRandCAll: Connection
        lateinit var connectHTandCAll: Connection
        lateinit var connectHDandCAll: Connection
        lateinit var connectCWithautDandCAll: Connection
        lateinit var connectCWithautDandHD2: Connection
        lateinit var connectCWithautDandOL: Connection


//        connectHRandCAll = Connection(atomHR, atomCAll, Direction.right)
//        connectHTandCAll = Connection(atomHT, atomCAll, Direction.top)
//        connectHDandCAll = Connection(atomHD, atomCAll, Direction.dawn)
//        connectCWithautDandCAll =
//            Connection(atomCWithautDawn, atomCAll, Direction.left)
//        connectCWithautDandHD2 =
//            Connection(atomCWithautDawn, atomHD2, Direction.top)
//        connectCWithautDandOL =
//            Connection(atomCWithautDawn, atomOL, Direction.right)

        val listConnection = ArrayList<Connection>()
        listConnection.add(connectHRandCAll)
        listConnection.add(connectHTandCAll)
        listConnection.add(connectHDandCAll)
        listConnection.add(connectCWithautDandCAll)
        listConnection.add(connectCWithautDandHD2)
        listConnection.add(connectCWithautDandOL)

        val builder = GsonBuilder()
        val gson = builder.create()
//        l(gson.toJson(listConnection))
//        atomHR.connections = arrayOf(connectHRandCAll)
//        atomHT.connections = arrayOf(connectHTandCAll)
//        atomHD.connections = arrayOf(connectHDandCAll)
//        atomHD2.connections = arrayOf(connectCWithautDandHD2)
//        atomOL.connections = arrayOf(connectCWithautDandOL)
//        atomCAll.connections = arrayOf(connectCWithautDandOL, connectHRandCAll, connectHTandCAll, connectHDandCAll)
//        atomCWithautDawn.connections = arrayOf(connectCWithautDandCAll, connectCWithautDandHD2, connectCWithautDandOL)

//        listResult.clear()
//        listResult.add(connectHRandCAll)
//        listResult.add(connectHTandCAll)
//        listResult.add(connectHDandCAll)
//        listResult.add(connectCWithautDandCAll)
//        listResult.add(connectCWithautDandHD2)
//        listResult.add(connectCWithautDandOL)

        val list = ArrayList<Atom>()
        list.add(atomHR)
        list.add(atomHT)
        list.add(atomHD)
        list.add(atomHD2)
//        list.add(atomOL)
//        list.add(atomCAll)
//        list.add(atomCWithautDawn)
        return list
    }
    fun setResult1(): ArrayList<Connection> {
        val atomHR = Atom(
            TypeAtom.H,
            arrayOf(Direction.right),
            0,
            XY(1, 17)
        )
        val atomHT = Atom(
            TypeAtom.H,
            arrayOf(Direction.top),
            0,
            XY(2, 18)
        )
        val atomHD = Atom(
            TypeAtom.H,
            arrayOf(Direction.dawn),
            0,
            XY(2, 16)
        )
        val atomHD2 = Atom(
            TypeAtom.H,
            arrayOf(Direction.dawn),
            0,
            XY(3, 16)
        )
        val atomOL = Atom(
            TypeAtom.O,
            arrayOf(Direction.left),
            0,
            XY(4, 17)
        )
        val atomCAll = Atom(
            TypeAtom.C,
            arrayOf(
                Direction.left,
                Direction.top,
                Direction.right,
                Direction.dawn
            ),
            0,
            XY(2, 17)
        )
        val atomCWithautDawn = Atom(
            TypeAtom.C,
            arrayOf(
                Direction.left,
                Direction.top,
                Direction.right
            ),
            0,
            XY(3, 17)
        )

        lateinit var connectHRandCAll: Connection
        lateinit var connectHTandCAll: Connection
        lateinit var connectHDandCAll: Connection
        lateinit var connectCWithautDandCAll: Connection
        lateinit var connectCWithautDandHD2: Connection
        lateinit var connectCWithautDandOL: Connection


        connectHRandCAll = Connection(atomHR, atomCAll, Direction.right)
        connectHTandCAll = Connection(atomHT, atomCAll, Direction.top)
        connectHDandCAll = Connection(atomHD, atomCAll, Direction.dawn)
        connectCWithautDandCAll =
            Connection(atomCWithautDawn, atomCAll, Direction.left)
        connectCWithautDandHD2 =
            Connection(atomCWithautDawn, atomHD2, Direction.top)
        connectCWithautDandOL =
            Connection(atomCWithautDawn, atomOL, Direction.right)

        val listConnection = ArrayList<Connection>()
        listConnection.add(connectHRandCAll)
        listConnection.add(connectHTandCAll)
        listConnection.add(connectHDandCAll)
        listConnection.add(connectCWithautDandCAll)
        listConnection.add(connectCWithautDandHD2)
        listConnection.add(connectCWithautDandOL)

        val builder = GsonBuilder()
        val gson = builder.create()
//        l(gson.toJson(listConnection))
//        atomHR.connections = arrayOf(connectHRandCAll)
//        atomHT.connections = arrayOf(connectHTandCAll)
//        atomHD.connections = arrayOf(connectHDandCAll)
//        atomHD2.connections = arrayOf(connectCWithautDandHD2)
//        atomOL.connections = arrayOf(connectCWithautDandOL)
//        atomCAll.connections = arrayOf(connectCWithautDandOL, connectHRandCAll, connectHTandCAll, connectHDandCAll)
//        atomCWithautDawn.connections = arrayOf(connectCWithautDandCAll, connectCWithautDandHD2, connectCWithautDandOL)

//        listResult.clear()
//        listResult.add(connectHRandCAll)
//        listResult.add(connectHTandCAll)
//        listResult.add(connectHDandCAll)
//        listResult.add(connectCWithautDandCAll)
//        listResult.add(connectCWithautDandHD2)
//        listResult.add(connectCWithautDandOL)

        val list = ArrayList<Atom>()
        list.add(atomHR)
        list.add(atomHT)
        list.add(atomHD)
        list.add(atomHD2)
        list.add(atomOL)
        list.add(atomCAll)
        list.add(atomCWithautDawn)
        return listConnection
    }

    fun createListAtomMock(): ArrayList<Atom> {
        val listAt = ArrayList<Atom>()
        listAt.add(
            Atom(
                TypeAtom.H,
                arrayOf(Direction.right),
                53,
                XY(5, 4)
            )
        )
        listAt.add(
            Atom(
                TypeAtom.O,
                arrayOf(Direction.left,Direction.right),
                59,
                XY(10, 8)
            )
        )
        listAt.add(
            Atom(
                TypeAtom.H,
                arrayOf(Direction.right),
                73,
                XY(5, 9)
            )
        )
        listAt.add(
            Atom(
                TypeAtom.H,
                arrayOf(Direction.dawn),
                77,
                XY(7, 4)
            )
        )
        listAt.add(
            Atom(
                TypeAtom.O,
                arrayOf(Direction.left),
                83,
                XY(10, 5)
            )
        )
        listAt.add(
            Atom(
                TypeAtom.C,
                arrayOf(
                    Direction.left,
                    Direction.top,
                    Direction.right,
                    Direction.dawn
                ),
                87,
                XY(7, 5)
            )
        )
        listAt.add(
            Atom(
                TypeAtom.C,
                arrayOf(
                    Direction.left,
                    Direction.top,
                    Direction.right
                ),
                115,
                XY(8, 5)
            )
        )

        return listAt
    }

}
