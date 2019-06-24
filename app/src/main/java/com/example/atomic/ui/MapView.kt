package com.example.atomic.ui

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
//import android.graphics.Path.Direction;
import android.view.MotionEvent
import com.example.atomic.*
import com.example.atomic.controler.*
import com.example.atomic.data.CurrentMap
import com.example.atomic.interfaces.InterfaceLogicClicks
import com.example.atomic.interfaces.InterfaceMapView
import com.example.atomic.utils.*


class MapView : MapViewPrimitives, InterfaceMapView, View.OnTouchListener{

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
        super.onDraw(canvas!!)
        setWH()

        drawMap(canvas)
        drawAtoms(canvas)
        drawVectors(canvas)
        drawBackgroundOfCells(canvas)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        logicClicks.onClick(XY(event!!.x.toInt(), event.y.toInt()))
        return false
    }

    private fun drawMap(canvas: Canvas) {
        val list = CurrentMap.getCurrentMap().listPassibility
        for (i in list.indices) {
            for (cell in list[i].indices) {
                drawWallOrCell(canvas, XY(cell,i).toGlobalCoordinateXY(), list[i][cell])
            }
        }
    }

    private fun drawAtoms(canvas: Canvas) {
        val list = CurrentMap.getCurrentMap().listAtoms
        val listResult = currentMap.listResultAtoms
        list.map {
            drawAtom(canvas, it)
        }
        listResult.map {
            drawAtom(canvas, it)
        }
    }

    private fun drawVectors(canvas: Canvas) {
        val list = CurrentMap.getCurrentMap().listVector
        if (list.size > 0)
            for(i in list)
                drawVector(canvas, i)
    }

    override fun render() {
        invalidate()
    }
}
