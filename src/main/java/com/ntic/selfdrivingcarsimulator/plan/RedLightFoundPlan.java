package com.ntic.selfdrivingcarsimulator.plan;

import com.ntic.selfdrivingcarsimulator.controller.MapController;
import com.ntic.selfdrivingcarsimulator.model.LightSensor;
import com.ntic.selfdrivingcarsimulator.service.Check;
import com.ntic.selfdrivingcarsimulator.service.Transformation;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class RedLightFoundPlan extends Plan {

    private MapController context;
    private Circle vcar;
    private LightSensor lightSensor;

    public void body(){
        if(condition){
            Boolean stoped = false;
            Rectangle passageLight = Check.checkingLight(context,vcar);
            if(passageLight != null) {
                int passageLightNumber = Integer.parseInt(Transformation.stripNonDigits(passageLight.getId()));
                while(context.getFeuxById(passageLightNumber).getColor().equals("RED")){
                    stoped = true;
                }

                if(stoped){
                    lightSensor.foundLight = true;
                }

            }
        }
    }


    public RedLightFoundPlan(MapController context,Circle vcar,LightSensor lightSensor){
        this.condition=false;
        this.context=context;
        this.vcar = vcar;
        this.lightSensor=lightSensor;
    }

}
