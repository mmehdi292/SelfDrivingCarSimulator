package com.ntic.selfdrivingcarsimulator.plan;

import com.ntic.selfdrivingcarsimulator.model.BDI;
import com.ntic.selfdrivingcarsimulator.service.Check;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class PlaqueSpeedFoundPlan  extends Plan {

    private BDI agent;

    private Circle vcar;

    public void body(){
        if(condition){
            Rectangle plaque = Check.checkingSpeedPlaques(agent.getContext(),vcar);
            if(plaque != null) {
                String[] plaqueValues = plaque.getId().split("_");
                agent.setSpeed(Integer.parseInt(plaqueValues[1]));
            }
        }
    }

    public PlaqueSpeedFoundPlan(BDI agent,Circle vcar){
        this.condition=false;
        this.agent=agent;
        this.vcar=vcar;

    }
}
