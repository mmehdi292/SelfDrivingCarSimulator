package com.ntic.selfdrivingcarsimulator.model;

import com.ntic.selfdrivingcarsimulator.config.Constants;

public class LightSensor extends Thread{
    public boolean foundLight;

    public LightSensor(Boolean foundLight){
        this.foundLight=foundLight;
    }

    @Override
    public void run(){
        while (true){
            System.out.print("");
            if(foundLight){
                try {
                    sleep(Constants.TIME_TO_REACTIVATE_SENSOR_OF_LIGHT_CHECKING);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                foundLight = false;
            }
        }
    }

}
