package com.ntic.selfdrivingcarsimulator.service;

import com.ntic.selfdrivingcarsimulator.controller.MapController;
import com.ntic.selfdrivingcarsimulator.model.Feux;
import com.ntic.selfdrivingcarsimulator.config.Constants;

import java.util.ArrayList;

public class FeuxManager extends Thread{
    MapController context;

    public FeuxManager(MapController context) {
        this.context = context;
    }

    @Override
    public void run(){
        ArrayList<Feux> feux = context.listFeux;
        while (true){
            try {
                sleep(Constants.CHANGING_LIGHT_COLOR_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for(Feux f : feux){
                if(f.getColor().equals("GREEN")){
                    f.setColor("RED");
                }
                else{
                    f.setColor("GREEN");
                }
                f.changeColor();
            }


        }
    }
}
