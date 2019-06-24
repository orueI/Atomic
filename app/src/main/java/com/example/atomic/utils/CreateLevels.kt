package com.example.atomic.utils

import com.example.atomic.*
import com.example.atomic.Map
import java.util.ArrayList

class CreateLevels {

    fun getListAtoms():ArrayList<Atom>{
        val listAt = ArrayList<Atom>()
        listAt.add(
            Atom(
                TypeAtom.H,
                arrayOf(Direction.top),
                53,
                XY(7, 11)
            )
        )
        return listAt
    }

    fun getListAtomsResult():ArrayList<Atom>{
        val listResult = ArrayList<Atom>()
        listResult.add(Atom(
            TypeAtom.H,
            arrayOf(Direction.top),
            0,
            XY(1, 17)
        ))
        return listResult
    }

    fun getListConnections():ArrayList<Connection>{
        val list = ArrayList<Connection>()
        val lA = getListAtomsResult()
//        list.add(Connection(lA[0],lA[1],Direction.right))
        return list
    }
    fun getArray(): ArrayList<ArrayList<Boolean>> {
        val array: ArrayList<ArrayList<Boolean>> = arrayListOf(
            arrayListOf(true,true,true,true,true,true,true,true,true,true,true,true,true,true,true),
            arrayListOf(true,true,true,true,true,true,false,false,false,false,false,true,true,true,true),
            arrayListOf(true,true,true,true,true,true,false,true,true,true,false,true,true,true,true),
            arrayListOf(true,true,false ,false,false,false,false,true,false,true,false,true,true,true,true),
            arrayListOf(true,true,false,true,true,true,true,true,true,true,false,true,true,true,true),
            arrayListOf(true,true,false,false,false,true,true,true,true,false,false,true,true,true,true),
            arrayListOf(true,true,true,true,false,true,false,true,false,true,false,false,false,true,true),
            arrayListOf(true,true,true,true,false,false,true,true,true,true,false,true,false,true,true),
            arrayListOf(true,false,false,false,false,true,true,true,true,true,true,true,false,true,true),
            arrayListOf(true,false,true,true,true,true,true,true,false,true,true,true,false,true,true),
            arrayListOf(true,false,false,false,false,false,true,false,true,true,false,true,false,true,true),
            arrayListOf(true,true,true,true,false,true,true,true,true,false,true,true,false,true,true),
            arrayListOf(true,true,true,true,false,false,false,false,false,false,false,false,false,true,true),
            arrayListOf(true,true,true,true,true,true,true,true,true,true,true,true,true,true,true),
            arrayListOf(true,true,true,true,true,true,true,true,true,true,true,true,true,true,true)
        )

        return array
    }

    fun getLevel():Map{
        return Map(
            getArray(),
            getListAtoms(),
            getListAtomsResult(),
            XY(15,15),
            getListConnections
        ())
    }

}