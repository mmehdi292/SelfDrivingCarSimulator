package com.ntic.selfdrivingcarsimulator.plan;

import com.ntic.selfdrivingcarsimulator.model.BDI;

public class CarFoundPlan extends Plan {

    private BDI agent;

    @Override
    public void body(){
        if(condition){

            Boolean isInCollision = false;
            Boolean isAgent = false;
            if(agent==agent.getContext().agent){
                isInCollision = agent.getContext().checkCollision(agent.getPhysical(),agent.getContext().agentNotSelected.getPhysical());
                isAgent = true;
            }
            else if(agent == agent.getContext().agentNotSelected){
                isInCollision = agent.getContext().checkCollision(agent.getPhysical(),agent.getContext().agent.getPhysical());
                isAgent = false;
            }

            if(isInCollision){
                switch (agent.getDirection()){
                    case "TOP":
                        if(isAgent){
                            if(agent.getContext().agentNotSelected.getDirection().equals("TOP")){
                                break;
                            }
                        }
                        else{
                            if(agent.getContext().agent.getDirection().equals("TOP")){
                                break;
                            }
                        }
                        agent.getPhysical().setLayoutX(agent.getPhysical().getLayoutX()+10);
                        agent.setStartMoveValue(false);

                        break;
                    case "BOTTOM":
                        if(isAgent){
                            if(agent.getContext().agentNotSelected.getDirection().equals("BOTTOM")){
                                break;
                            }
                        }
                        else{
                            if(agent.getContext().agent.getDirection().equals("BOTTOM")){
                                break;
                            }
                        }
                        agent.getPhysical().setLayoutX(agent.getPhysical().getLayoutX()-10);
                        agent.setStartMoveValue(false);
                        break;
                    case "LEFT":
                        if(isAgent){
                            if(agent.getContext().agentNotSelected.getDirection().equals("LEFT")){
                                break;
                            }
                        }
                        else{
                            if(agent.getContext().agent.getDirection().equals("LEFT")){
                                break;
                            }
                        }
                        agent.getPhysical().setLayoutY(agent.getPhysical().getLayoutY()+10);
                        agent.setStartMoveValue(true);
                        break;
                    case "RIGHT":
                        if(isAgent){
                            if(agent.getContext().agentNotSelected.getDirection().equals("RIGHT")){
                                break;
                            }
                        }
                        else{
                            if(agent.getContext().agent.getDirection().equals("RIGHT")){
                                break;
                            }
                        }
                        agent.getPhysical().setLayoutY(agent.getPhysical().getLayoutY()-10);
                        agent.setStartMoveValue(true);
                        break;
                }
            }
        }
    }

    public CarFoundPlan(BDI agent){
        this.condition=false;
        this.agent=agent;
    }

}
