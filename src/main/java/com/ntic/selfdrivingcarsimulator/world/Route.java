package com.ntic.selfdrivingcarsimulator.world;

import javafx.scene.shape.Rectangle;

import java.util.Objects;
import java.util.function.Supplier;

public class Route{

    private Rectangle route;
    private Intersection start;
    private Intersection end;

    public Route(Rectangle route, Intersection start, Intersection end) {
        this.route = route;
        this.start = start;
        this.end = end;
    }

    public Rectangle getRoute() {
        return route;
    }

    public Intersection getStart() {
        return start;
    }

    public Intersection getEnd() {
        return end;
    }
}
