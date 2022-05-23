package com.ntic.selfdrivingcarsimulator.util;

import com.ntic.selfdrivingcarsimulator.agent.BDI;
import com.ntic.selfdrivingcarsimulator.gui.MapController;
import com.ntic.selfdrivingcarsimulator.reasoning.Point;
import com.ntic.selfdrivingcarsimulator.setting.Constants;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

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


    public static String moveXThanY(Circle physical,Circle vcar,Point point){
        if(physical.getLayoutX()!=point.getX()) {
            vcar.setLayoutY(physical.getLayoutY());
            if(physical.getLayoutX()>point.getX()) {
                physical.setLayoutX(physical.getLayoutX() - 1);
                vcar.setLayoutX(physical.getLayoutX() - Constants.VCAR_DETETION);
                return "LEFT";
            }
            else {
                physical.setLayoutX(physical.getLayoutX() + 1);
                vcar.setLayoutX(physical.getLayoutX()+Constants.VCAR_DETETION);
                return "RIGHT";
            }

        }else{
            vcar.setLayoutX(physical.getLayoutX());
            if(physical.getLayoutY()!=point.getY()) {
                if(physical.getLayoutY()>point.getY()) {
                    physical.setLayoutY(physical.getLayoutY() - 1);
                    vcar.setLayoutY(physical.getLayoutY() - Constants.VCAR_DETETION);
                    return "TOP";
                }
                else {
                    physical.setLayoutY(physical.getLayoutY() + 1);
                    vcar.setLayoutY(physical.getLayoutY()+Constants.VCAR_DETETION);
                    return "BOTTOM";
                }
            }
        }
        return null;
    }
    public static String moveYThanX(Circle physical,Circle vcar,Point point){

        //I'AM not sure about retrun cases [NOW]
        if(physical.getLayoutY()!=point.getY()) {
            vcar.setLayoutX(physical.getLayoutX());
            if(physical.getLayoutY()>point.getY()) {
                physical.setLayoutY(physical.getLayoutY() - 1);
                vcar.setLayoutY(physical.getLayoutY() - Constants.VCAR_DETETION);
                return "LEFT";
            }
            else {
                physical.setLayoutY(physical.getLayoutY() + 1);
                vcar.setLayoutY(physical.getLayoutY()+Constants.VCAR_DETETION);
                return "RIGHT";
            }

        }else{
            vcar.setLayoutY(physical.getLayoutY());
            if(physical.getLayoutX()!=point.getX()) {
                if(physical.getLayoutX()>point.getX()) {
                    physical.setLayoutX(physical.getLayoutX() - 1);
                    vcar.setLayoutX(physical.getLayoutX() - Constants.VCAR_DETETION);
                    return "TOP";
                }
                else {
                    physical.setLayoutX(physical.getLayoutX() + 1);

                    vcar.setLayoutX(physical.getLayoutX()+Constants.VCAR_DETETION);
                    return "BOTTOM";
                }
            }
        }
        return null;
    }

    public static void goToPoint(BDI agent, Point point){
        Circle vcar = new Circle();
        // this for make desion if go in x than y  or y -> x
        while (agent.getPhysical().getLayoutX()!=point.getX() || agent.getPhysical().getLayoutY()!=point.getY() ){

            // move x than y
            //String routing = moveXThanY(vcar,point);
            if(agent.getStartMoveValue()){
                Movement.moveXThanY(agent.getPhysical(),vcar,point);
            }
            else{
                Movement.moveYThanX(agent.getPhysical(),vcar,point);
            }


            //cheking obstact after Constants.VCAR_DETETION px
            /*
            Rectangle obstacle = Check.checkingObstacle(agent.getContext(),vcar);
            if(obstacle != null) {
                //commonObstacles.add(obstacle);
                Plan.changePlan(agent,vcar);
                break;

            }*/
            String observation = agent.observation();
            agent.deliberation(observation);



            //default 60 ---> 2
            // 50 ---> x    x = (50 *2)+60 /60 ==

            try {
                agent.sleep((int) agent.speed*2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
