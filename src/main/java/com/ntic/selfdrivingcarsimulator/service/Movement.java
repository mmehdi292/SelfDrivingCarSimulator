package com.ntic.selfdrivingcarsimulator.service;

import com.ntic.selfdrivingcarsimulator.model.BDI;
import com.ntic.selfdrivingcarsimulator.controller.MapController;
import com.ntic.selfdrivingcarsimulator.model.Point;
import com.ntic.selfdrivingcarsimulator.config.Constants;
import javafx.scene.shape.Circle;

public class Movement {

    public static Point doMoveInXLeft(MapController context, Point vPoint){
        Circle vCar = new Circle();
        vCar.setLayoutX(vPoint.getX());
        vCar.setLayoutY(vPoint.getY());
        Boolean gotIntersection = false;

        while (!gotIntersection  && vCar.getLayoutX()>0 && vCar.getLayoutY()>0 && vCar.getLayoutX()<=Constants.WEIGHT_RESOLUTION && vCar.getLayoutY()<=Constants.HEIGHT_RESOLUTION){
            if(context.checkIntersection(vCar)){
                gotIntersection = true;
            }
            vCar.setLayoutX(vCar.getLayoutX() - 1);
        }

        if(gotIntersection){
            return new Point(vCar.getLayoutX()- Constants.VCAR_DETETION,vCar.getLayoutY());
        }
        else{
            return null;
        }
    }
    public static Point doMoveInXRight(MapController context,Point vPoint){

        Circle vCar = new Circle();
        vCar.setLayoutX(vPoint.getX());
        vCar.setLayoutY(vPoint.getY());
        Boolean gotIntersection = false;

        while (!gotIntersection  && vCar.getLayoutX()>0 && vCar.getLayoutY()>0 && vCar.getLayoutX()<=Constants.WEIGHT_RESOLUTION && vCar.getLayoutY()<=Constants.HEIGHT_RESOLUTION){
            if(context.checkIntersection(vCar)){
                gotIntersection = true;
            }
            vCar.setLayoutX(vCar.getLayoutX() + 1);
        }

        if(gotIntersection){
            return new Point(vCar.getLayoutX()+Constants.VCAR_DETETION,vCar.getLayoutY());
        }
        else{
            return null;
        }
    }
    public static Point doMoveInYTop(MapController context,Point vPoint){
        Circle vCar = new Circle();


        //Transformation.updateScreenInX(vCar,0);
        //Transformation.updateScreenInY(vCar,0);
        vCar.setLayoutX(vPoint.getX());
        vCar.setLayoutY(vPoint.getY());
        Boolean gotIntersection = false;

        while (!gotIntersection  && vCar.getLayoutX()>0 && vCar.getLayoutY()>0 && vCar.getLayoutX()<=Constants.WEIGHT_RESOLUTION && vCar.getLayoutY()<=Constants.HEIGHT_RESOLUTION){
            if(context.checkIntersection(vCar)){
                gotIntersection = true;
            }
            vCar.setLayoutY(vCar.getLayoutY() - 1);
        }

        if(gotIntersection){
            return new Point(vCar.getLayoutX(),vCar.getLayoutY()-Constants.VCAR_DETETION);
        }
        else{
            return null;
        }

    }
    public static Point doMoveInYButtom(MapController context,Point vPoint){

        Circle vCar = new Circle();
        vCar.setLayoutX(vPoint.getX());
        vCar.setLayoutY(vPoint.getY());
        Boolean gotIntersection = false;

        while (!gotIntersection  && vCar.getLayoutX()>0 && vCar.getLayoutY()>0 && vCar.getLayoutX()<=Constants.WEIGHT_RESOLUTION && vCar.getLayoutY()<=Constants.HEIGHT_RESOLUTION){
            if(context.checkIntersection(vCar)){
                gotIntersection = true;
            }
            vCar.setLayoutY(vCar.getLayoutY() + 1);
        }

        if(gotIntersection){
            return new Point(vCar.getLayoutX(),vCar.getLayoutY()+Constants.VCAR_DETETION);
        }
        else{
            return null;
        }
    }


