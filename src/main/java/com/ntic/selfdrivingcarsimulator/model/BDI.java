package com.ntic.selfdrivingcarsimulator.model;

import com.ntic.selfdrivingcarsimulator.controller.MapController;
import com.ntic.selfdrivingcarsimulator.config.Constants;
import com.ntic.selfdrivingcarsimulator.service.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.*;

public class BDI extends Thread {

    private Circle physical;
    private MapController context;
    private LinkedList<Rectangle> commonObstacles;

    private Point desires;
    private ArrayList<Point> planPath;
    private ArrayList<Point> newPlanPath;
    private Boolean hasNewDesires;
    private Boolean foundObstacle = false;
    private Point newPointOfEviteObstacle;
    private Boolean startMoveValue = true;

    public LightSensor sensor;
    public StopSensor stopSensor;
    public double speed;
    public int petrolTank;
    private Boolean directionToFuelStation;
    private Point fuelStation;

    public BDI(Circle physical,MapController context){
        this.physical=physical;
        this.context=context;
        this.planPath = new ArrayList<>();
        this.hasNewDesires = false;
        this.commonObstacles = new LinkedList<>();
        this.newPlanPath = new ArrayList<>();
        this.speed = Constants.CAR_DEFAULT_SPEED;
        this.petrolTank= Constants.CAR_MAX_PETROL_TANK;
        this.directionToFuelStation = false;
        this.sensor = new LightSensor(false);
        this.stopSensor = new StopSensor(false);
        this.sensor.start();
        this.stopSensor.start();
    }

    //don't forget it
    public Circle vcarInit(){

        Circle vcar = new Circle();

        List list = Collections.synchronizedList(planPath);
        Iterator i = list.iterator();
        Point point = null;
        if(i.hasNext())
            point = (Point) i.next();
        else
            point = desires;

        if(physical.getLayoutX()!=point.getX()) {
            vcar.setLayoutY(physical.getLayoutY());
            if(physical.getLayoutX()>point.getX()) {
                vcar.setLayoutX(physical.getLayoutX() - Constants.VCAR_DETETION);
            }
            else {
                vcar.setLayoutX(physical.getLayoutX()+Constants.VCAR_DETETION);
            }

        }else{
            vcar.setLayoutX(physical.getLayoutX());
            if(physical.getLayoutY()!=point.getY()) {
                if(physical.getLayoutY()>point.getY()) {
                    vcar.setLayoutY(physical.getLayoutY() - Constants.VCAR_DETETION);
                }
                else {
                    vcar.setLayoutY(physical.getLayoutY()+Constants.VCAR_DETETION);
                }
            }
        }



        return vcar;

    }

    public String observation(){


        Circle vcar = vcarInit();

        Rectangle obstacle = Check.checkingObstacle(context,vcar);
        if(obstacle != null) {
            // for add obstacle to his list
            commonObstacles.add(obstacle);

            return "Obstacle";
        }

        if(!sensor.foundLight){
            Rectangle passageLight = Check.checkingLight(context,vcar);
            if(passageLight != null) {
                int passageLightNumber = Integer.parseInt(Transformation.stripNonDigits(passageLight.getId()));
                if(context.getFeuxById(passageLightNumber).getColor().equals("RED")){
                    return "RED_LIGHT";
                }
                else{
                    this.sensor.foundLight = true;
                }
            }
        }

        Rectangle plaque = Check.checkingSpeedPlaques(context,this.getPhysical());
        if(plaque !=null){
            return "PLAQUE";
        }
        else {
            this.speed=1;
        }

        if(!stopSensor.foundPlaque){
            Rectangle stop = Check.checkingStopPlaques(context,this.getPhysical());
            if(stop !=null){
                this.stopSensor.foundPlaque = true;
                return "STOP";

            }
        }

        Walkway walkway = Check.checkingWalkways(context,vcar);
        if(walkway !=null){
            return "WALKWAY";
        }



        //must be last
        if(this.directionToFuelStation == false){
            return "FUEL_STATION";
        }

        return "Empty";
    }

