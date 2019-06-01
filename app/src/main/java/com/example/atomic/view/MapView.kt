package com.example.atomic.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.graphics.Paint.ANTI_ALIAS_FLAG
//import android.graphics.Path.Direction;
import android.view.MotionEvent
import com.example.atomic.*
import com.example.atomic.controler.*
import com.example.atomic.data.CurrentMap
import com.example.atomic.interfaces.CallBack
import com.example.atomic.interfaces.InterfaceLogicClicks
import com.example.atomic.interfaces.InterfaceMapView
import com.example.atomic.Direction.*
import com.example.atomic.Vector
import com.example.atomic.utils.*
import kotlin.collections.ArrayList


class MapView : View, View.OnTouchListener, InterfaceMapView {

    private lateinit var currentMap: CurrentMap
    private val logicClicks: InterfaceLogicClicks = LogicClicks(this)

    companion object {
        var sideOfSquare: Int = 0
        var widthOfFields: Int = 0
    }

    constructor(context: Context?) : super(context) {
        init()

    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()

    }

    private fun init() {
        currentMap = CurrentMap.getCurrentMap()
        setOnTouchListener(this)
    }

    private fun setWH() {
        val sideOfSquareAndWidthOfFields = width / currentMap.wh.x
        sideOfSquare = (sideOfSquareAndWidthOfFields * 0.95).toInt()
        widthOfFields = (sideOfSquareAndWidthOfFields * 0.05).toInt()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        setWH()
        drawBackground(canvas!!)

        drawMap(canvas)
        drawAtoms(canvas)
        drawVectors(canvas)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        initCurrentMap()
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        logicClicks.onClick(XY(event!!.x.toInt(), event.y.toInt()))
        return false
    }

    private fun drawMap(canvas: Canvas) {
        val list = CurrentMap.getCurrentMap().listMapPassibility
        for (i in list.indices) {
            for (cell in list[i].indices) {
                drawWallOrCell(canvas, XY(cell,i).toGlobalCoordinateXY(), list[i][cell])
            }
        }
    }

    private fun drawAtoms(canvas: Canvas) {
        CurrentMap.getCurrentMap().setResult()
        val list = currentMap.listAtoms
        val listResult = currentMap.listResultAtoms
        list.map {
            drawAtom(canvas, it)
        }
        listResult.map {
            drawAtom(canvas, it)
        }
    }

    private fun drawVectors(canvas: Canvas) {
        val list = currentMap.listVector
        if (list.size > 0)
            list.map {
                drawVector(canvas, it)
            }
    }

    private fun bypassAllAtomsOfResult(a : Atom, canvas: Canvas, list:ArrayList<Atom>){
        drawAtom(canvas,a)
        list.add(a)
        a.connections?.map {
            val ob1 = it.object1
            val ob2 = it.object2
            if (ob1 != a && list.find { it.equals(ob1) }==null){
                it.object1.xy.x += it.direction.getCos()*-1
                it.object1.xy.y += it.direction.getSin()*-1
                bypassAllAtomsOfResult(it.object1,canvas,list)
            }else if(it.equals(ob2)){
                it.object2.xy.x += it.direction.getCos()*-1
                it.object2.xy.y += it.direction.getSin()*-1
                bypassAllAtomsOfResult(it.object2,canvas,list)
            }
        }
    }

    override fun render() {
        invalidate()
    }

    private fun drawVector(canvas: Canvas, vector: Vector) {
        val x = vector.xy.x.toGlobalCoordinate()
        val y = vector.xy.y.toGlobalCoordinate()
        val sin = vector.vector.getSin()
        val cos = vector.vector.getCos()

        val mPaint = Paint(ANTI_ALIAS_FLAG)
        val mPath = Path()
        mPaint.color = Color.RED

        mPath.moveTo(
            (x + 0.5 * sideOfSquare + 0.5 * sideOfSquare * cos).toFloat(),
            (y + 0.5 * sideOfSquare + 0.5 * sideOfSquare * sin * -1).toFloat()
        )
        mPath.lineTo(
            (x + 0.5 * sideOfSquare + 0.5 * sideOfSquare * sin * -1).toFloat(),
            (y + 0.5 * sideOfSquare + 0.5 * sideOfSquare * cos).toFloat()
        )
        mPath.lineTo(
            (x + 0.5 * sideOfSquare + 0.25 * sideOfSquare * sin * -1).toFloat(),
            (y + 0.5 * sideOfSquare + 0.25 * sideOfSquare * cos).toFloat()
        )
        mPath.lineTo(
            (x + 0.5 * sideOfSquare + 0.5 * sideOfSquare * cos * -1).toFloat(),
            (y + 0.5 * sideOfSquare + 0.5 * sideOfSquare * sin ).toFloat()
        )
        mPath.lineTo(
            (x + 0.5 * sideOfSquare + 0.25 * sideOfSquare * sin ).toFloat(),
            (y + 0.5 * sideOfSquare + 0.25 * sideOfSquare * cos * -1).toFloat()
        )
        mPath.lineTo(
            (x + 0.5 * sideOfSquare + 0.5 * sideOfSquare * sin ).toFloat(),
            (y + 0.5 * sideOfSquare + 0.5 * sideOfSquare * cos * -1).toFloat()
        )
        mPath.lineTo(
            (x + 0.5 * sideOfSquare + 0.5 * sideOfSquare * cos).toFloat(),
            (y + 0.5 * sideOfSquare + 0.5 * sideOfSquare * sin* -1).toFloat()
        )
        canvas.drawPath(mPath, mPaint)

    }

