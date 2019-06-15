package com.example.atomic.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.atomic.Atom
import com.example.atomic.Vector
import com.example.atomic.XY
import com.example.atomic.utils.l
import com.example.atomic.utils.toGlobalCoordinate
import com.example.atomic.view.MapView
import com.example.atomic.view.MapView.Companion.sideOfSquare
import com.example.atomic.view.MapView.Companion.widthOfFields

open class MapViewPrimitives : View {


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    constructor(context: Context?) : super(context) {
//        setOnTouchListener(this)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
//        setOnTouchListener(this)
    }

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
            (y + 0.5 * MapView.sideOfSquare + 0.5 * MapView.sideOfSquare * sin).toFloat()
        )
        mPath.lineTo(
            (x + 0.5 * MapView.sideOfSquare + 0.25 * MapView.sideOfSquare * sin).toFloat(),
            (y + 0.5 * MapView.sideOfSquare + 0.25 * MapView.sideOfSquare * cos * -1).toFloat()
        )
        mPath.lineTo(
            (x + 0.5 * MapView.sideOfSquare + 0.5 * MapView.sideOfSquare * sin).toFloat(),
            (y + 0.5 * MapView.sideOfSquare + 0.5 * MapView.sideOfSquare * cos * -1).toFloat()
        )
        mPath.lineTo(
            (x + 0.5 * MapView.sideOfSquare + 0.5 * MapView.sideOfSquare * cos).toFloat(),
            (y + 0.5 * MapView.sideOfSquare + 0.5 * MapView.sideOfSquare * sin * -1).toFloat()
        )
        canvas.drawPath(mPath, mPaint)

    }

    protected fun drawBackground(canvas: Canvas) {
        val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint.color = Color.WHITE
        val rect = Rect()

        rect.left = 0
        rect.right = width
        rect.top = 0
        rect.bottom = height
        canvas.drawRect(rect, mPaint)
    }

    protected fun drawWallOrCell(canvas: Canvas, xy: XY, passability: Boolean) {
        val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        val rect = Rect()
        val mCornerPathEffect = CornerPathEffect(60.0f)

        mPaint.pathEffect = mCornerPathEffect
        if (passability)
//            mPaint.color = 0xFFFFF6D4.toInt()
            mPaint.color = Color.rgb(255, 252, 242)
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
        mPaintToText.style = Paint.Style.STROKE

        canvas.drawText(
            atom.type.toString(),
            (x + MapView.sideOfSquare * 0.3).toFloat(),
            (y + MapView.sideOfSquare * 0.7).toFloat(),
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
                (y + 0.5 * MapView.sideOfSquare + 0.4 * MapView.sideOfSquare * sin).toFloat(),
                (x + 0.5 * MapView.sideOfSquare + 0.5 * MapView.sideOfSquare * cos).toFloat(),
                (y + 0.5 * MapView.sideOfSquare + 0.5 * MapView.sideOfSquare * sin).toFloat(),
                mPaint
            )
        }
        canvas.drawPath(mPath, mPaint)
    }

    protected fun drawBackgroundWhite(canvas: Canvas) {
        val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint.color = Color.rgb(255, 252, 242)
        val rect = Rect()

        rect.left = 0
        rect.right = width
        rect.top = 0
        rect.bottom = height
        canvas.drawRect(rect, mPaint)
    }

    protected fun drawBackgroundOfCells(canvas: Canvas) {
        val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint.color = Color.rgb(255, 252, 242)
//        val rect = Rect()

//        rect.left = 0
//        rect.right = getWidth()
//        rect.top = 0
//        rect.bottom = getHeight()
//        canvas.drawRect(rect, mPaint)

        mPaint.color = Color.rgb(131, 176, 221)
        val sideAndWidth = (sideOfSquare + widthOfFields)
        val numWidth = width / sideAndWidth
        val numHeight = height / sideAndWidth
        for (i in 1..numWidth) {
            val rect = Rect()

            rect.top = 0
            rect.left = i * sideAndWidth
            rect.bottom = height
            rect.right = i * sideAndWidth + widthOfFields

            canvas.drawRect(rect, mPaint)
        }
        for (i in 1..numHeight) {
            val rect = Rect()

            rect.top = i * sideAndWidth
            rect.left = 0
            rect.bottom = i * sideAndWidth + widthOfFields
            rect.right = width

            canvas.drawRect(rect, mPaint)
        }
    }

    protected fun drawWallOrCellStyleNotebook2(canvas: Canvas, xy: XY, passability: Boolean) {
        val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        val mPath = Path()
        val mCornerPathEffect = CornerPathEffect(60.0f)

//        mPaint.pathEffect = mCornerPathEffect
        if (passability)
//            mPaint.color = 0xFFFFF6D4.toInt()
            mPaint.color = Color.rgb(255, 252, 242)
        else {
            mPath.reset();
            mPaint.isAntiAlias = true
//            mPaint.isAntiAlias = true;
            mPaint.color = Color.rgb(52, 70, 137)
            mPaint.strokeWidth = 2f
            mPaint.isAntiAlias = true
            mPaint.style = Paint.Style.STROKE

            mPath.moveTo(xy.x.toFloat(), (xy.y + (sideOfSquare * 0.05)).toFloat())
            mPath.lineTo((xy.x + (sideOfSquare * 0.05)).toFloat(), (xy.y + (sideOfSquare * 0.00)).toFloat())
            mPath.lineTo((xy.x + (sideOfSquare * 0.00)).toFloat(), (xy.y + (sideOfSquare * 0.12)).toFloat())
            mPath.lineTo((xy.x + (sideOfSquare * 0.18)).toFloat(), (xy.y + (sideOfSquare * 0.0)).toFloat())
            mPath.lineTo((xy.x + (sideOfSquare * 0.0)).toFloat(), (xy.y + (sideOfSquare * 0.24)).toFloat())
            mPath.lineTo((xy.x + (sideOfSquare * 0.30)).toFloat(), (xy.y + (sideOfSquare * 0.0)).toFloat())
            mPath.lineTo((xy.x + (sideOfSquare * 0.0)).toFloat(), (xy.y + (sideOfSquare * 0.36)).toFloat())
            mPath.lineTo((xy.x + (sideOfSquare * 0.42)).toFloat(), (xy.y + (sideOfSquare * 0.0)).toFloat())
            mPath.lineTo((xy.x + (sideOfSquare * 0.0)).toFloat(), (xy.y + (sideOfSquare * 0.49)).toFloat())
            mPath.lineTo((xy.x + (sideOfSquare * 0.53)).toFloat(), (xy.y + (sideOfSquare * 0.00)).toFloat())
            mPath.lineTo((xy.x + (sideOfSquare * 0.00)).toFloat(), (xy.y + (sideOfSquare * 0.58)).toFloat())
            mPath.lineTo((xy.x + (sideOfSquare * 0.63)).toFloat(), (xy.y + (sideOfSquare * 0.0)).toFloat())
            mPath.lineTo((xy.x + (sideOfSquare * 0.0)).toFloat(), (xy.y + (sideOfSquare * 0.67)).toFloat())
            mPath.lineTo((xy.x + (sideOfSquare * 0.72)).toFloat(), (xy.y + (sideOfSquare * 0.0)).toFloat())
            mPath.lineTo((xy.x + (sideOfSquare * 0.0)).toFloat(), (xy.y + (sideOfSquare * 0.78)).toFloat())
            mPath.lineTo((xy.x + (sideOfSquare * 0.85)).toFloat(), (xy.y + (sideOfSquare * 0.0)).toFloat())
            mPath.lineTo((xy.x + (sideOfSquare * 0.0)).toFloat(), (xy.y + (sideOfSquare * 0.92)).toFloat())
            mPath.lineTo((xy.x + (sideOfSquare * 0.98)).toFloat(), (xy.y + (sideOfSquare * 0.0)).toFloat())
            mPath.lineTo((xy.x + (sideOfSquare * 1.0)).toFloat(), (xy.y + (sideOfSquare * 0.96)).toFloat())
            mPath.lineTo((xy.x + (sideOfSquare * 0.95)).toFloat(), (xy.y + (sideOfSquare * 1.0)).toFloat())
            mPath.lineTo((xy.x + (sideOfSquare * 1)).toFloat(), (xy.y + (sideOfSquare * 0.89)).toFloat())
            mPath.lineTo((xy.x + (sideOfSquare * 0.82)).toFloat(), (xy.y + (sideOfSquare * 1.0)).toFloat())
            mPath.lineTo((xy.x + (sideOfSquare * 1)).toFloat(), (xy.y + (sideOfSquare * 0.77)).toFloat())
            mPath.lineTo((xy.x + (sideOfSquare * 0.73)).toFloat(), (xy.y + (sideOfSquare * 1.0)).toFloat())
            mPath.lineTo((xy.x + (sideOfSquare * 1)).toFloat(), (xy.y + (sideOfSquare * 0.68)).toFloat())
            mPath.lineTo((xy.x + (sideOfSquare * 0.61)).toFloat(), (xy.y + (sideOfSquare * 1.0)).toFloat())
            mPath.lineTo((xy.x + (sideOfSquare * 1)).toFloat(), (xy.y + (sideOfSquare * 0.56)).toFloat())
            mPath.lineTo((xy.x + (sideOfSquare * 0.49)).toFloat(), (xy.y + (sideOfSquare * 1.0)).toFloat())
            mPath.lineTo((xy.x + (sideOfSquare * 1)).toFloat(), (xy.y + (sideOfSquare * 0.42)).toFloat())
            mPath.lineTo((xy.x + (sideOfSquare * 0.36)).toFloat(), (xy.y + (sideOfSquare * 1.0)).toFloat())
            mPath.lineTo((xy.x + (sideOfSquare * 1)).toFloat(), (xy.y + (sideOfSquare * 0.30)).toFloat())
            mPath.lineTo((xy.x + (sideOfSquare * 0.26)).toFloat(), (xy.y + (sideOfSquare * 1.0)).toFloat())
            mPath.lineTo((xy.x + (sideOfSquare * 1)).toFloat(), (xy.y + (sideOfSquare * 0.21)).toFloat())
            mPath.lineTo((xy.x + (sideOfSquare * 0.16)).toFloat(), (xy.y + (sideOfSquare * 1.0)).toFloat())
            mPath.lineTo((xy.x + (sideOfSquare * 1)).toFloat(), (xy.y + (sideOfSquare * 0.10)).toFloat())
            mPath.lineTo((xy.x + (sideOfSquare * 0.03)).toFloat(), (xy.y + (sideOfSquare * 1.0)).toFloat())
//            mPath.lineTo((xy.x + (sideOfSquare * 1)).toFloat(), (xy.y + (sideOfSquare * 0.21)).toFloat())
//            mPath.lineTo((xy.x + (sideOfSquare * 0.76)).toFloat(), (xy.y + (sideOfSquare * 1.0)).toFloat())
//            mPath.lineTo((xy.x + (sideOfSquare * 1)).toFloat(), (xy.y + (sideOfSquare * 0.60)).toFloat())
//            mPath.lineTo((xy.x + (sideOfSquare * 0.48)).toFloat(), (xy.y + (sideOfSquare * 1)).toFloat())
//            mPath.lineTo((xy.x + (sideOfSquare * 1)).toFloat(), (xy.y + (sideOfSquare * 0.35)).toFloat())
//            mPath.lineTo((xy.x + (sideOfSquare * 0.26)).toFloat(), (xy.y + (sideOfSquare * 1)).toFloat())
//            mPath.lineTo((xy.x + (sideOfSquare * 1)).toFloat(), (xy.y + (sideOfSquare * 0.14)).toFloat())
//            mPath.lineTo((xy.x + (sideOfSquare * 0)).toFloat(), (xy.y + (sideOfSquare * 1)).toFloat())
            canvas.drawPath(mPath, mPaint)
        }
    }

    protected fun drawWallOrCellStyleNotebook1(canvas: Canvas, xy: XY, passability: Boolean) {
        val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        val mPath = Path()
        val mCornerPathEffect = CornerPathEffect(60.0f)

//        mPaint.pathEffect = mCornerPathEffect
        if (passability)
//            mPaint.color = 0xFFFFF6D4.toInt()
            mPaint.color = Color.rgb(255, 252, 242)
        else {
            mPath.reset();
            mPaint.isAntiAlias = true
//            mPaint.isAntiAlias = true;
            mPaint.color = Color.rgb(52, 70, 137)
            mPaint.strokeWidth = 2f
            mPaint.isAntiAlias = true
            mPaint.style = Paint.Style.STROKE

            for (i in 1..6) {
                val index = 0.15
                mPath.moveTo((xy.x + (sideOfSquare * (index * i))).toFloat(), (xy.y + (sideOfSquare * 0.00)).toFloat())
                mPath.lineTo((xy.x + (sideOfSquare * 0)).toFloat(), (xy.y + (sideOfSquare * (index * i))).toFloat())
                mPath.moveTo((xy.x + (sideOfSquare * (index * i))).toFloat(), (xy.y + (sideOfSquare * 1.00)).toFloat())
                mPath.lineTo((xy.x + (sideOfSquare * 1)).toFloat(), (xy.y + (sideOfSquare * (index * i))).toFloat())

                mPath.moveTo(
                    (xy.x + (sideOfSquare * (1 - (index * i)))).toFloat(),
                    (xy.y + (sideOfSquare * 0.00)).toFloat()
                )
                mPath.lineTo((xy.x + (sideOfSquare * 1)).toFloat(), (xy.y + (sideOfSquare * ((index * i)))).toFloat())
                mPath.moveTo((xy.x + (sideOfSquare * 0).toFloat()), (xy.y + (sideOfSquare * (index * i))).toFloat())
                mPath.lineTo(
                    (xy.x + (sideOfSquare * (1 - ((index * i))))).toFloat(),
                    (xy.y + (sideOfSquare * 1)).toFloat()
                )
            }

            canvas.drawPath(mPath, mPaint)
        }
    }
}



