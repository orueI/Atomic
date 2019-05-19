package com.example.atomic.controler

import android.os.StatFs

data class XY(var x: Int, var y: Int)

open class Cell(
    open val id: Int,
    var xy: XY
)

class Wall(var passability: Boolean, id: Int, xy: XY) : Cell(id, xy)// passability = true пусто

class Atom(val type: String, val vectorConnect: Direction, id: Int, xy: XY,connection: Connection? = null) : Cell(id, xy)

class Vector(val atom: Atom, val vector: Direction, id: Int, xy: XY) : Cell(id, xy)

enum class Direction {
    right {
        override fun getSin() = 0
        override fun getCos() = 1
    },
    left {
        override fun getSin() = 0
        override fun getCos() = -1
    },
    dawn {
        override fun getSin() = 1
        override fun getCos() = 0
    },
    top {
        override fun getSin() = -1
        override fun getCos() = 0
    };

    abstract fun getSin():Int
    abstract fun getCos():Int
//    |----> cos
//    |
//   \/ sin
}

data class Connection(val direction: Direction,val parent:Atom,val son: Atom)
