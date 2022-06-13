package com.ntic.selfdrivingcarsimulator.plan;

import com.ntic.selfdrivingcarsimulator.controller.MapController;
import com.ntic.selfdrivingcarsimulator.model.BDI;
import com.ntic.selfdrivingcarsimulator.model.Walkway;
import com.ntic.selfdrivingcarsimulator.service.Check;
import javafx.scene.shape.Circle;

public class WalkwayFoundPlan extends Plan {

    private BDI agent;

    private MapController context;
    private Circle vcar; ///ADD THEM DON'T FORGET

    public void body(){
        if(condition){
            Walkway walkway = Check.checkingWalkways(context,vcar);
            Boolean loop = (walkway !=null);
            while (loop){
                System.out.print("");
                if(!walkway.getOccupied()){
                    loop=false;
                }
            }
        }
    }

    public WalkwayFoundPlan(BDI agent,MapController context,Circle vcar){
        this.condition=false;
        this.agent=agent;
        this.context = context;
        this.vcar=vcar;
    }
}
