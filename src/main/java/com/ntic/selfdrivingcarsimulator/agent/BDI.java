package com.ntic.selfdrivingcarsimulator.agent;

import com.ntic.selfdrivingcarsimulator.gui.MapController;
import com.ntic.selfdrivingcarsimulator.reasoning.Point;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class BDI extends Thread {

    private Circle physical;
    private MapController world;
    private LinkedList<String> Beliefs;
    private Point desires;
    private ArrayList<Point> planPath;
    private Boolean hasNewDesires;

    public BDI(Circle physical,MapController world){
        this.physical=physical;
        this.world=world;
        this.planPath = new ArrayList<Point>();
        this.hasNewDesires = false;
    }
    public BDI(Circle physical){
        this.physical=physical;

    }

    //intentions
    public void run(){
        while (true){
            System.out.println("while : "+hasNewDesires);

            if(hasNewDesires){
                createPlan();
                this.doMove();
                hasNewDesires = false;
            }
        }

    }

    private synchronized void createPlan(){

        System.out.println("---- create plan in -----");
        //car point
        Point physicalPoint = new Point(physical.getLayoutX(),physical.getLayoutY());

        System.out.println("---- physical point ok -----");
        //point 1
        System.out.println("---- Done point -----" + physicalPoint.getX()+"  "+physicalPoint.getY());
        Point p1Top = doMoveInYTop(physicalPoint);
        System.out.println("---- is null -----");
        Point p1Bottom = doMoveInYButtom(physicalPoint);
        Point p1Left = doMoveInXLeft(physicalPoint);
        Point p1Right = doMoveInXRight(physicalPoint);

        System.out.println("---p1Top---");
        System.out.println(p1Top==null);
        System.out.println("---p1Bottom---");
        System.out.println(p1Bottom==null);
        System.out.println("---p1Left---");
        System.out.println(p1Left==null);
        System.out.println("---p1Right---");
        System.out.println(p1Right==null);
        System.out.println("------");

        //logic
        if(p1Top!=null && p1Bottom==null && p1Left==null && p1Right==null){

            System.out.println("---- p1 top -----");

            planPath.add(p1Top);
        }
        if(p1Top==null && p1Bottom!=null && p1Left==null && p1Right==null){

            System.out.println("---- p1 bottom -----");
            planPath.add(p1Bottom);
        }
        if(p1Top==null && p1Bottom==null && p1Left!=null && p1Right==null){

            System.out.println("---- p1left -----");
            planPath.add(p1Left);
        }
        if(p1Top==null && p1Bottom==null && p1Left==null && p1Right!=null){

            System.out.println("---- p1Right -----");
            planPath.add(p1Right);
        }

        System.out.println("---- one is null ok -----");

        if(p1Top!=null && p1Bottom!=null){
            //get the closer point to desires Y
            //why desires ? [ Best path]

            System.out.println("---- in top and bottom  -----");
            if(Math.abs(desires.getY()- p1Top.getY())<Math.abs(desires.getY()- p1Bottom.getY())){

                System.out.println("---- add top -----");
                planPath.add(p1Top);
            }
            else{

                System.out.println("---- add bottom -----");
                planPath.add(p1Bottom);
            }
        }
        else{
            if(p1Left!=null && p1Right!=null){

                System.out.println("---- p1 right left -----");
                //get the closer point to desires X
                if(Math.abs(desires.getX()- p1Left.getX())<Math.abs(desires.getX()- p1Right.getX())){

                    System.out.println("---- add left -----");
                    planPath.add(p1Left);
                }
                else{
                    System.out.println("---- add right -----");
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

        for(Point p :planPath){
            goToPoint(p);
        }
        planPath.clear();
        goToPoint(desires);
    }

    private void goToPoint(Point point){
        Circle vcar = new Circle();
        while (physical.getLayoutX()!=point.getX() || physical.getLayoutY()!=point.getY() ){

        if(physical.getLayoutX()!=point.getX()) {
            vcar.setLayoutX(this.physical.getLayoutX()+20);
            vcar.setLayoutY(this.physical.getLayoutY());
            if(physical.getLayoutX()>point.getX()) {
                physical.setLayoutX(physical.getLayoutX() - 1);
            }
            else {
                physical.setLayoutX(physical.getLayoutX() + 1);
            }

        }else{
            vcar.setLayoutX(this.physical.getLayoutX());
            vcar.setLayoutY(this.physical.getLayoutY()+20);
            if(physical.getLayoutY()!=point.getY()) {
                if(physical.getLayoutY()>point.getY()) {
                    physical.setLayoutY(physical.getLayoutY() - 1);
                }
                else {
                    physical.setLayoutY(physical.getLayoutY() + 1);
                }
            }
        }
            //cheking obstact after 20 px
            if(checkingObstacle(vcar)){
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
            System.out.println("--------"+ vCar.getLayoutY());
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

    private boolean checkingObstacle(Circle circle){
        for(Rectangle obstacle : world.obstaclsList){
            if(world.checkCollision(circle,obstacle)){
                return true;
            }
        }
        return false;
    }


/*
    private void doMoveInX(){
        Circle virtualCar = physical;

        if(physical.getLayoutX()>desires.getX()) {
            virtualCar.setLayoutX(physical.getLayoutX()-1);
            if(world.checkAbility(virtualCar)){
                physical.setLayoutX(physical.getLayoutX() - 1);
            }
        }
        else {
            virtualCar.setLayoutX(physical.getLayoutX()+1);
            if(world.checkAbility(virtualCar)){
                physical.setLayoutX(physical.getLayoutX() + 1);
            }
        }
    }

    private void doMoveInY(){

        Circle virtualCar = physical;

            if(physical.getLayoutY()>desires.getY()) {
                virtualCar.setLayoutY(physical.getLayoutY()-1);
                if(world.checkAbility(virtualCar)){
                    physical.setLayoutX(physical.getLayoutY() - 1);
                }
            }
            else {
                virtualCar.setLayoutY(physical.getLayoutY()+1);
                if(world.checkAbility(virtualCar)){
                    physical.setLayoutX(physical.getLayoutY() + 1);
                }
            }

    }

    private Boolean doMove(Point desires){
        if(desires==null) return false;

        System.out.println("Start point: "+desires.getY()+" | "+desires.getY());
        while (physical.getLayoutX()!=desires.getX() || physical.getLayoutY()!=desires.getY() ){
            if(physical.getLayoutX()>desires.getX()) {
                physical.setLayoutX(physical.getLayoutX() - 1);
            }
            else {
                physical.setLayoutX(physical.getLayoutX() + 1);
            }

            if(physical.getLayoutY()>desires.getY()) {
                physical.setLayoutY(physical.getLayoutY() - 1);
            }
            else {
                physical.setLayoutY(physical.getLayoutY() + 1);
            }
            System.out.println("physical: "+physical.getLayoutY()+" | "+physical.getLayoutY());
            System.out.println("desire: "+desires.getX()+" | "+desires.getY());
            try {
                this.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        return true;




    }
*/





    public Circle getPhysical() {
        return physical;
    }

    public void setPhysical(Circle physical) {
        this.physical = physical;
    }

    public LinkedList<String> getBeliefs() {
        return Beliefs;
    }

    public void setBeliefs(LinkedList<String> beliefs) {
        Beliefs = beliefs;
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
}
