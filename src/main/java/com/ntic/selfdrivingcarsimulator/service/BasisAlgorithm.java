package com.ntic.selfdrivingcarsimulator.service;

import com.ntic.selfdrivingcarsimulator.model.BDI;
import com.ntic.selfdrivingcarsimulator.controller.MapController;
import com.ntic.selfdrivingcarsimulator.model.LightSensor;
import com.ntic.selfdrivingcarsimulator.model.Point;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class BasisAlgorithm {

    public static void addPointToList(MapController context,Circle physical, ArrayList<Point> planPath,Point desires){

        //clean
        planPath.clear();
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


}
