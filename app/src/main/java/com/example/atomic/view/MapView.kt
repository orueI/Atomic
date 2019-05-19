package com.example.atomic.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.graphics.Paint.ANTI_ALIAS_FLAG
//import android.graphics.Path.Direction;
import android.view.MotionEvent
import com.example.atomic.controler.*
import com.example.atomic.data.CurrentMap
import com.example.atomic.interfaces.CallBack
import com.example.atomic.interfaces.InterfaceLogicClicks
import com.example.atomic.interfaces.InterfaceMapView
import com.example.atomic.utils.ArrayListCustom
import com.example.atomic.utils.l
import com.example.atomic.utils.toGlobalCoordinate
import com.example.atomic.utils.toGlobalCoordinateXY
import com.example.atomic.controler.Direction.*
import org.mockito.Mockito.mock


class MapView : View, View.OnTouchListener, InterfaceMapView {

    private lateinit var currentMap: CurrentMap
    private val logicClicks: InterfaceLogicClicks = LogicClicks(this)

    companion object {
        var sideOfSquare: Int = 0
        var widthOfFields: Int = 0
    }

//    private lateinit var canvas: Canvas

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
        l("onMeasure")
        initCurrentMap()
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        l("onTouch")
        logicClicks.onClick(XY(event!!.x.toInt(), event.y.toInt()))
        return false
    }

    private fun drawMap(canvas: Canvas) {
        l("drawMap")
        val list = CurrentMap.getCurrentMap().listMap
        for (i in list) {
            for (cell in i) {
                drawWallOrCell(canvas, cell.xy.toGlobalCoordinateXY(), cell.passability)
            }
        }
    }

    private fun drawAtoms(canvas: Canvas) {
        l("drawAtoms")
        val list = currentMap.listAtoms
        list.map {
            drawAtom(canvas, it)
        }
    }

    private fun drawVectors(canvas: Canvas) {
        l("drawVectors")
        val list = currentMap.listVector
        if (list.size > 0)
            list.map {
                drawVector(canvas, it)
            }
    }

    override fun render() {
        l("render")
        invalidate()
    }

    private fun drawVector(canvas: Canvas, vector: Vector) {
        val mPaint = Paint(ANTI_ALIAS_FLAG)
        val mPath = Path()
        mPaint.color = Color.YELLOW
        mPath.addCircle(
            (vector.xy.x.toGlobalCoordinate() + sideOfSquare / 2).toFloat(),
            (vector.xy.y.toGlobalCoordinate() + sideOfSquare / 2).toFloat(),
            (sideOfSquare / 2.5).toFloat(),
            Path.Direction.CCW
        )
        l("drawVector")
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
        val mPath = Path()
        val x = atom.xy.x.toGlobalCoordinate()
        val y = atom.xy.y.toGlobalCoordinate()
        mPaint.color = Color.LTGRAY
        mPath.addCircle(
//            TODO X IT'S Y
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
            atom.type,
            (x).toFloat(),
            (y + sideOfSquare).toFloat(),
            mPaintToText
        )
    }

//    private fun Int.toGlobalCoordinate(): Int {
//        return this * (widthOfFields + sideOfSquare) + widthOfFields
//    }


    fun initCurrentMap() {
        val map = CurrentMap.getCurrentMap()

        map.view = this
        map.listMap = createListMapMock()
        map.listAtoms = createListAtomMock()
    }                       //It's litter

    fun createListMapMock(): ArrayListCustom<ArrayList<Wall>> {
        val list = ArrayListCustom<ArrayList<Wall>>(null)
        for (i in 0..9) {
            val list1 = ArrayList<Wall>()
            for (i1 in 0..9) {
//                if (i1 == 0 || i1 == 9)
//                    list1.add(Wall(false, i * 10 + i1, XY(i, i1)))
//                else
                list1.add(Wall(true, i * 10 + i1, XY(i, i1)))
            }
            list.add(list1)
        }
        for (i in 0..9)
            list[0][i].passability=false
        for (i in 0..9)
            list[9][i].passability=false
for (i in 0..9){
    list[i][0].passability=false
    list[i][9].passability=false
}
        return list
    }        //It's litter

    fun createListAtomMock(): ArrayListCustom<Atom> {
        val listAt = ArrayListCustom<Atom>(object : CallBack {
            override fun callBack() {
            }
        })
        listAt.add(Atom("H", Direction.dawn, 12, XY(1, 2)))
        listAt.add(Atom("H", Direction.left, 22, XY(2, 2)))
        listAt.add(Atom("H", Direction.right, 25, XY(2, 5)))
        listAt.add(Atom("H", Direction.top, 65, XY(6, 5)))
        listAt.add(Atom("H", Direction.left, 83, XY(8, 3)))
        return listAt
    }                  //It's litter
}