    private fun drawBackground(canvas: Canvas) {
        val mPaint = Paint(ANTI_ALIAS_FLAG)
        mPaint.color = Color.WHITE
        val rect = Rect()

        rect.left = 0
        rect.right = getWidth()
        rect.top = 0
        rect.bottom = getHeight()
        canvas.drawRect(rect, mPaint)
    }

    private fun drawWallOrCell(canvas: Canvas, xy: XY, passability: Boolean) {
        val mPaint = Paint(ANTI_ALIAS_FLAG)
        val rect = Rect()
        val mCornerPathEffect = CornerPathEffect(60.0f)

        mPaint.setPathEffect(mCornerPathEffect)
        if (passability)
            mPaint.color = 0xFFFFF6D4.toInt()
        else
            mPaint.color = Color.BLACK

        rect.left = xy.x
        rect.top = xy.y
        rect.right = xy.x + sideOfSquare
        rect.bottom = xy.y + sideOfSquare
        canvas.drawRect(rect, mPaint)
    }

    private fun drawAtom(canvas: Canvas, atom: Atom) {

        val mPaint = Paint(ANTI_ALIAS_FLAG)
        var mPath = Path()
        val x = atom.xy.x.toGlobalCoordinate()
        val y = atom.xy.y.toGlobalCoordinate()
        mPaint.color = Color.LTGRAY
        mPath.addCircle(
            (x + sideOfSquare / 2).toFloat(),
            (y + sideOfSquare / 2).toFloat(),
            (sideOfSquare / 2.5).toFloat(),
            Path.Direction.CCW
        )
        canvas.drawPath(mPath, mPaint)
        val mPaintToText = Paint(ANTI_ALIAS_FLAG)
        mPaintToText.textSize = (sideOfSquare / 2).toFloat()
        mPaintToText.setStyle(Paint.Style.STROKE)

        canvas.drawText(
            atom.type.toString(),
            (x + sideOfSquare * 0.3).toFloat(),
            (y + sideOfSquare*0.7).toFloat(),
            mPaintToText
        )

        mPaint.color = Color.GREEN
        mPaint.strokeWidth = 8f
        for (i in atom.vectorConnects) {
            mPath = Path()
            val sin = i.getSin()
            val cos = i.getCos()

            canvas.drawLine(
                (x + 0.5 * sideOfSquare + 0.4 * sideOfSquare * cos).toFloat(),
            (y + 0.5 * sideOfSquare + 0.4 * sideOfSquare * sin ).toFloat(),
                (x + 0.5 * sideOfSquare + 0.5 * sideOfSquare * cos ).toFloat(),
                (y + 0.5 * sideOfSquare + 0.5 * sideOfSquare * sin ).toFloat(),
                mPaint)
        }
        canvas.drawPath(mPath, mPaint)
    }


    fun initCurrentMap() {
        val map = CurrentMap.getCurrentMap()

        map.view = this

        val array: ArrayList<ArrayList<Boolean>> = getArray()

        map.listAtoms = createListAtomMock()
    }                       //It's litter

    fun createListMapMock(): ArrayListCustom<ArrayList<Wall>> {
        val map = CurrentMap.getCurrentMap()
        val list = ArrayListCustom<ArrayList<Wall>>(null)
        for (i in 0..14) {
            val list1 = ArrayList<Wall>()
            for (i1 in 0..14) {
                list1.add(Wall(true, i * map.wh.x + i1, XY(i, i1)))
            }
            list.add(list1)
        }
        for (i in 0..14)
            list[0][i].passability = false
        for (i in 0..14)
            list[14][i].passability = false
        for (i in 0..14) {
            list[i][0].passability = false
            list[i][14].passability = false
        }
        return list
    }        //It's litter

    fun createListAtomMock(): ArrayListCustom<Atom> {
        val listAt = ArrayListCustom<Atom>(object : CallBack {
            override fun callBack() {
            }
        })
        listAt.add(
            Atom(
                TypeAtom.H,
                arrayOf(Direction.right),
                53,
                XY(6, 5)
            )
        )
        listAt.add(
            Atom(
                TypeAtom.H,
                arrayOf(Direction.top),
                59,
                XY(7, 6)
            )
        )
        listAt.add(
            Atom(
                TypeAtom.H,
                arrayOf(dawn),
                73,
                XY(8, 4)
            )
        )
        listAt.add(
            Atom(
                TypeAtom.H,
                arrayOf(dawn),
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
                    dawn
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
    }                  //It's litter

//    fun setPossability(array: Array<Array<Boolean>>) {
//        val map = CurrentMap.getCurrentMap()
//        map.listMap.map {
//            it.map {
//                it.passability = array[it.xy.y][it.xy.x]
//            }
//        }
//    }

}
