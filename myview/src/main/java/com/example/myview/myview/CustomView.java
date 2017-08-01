package com.example.myview.myview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by xuwei on 2017/7/10.
 */

public class CustomView extends View {
    private int lastX;
    private int lastY;
    private int offsetX;
    private int offsetY;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //获取点击事件的坐标
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                //获取移动的距离
                offsetX = x - lastX;
                offsetY = y - lastY;

                //方法1：调用layout()方法来重新放置View的位置
//                layout(getLeft() + offsetX, getTop() + offsetY, getRight() + offsetX, getBottom() + offsetY);

                //方法2：调用offsetLeftAndRight()和offsetTopAndBottom()方法
                //对left和right进行偏移
//                offsetLeftAndRight(offsetX);
                //对top和bottom进行偏移
//                offsetTopAndBottom(offsetY);

                //方法3：LayoutParams（改变布局参数）
//                因为父控件是LinearLayout，所以我们用了LinearLayout.LayoutParams，如果父控件是RelativeLayout则要使用RelativeLayout.LayoutParams。除了使用布局的LayoutParams外，我们还可以用ViewGroup.MarginLayoutParams来实现：
//                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams();
//                layoutParams.leftMargin = getLeft() + offsetX;
//                layoutParams.topMargin = getTop() + offsetY;
//                setLayoutParams(layoutParams);

                //方法4：除了使用布局的LayoutParams外，我们还可以用ViewGroup.MarginLayoutParams来实现：
//                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
//                marginLayoutParams.leftMargin = getLeft() + offsetX;
//                marginLayoutParams.topMargin = getTop() + offsetY;
//                setLayoutParams(marginLayoutParams);

                //方法5：scollTo(x,y)表示移动到一个具体的坐标点，而scollBy(dx,dy)则表示移动的增量为dx、dy。
                // 其中scollBy最终也是要调用scollTo的。scollTo、scollBy移动的是View的内容，
                // 如果在ViewGroup中使用则是移动他所有的子View。我们将ACTION_MOVE中的代码替换成如下代码：
                // 这里要实现CustomView随着我们手指移动的效果的话，我们就需要将偏移量设置为负值。
                ((View)getParent()).scrollBy(-offsetX,-offsetY);
                break;
        }
        return true;
    }
}
