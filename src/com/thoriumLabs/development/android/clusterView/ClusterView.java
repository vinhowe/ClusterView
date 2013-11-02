package com.thoriumLabs.development.android.clusterView;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.ClipData.Item;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.*;
import android.graphics.BlurMaskFilter.Blur;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.Shape;
import android.os.Build;
import android.util.AttributeSet;
import android.view.*;
import android.widget.Scroller;
import com.actionbarsherlock.*;

import java.lang.Math;
import java.lang.Override;
import java.util.Random;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

import com.thoriumLabs.development.android.circlecluster.R;

/**
 * Custom view that shows a pie chart and, optionally, a label.
 */
public class ClusterView extends View {
    private List<Item> mData = new ArrayList<Item>();

    private Paint mPiePaint;
    private Paint mTextPaint;
    private Paint[] mClusterPaint;

    int determinedRadius;
    int determinedX;
    int determinedY;
    int ypadding;
    int xpadding;
    int xhalfMark;
    int yhalfMark;
    int negativeRadius;
    int xpositiveRadius;
    int ypositiveRadius;
    int distance;
    boolean firstDrawn;

    /**
     * Draw text to the left of the pie chart
     */
    public static final int TEXTPOS_LEFT = 1;

    /**
     * Draw text to the right of the pie chart
     */
    public static final int TEXTPOS_RIGHT = 0;

    /**
     * The initial fling velocity is divided by this amount.
     */
    public static final int FLING_VELOCITY_DOWNSCALE = 4;

    /**
     *
     */
    public static final int AUTOCENTER_ANIM_DURATION = 250;

    private Random rnd = new Random();

    Shape s;

    android.graphics.drawable.ShapeDrawable sd = new android.graphics.drawable.ShapeDrawable(new OvalShape());

    /**
     * Interface definition for a callback to be invoked when the current
     * item changes.
     */
    public interface OnCurrentItemChangedListener {
        void OnCurrentItemChanged(ClusterView source, int currentItem);
    }

    /**
     * Class constructor taking only a context. Use this constructor to create
     * {@link ClusterView} objects from your own code.
     *
     * @param context
     */
    public ClusterView(Context context) {
        super(context);
        init();
    }

