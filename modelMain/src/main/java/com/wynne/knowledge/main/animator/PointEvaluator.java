package com.wynne.knowledge.main.animator;

import android.animation.TypeEvaluator;

/**
 * @author Wynne
 * @date 2018/10/24
 */

public class PointEvaluator implements TypeEvaluator<Point> {
    private Point point = new Point();

    @Override
    public Point evaluate(float fraction, Point startValue, Point endValue) {
        point.x = (int) (startValue.x + (endValue.x - startValue.x) * fraction);

        if (fraction * 2 < 1) {
            point.y = (int) (startValue.x + (endValue.x - startValue.x) * fraction * 2);
        } else {
            point.y = endValue.y;
        }
        return point;
    }
}
