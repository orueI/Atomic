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
import com.example.atomic.controler.Direction.*
import com.example.atomic.controler.Vector
import com.example.atomic.utils.*
import org.mockito.Mockito.mock
import java.util.*
import kotlin.collections.ArrayList


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
//        drawExempleResult(canvas)
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
                drawWallOrCell(canvas, cell.xy.toGlobalCoordinateXY(), cell.passability, cell)
            }
        }
    }

    private fun drawAtoms(canvas: Canvas) {
        l("drawAtoms")
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
        l("drawVectors")
        val list = currentMap.listVector
        if (list.size > 0)
            list.map {
                drawVector(canvas, it)
            }
    }

    private fun drawExempleResult(canvas: Canvas){
        CurrentMap.getCurrentMap().setResult()
        val result = CurrentMap.getCurrentMap().listResult
        var xy = XY(1,currentMap.wh.y+2)
            result[0].object1.xy = xy
        bypassAllAtomsOfResult(result[0].object1,canvas,ArrayList())

    }

    private fun bypassAllAtomsOfResult(a :Atom,canvas: Canvas,list:ArrayList<Atom>){
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
        l("render")
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

    private fun drawWallOrCell(canvas: Canvas, xy: XY, passability: Boolean, cell: Cell) {
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
            atom.type,
            (x + sideOfSquare * 0.3).toFloat(),
            (y + sideOfSquare*0.7).toFloat(),
            mPaintToText
        )

        mPaint.color = Color.GREEN
        mPaint.strokeWidth = 5f
        for (i in atom.vectorConnects) {
            mPath = Path()
//            mPath.moveTo((x +0.5* sideOfSquare+0.4 * sideOfSquare * i.getSin()).toFloat(), (y +0.5* sideOfSquare+0.4 * sideOfSquare * i.getCos()).toFloat())
//            mPath.lineTo((x +0.5* sideOfSquare+0.5 * sideOfSquare * i.getSin()).toFloat(), (y +0.5* sideOfSquare+0.5 * sideOfSquare * i.getCos()).toFloat())
//            mPath.lineTo((x +0.5* sideOfSquare+0.4 * sideOfSquare * i.getSin()).toFloat(), (y +0.5* sideOfSquare+0.4 * sideOfSquare * i.getCos()).toFloat())
//            mPaint.color = Color.RED
            val sin = i.getSin()
            val cos = i.getCos()

//            mPath.moveTo(
//                (x + 0.5 * sideOfSquare + 0.4 * sideOfSquare * cos).toFloat(),
//                (y + 0.5 * sideOfSquare + 0.4 * sideOfSquare * sin * -1).toFloat()
//            )
//            mPath.lineTo(
//                (x + 0.5 * sideOfSquare + 0.5 * sideOfSquare * cos ).toFloat(),
//                (y + 0.5 * sideOfSquare + 0.5 * sideOfSquare * sin * -1).toFloat()
//            )
//            mPath.lineTo(
//                (x + 0.5 * sideOfSquare + 0.4 * sideOfSquare * cos).toFloat(),
//                (y + 0.5 * sideOfSquare + 0.4 * sideOfSquare * sin * -1).toFloat()
//            )
            canvas.drawLine(
                (x + 0.5 * sideOfSquare + 0.4 * sideOfSquare * cos).toFloat(),
            (y + 0.5 * sideOfSquare + 0.4 * sideOfSquare * sin ).toFloat(),
                (x + 0.5 * sideOfSquare + 0.5 * sideOfSquare * cos ).toFloat(),
                (y + 0.5 * sideOfSquare + 0.5 * sideOfSquare * sin ).toFloat(),
                mPaint)
//            canvas.drawPath(mPath, mPaint)
        }
        canvas.drawPath(mPath, mPaint)
    }


    fun initCurrentMap() {
        val map = CurrentMap.getCurrentMap()

        map.view = this
        map.listMap = createListMapMock()

        val array: Array<Array<Boolean>> = getArray()
        setPossability(array)

        map.listAtoms = createListAtomMock()
    }                       //It's litter

    fun createListMapMock(): ArrayListCustom<ArrayList<Wall>> {
        val map = CurrentMap.getCurrentMap()
        val list = ArrayListCustom<ArrayList<Wall>>(null)
        for (i in 0..14) {
            val list1 = ArrayList<Wall>()
            for (i1 in 0..14) {
//                if (i1 == 0 || i1 == 9)
//                    list1.add(Wall(false, i * 10 + i1, XY(i, i1)))
//                else
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
        listAt.add(Atom("H", arrayOf(Direction.right), 53, XY(3, 5)))
        listAt.add(Atom("H", arrayOf(Direction.top),  59, XY(9, 5)))
        listAt.add(Atom("H", arrayOf(Direction.dawn), 73, XY(3, 7)))
        listAt.add(Atom("H", arrayOf(Direction.dawn),77, XY(7, 7)))
        listAt.add(Atom("O", arrayOf(Direction.left), 83, XY(3, 8)))
        listAt.add(Atom("C", arrayOf(Direction.left, Direction.top, Direction.right, Direction.dawn),  87, XY(7, 8)))
        listAt.add(Atom("C", arrayOf(Direction.left, Direction.top, Direction.right),  115, XY(5, 11)))
        return listAt
    }                  //It's litter

    fun setPossability(array: Array<Array<Boolean>>) {
        val map = CurrentMap.getCurrentMap()
        map.listMap.map {
            it.map {
                it.passability = array[it.xy.y][it.xy.x]
            }
        }
    }

}
