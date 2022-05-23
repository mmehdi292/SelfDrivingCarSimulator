package com.ntic.selfdrivingcarsimulator.service;

import com.ntic.selfdrivingcarsimulator.controller.MapController;
import com.ntic.selfdrivingcarsimulator.model.Walkway;
import javafx.application.Platform;
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
                physique.setLayoutY(physique.getLayoutY()-1);
                vHuman.setLayoutY(physique.getLayoutY()-20);
            }
            else{
                physique.setLayoutY(physique.getLayoutY()+1);
                vHuman.setLayoutY(physique.getLayoutY()+20);
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
