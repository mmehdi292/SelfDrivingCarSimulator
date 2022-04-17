package com.ntic.selfdrivingcarsimulator.world;

import javafx.scene.shape.Rectangle;

public class Intersection {

    private Rectangle route;
    private Route top;
    private Route right;
    private Route bottom;
    private Route left;

    public Intersection(Rectangle route, Route top, Route right, Route bottom, Route left) {
        this.route = route;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.left = left;
    }

    public Rectangle getRoute() {
        return route;
    }

    public Route getTop() {
        return top;
    }

    public Route getRight() {
        return right;
    }

    public Route getBottom() {
        return bottom;
    }

    public Route getLeft() {
        return left;
    }
}
