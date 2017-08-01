package com.example.myview.myview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by xuwei on 2017/7/14.
 */

public class HorizontalView extends ViewGroup {
    private int lastInterceptX;
    private int lastInterceptY;
    private int lastX;
    private int lastY;
    private int currentIndex = 0;
    private int childWidth = 0;
    //Scroller只是个计算器，提供插值计算，让滚动过程具有动画属性，
    // 但它并不是UI，也不是辅助UI滑动，反而是单纯地为滑动提供计算。
    private Scroller scroller;
    private VelocityTracker tracker; //增加速度检测,如果速度比较快的话,就算没有滑动超过一半的屏幕也可以

    public HorizontalView(Context context) {
        super(context);
        init();
    }

    public HorizontalView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HorizontalView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        scroller = new Scroller(getContext());
        tracker = VelocityTracker.obtain();
    }

    @Override
    protected void onLayout(boolean change, int l, int t, int r, int b) {
        /**
         * 遍历所有的子元素，如果子元素不是GONE，则调用子元素的layout方法将其放置到合适的位置上，
         * 相当于默认第一个子元素占满了屏幕，后面的子元素就是在第一个屏幕后面紧挨着和屏幕一样大小的后续元素，
         * 所以left是一直累加的，top保持0，bottom保持第一个元素的高度，right就是left+元素的宽度，
         * 同样这里没有处理自身的pading以及子元素的margin。
         */
        View child;
        int childCount = getChildCount();
        int left = 0;
        for (int i = 0; i < childCount; i++) {
            child = getChildAt(i);
            if (child.getVisibility() != View.GONE) {
                int width = child.getMeasuredWidth();
                //赋值为子元素的宽度
                childWidth = width;
                child.layout(left, 0, left + width, child.getMeasuredHeight());
                left += width;
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        /**
         * 注：这里如果没有子元素时采用了简化的写法直接将宽和高直接设置为0，
         * 正常的话我们应该根据LayoutParams中的宽和高来做相应的处理，
         * 另外我们在测量时没有考虑它的padding和子元素的margin。
         */
        if (getChildCount() == 0) { //简单处理，如果没有子View，直接设置宽高为0
            setMeasuredDimension(0, 0);
            //宽和高都是AT_MOST，则设置宽度所有子元素的宽度的和；高度设置为第一个元素的高度；
        } else if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            View childOne = getChildAt(0);
            int childWidth = childOne.getMeasuredWidth();
            int childHeight = childOne.getMeasuredHeight();
            setMeasuredDimension(childWidth * getChildCount(), childHeight);
            //如果宽度是wrap_content，则宽度为所有子元素的宽度的和
        } else if (widthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(getChildAt(0).getMeasuredWidth() * getChildCount(), heightSize);
            //如果高度是wrap_content，则高度为第一个子元素的高度
        } else if (heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSize, getChildAt(0).getMeasuredHeight());
        }
    }

    /**
     * 处理滑动冲突
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept = false;
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                intercept = false;
                if (!scroller.isFinished()) {
                    scroller.abortAnimation(); //如果动画还没有执行完成，则打断
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - lastInterceptX;
                int deltaY = y - lastInterceptY;
                //用户想水平滑动的，所以拦截   Math.abs()：求绝对值
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    intercept = true;
                } else {
                    intercept = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        //因为DOWN返回false,所以onTouchEvent中无法获取DOWN事件,这里要负责设置lastX,lastY
        lastX = x;
        lastY = y;
        lastInterceptX = x;
        lastInterceptY = y;
        return intercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        tracker.addMovement(event); //将MotionEvent加入到VelocityTracker类实例中
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!scroller.isFinished()) {
                    scroller.abortAnimation();
                }
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - lastX;//跟随手指滑动
                scrollBy(-deltaX, 0);
                break;
            case MotionEvent.ACTION_UP:
                int instance = getScrollX() - currentIndex * childWidth;
                //滑动的距离要大于1/2个宽度,否则不会切换到其他页面
                if (Math.abs(instance) > childWidth / 2) {
                    if (instance > 0) {
                        currentIndex++;
                    } else {
                        currentIndex--;
                    }
                } else {
                    //设置units的值为1000，意思为一秒时间内运动了多少个像素
                    tracker.computeCurrentVelocity(1000);
                    float xV = tracker.getXVelocity(); //获取横向的速率
                    if (Math.abs(xV) > 50) {
                        if (xV > 0) {
                            currentIndex++;
                        } else {
                            currentIndex--;
                        }
                    }
                }
                currentIndex = currentIndex < 0 ? 0 : currentIndex > getChildCount() - 1 ? getChildCount() - 1 : currentIndex;
                smoothScrollTo(currentIndex * childWidth, 0);
                tracker.clear();
                break;
        }
        lastX = x;
        lastY = y;
        return true;
    }

    //弹性滑动到指定位置
    private void smoothScrollTo(int destX, int destY) {
        scroller.startScroll(getScrollX(), getScrollY(), destX - getScrollX(), destY - getScrollY());
        invalidate();
    }

    /**
     * 注：computeScroll也不是来让ViewGroup滑动的，真正让ViewGroup滑动的是scrollTo,scrollBy。
     * computeScroll的作用是计算ViewGroup如何滑动。而computeScroll是通过draw来调用的。
     * computeScroll的连续性受invalidate方法控制，scrollTo,scrollBy都会调用invalidate，
     * 而invalidate会去触发draw,从而computeScroll被连续调用
     */
    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()) { //判断是否还在滚动，还在滚动为true
            /**
             * scollTo(x,y)表示移动到一个具体的坐标点，而scollBy(dx,dy)则表示移动的增量为dx、dy。
             * 其中scollBy最终也是要调用scollTo的。scollTo、scollBy移动的是View的内容，
             * 如果在ViewGroup中使用则是移动他所有的子View.
             */
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            //注意：postInvalidate()一定是在判断if条件满足的条件下（表示滑动还没有完成），才能调用的。
            postInvalidate(); //更新界面
        }
    }
}
