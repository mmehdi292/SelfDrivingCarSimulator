package com.ntic.selfdrivingcarsimulator.gui;

import com.ntic.selfdrivingcarsimulator.world.Intersection;
import com.ntic.selfdrivingcarsimulator.world.Route;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

public class Map {

    AnchorPane root;
    Rectangle zone1,zone2,zone3,zone4,zone5,zone6,zone7,zone8,zone9,zone10,zone11,zone12,zone13,zone14,zone15,zone16;
    Route route1,route2,route3,route4,route5,route6,route7,route8,route9,route10,route11,route12,route13,route14,route15,route16,route17;
    Intersection intersection1,intersection2,intersection3,intersection4,intersection5,intersection6;

    public Map(){
        root = new AnchorPane();

        zone1 = new Rectangle();
        zone1.setArcHeight(5.0);
        zone1.setArcWidth(5.0);
        zone1.setFill(Color.GREEN);
        zone1.setWidth(139);
        zone1.setHeight(314);
        zone1.setStroke(Color.BLACK);
        zone1.setStrokeType(StrokeType.INSIDE);

        zone2 = new Rectangle();
        zone2.setArcHeight(5.0);
        zone2.setArcWidth(5.0);
        zone2.setFill(Color.GREEN);
        zone2.setWidth(314);
        zone2.setHeight(139);
        zone2.setStroke(Color.BLACK);
        zone2.setStrokeType(StrokeType.INSIDE);


        zone1 = new Rectangle();
        zone1.setArcHeight(5.0);
        zone1.setArcWidth(5.0);
        zone1.setFill(Color.GREEN);
        zone1.setWidth(139);
        zone1.setHeight(314);
        zone1.setStroke(Color.BLACK);
        zone1.setStrokeType(StrokeType.INSIDE);


        zone1 = new Rectangle();
        zone1.setArcHeight(5.0);
        zone1.setArcWidth(5.0);
        zone1.setFill(Color.GREEN);
        zone1.setWidth(139);
        zone1.setHeight(314);
        zone1.setStroke(Color.BLACK);
        zone1.setStrokeType(StrokeType.INSIDE);


        zone1 = new Rectangle();
        zone1.setArcHeight(5.0);
        zone1.setArcWidth(5.0);
        zone1.setFill(Color.GREEN);
        zone1.setWidth(139);
        zone1.setHeight(314);
        zone1.setStroke(Color.BLACK);
        zone1.setStrokeType(StrokeType.INSIDE);


        zone1 = new Rectangle();
        zone1.setArcHeight(5.0);
        zone1.setArcWidth(5.0);
        zone1.setFill(Color.GREEN);
        zone1.setWidth(139);
        zone1.setHeight(314);
        zone1.setStroke(Color.BLACK);
        zone1.setStrokeType(StrokeType.INSIDE);

        zone1 = new Rectangle();
        zone1.setArcHeight(5.0);
        zone1.setArcWidth(5.0);
        zone1.setFill(Color.GREEN);
        zone1.setWidth(139);
        zone1.setHeight(314);
        zone1.setStroke(Color.BLACK);
        zone1.setStrokeType(StrokeType.INSIDE);

        zone1 = new Rectangle();
        zone1.setArcHeight(5.0);
        zone1.setArcWidth(5.0);
        zone1.setFill(Color.GREEN);
        zone1.setWidth(139);
        zone1.setHeight(314);
        zone1.setStroke(Color.BLACK);
        zone1.setStrokeType(StrokeType.INSIDE);

        zone1 = new Rectangle();
        zone1.setArcHeight(5.0);
        zone1.setArcWidth(5.0);
        zone1.setFill(Color.GREEN);
        zone1.setWidth(139);
        zone1.setHeight(314);
        zone1.setStroke(Color.BLACK);
        zone1.setStrokeType(StrokeType.INSIDE);

        zone1 = new Rectangle();
        zone1.setArcHeight(5.0);
        zone1.setArcWidth(5.0);
        zone1.setFill(Color.GREEN);
        zone1.setWidth(139);
        zone1.setHeight(314);
        zone1.setStroke(Color.BLACK);
        zone1.setStrokeType(StrokeType.INSIDE);
    }

}
