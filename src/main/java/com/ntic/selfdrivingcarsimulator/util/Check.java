package com.ntic.selfdrivingcarsimulator.util;

import com.ntic.selfdrivingcarsimulator.gui.MapController;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Check {

    public static Rectangle checkingObstacle(MapController context,Circle circle){
        for(Rectangle obstacle : context.obstaclsList){
            if(context.checkCollision(circle,obstacle)){
                return obstacle;
            }
        }
        return null;
    }

    public static Rectangle checkingLight(MapController context,Circle circle){

        for(Rectangle passageLight : context.passageLightList){
            if(context.checkCollision(circle,passageLight)){
                return passageLight;
            }
        }
        return null;
    }

    public static Rectangle checkingSpeedPlaques(MapController context,Circle circle){

        for(Rectangle plaque : context.plaquesVitasse()){
            if(context.checkCollision(circle,plaque)){
                return plaque;
            }
        }
        return null;
    }

    public static Rectangle checkingStopPlaques(MapController context,Circle circle){

        for(Rectangle plaque : context.stopPlaquesList()){
            if(context.checkCollision(circle,plaque)){
                return plaque;
            }
        }
        return null;
    }
}
