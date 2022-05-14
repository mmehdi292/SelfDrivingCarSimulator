package com.ntic.selfdrivingcarsimulator.agent;

import com.ntic.selfdrivingcarsimulator.gui.MapController;
import com.ntic.selfdrivingcarsimulator.reasoning.Point;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.*;

public class BDI extends Thread {

    private Circle physical;
    private MapController world;
    private LinkedList<Rectangle> commonObstacles;

    private Point desires;
    private ArrayList<Point> planPath;
    private ArrayList<Point> newPlanPath;
    private Boolean hasNewDesires;
    private Boolean foundObstacle = false;
    private Point newPointOfEviteObstacle;
    private Boolean startMoveValue = true;

    public BDI(Circle physical,MapController world){
        this.physical=physical;
        this.world=world;
        this.planPath = new ArrayList<>();
        this.hasNewDesires = false;
        this.commonObstacles = new LinkedList<>();
        this.newPlanPath = new ArrayList<>();
    }
    public BDI(Circle physical){
        this.physical=physical;

    }

    //intentions
    public void run(){
        while (true){

            System.out.print("");

            if(hasNewDesires){
                createPlan();
                this.doMove();
                hasNewDesires = false;

            }
        }
    }

    private synchronized void createPlan(){

        //car point
        Point physicalPoint = new Point(physical.getLayoutX(),physical.getLayoutY());

        //point 1
        Point p1Top = doMoveInYTop(physicalPoint);

        Point p1Bottom = doMoveInYButtom(physicalPoint);
        Point p1Left = doMoveInXLeft(physicalPoint);
        Point p1Right = doMoveInXRight(physicalPoint);


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
        Point p2Top = doMoveInYTop(desires);
        Point p2Bottom = doMoveInYButtom(desires);
        Point p2Left = doMoveInXLeft(desires);
        Point p2Right = doMoveInXRight(desires);


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

        //planPath.add(doMoveInXRight(new Point(physical.getLayoutX(),physical.getLayoutY())));
        //planPath.add(doMoveInYTop(desires));
    }
    private synchronized void doMove(){

        List list = Collections.synchronizedList(planPath);
        synchronized (list) {
            Iterator i = list.iterator(); // Must be in synchronized block
            while (i.hasNext())
                goToPoint((Point) i.next());
        }
        /*
        for(Point p :planPath){
            goToPoint(p);
        }*/
        planPath.clear();

        goToPoint(desires);
    }

