package com.ntic.selfdrivingcarsimulator.util;

import com.ntic.selfdrivingcarsimulator.agent.BDI;
import com.ntic.selfdrivingcarsimulator.gui.MapController;
import com.ntic.selfdrivingcarsimulator.reasoning.Point;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Plan {

    public static void addPointToList(MapController context,Circle physical, ArrayList<Point> planPath,Point desires){
        //car point
        Point physicalPoint = new Point(physical.getLayoutX(),physical.getLayoutY());

        //point 1
        Point p1Top = Movement.doMoveInYTop(context,physicalPoint);

        Point p1Bottom = Movement.doMoveInYButtom(context,physicalPoint);
        Point p1Left = Movement.doMoveInXLeft(context,physicalPoint);
        Point p1Right = Movement.doMoveInXRight(context,physicalPoint);


        //logic
        if(p1Top!=null && p1Bottom==null && p1Left==null && p1Right==null){


            planPath.add(p1Top);
        }
        if(p1Top==null && p1Bottom!=null && p1Left==null && p1Right==null){

            planPath.add(p1Bottom);
        }
        if(p1Top==null && p1Bottom==null && p1Left!=null && p1Right==null){

            planPath.add(p1Left);
        }
        if(p1Top==null && p1Bottom==null && p1Left==null && p1Right!=null){

            planPath.add(p1Right);
        }


        if(p1Top!=null && p1Bottom!=null){
            //get the closer point to desires Y
            //why desires ? [ Best path]

            if(Math.abs(desires.getY()- p1Top.getY())<Math.abs(desires.getY()- p1Bottom.getY())){

                planPath.add(p1Top);
            }
            else{

                planPath.add(p1Bottom);
            }
        }
        else{
            if(p1Left!=null && p1Right!=null){

                //get the closer point to desires X
                if(Math.abs(desires.getX()- p1Left.getX())<Math.abs(desires.getX()- p1Right.getX())){

                    planPath.add(p1Left);
                }
                else{

                    planPath.add(p1Right);
                }
            }

        }

        //point 2
        Point p2Top = Movement.doMoveInYTop(context,desires);
        Point p2Bottom = Movement.doMoveInYButtom(context,desires);
        Point p2Left = Movement.doMoveInXLeft(context,desires);
        Point p2Right = Movement.doMoveInXRight(context,desires);


        //logic
        if(p2Top!=null && p2Bottom==null && p2Left==null && p2Right==null){
            planPath.add(p2Top);
        }
        if(p2Top==null && p2Bottom!=null && p2Left==null && p2Right==null){
            planPath.add(p2Bottom);
        }
        if(p2Top==null && p2Bottom==null && p2Left!=null && p2Right==null){
            planPath.add(p2Left);
        }
        if(p2Top==null && p2Bottom==null && p2Left==null && p2Right!=null){
            planPath.add(p2Right);
        }

        if(p2Top!=null && p2Bottom!=null){
            //get the closer point to physicalPoint Y
            //why desires ? [ Best path]
            if(Math.abs(physicalPoint.getY()- p2Top.getY())<Math.abs(physicalPoint.getY()- p2Bottom.getY())){
                planPath.add(p2Top);
            }
            else{
                planPath.add(p2Bottom);
            }
        }
        else{
            if(p2Left!=null && p2Right!=null){
                //get the closer point to desires X
                if(Math.abs(physicalPoint.getX()- p2Left.getX())<Math.abs(physicalPoint.getX()- p2Right.getX())){
                    planPath.add(p2Left);
                }
                else{
                    planPath.add(p2Right);
                }
            }

        }
    }

    //used when car found obstacles
    public static void changePlan(BDI agent, Circle vcar){

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
            agent.setNewPointOfEviteObstacle(Plan.createNewPlan(agent,obstaclePositionReverseValue));
            Movement.goToPoint(agent,agent.getNewPointOfEviteObstacle());
            Movement.goToPoint(agent,point2);
            Movement.goToPoint(agent,agent.getDesires());
        }

    }


    public static Point createNewPlan(BDI agent,String obstaclePosition){

        agent.setFoundObstacle(true);

        //car point
        Point physicalPoint = new Point(agent.getPhysical().getLayoutX(),agent.getPhysical().getLayoutY());

        Point p1Top = null;
        Point p1Bottom = null;
        Point p1Left = null;
        Point p1Right = null;

        if(obstaclePosition=="LEFT" ){
            p1Top = Movement.doMoveInYTop(agent.getContext(),physicalPoint,100);
            p1Bottom = Movement.doMoveInYButtom(agent.getContext(),physicalPoint,100);
            p1Left = Movement.doMoveInXLeft(agent.getContext(),physicalPoint,100);
            //p1Right = doMoveInXRight(physicalPoint);
        }

        if(obstaclePosition=="RIGHT" ){
            p1Top = Movement.doMoveInYTop(agent.getContext(),physicalPoint,100);
            p1Bottom = Movement.doMoveInYButtom(agent.getContext(),physicalPoint,100);
            //p1Left = doMoveInXLeft(physicalPoint);
            p1Right = Movement.doMoveInXRight(agent.getContext(),physicalPoint,100);
        }

        if(obstaclePosition=="TOP" ){
            p1Top = Movement.doMoveInYTop(agent.getContext(),physicalPoint,100);
            //p1Bottom = doMoveInYButtom(physicalPoint);
            p1Left = Movement.doMoveInXLeft(agent.getContext(),physicalPoint,100);
            p1Right = Movement.doMoveInXRight(agent.getContext(),physicalPoint,100);
        }

        if(obstaclePosition=="BOTTOM" ){
            //p1Top = doMoveInYTop(physicalPoint);
            p1Bottom = Movement.doMoveInYButtom(agent.getContext(),physicalPoint,100);
            p1Left = Movement.doMoveInXLeft(agent.getContext(),physicalPoint,100);
            p1Right = Movement.doMoveInXRight(agent.getContext(),physicalPoint,100);
        }

        //logic
        if(p1Top!=null && p1Bottom==null && p1Left==null && p1Right==null){
            System.out.println("p1 top");
            return p1Top ;
        }
        if(p1Top==null && p1Bottom!=null && p1Left==null && p1Right==null){
            System.out.println("p1 bottom");
            return p1Bottom;
        }


        if(p1Top==null && p1Bottom==null && p1Left!=null && p1Right==null){
            System.out.println("Left");
            return p1Left;
        }
        if(p1Top==null && p1Bottom==null && p1Left==null && p1Right!=null){
            System.out.println("Right");
            return p1Right;
        }

        if(p1Top!=null && p1Bottom!=null){
            //get the closer point to desires Y
            //why desires ? [ Best path]
            System.out.println("TOP / BOTTOM");
            if(Math.abs(agent.getDesires().getY()- p1Top.getY())<Math.abs(agent.getDesires().getY()- p1Bottom.getY())){
                System.out.println("TOP");
                return p1Top;
            }
            else{
                System.out.println("Bottom");
                return p1Bottom;
            }
        }
        else{
            System.out.println("LEFT / RIGHT");
            if(p1Left!=null && p1Right!=null){
                System.out.println("In left/right");
                //get the closer point to desires X
                if(Math.abs(agent.getDesires().getX()- p1Left.getX())<Math.abs(agent.getDesires().getX()- p1Right.getX())){
                    System.out.println("left");
                    return p1Left;
                }
                else{
                    System.out.println("RIGHT");
                    return p1Right;
                }
            }
            else{
                switch (obstaclePosition){
                    case "RIGHT": case "LEFT": if(p1Bottom!=null) return p1Bottom; if (p1Top==null) return  p1Top; break;
                    case "TOP": case "BOTTOM": if(p1Right!=null) return p1Right; if (p1Left==null) return  p1Left; break;
                }
            }

        }
        System.out.println("------left:"+p1Left);
        System.out.println("------right:"+p1Right);
        System.out.println("------top:"+p1Top);
        System.out.println("------bottom:"+p1Bottom);
        System.out.println("------obstaclePosition:"+obstaclePosition);

        return null;

    }

    public static void waitChangingColor(MapController context,Circle vcar,LightSensor lightSensor){
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
