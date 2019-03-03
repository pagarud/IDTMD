package io.pagarusha.geoguess.utilities;

import android.view.GestureDetector;
import android.view.MotionEvent;

public class OnSwipeListener extends GestureDetector.SimpleOnGestureListener {

    private static final String TAG = "GESTURE";
    private float initialX;
    private float initialY;

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        this.initialX = e1.getX();
        this.initialY = e1.getY();

        if (e2 != null ) {

            float x1 = e1.getX();
            float y1 = e1.getY();

            float x2 = e2.getX();
            float y2 = e2.getY();

            Direction direction = getDirection(x1, y1, x2, y2);

            return onSwipe(direction);
        } else {

            return true;
        }
    }

    public float getInitialX() {
        return initialX;
    }

    public float getInitialY() {
        return initialY;
    }

    public boolean onSwipe(Direction direction) {

        return false;
    }

    /**
     * Get direction of arrow going from (x1, y1) to (x2, y2).
     *
     * @param x1 X coordinate of first point.
     * @param y1 Y coordinate of first point.
     * @param x2 X coordinate of second point.
     * @param y2 Y coordinate of second point.
     * @return A direction, possible values are up, down, left and right.
     */
    private Direction getDirection(float x1, float y1, float x2, float y2) {
        double angle = getAngle(x1, y1, x2, y2);

        return Direction.fromAngle(angle);
    }

    /**
     * Calculates the angle with respect to the x-axis of an arrow from
     * point (x1, y1) to (x2, y2).
     *
     * @param x1 X coordinate of first point.
     * @param y1 Y coordinate of first point.
     * @param x2 X coordinate of second point.
     * @param y2 Y coordinate of second point.
     * @return An angle in degrees in the range [0, 360).
     */
    public double getAngle(float x1, float y1, float x2, float y2) {
        double rad = Math.atan2(y1 - y2, x2 - x1) + Math.PI;
        double angle = (180 * rad / Math.PI + 180) % 360;

        return angle;
    }
}
