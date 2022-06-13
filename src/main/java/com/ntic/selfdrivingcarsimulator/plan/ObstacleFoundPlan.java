package com.ntic.selfdrivingcarsimulator.plan;

import com.ntic.selfdrivingcarsimulator.model.BDI;
import com.ntic.selfdrivingcarsimulator.model.Point;
import com.ntic.selfdrivingcarsimulator.service.MainPlan;
import com.ntic.selfdrivingcarsimulator.service.Message;
import com.ntic.selfdrivingcarsimulator.service.Movement;
import javafx.scene.shape.Circle;

public class ObstacleFoundPlan extends Plan{

    private BDI agent;
    private Circle vcar;

    @Override
    public void body(){
        if(condition){
            // get last obstacle position
            //Rectangle lastObstacle = this.commonObstacles.getLast();

            //check is left or right or top or bottom of car by vcar
            //and save it in obstacle position with invers value
            String obstaclePositionReverseValue = "";
            Point nextPoint = null;
            Point point = new Point(agent.getPhysical().getLayoutX(),agent.getPhysical().getLayoutY());
            if(agent.getPhysical().getLayoutX()<vcar.getLayoutX()){
                obstaclePositionReverseValue="LEFT";
                nextPoint = Movement.doMoveInXLeft(agent.getContext(),point);

            }
            else if(agent.getPhysical().getLayoutX()>vcar.getLayoutX()){
                obstaclePositionReverseValue="RIGHT";
                nextPoint = Movement.doMoveInXRight(agent.getContext(),point);
            }
            else{
                if(agent.getPhysical().getLayoutY()<vcar.getLayoutY()){
                    obstaclePositionReverseValue="TOP";
                    nextPoint = Movement.doMoveInYTop(agent.getContext(),point);
                }
                else{
                    obstaclePositionReverseValue="BOTTOM";
                    nextPoint = Movement.doMoveInYButtom(agent.getContext(),point);
                }
            }

            //point 2
            Point point2 = null;
            switch (obstaclePositionReverseValue){
                case "LEFT": point2 = Movement.doMoveInXRight(agent.getContext(),agent.getDesires()); System.out.println("IN LEFT"+point2.getY()); break;
                case "RIGHT": point2 = Movement.doMoveInXLeft(agent.getContext(),agent.getDesires()); System.out.println("IN RIGHT"+point2.getY()); break;
                case "TOP": point2 = Movement.doMoveInYButtom(agent.getContext(),agent.getDesires()); System.out.println("IN TOP"+point2.getY());  break;
                case "BOTTOM": point2 = Movement.doMoveInYTop(agent.getContext(),agent.getDesires()); System.out.println("IN BOTTOM"+point2.getY()); break;
            }

            if(nextPoint == null || point2 == null){
                // error message
                Message.messageErrorThread(agent.getContext());

            }
            //FIX IT DON'T FORGET
            else{
                switch (obstaclePositionReverseValue){
                    case "BOTTOM": case "TOP": agent.setStartMoveValue(false); break;
                    case "LEFT": case "RIGHT": agent.setStartMoveValue(true); break;
                }
                Movement.goToPoint(agent,nextPoint);
                agent.getPlanPath().clear();
                // create new plan
                agent.setNewPointOfEviteObstacle(MainPlan.createNewPlan(agent,obstaclePositionReverseValue));
                Movement.goToPoint(agent,agent.getNewPointOfEviteObstacle());
                Movement.goToPoint(agent,point2);
                Movement.goToPoint(agent,agent.getDesires());
            }
        }
    }

    public ObstacleFoundPlan(BDI agent, Circle vcar){
        this.condition=false;
        this.agent = agent;
        this.vcar=vcar;
    }

}
