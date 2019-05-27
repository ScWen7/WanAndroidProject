package com.scwen.wanandroid.weight

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.scwen.wanandroid.R

/**
 * Created by scwen on 2019/5/27.
 *  QQ ：811733738
 *  作用：
 */
class ShadowView : View {
    val DEFAULT_PADDING = 40
    val DEFAULT_OFFSET = 40
    val DEFAULT_SHADOW_RADIUS = 40


    var mPaintShadow: Paint? = null

    private var mRadius = 0

    private var mPadding = 0


    private var mMainColor = -1

    private var mOffsetY = DEFAULT_OFFSET

    private var mShadowRadius = DEFAULT_SHADOW_RADIUS

    private var mRectfShadow: RectF? = null


    private var buttonHeight = 0


    constructor(context: Context) : this(context, null) {}


    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView(context, attrs)
    }

    private fun initView(context: Context, attrs: AttributeSet?) {
        attrs?.let {
            var a: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.ShadowView)
            mRadius = a.getDimensionPixelSize(R.styleable.ShadowView_paletteRadius, 0)
            mPadding = a.getDimensionPixelSize(R.styleable.ShadowView_palettePadding, DEFAULT_PADDING)
            mOffsetY = a.getDimensionPixelSize(R.styleable.ShadowView_paletteOffsetY, DEFAULT_OFFSET)
            mShadowRadius = a.getDimensionPixelSize(R.styleable.ShadowView_paletteShadowRadius, DEFAULT_SHADOW_RADIUS)
            buttonHeight = a.getDimensionPixelSize(R.styleable.ShadowView_buttonHeight, DEFAULT_SHADOW_RADIUS)
            mMainColor =
                a.getColor(R.styleable.ShadowView_mainColor, ContextCompat.getColor(context, R.color.alphaff9c27))
            a.recycle()

        }

        mPaintShadow = Paint()
        mPaintShadow?.isDither = true

        setLayerType(LAYER_TYPE_SOFTWARE, null)
        mRadius = buttonHeight / 2

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = View.MeasureSpec.getSize(widthMeasureSpec)
        val widthMode = View.MeasureSpec.getMode(widthMeasureSpec)
        var height = View.MeasureSpec.getSize(heightMeasureSpec)

        height = buttonHeight + mPadding + mOffsetY
        setMeasuredDimension(width, height)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mRectfShadow = RectF(mPadding.toFloat(),0f, (width-mPadding).toFloat(), buttonHeight.toFloat())

        mPaintShadow?.setShadowLayer(mShadowRadius.toFloat(),0f, mOffsetY.toFloat(),mMainColor)

        mPaintShadow?.setColor(Color.TRANSPARENT)

    }

    override fun onDraw(canvas: Canvas?) {
//        super.onDraw(canvas)
        canvas?.drawRoundRect(mRectfShadow, mRadius.toFloat(), mRadius.toFloat(), mPaintShadow)
    }


}
