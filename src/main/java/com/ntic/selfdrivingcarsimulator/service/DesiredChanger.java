package com.ntic.selfdrivingcarsimulator.service;

import com.ntic.selfdrivingcarsimulator.controller.MapController;
import com.ntic.selfdrivingcarsimulator.model.BDI;

public class DesiredChanger extends Thread{

    private BDI agent;
    private MapController context;

    public DesiredChanger(BDI agent, MapController context) {
        this.agent = agent;
        this.context = context;
    }

    @Override
    public void run(){
        while (true){
            System.out.print("");
            if(!agent.getHasNewDesires()){
                agent.setDesires(context.randomePoint());
                agent.setHasNewDesires(true);
            }

        }
    }

    public BDI getAgent() {
        return agent;
    }

    public void setAgent(BDI agent) {
        this.agent = agent;
    }

    public MapController getContext() {
        return context;
    }

    public void setContext(MapController context) {
        this.context = context;
    }
}
