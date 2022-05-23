package com.ntic.selfdrivingcarsimulator.util;

import com.ntic.selfdrivingcarsimulator.setting.Constants;

public class StopSensor extends Thread{
    public boolean foundPlaque;

    public StopSensor(Boolean foundPlaque){
        this.foundPlaque=foundPlaque;
    }

    @Override
    public void run(){
        while (true){
            System.out.print("");
            if(foundPlaque){
                try {
                    sleep(Constants.TIME_TO_REACTIVATE_SENSOR_OF_PLAQUE_CHECKING);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                foundPlaque = false;
            }
        }
    }

}