    public void genererLesButs(){
        // in pathPlan
        Plan.addPointToList(context,physical,planPath,desires);
    }

    public void deliberation(String observation){

        Circle vcar = vcarInit();
        switch(observation){
            case "Obstacle":Plan.changePlan(this,vcar); break;
            case "RED_LIGHT":Plan.waitChangingColor(context,vcar,sensor);break;
            case "PLAQUE":Plan.changeSpeed(this,vcar);break;
            case "STOP":Plan.stopAndCheckRightPlan(this,vcar);break;
            case "WALKWAY": Plan.checkHumans(this.getContext(),vcar); break;
            case "FUEL_STATION":Plan.goToFuelStationPlan(this);break;
        }
    }

    public void realiserLesIntentions(){
        if(this.getDirectionToFuelStation()){
            goToStation();
            this.setDirectionToFuelStation(false);
            while (this.petrolTank<= Constants.CAR_MAX_PETROL_TANK){
                try {
                    this.sleep(Constants.TIME_TO_ADD_ONE_UNIT_OF_FUEL);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                this.petrolTank += 1;
                Message.UIPetrolThread(getContext(),petrolTank);
            }
            Plan.addPointToList(this.getContext(), this.getPhysical(),this.getPlanPath(),this.getDesires());
            doMove();
        }
        else{
            doMove();
        }

    }



    //intentions
    public void run(){
        while(true){
            System.out.print("");
            if(hasNewDesires){
                genererLesButs();
                String observation = observation();
                deliberation(observation);
                realiserLesIntentions();
                hasNewDesires = false;
            }
        }
    }


    private synchronized void doMove(){

        List list = Collections.synchronizedList(planPath);
        synchronized (list) {
            Iterator i = list.iterator(); // Must be in synchronized block
            while (i.hasNext())
                Movement.goToPoint(this,(Point) i.next());
        }
        planPath.clear();

        Movement.goToPoint(this,desires);
    }

    private synchronized void goToStation(){

        List list = Collections.synchronizedList(planPath);
        synchronized (list) {
            Iterator i = list.iterator(); // Must be in synchronized block
            while (i.hasNext())
                Movement.goToPoint(this,(Point) i.next());
        }
        planPath.clear();

        Movement.goToPoint(this,fuelStation);
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

    public MapController getContext() {
        return context;
    }

    public void setContext(MapController context) {
        this.context = context;
    }

    public LinkedList<Rectangle> getCommonObstacles() {
        return commonObstacles;
    }

    public void setCommonObstacles(LinkedList<Rectangle> commonObstacles) {
        this.commonObstacles = commonObstacles;
    }

    public ArrayList<Point> getPlanPath() {
        return planPath;
    }

    public void setPlanPath(ArrayList<Point> planPath) {
        this.planPath = planPath;
    }

    public ArrayList<Point> getNewPlanPath() {
        return newPlanPath;
    }

    public void setNewPlanPath(ArrayList<Point> newPlanPath) {
        this.newPlanPath = newPlanPath;
    }

    public Boolean getFoundObstacle() {
        return foundObstacle;
    }

    public void setFoundObstacle(Boolean foundObstacle) {
        this.foundObstacle = foundObstacle;
    }

    public Point getNewPointOfEviteObstacle() {
        return newPointOfEviteObstacle;
    }

    public void setNewPointOfEviteObstacle(Point newPointOfEviteObstacle) {
        this.newPointOfEviteObstacle = newPointOfEviteObstacle;
    }

    public Boolean getStartMoveValue() {
        return startMoveValue;
    }

    public void setStartMoveValue(Boolean startMoveValue) {
        this.startMoveValue = startMoveValue;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public Boolean getDirectionToFuelStation() {
        return directionToFuelStation;
    }

    public void setDirectionToFuelStation(Boolean directionToFuelStation) {
        this.directionToFuelStation = directionToFuelStation;
    }

    public Point getFuelStation() {
        return fuelStation;
    }

    public void setFuelStation(Point fuelStation) {
        this.fuelStation = fuelStation;
    }
}