    private void goToPoint(Point point){
        Circle vcar = new Circle();
        // this for make desion if go in x than y  or y -> x
        while (physical.getLayoutX()!=point.getX() || physical.getLayoutY()!=point.getY() ){

            // move x than y
            //String routing = moveXThanY(vcar,point);
            if(startMoveValue){
                moveXThanY(vcar,point);
            }
            else{
                moveYThanX(vcar,point);
            }


            //cheking obstact after 20 px

            Rectangle obstacle = checkingObstacle(vcar);
            if(obstacle != null) {
                commonObstacles.add(obstacle);
                changePlan(vcar);
                break;

            }

            try {
                this.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Point doMoveInXLeft(Point vPoint){
        Circle vCar = new Circle();
        vCar.setLayoutX(vPoint.getX());
        vCar.setLayoutY(vPoint.getY());
        Boolean gotIntersection = false;

        while (!gotIntersection  && vCar.getLayoutX()>0 && vCar.getLayoutY()>0 && vCar.getLayoutX()<=1280 && vCar.getLayoutY()<=720){
            if(world.checkIntersection(vCar)){
                gotIntersection = true;
            }
            vCar.setLayoutX(vCar.getLayoutX() - 1);
        }

        if(gotIntersection){
            return new Point(vCar.getLayoutX()-20,vCar.getLayoutY());
        }
        else{
            return null;
        }
    }
    private Point doMoveInXRight(Point vPoint){

        Circle vCar = new Circle();
        vCar.setLayoutX(vPoint.getX());
        vCar.setLayoutY(vPoint.getY());
        Boolean gotIntersection = false;

        while (!gotIntersection  && vCar.getLayoutX()>0 && vCar.getLayoutY()>0 && vCar.getLayoutX()<=1280 && vCar.getLayoutY()<=720){
            if(world.checkIntersection(vCar)){
                gotIntersection = true;
            }
            vCar.setLayoutX(vCar.getLayoutX() + 1);
        }

        if(gotIntersection){
            return new Point(vCar.getLayoutX()+20,vCar.getLayoutY());
        }
        else{
            return null;
        }
    }
    private Point doMoveInYTop(Point vPoint){
        Circle vCar = new Circle();
        vCar.setLayoutX(vPoint.getX());
        vCar.setLayoutY(vPoint.getY());
        Boolean gotIntersection = false;

        while (!gotIntersection  && vCar.getLayoutX()>0 && vCar.getLayoutY()>0 && vCar.getLayoutX()<=1280 && vCar.getLayoutY()<=720){
            if(world.checkIntersection(vCar)){
                gotIntersection = true;
            }
            vCar.setLayoutY(vCar.getLayoutY() - 1);
        }

        if(gotIntersection){
            return new Point(vCar.getLayoutX(),vCar.getLayoutY()-20);
        }
        else{
            return null;
        }

    }
    private Point doMoveInYButtom(Point vPoint){

        Circle vCar = new Circle();
        vCar.setLayoutX(vPoint.getX());
        vCar.setLayoutY(vPoint.getY());
        Boolean gotIntersection = false;

        while (!gotIntersection  && vCar.getLayoutX()>0 && vCar.getLayoutY()>0 && vCar.getLayoutX()<=1280 && vCar.getLayoutY()<=720){
            if(world.checkIntersection(vCar)){
                gotIntersection = true;
            }
            vCar.setLayoutY(vCar.getLayoutY() + 1);
        }

        if(gotIntersection){
            return new Point(vCar.getLayoutX(),vCar.getLayoutY()+20);
        }
        else{
            return null;
        }
    }

    private Rectangle checkingObstacle(Circle circle){
        for(Rectangle obstacle : world.obstaclsList){
            if(world.checkCollision(circle,obstacle)){
                return obstacle;
            }
        }
        return null;
    }


    private String moveXThanY(Circle vcar,Point point){
        if(physical.getLayoutX()!=point.getX()) {
            vcar.setLayoutY(this.physical.getLayoutY());
            if(physical.getLayoutX()>point.getX()) {
                physical.setLayoutX(physical.getLayoutX() - 1);
                vcar.setLayoutX(this.physical.getLayoutX() - 20);
                return "LEFT";
            }
            else {
                physical.setLayoutX(physical.getLayoutX() + 1);
                vcar.setLayoutX(this.physical.getLayoutX()+20);
                return "RIGHT";
            }

        }else{
            vcar.setLayoutX(this.physical.getLayoutX());
            if(physical.getLayoutY()!=point.getY()) {
                if(physical.getLayoutY()>point.getY()) {
                    physical.setLayoutY(physical.getLayoutY() - 1);
                    vcar.setLayoutY(this.physical.getLayoutY() - 20);
                    return "TOP";
                }
                else {
                    physical.setLayoutY(physical.getLayoutY() + 1);
                    vcar.setLayoutY(this.physical.getLayoutY()+20);
                    return "BOTTOM";
                }
            }
        }
        return null;
    }
    private void moveYThanX(Circle vcar,Point point){
        if(physical.getLayoutY()!=point.getY()) {
            vcar.setLayoutX(this.physical.getLayoutX());
            if(physical.getLayoutY()>point.getY()) {
                physical.setLayoutY(physical.getLayoutY() - 1);
                vcar.setLayoutY(this.physical.getLayoutY() - 20);
            }
            else {
                physical.setLayoutY(physical.getLayoutY() + 1);
                vcar.setLayoutY(this.physical.getLayoutY()+20);
            }

        }else{
            vcar.setLayoutY(this.physical.getLayoutY());
            if(physical.getLayoutX()!=point.getX()) {
                if(physical.getLayoutX()>point.getX()) {
                    physical.setLayoutX(physical.getLayoutX() - 1);
                    vcar.setLayoutX(this.physical.getLayoutX() - 20);
                }
                else {
                    physical.setLayoutX(physical.getLayoutX() + 1);

                    vcar.setLayoutX(this.physical.getLayoutX()+20);
                }
            }
        }
    }

    //used when car found obstacles
    private void changePlan(Circle vcar){

        // get last obstacle position
        //Rectangle lastObstacle = this.commonObstacles.getLast();

        //check is left or right or top or bottom of car by vcar
        //and save it in obstacle position with invers value
        String obstaclePositionReverseValue = "";
        Point nextPoint = null;
        Point point = new Point(physical.getLayoutX(),physical.getLayoutY());
        if(physical.getLayoutX()<vcar.getLayoutX()){
            obstaclePositionReverseValue="LEFT";
            nextPoint = doMoveInXLeft(point);

        }
        else if(physical.getLayoutX()>vcar.getLayoutX()){
            obstaclePositionReverseValue="RIGHT";
            nextPoint = doMoveInXRight(point);
        }
        else{
            if(physical.getLayoutY()<vcar.getLayoutY()){
                obstaclePositionReverseValue="TOP";
                nextPoint = doMoveInYTop(point);
            }
            else{
                obstaclePositionReverseValue="BOTTOM";
                nextPoint = doMoveInYButtom(point);
            }
        }

        //point 2
        Point point2 = null;
        switch (obstaclePositionReverseValue){
            case "LEFT": point2 = doMoveInXRight(desires); System.out.println("IN LEFT"+point2.getY()); break;
            case "RIGHT": point2 = doMoveInXLeft(desires); System.out.println("IN RIGHT"+point2.getY()); break;
            case "TOP": point2 = doMoveInYButtom(desires); System.out.println("IN TOP"+point2.getY());  break;
            case "BOTTOM": point2 = doMoveInYTop(desires); System.out.println("IN BOTTOM"+point2.getY()); break;
        }
        //Point p2Top = doMoveInYTop(desires);
        //Point p2Bottom = doMoveInYButtom(desires);
        //Point p2Left = doMoveInXLeft(desires);
        //Point p2Right = doMoveInXRight(desires);



        if(nextPoint == null || point2 == null){
            // error message
            Platform.runLater(new Runnable(){
                @Override
                public void run() {
                    world.messageAlert("Can't Found other plan","Can't Found other plan", Alert.AlertType.ERROR);
                }
            });

        }
        //FIX IT DON'T FORGET
        else{
            switch (obstaclePositionReverseValue){
                case "BOTTOM": case "TOP": startMoveValue=false; break;
                case "LEFT": case "RIGHT": startMoveValue=true; break;
            }
            goToPoint(nextPoint);
            planPath.clear();
            // create new plan
            newPointOfEviteObstacle = createNewPlan(obstaclePositionReverseValue);
            goToPoint(newPointOfEviteObstacle);
            goToPoint(point2);
            goToPoint(desires);
        }

    }


    private synchronized Point createNewPlan(String obstaclePosition){

        foundObstacle = true;

        //car point
        Point physicalPoint = new Point(physical.getLayoutX(),physical.getLayoutY());

        Point p1Top = null;
        Point p1Bottom = null;
        Point p1Left = null;
        Point p1Right = null;

        if(obstaclePosition=="LEFT" ){
            p1Top = doMoveInYTop(physicalPoint,100);
            p1Bottom = doMoveInYButtom(physicalPoint,100);
            p1Left = doMoveInXLeft(physicalPoint,100);
            //p1Right = doMoveInXRight(physicalPoint);
        }

        if(obstaclePosition=="RIGHT" ){
            p1Top = doMoveInYTop(physicalPoint,100);
            p1Bottom = doMoveInYButtom(physicalPoint,100);
            //p1Left = doMoveInXLeft(physicalPoint);
            p1Right = doMoveInXRight(physicalPoint,100);
        }

        if(obstaclePosition=="TOP" ){
            p1Top = doMoveInYTop(physicalPoint,100);
            //p1Bottom = doMoveInYButtom(physicalPoint);
            p1Left = doMoveInXLeft(physicalPoint,100);
            p1Right = doMoveInXRight(physicalPoint,100);
        }

        if(obstaclePosition=="BOTTOM" ){
            //p1Top = doMoveInYTop(physicalPoint);
            p1Bottom = doMoveInYButtom(physicalPoint,100);
            p1Left = doMoveInXLeft(physicalPoint,100);
            p1Right = doMoveInXRight(physicalPoint,100);
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
            if(Math.abs(desires.getY()- p1Top.getY())<Math.abs(desires.getY()- p1Bottom.getY())){
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
                if(Math.abs(desires.getX()- p1Left.getX())<Math.abs(desires.getX()- p1Right.getX())){
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

    private Point doMoveInXLeft(Point vPoint,int addPixel){
        Circle vCar = new Circle();
        vCar.setLayoutX(vPoint.getX()-addPixel);
        vCar.setLayoutY(vPoint.getY());
        Boolean gotIntersection = false;

        while (!gotIntersection  && vCar.getLayoutX()>0 && vCar.getLayoutY()>0 && vCar.getLayoutX()<=1280 && vCar.getLayoutY()<=720){
            if(world.checkIntersection(vCar)){
                gotIntersection = true;
            }
            vCar.setLayoutX(vCar.getLayoutX() - 1);
        }

        if(gotIntersection){
            return new Point(vCar.getLayoutX()-20,vCar.getLayoutY());
        }
        else{
            return null;
        }
    }
    private Point doMoveInXRight(Point vPoint,int addPixel){

        Circle vCar = new Circle();
        vCar.setLayoutX(vPoint.getX()+addPixel);
        vCar.setLayoutY(vPoint.getY());
        Boolean gotIntersection = false;

        while (!gotIntersection  && vCar.getLayoutX()>0 && vCar.getLayoutY()>0 && vCar.getLayoutX()<=1280 && vCar.getLayoutY()<=720){
            if(world.checkIntersection(vCar)){
                gotIntersection = true;
            }
            vCar.setLayoutX(vCar.getLayoutX() + 1);
        }

        if(gotIntersection){
            return new Point(vCar.getLayoutX()+20,vCar.getLayoutY());
        }
        else{
            return null;
        }
    }
    private Point doMoveInYTop(Point vPoint,int addPixel){
        Circle vCar = new Circle();
        vCar.setLayoutX(vPoint.getX());
        vCar.setLayoutY(vPoint.getY()-addPixel);
        Boolean gotIntersection = false;

        while (!gotIntersection  && vCar.getLayoutX()>0 && vCar.getLayoutY()>0 && vCar.getLayoutX()<=1280 && vCar.getLayoutY()<=720){
            if(world.checkIntersection(vCar)){
                gotIntersection = true;
            }
            vCar.setLayoutY(vCar.getLayoutY() - 1);
        }

        if(gotIntersection){
            return new Point(vCar.getLayoutX(),vCar.getLayoutY()-20);
        }
        else{
            return null;
        }

    }
    private Point doMoveInYButtom(Point vPoint,int addPixel){

        Circle vCar = new Circle();
        vCar.setLayoutX(vPoint.getX());
        vCar.setLayoutY(vPoint.getY()+addPixel);
        Boolean gotIntersection = false;

        while (!gotIntersection  && vCar.getLayoutX()>0 && vCar.getLayoutY()>0 && vCar.getLayoutX()<=1280 && vCar.getLayoutY()<=720){
            if(world.checkIntersection(vCar)){
                gotIntersection = true;
            }
            vCar.setLayoutY(vCar.getLayoutY() + 1);
        }

        if(gotIntersection){
            return new Point(vCar.getLayoutX(),vCar.getLayoutY()+20);
        }
        else{
            return null;
        }
    }


    public Circle getPhysical() {
        return physical;
    }

    public void setPhysical(Circle physical) {
        this.physical = physical;
    }

    public Point getDesires() {
        return desires;
    }

    public void setDesires(Point desires) {
        this.desires = desires;
    }

    public Boolean getHasNewDesires() {
        return hasNewDesires;
    }

    public void setHasNewDesires(Boolean hasNewDesires) {
        this.hasNewDesires = hasNewDesires;
    }

    public void runAgain(){
        this.notify();
    }

    public void changingValueOfMovingByXorY(){

    }

}
