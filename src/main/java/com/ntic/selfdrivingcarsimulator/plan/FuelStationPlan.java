package com.ntic.selfdrivingcarsimulator.plan;

import com.ntic.selfdrivingcarsimulator.config.Constants;
import com.ntic.selfdrivingcarsimulator.model.BDI;
import com.ntic.selfdrivingcarsimulator.model.Point;
import com.ntic.selfdrivingcarsimulator.service.MainPlan;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class FuelStationPlan extends Plan {

    private BDI agent;

    @Override
    public void body(){
        if(condition){
            int fuelRemaining = agent.petrolTank;
            double distanceToDisere = Math.abs(agent.getPhysical().getLayoutX()-agent.getDesires().getX())
                    +Math.abs(agent.getPhysical().getLayoutY()-agent.getDesires().getY());

            if(fuelRemaining>distanceToDisere){
                if((fuelRemaining-distanceToDisere)>(Constants.CAR_MAX_PETROL_TANK*20/100)){
                    return;
                }
            }

            ArrayList<Circle> list = agent.getContext().feulStations();
            for(Circle p : list){
                double distanceBetweenCarAndStation = Math.abs(agent.getPhysical().getLayoutX()-p.getLayoutX())
                        +Math.abs(agent.getPhysical().getLayoutY()-p.getLayoutY());
                if(agent.getFuelStation()==null){
                    agent.setFuelStation(new Point(p.getLayoutX(),p.getLayoutY()));
                }
                else{
                    double distanceBetweenCarAndSavedStation = Math.abs(agent.getPhysical().getLayoutX()-agent.getFuelStation().getX())
                            +Math.abs(agent.getPhysical().getLayoutY()-agent.getFuelStation().getY());
                    if(distanceBetweenCarAndStation<distanceBetweenCarAndSavedStation){
                        agent.setFuelStation(new Point(p.getLayoutX(),p.getLayoutY()));
                    }
                }

            }
            if(agent.getFuelStation()!=null){
                agent.setDirectionToFuelStation(true);
                MainPlan.addPointToList(agent.getContext(), agent.getPhysical(),agent.getPlanPath(),agent.getFuelStation());

            }
        }
    }

    public FuelStationPlan(BDI agent){
        this.condition=false;
        this.agent=agent;
    }

}
