package com.jinfukeji.shuntupinche.activity.owenr.refresh;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Scroller;
import android.widget.TextView;

import com.jinfukeji.shuntupinche.R;

/**
 * Created by "于志渊"
 * 时间:"15:00"
 * 包名:com.jinfukeji.shuaxin
 * 描述:
 */

public class RefreshView extends ViewGroup implements GestureDetector.OnGestureListener {
    /*手势转换类*/
    private GestureDetector mGesture ;
    /*滚动动画实现类*/
    private Scroller mScroller ;
    /*下来刷新头部View*/
    private View mHeaderView ;
    /*内容View，需要刷新显示的内容*/
    private View mContentView ;
    /*Xml View加载处理类*/
    private LayoutInflater mInflater ;
    /*为了防止重复执行，这里判断是否是第一次*/
    private boolean isFirst = true ;
    /*按下时记录Y轴的坐标*/
    private int mDownY ;

    /*是否开始拖拽*/
    private boolean mIsBeginDrag ;
    /*是否停止拖拽*/
    private boolean mIsStopDrag ;

    private AdapterView mAdapter ;
    /*刷新时动画显示的View*/
    private View mTextView ;
    /*根据此下拉的距离，从而触发下拉操作*/
    private int mRefreshHeight ;
    /*刷新头部的高度*/
    private int mHeaderHeight ;

    private final int STATUS_NORMAL = 0 ;
    private final int STATUS_REFRESH = 1 ;
    private final int STATUS_HIDE = 0 ;

    private int STATUS = STATUS_NORMAL;

    private int mDuration = 500;

    private ImageView mAnimView ;

    private AnimationDrawable mAnimDrawable ;

    public RefreshView(Context context) {
        this(context, null);
    }

    public RefreshView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefreshView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initRefreshView() ;
    }

    /**
     * 初始化ViewGroup的一些基本操作
     * 实例化GestureDetector
     * 实例化LayoutInflater
     * 添加头部View
     */
    private void initRefreshView() {
        mGesture = new GestureDetector(getContext(),this) ;
        mInflater = LayoutInflater.from(getContext()) ;
        mScroller = new Scroller(getContext());
        mHeaderView = mInflater.inflate(R.layout.refresh_header_view,null) ;
        mTextView = mHeaderView.findViewById(R.id.id_txt_header) ;
        mAnimView = (ImageView) mHeaderView.findViewById(R.id.id_anim_header);
        mAnimDrawable = (AnimationDrawable) mAnimView.getDrawable();
        mHeaderHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,130,
                getResources().getDisplayMetrics()) ;
        addView(mHeaderView);
    }

    @Override
    public void addView(View child, int index, LayoutParams params) {
        mContentView = child ;
        if (mContentView instanceof AdapterView){
            mAdapter = (AdapterView)  mContentView;
        }
        super.addView(child, index, params);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec) ;
        int height = MeasureSpec.getSize(heightMeasureSpec) ;
        measureChild(mHeaderView,widthMeasureSpec,MeasureSpec.makeMeasureSpec(mHeaderHeight ,MeasureSpec.EXACTLY));
        Log.v("zgy","==========mHeaderView============"+mHeaderView.getMeasuredHeight());
        measureChild(mContentView, widthMeasureSpec, MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST));
        mRefreshHeight = mTextView.getMeasuredHeight() ;
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mHeaderView.layout(0,0,mHeaderView.getMeasuredWidth(),mHeaderView.getMeasuredHeight());
        mContentView.layout(0,mHeaderView.getMeasuredHeight(),mContentView.getMeasuredWidth(),
                mHeaderView.getMeasuredHeight()+mContentView.getMeasuredHeight());
        if (isFirst){
            scrollTo(0,mHeaderView.getMeasuredHeight());
        }
        isFirst = false ;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP||event.getAction() == MotionEvent.ACTION_CANCEL){
            mIsBeginDrag = false ;
            scrollNormal() ;
        }
        return mGesture.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.v("zgy","====onInterceptTouchEvent====");
        if (mIsBeginDrag){
            return true ;
        }
        if(ev.getAction() == MotionEvent.ACTION_DOWN){
            mDownY = (int) ev.getY();
        }
        if (ev.getAction() == MotionEvent.ACTION_MOVE){
            int currentY = (int) ev.getY();
            if (isIntercept(currentY-mDownY)){
                ev.setAction(MotionEvent.ACTION_DOWN);
                onTouchEvent(ev) ;
                requestDisallowInterceptTouchEvent(true);
                mIsBeginDrag = true ;
                return true ;
            }
        }
        return super.onInterceptTouchEvent(ev);
    }

    private boolean isIntercept(int distance) {
        if(distance > 0){
            Log.v("zgy","====mAdapter===="+mAdapter);
            if(mAdapter != null){
                Log.v("zgy","====mAdapter===="+mAdapter);
                View firstChild =  mAdapter.getChildAt(0);
                if(firstChild != null){
                    if (firstChild.getTop() == 0){
                        return true ;
                    }
                }
            }
        }
        return false;
    }

    private void scrollNormal() {
        if (STATUS == STATUS_REFRESH){
            STATUS = STATUS_HIDE ;
            int scroll = mHeaderHeight - mRefreshHeight -getScrollY() ;
            int currentDuration = (int) (mDuration*0.6f* scroll/(mHeaderHeight - mRefreshHeight));
            mScroller.startScroll(0,getScrollY(),0,scroll,currentDuration);
            /*测试*/
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    stopRefresh();
                }
            },1000) ;
            mAnimView.setVisibility(VISIBLE);
            mAnimDrawable.start();
            invalidate();
        }else if(STATUS == STATUS_HIDE){
            STATUS = STATUS_NORMAL ;
            int scroll = mHeaderHeight - getScrollY() ;
            int currentDuration = mDuration* scroll/mHeaderHeight ;
            mScroller.startScroll(0,getScrollY(),0,scroll,currentDuration);
            mAnimView.setVisibility(View.INVISIBLE);
            mAnimDrawable.stop();
            invalidate();
        }
    }

    private void stopRefresh() {
        scrollNormal();
    }

    @Override
    public void computeScroll() {
        if(mScroller.computeScrollOffset()){
            Log.v("zgy","======onDown====="+mScroller.getCurrY());
            scrollTo(0, mScroller.getCurrY());
            invalidate();
        }
    }

     /*-----------------------------OnGestureListener----------------------------------------------*/

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        /*根据我前面所讲的Android事件处理全面剖析可知，这里应该返回true*/
        return true;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        /*这里是让下拉的View 越拉越紧，给人的感觉时越要用力*/
        distanceY = distanceY *  (0.8f * (getScrollY() * 1.0f / mHeaderHeight));
        int scrollY = cling(0, mHeaderHeight, getScrollY()+(int) distanceY) ;
        Log.v("zgy","=======onScroll===="+distanceY+",scrollY=="+scrollY+",getScrollY()="+getScrollY());
        scrollTo(0,scrollY);
        if (scrollY < mHeaderHeight-mRefreshHeight){
            ((TextView)mTextView).setText("松开可以刷新");
            STATUS = STATUS_REFRESH ;
        }else{
            ((TextView)mTextView).setText("下拉可以刷新");
            STATUS = STATUS_HIDE ;
        }
        return true;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    private int cling(int min, int max, int value) {
        return Math.min(Math.max(min, value), max) ;
    }
}
