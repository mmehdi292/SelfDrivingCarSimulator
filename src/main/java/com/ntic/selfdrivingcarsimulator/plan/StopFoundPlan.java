package com.ntic.selfdrivingcarsimulator.plan;

import com.ntic.selfdrivingcarsimulator.controller.MapController;
import com.ntic.selfdrivingcarsimulator.model.BDI;
import com.ntic.selfdrivingcarsimulator.service.Message;
import javafx.scene.shape.Circle;

public class StopFoundPlan extends Plan{

    private BDI agent;
    private Circle vcar;

    public void body(){
        if(condition){
            if(!agent.getForGenerateDesiredAuto())
                Message.UISpeedThread(agent.getContext(), 0.0);
            try {
                agent.wait(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(!agent.getForGenerateDesiredAuto())
                Message.UISpeedThread(agent.getContext(), agent.speed);
        }
    }

    public StopFoundPlan(BDI agent,Circle vcar){
        this.condition=false;
        this.agent=agent;
        this.vcar=vcar;
    }
}
