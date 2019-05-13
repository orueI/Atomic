package com.example.atomic.controler

import android.os.StatFs

data class XY(var x: Int, var y: Int)

open class Cell(
    open val id: Int,
    var xy: XY
)

class Wall(var passability: Boolean, id: Int, xy: XY) : Cell(id, xy)// passability = true пусто

class Atom(val type: String, val vectorConnect: Int, id: Int, xy: XY) : Cell(id, xy)

class Vector(val atom: Atom, val vector: Int, id: Int, xy: XY) : Cell(id, xy)

//class