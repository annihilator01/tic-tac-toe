package com.ilian.tictactoe.frontend.customviews;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Win-line.
 */
public class PathView extends View {
    Path path;
    Paint paint;
    float length;

    public PathView(Context context) {
        super(context);
    }

    public PathView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PathView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Start animation of win-line.
     * @param activity - activity where to start.
     * @param color - color id of win-line
     * @param startX - x location of most-left figure in winning sequence
     * @param startY - y location of most-left figure in winning sequence
     * @param endX - x location of most-right figure in winning sequence
     * @param endY - y location of most-right figure in winning sequence
     * @param strokeWidth - stroke width of win-line.
     */
    public void init(AppCompatActivity activity, int color, int startX, int startY, int endX, int endY, int strokeWidth) {
        paint = new Paint();
        paint.setColor(color);
        paint.setStrokeWidth(strokeWidth);
        paint.setStyle(Paint.Style.STROKE);

        path = new Path();
        path.moveTo(startX, startY);
        path.lineTo(endX, endY);

        // Measure the path
        PathMeasure measure = new PathMeasure(path, false);
        length = measure.getLength();

        float[] intervals = new float[]{length, length};

        ObjectAnimator animator = ObjectAnimator.ofFloat(PathView.this, "phase", 1.0f, 0.0f);
        animator.setDuration(1500);
        animator.start();
    }

    public void setPhase(float phase) {
        paint.setPathEffect(createPathEffect(length, phase));
        invalidate();
    }

    /**
     * Creating path effect.
     * @param pathLength
     * @param phase
     * @return PathEffect
     */
    private static PathEffect createPathEffect(float pathLength, float phase) {
        return new DashPathEffect(new float[] {pathLength, pathLength},
                Math.max(phase * pathLength, (float) 0.0));
    }

    /**
     * Draw path on draw.
     * @param c
     */
    @Override
    public void onDraw(Canvas c) {
        super.onDraw(c);
        c.drawPath(path, paint);
    }
}