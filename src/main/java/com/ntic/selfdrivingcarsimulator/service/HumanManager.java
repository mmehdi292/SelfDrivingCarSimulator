package com.ntic.selfdrivingcarsimulator.service;

import com.ntic.selfdrivingcarsimulator.config.Constants;
import com.ntic.selfdrivingcarsimulator.controller.MapController;
import com.ntic.selfdrivingcarsimulator.model.Walkway;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class HumanManager  extends Thread {

    MapController context;

    public HumanManager(MapController context) {
        this.context = context;
    }

    @Override
    public void run(){
        Boolean up = true;
        while(true){
            for(Circle h : context.humans()){
                doMoveUpDown(up,h);
            }
            up=!up;
        }
    }

    public void doMoveUpDown(Boolean isUp, Circle physique){

        int pixels = 0;


        while (pixels!=80){
            Circle vHuman = new Circle();

            if(isUp){
                Transformation.updateScreenInY(physique,-1);
                Transformation.updateScreenInY(vHuman,-Constants.VCAR_DETETION);
            }
            else{
                Transformation.updateScreenInY(physique,1);
                Transformation.updateScreenInY(vHuman,Constants.VCAR_DETETION);
            }

            String[] humanIds =   physique.getId().split("_");
            Walkway walkway = null;
            for(Walkway w : context.listWalkways){
                if(("walkway_"+humanIds[1]).equals(w.getWalkwayUI().getId())){
                    walkway = w;
                }
            }

            if(walkway!=null){
                walkway.setOccupied(true);
            }


            try {
                sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            pixels++;

            if(pixels==80){
                walkway.setOccupied(false);

            }





        }

    }
}
