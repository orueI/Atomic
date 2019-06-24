package com.example.atomic

data class XY(var x: Int, var y: Int)

open class Cell(
    open val id: Int,
    var xy: XY
)

class Atom(
    val type: TypeAtom,
    val vectorConnects: Array<Direction>,
    id: Int,
    xy: XY,
    var connections: ArrayList<Connection>? = null
) : Cell(id, xy)

class Vector(val atom: Atom, val vector: Direction, id: Int, xy: XY) : Cell(id, xy)

data class Connection(val object1: Atom, val object2: Atom, val direction: Direction)

open class Map(
    var listPassibility: ArrayList<ArrayList<Boolean>>,
    var listAtoms: ArrayList<Atom>,
    var listResultAtoms: ArrayList<Atom>,
    var wh: XY,
    var listConnection: ArrayList<Connection>? = null
)

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

    abstract fun getSin(): Int
    abstract fun getCos(): Int
//    |----> cos
//    |
//   \/ sin
}

enum class TypeAtom { H, C, O }

enum class Levels(val numForLevel:Int) {
    level_1(1) {
        override fun getNameOfLevel(): String = "Hydrogenium"

        override fun getImage(): Int {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getInt(): Int =
            R.string.level_1

    },
    level_2(2) {
        override fun getNameOfLevel(): String = "Hydrogen oxide"

        override fun getImage(): Int = R.drawable.image_of_level_5

        override fun getInt(): Int =
            R.string.level_2
    },
    level_3(3) {
        override fun getNameOfLevel(): String = "Methanum"

        override fun getImage(): Int {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getInt(): Int =
            R.string.level_3

    },
    level_4(4) {
        override fun getNameOfLevel(): String = "Methanol"

        override fun getImage(): Int {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getInt(): Int =
            R.string.level_4

    },
    level_5(5) {
        override fun getNameOfLevel(): String = "Acetaldehyde"

        override fun getImage(): Int = R.drawable.image_of_level_5

        override fun getInt(): Int =
            R.string.level_5

    };

    abstract fun getInt(): Int
    abstract fun getImage(): Int
    abstract fun getNameOfLevel(): String

    fun getQuantityLevels():Int = 5
}
