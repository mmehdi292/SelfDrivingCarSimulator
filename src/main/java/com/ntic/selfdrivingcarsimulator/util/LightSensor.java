package com.ntic.selfdrivingcarsimulator.util;

import com.ntic.selfdrivingcarsimulator.setting.Constants;

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
                System.out.println("--=------------ [Thead in]"+foundLight);
                try {
                    sleep(Constants.TIME_TO_REACTIVATE_SENSOR_OF_LIGHT_CHECKING);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                foundLight = false;
                System.out.println("---=--------- [Thead be out]"+foundLight);
            }
        }
    }

}