    /**
     * Class constructor taking a context and an attribute set. This constructor
     * is used by the layout engine to construct a {@link ClusterView} from a set of
     * XML attributes.
     *
     * @param context
     * @param attrs   An attribute set which can contain attributes from
     *                {@link R.styleable} as well as attributes inherited
     *                from {@link android.view.View}.
     */
    public ClusterView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // attrs contains the raw values for the XML attributes
        // that were specified in the layout, which don't include
        // attributes set by styles or themes, and which may have
        // unresolved references. Call obtainStyledAttributes()
        // to get the final values for each attribute.
        //
        // This call uses R.styleable.PieChart, which is an array of
        // the custom attributes that were declared in attrs.xml.
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.ClusterView,
                0, 0
        );

        try {
            // Retrieve the values from the TypedArray and store into
            // fields of this class.
            //
            // The R.styleable.PieChart_* constants represent the index for
            // each custom attribute in the R.styleable.PieChart array.
            //mShowText = a.getBoolean(R.styleable.ClusterView_showText, false);
            //mTextY = a.getDimension(R.styleable.ClusterView_labelY, 0.0f);
            //mTextWidth = a.getDimension(R.styleable.ClusterView_labelWidth, 0.0f);
            //mTextHeight = a.getDimension(R.styleable.ClusterView_labelHeight, 0.0f);
            //mTextPos = a.getInteger(R.styleable.ClusterView_labelPosition, 0);
            //mTextColor = a.getColor(R.styleable.ClusterView_labelColor, 0xff000000);
            //mHighlightStrength = a.getFloat(R.styleable.ClusterView_highlightStrength, 1.0f);
            //mPieRotation = a.getInt(R.styleable.ClusterView_pieRotation, 0);
            //mPointerRadius = a.getDimension(R.styleable.ClusterView_pointerRadius, 2.0f);
           // mAutoCenterInSlice = a.getBoolean(R.styleable.ClusterView_autoCenterPointerInSlice, false);
        } finally {
            // release the TypedArray so that it can be reused.
            a.recycle();
        }

        init();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        yhalfMark=(canvas.getHeight()/2);
        xhalfMark= canvas.getWidth()/2/2;
        ypadding=(canvas.getHeight()/20);
        xpadding=(canvas.getWidth())/20;
        xpositiveRadius=(xhalfMark - xpadding);
        ypositiveRadius=yhalfMark - ypadding;
        // Draw the demo circle
        //sd.draw(canvas);
        if (!firstDrawn){
        canvas.drawCircle(xhalfMark-120,yhalfMark-120,ypositiveRadius, mClusterPaint[6]);
        canvas.drawCircle(xhalfMark-100,yhalfMark-100,ypositiveRadius, mClusterPaint[5]);
        canvas.drawCircle(xhalfMark-80,yhalfMark-80,ypositiveRadius, mClusterPaint[4]);
        canvas.drawCircle(xhalfMark-60,yhalfMark-60,ypositiveRadius, mClusterPaint[3]);
        canvas.drawCircle(xhalfMark-40,yhalfMark-40,ypositiveRadius, mClusterPaint[2]);
        canvas.drawCircle(xhalfMark-20,yhalfMark-20,ypositiveRadius, mClusterPaint[1]);
        canvas.drawCircle(xhalfMark,yhalfMark,ypositiveRadius, mClusterPaint[0]);
        firstDrawn = true;
        }
        //canvas.drawCircle((canvas.getWidth()/2)+determinedX,(canvas.getHeight()/2)+determinedY,determinedRadius, mClusterPaint);
        for(int i = 0; i < 3; i++){
        do
        {
            determinedX=rnd.nextInt(ypositiveRadius*2) - ypositiveRadius+xhalfMark;
            determinedY=rnd.nextInt(ypositiveRadius*2) - ypositiveRadius+yhalfMark;
        }while(Math.sqrt( Math.pow((determinedX - xhalfMark),2) + Math.pow((determinedY - yhalfMark),2))> ypositiveRadius);
        distance = (int) Math.sqrt( Math.pow((determinedX - xhalfMark),2) + Math.pow((determinedY - yhalfMark),2));
        determinedRadius= Math.abs(ypositiveRadius-distance);
        canvas.drawCircle(determinedX,determinedY, determinedRadius, mClusterPaint[0]);
        }
        }
    /**
     * Initialize the control. This code is in a separate method so that it can be
     * called from both constructors.
     */
    private void init() {
        // Force the background to software rendering because otherwise the Blur
        // filter won't work.
        setLayerToSW(this);

        // Set up the paint for the label text
        //mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //mTextPaint.setColor(mTextColor);
        //if (mTextHeight == 0) {
          //  mTextHeight = mTextPaint.getTextSize();
        //} else {
          //  mTextPaint.setTextSize(mTextHeight);
        //}

        // Set up the paint for the pie slices
        //mPiePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //mPiePaint.setStyle(Paint.Style.STROKE);
        //mPiePaint.setTextSize(mTextHeight);

        // Set up the paint for the shadow
        mClusterPaint = new Paint[60];
        float[][] hsv = new float[10][3];
        float blur = 1;
        for (int i = 0; 10>i; i++){
        mClusterPaint[i] = new Paint(Paint.ANTI_ALIAS_FLAG);
        mClusterPaint[i].setStyle(Paint.Style.STROKE);
        mClusterPaint[i].setStrokeWidth(3F);
        Color.RGBToHSV(Color.red(Color.parseColor("#33B5E5")),Color.green(Color.parseColor("#33B5E5")),Color.blue(Color.parseColor("#33B5E5")),hsv[i]);
        hsv[i][1]=hsv[i][1]-i*2/8.88F;
        //hsv[i][2]=hsv[i][1]-i/18.88F;
        mClusterPaint[i].setColor(Color.HSVToColor(hsv[i]));
        mClusterPaint[i].setMaskFilter(new BlurMaskFilter(blur, Blur.NORMAL));
        blur=blur*1.33F;
    }
    }
    private void setLayerToSW(View v) {
        if (!v.isInEditMode() && Build.VERSION.SDK_INT >= 11) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
    }

    private void setLayerToHW(View v) {
        if (!v.isInEditMode() && Build.VERSION.SDK_INT >= 11) {
            setLayerType(View.LAYER_TYPE_HARDWARE, null);
        }
    }
    }

