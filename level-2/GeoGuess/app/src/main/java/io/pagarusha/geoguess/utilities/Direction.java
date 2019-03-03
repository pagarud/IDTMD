package io.pagarusha.geoguess.utilities;

public enum Direction {
    UP,
    DOWN,
    RIGHT,
    LEFT;

    /**
     * Returns a direction given an angle.
     * Directions are defined as follows:
     *
     * Right: [0, 45] and [315, 360]
     * Up: [45, 135]
     * Left: [135, 225]
     * Down: [225, 315]
     *
     * @param angle An angle in the range [0, 360).
     * @return The direction corresponding to the given angle.
     */
    public static Direction fromAngle(double angle) {

        if (inRange(angle, 0, 45) || inRange(angle, 315, 360)) {
            return Direction.RIGHT;
        } else if (inRange(angle, 45, 135)) {
            return Direction.UP;
        } else if (inRange(angle, 135, 225)) {
            return Direction.LEFT;
        } else {
            return Direction.DOWN;
        }
    }

    /**
     * Checks if an angle is within a given range.
     *
     * @param angle An angle
     * @param start The starting bound.
     * @param end The terminating bound.
     * @return Returns true if the given angle is in the interval [start, end)
     */
    public static boolean inRange(double angle, float start, float end) {

        return (angle >= start) && (angle < end);
    }
}
