package com.ntic.selfdrivingcarsimulator.model;

import com.ntic.selfdrivingcarsimulator.controller.MapController;
import javafx.scene.shape.Circle;

public class Human extends Thread{

    private Circle physique;
    private MapController context;

    public Human(Circle physique, MapController context) {
        this.physique = physique;
        this.context = context;
    }

    @Override
    public void run(){
        Boolean up = true;
        while (true){
            doMoveUpDown(up);
            up=!up;
        }
    }

    public void doMoveUpDown(Boolean isUp){

        int pixels = 0;


        while (pixels!=80){

            if(isUp)
                physique.setLayoutY(physique.getLayoutY()-1);
            else
                physique.setLayoutY(physique.getLayoutY()+1);

            try {
                sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            pixels++;


        }
    }


    public Circle getPhysique() {
        return physique;
    }

    public void setPhysique(Circle physique) {
        this.physique = physique;
    }

    public MapController getContext() {
        return context;
    }

    public void setContext(MapController context) {
        this.context = context;
    }
}
