package com.example.atomic.controler

import android.os.StatFs

data class XY(var x: Int, var y: Int)

open class Cell(
    open val id: Int,
    var xy: XY
)

class Wall(var passability: Boolean, id: Int, xy: XY) : Cell(id, xy)// passability = true пусто

class Atom(val type: String, val vectorConnect: Direction, id: Int, xy: XY) : Cell(id, xy)

class Vector(val atom: Atom, val vector: Direction, id: Int, xy: XY) : Cell(id, xy)

enum class Direction {
    right {
        val sin = 0
        val cos = 1
    },
    left {
        val sin = 0
        val cos = -1
    },
    dawn {
        val sin = 1
        val cos = 0
    },
    top {
        val sin = -1
        val cos = 0
    };
//    |----> cos
//    |
//   \/ sin
}
