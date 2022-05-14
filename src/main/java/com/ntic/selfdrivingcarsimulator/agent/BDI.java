package com.ntic.selfdrivingcarsimulator.agent;

import com.ntic.selfdrivingcarsimulator.gui.MapController;
import com.ntic.selfdrivingcarsimulator.reasoning.Point;
import com.ntic.selfdrivingcarsimulator.util.Check;
import com.ntic.selfdrivingcarsimulator.util.Message;
import com.ntic.selfdrivingcarsimulator.util.Movement;
import com.ntic.selfdrivingcarsimulator.util.Plan;
import javafx.application.Platform;
import javafx.scene.control.Alert;
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

    public BDI(Circle physical,MapController context){
        this.physical=physical;
        this.context=context;
        this.planPath = new ArrayList<>();
        this.hasNewDesires = false;
        this.commonObstacles = new LinkedList<>();
        this.newPlanPath = new ArrayList<>();
    }

    public Circle vcarInit(){

        Circle vcar = new Circle();
        vcar.setLayoutX(physical.getLayoutX()+20);
        vcar.setLayoutY(physical.getLayoutY()+20);
        return vcar;

    }

    private String observation(){
        Circle vcar = vcarInit();
        Rectangle obstacle = Check.checkingObstacle(context,vcar);
        if(obstacle != null) {
            // for add obstacle to his list
            commonObstacles.add(obstacle);

            return "Obstacle";

        }
        return "Empty";
    }

    private void genererLesButs(){
        // in pathPlan
        Plan.addPointToList(context,physical,planPath,desires);
    }

    private void deliberation(String observation){

        Circle vcar = vcarInit();
        switch(observation){
            case "Obstacle":Plan.changePlan(this,vcar); break;
        }
    }

    private void realiserLesIntentions(){
        doMove();
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
}
