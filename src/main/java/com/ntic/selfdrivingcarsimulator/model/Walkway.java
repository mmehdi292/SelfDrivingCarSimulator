package com.ntic.selfdrivingcarsimulator.model;

import javafx.scene.shape.Rectangle;

public class Walkway {

    private Rectangle walkwayUI;
    private Boolean isOccupied;

    public Walkway(Rectangle walkwayUI) {
        this.walkwayUI = walkwayUI;
        this.isOccupied = false;
    }

    public Rectangle getWalkwayUI() {
        return walkwayUI;
    }

    public void setWalkwayUI(Rectangle walkwayUI) {
        this.walkwayUI = walkwayUI;
    }

    public Boolean getOccupied() {
        return isOccupied;
    }

    public void setOccupied(Boolean occupied) {
        isOccupied = occupied;
    }
}