    public static Point doMoveInXLeft(MapController context,Point vPoint,int addPixel){
        Circle vCar = new Circle();
        vCar.setLayoutX(vPoint.getX()-addPixel);
        vCar.setLayoutY(vPoint.getY());
        Boolean gotIntersection = false;

        while (!gotIntersection  && vCar.getLayoutX()>0 && vCar.getLayoutY()>0 && vCar.getLayoutX()<=Constants.WEIGHT_RESOLUTION && vCar.getLayoutY()<=Constants.HEIGHT_RESOLUTION){
            if(context.checkIntersection(vCar)){
                gotIntersection = true;
            }
            vCar.setLayoutX(vCar.getLayoutX() - 1);
        }

        if(gotIntersection){
            return new Point(vCar.getLayoutX()-Constants.VCAR_DETETION,vCar.getLayoutY());
        }
        else{
            return null;
        }
    }
    public static Point doMoveInXRight(MapController context,Point vPoint,int addPixel){

        Circle vCar = new Circle();
        vCar.setLayoutX(vPoint.getX()+addPixel);
        vCar.setLayoutY(vPoint.getY());
        Boolean gotIntersection = false;

        while (!gotIntersection  && vCar.getLayoutX()>0 && vCar.getLayoutY()>0 && vCar.getLayoutX()<=Constants.WEIGHT_RESOLUTION && vCar.getLayoutY()<=Constants.HEIGHT_RESOLUTION){
            if(context.checkIntersection(vCar)){
                gotIntersection = true;
            }
            vCar.setLayoutX(vCar.getLayoutX() + 1);
        }

        if(gotIntersection){
            return new Point(vCar.getLayoutX()+Constants.VCAR_DETETION,vCar.getLayoutY());
        }
        else{
            return null;
        }
    }
    public static Point doMoveInYTop(MapController context,Point vPoint,int addPixel){
        Circle vCar = new Circle();
        vCar.setLayoutX(vPoint.getX());
        vCar.setLayoutY(vPoint.getY()-addPixel);
        Boolean gotIntersection = false;

        while (!gotIntersection  && vCar.getLayoutX()>0 && vCar.getLayoutY()>0 && vCar.getLayoutX()<=Constants.WEIGHT_RESOLUTION && vCar.getLayoutY()<=Constants.HEIGHT_RESOLUTION){
            if(context.checkIntersection(vCar)){
                gotIntersection = true;
            }
            vCar.setLayoutY(vCar.getLayoutY() - 1);
        }

        if(gotIntersection){
            return new Point(vCar.getLayoutX(),vCar.getLayoutY()-Constants.VCAR_DETETION);
        }
        else{
            return null;
        }

    }
    public static Point doMoveInYButtom(MapController context,Point vPoint,int addPixel){

        Circle vCar = new Circle();
        vCar.setLayoutX(vPoint.getX());
        vCar.setLayoutY(vPoint.getY()+addPixel);
        Boolean gotIntersection = false;

        while (!gotIntersection  && vCar.getLayoutX()>0 && vCar.getLayoutY()>0 && vCar.getLayoutX()<=Constants.WEIGHT_RESOLUTION && vCar.getLayoutY()<=Constants.HEIGHT_RESOLUTION){
            if(context.checkIntersection(vCar)){
                gotIntersection = true;
            }
            vCar.setLayoutY(vCar.getLayoutY() + 1);
        }

        if(gotIntersection){
            return new Point(vCar.getLayoutX(),vCar.getLayoutY()+Constants.VCAR_DETETION);
        }
        else{
            return null;
        }
    }


    public static String moveXThanY(BDI agent,Circle vcar,Point point){
        if(agent.getPhysical().getLayoutX()!=point.getX()) {
            vcar.setLayoutY(agent.getPhysical().getLayoutY());
            if(agent.getPhysical().getLayoutX()>point.getX()) {
                Transformation.updateScreenInX(agent.getPhysical(),-1);
                Transformation.updateScreenInX(vcar,-Constants.VCAR_DETETION);
                agent.setDirection("LEFT");
                return "LEFT";
            }
            else {
                Transformation.updateScreenInX(agent.getPhysical(),1);
                Transformation.updateScreenInX(vcar,Constants.VCAR_DETETION);
                agent.setDirection("RIGHT");
                return "RIGHT";
            }

        }else{
            vcar.setLayoutX(agent.getPhysical().getLayoutX());
            if(agent.getPhysical().getLayoutY()!=point.getY()) {
                if(agent.getPhysical().getLayoutY()>point.getY()) {
                    Transformation.updateScreenInY(agent.getPhysical(),-1);
                    Transformation.updateScreenInY(vcar,-Constants.VCAR_DETETION);
                    agent.setDirection("TOP");
                    return "TOP";
                }
                else {
                    Transformation.updateScreenInY(agent.getPhysical(),1);
                    Transformation.updateScreenInY(vcar,Constants.VCAR_DETETION);
                    agent.setDirection("BOTTOM");
                    return "BOTTOM";
                }
            }
        }
        return null;
    }
    public static String moveYThanX(BDI agent,Circle vcar,Point point){

        if(agent.getPhysical().getLayoutY()!=point.getY()) {
            vcar.setLayoutX(agent.getPhysical().getLayoutX());
            if(agent.getPhysical().getLayoutY()>point.getY()) {
                Transformation.updateScreenInY(agent.getPhysical(),-1);
                Transformation.updateScreenInY(vcar,-Constants.VCAR_DETETION);
                agent.setDirection("TOP");
                return "TOP";
            }
            else {
                Transformation.updateScreenInY(agent.getPhysical(),1);
                Transformation.updateScreenInY(vcar,Constants.VCAR_DETETION);
                agent.setDirection("BOTTOM");
                return "BOTTOM";
            }

        }else{
            vcar.setLayoutY(agent.getPhysical().getLayoutY());
            if(agent.getPhysical().getLayoutX()!=point.getX()) {
                if(agent.getPhysical().getLayoutX()>point.getX()) {
                    Transformation.updateScreenInX(agent.getPhysical(),-1);
                    Transformation.updateScreenInX(vcar,-Constants.VCAR_DETETION);
                    agent.setDirection("LEFT");
                    return "LEFT";
                }
                else {
                    Transformation.updateScreenInX(agent.getPhysical(),1);
                    Transformation.updateScreenInX(vcar,Constants.VCAR_DETETION);
                    agent.setDirection("RIGHT");
                    return "RIGHT";
                }
            }
        }
        return null;
    }

    public static void goToPoint(BDI agent, Point point){
        Circle vcar = new Circle();

        while (agent.getPhysical().getLayoutX()!=point.getX() || agent.getPhysical().getLayoutY()!=point.getY() ){

            if(agent.getStartMoveValue()){
                Movement.moveXThanY(agent,vcar,point);
            }
            else{
                Movement.moveYThanX(agent,vcar,point);
            }

            agent.petrolTank-=1;
            if(!agent.getForGenerateDesiredAuto()){
                Message.UIPetrolThread(agent.getContext(),agent.petrolTank);
                Message.UISpeedThread(agent.getContext(),agent.speed);
            }

            String observation = agent.observation();
            agent.deliberation(observation);

            try {
                agent.sleep((int) agent.speed*2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
