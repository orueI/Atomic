package com.example.atomic.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.atomic.Atom
import com.example.atomic.Vector
import com.example.atomic.XY
import com.example.atomic.utils.toGlobalCoordinate
import com.example.atomic.view.MapView

open class MapViewPrimitives : View {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    protected fun drawVector(canvas: Canvas, vector: Vector) {
        val x = vector.xy.x.toGlobalCoordinate()
        val y = vector.xy.y.toGlobalCoordinate()
        val sin = vector.vector.getSin()
        val cos = vector.vector.getCos()

        val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        val mPath = Path()
        mPaint.color = Color.RED

        mPath.moveTo(
            (x + 0.5 * MapView.sideOfSquare + 0.5 * MapView.sideOfSquare * cos).toFloat(),
            (y + 0.5 * MapView.sideOfSquare + 0.5 * MapView.sideOfSquare * sin * -1).toFloat()
        )
        mPath.lineTo(
            (x + 0.5 * MapView.sideOfSquare + 0.5 * MapView.sideOfSquare * sin * -1).toFloat(),
            (y + 0.5 * MapView.sideOfSquare + 0.5 * MapView.sideOfSquare * cos).toFloat()
        )
        mPath.lineTo(
            (x + 0.5 * MapView.sideOfSquare + 0.25 * MapView.sideOfSquare * sin * -1).toFloat(),
            (y + 0.5 * MapView.sideOfSquare + 0.25 * MapView.sideOfSquare * cos).toFloat()
        )
        mPath.lineTo(
            (x + 0.5 * MapView.sideOfSquare + 0.5 * MapView.sideOfSquare * cos * -1).toFloat(),
            (y + 0.5 * MapView.sideOfSquare + 0.5 * MapView.sideOfSquare * sin ).toFloat()
        )
        mPath.lineTo(
            (x + 0.5 * MapView.sideOfSquare + 0.25 * MapView.sideOfSquare * sin ).toFloat(),
            (y + 0.5 * MapView.sideOfSquare + 0.25 * MapView.sideOfSquare * cos * -1).toFloat()
        )
        mPath.lineTo(
            (x + 0.5 * MapView.sideOfSquare + 0.5 * MapView.sideOfSquare * sin ).toFloat(),
            (y + 0.5 * MapView.sideOfSquare + 0.5 * MapView.sideOfSquare * cos * -1).toFloat()
        )
        mPath.lineTo(
            (x + 0.5 * MapView.sideOfSquare + 0.5 * MapView.sideOfSquare * cos).toFloat(),
            (y + 0.5 * MapView.sideOfSquare + 0.5 * MapView.sideOfSquare * sin* -1).toFloat()
        )
        canvas.drawPath(mPath, mPaint)

    }

    protected fun drawBackground(canvas: Canvas) {
        val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint.color = Color.WHITE
        val rect = Rect()

        rect.left = 0
        rect.right = getWidth()
        rect.top = 0
        rect.bottom = getHeight()
        canvas.drawRect(rect, mPaint)
    }

    protected fun drawWallOrCell(canvas: Canvas, xy: XY, passability: Boolean) {
        val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        val rect = Rect()
        val mCornerPathEffect = CornerPathEffect(60.0f)

        mPaint.setPathEffect(mCornerPathEffect)
        if (passability)
            mPaint.color = 0xFFFFF6D4.toInt()
        else
            mPaint.color = Color.BLACK

        rect.left = xy.x
        rect.top = xy.y
        rect.right = xy.x + MapView.sideOfSquare
        rect.bottom = xy.y + MapView.sideOfSquare
        canvas.drawRect(rect, mPaint)
    }

    protected fun drawAtom(canvas: Canvas, atom: Atom) {

        val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        var mPath = Path()
        val x = atom.xy.x.toGlobalCoordinate()
        val y = atom.xy.y.toGlobalCoordinate()
        mPaint.color = Color.LTGRAY
        mPath.addCircle(
            (x + MapView.sideOfSquare / 2).toFloat(),
            (y + MapView.sideOfSquare / 2).toFloat(),
            (MapView.sideOfSquare / 2.5).toFloat(),
            Path.Direction.CCW
        )
        canvas.drawPath(mPath, mPaint)
        val mPaintToText = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaintToText.textSize = (MapView.sideOfSquare / 2).toFloat()
        mPaintToText.setStyle(Paint.Style.STROKE)

        canvas.drawText(
            atom.type.toString(),
            (x + MapView.sideOfSquare * 0.3).toFloat(),
            (y + MapView.sideOfSquare *0.7).toFloat(),
            mPaintToText
        )

        mPaint.color = Color.GREEN
        mPaint.strokeWidth = 8f
        for (i in atom.vectorConnects) {
            mPath = Path()
            val sin = i.getSin()
            val cos = i.getCos()

            canvas.drawLine(
                (x + 0.5 * MapView.sideOfSquare + 0.4 * MapView.sideOfSquare * cos).toFloat(),
                (y + 0.5 * MapView.sideOfSquare + 0.4 * MapView.sideOfSquare * sin ).toFloat(),
                (x + 0.5 * MapView.sideOfSquare + 0.5 * MapView.sideOfSquare * cos ).toFloat(),
                (y + 0.5 * MapView.sideOfSquare + 0.5 * MapView.sideOfSquare * sin ).toFloat(),
                mPaint)
        }
        canvas.drawPath(mPath, mPaint)
    }
}