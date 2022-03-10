package com.ntic.selfdrivingcarsimulator.agent;

import com.ntic.selfdrivingcarsimulator.reasoning.Action;
import com.ntic.selfdrivingcarsimulator.reasoning.Point;
import javafx.scene.shape.Circle;

import java.util.LinkedList;
import java.util.Queue;

public class BDI extends Thread {

    private Circle physical;
    private LinkedList<String> Beliefs;
    private Point desires;
    //private Queue<Action> intentions;

    public BDI(Circle physical,Point desires){
        this.physical=physical;
        this.desires=desires;

    }

    //intentions
    public void run(){
        while (true){

            this.doMove();
            try {
                this.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void doMove(){
        if(physical.getLayoutX()!=desires.getX()) {
            if(physical.getLayoutX()>desires.getX()) {
                physical.setLayoutX(physical.getLayoutX() - 1);
            }
            else {
                physical.setLayoutX(physical.getLayoutX() + 1);
            }

        }else{
            if(physical.getLayoutY()!=desires.getY()) {
                if(physical.getLayoutY()>desires.getY()) {
                    physical.setLayoutY(physical.getLayoutY() - 1);
                }
                else {
                    physical.setLayoutY(physical.getLayoutY() + 1);
                }
            }
        }
    }



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
}
