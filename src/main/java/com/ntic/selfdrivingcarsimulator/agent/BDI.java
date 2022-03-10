package com.ntic.selfdrivingcarsimulator.agent;

import com.ntic.selfdrivingcarsimulator.reasoning.Action;
import com.ntic.selfdrivingcarsimulator.reasoning.Point;
import javafx.scene.shape.Circle;

import java.util.LinkedList;
import java.util.Queue;

public class BDI extends Thread {
    private String id;
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
        while (physical.getLayoutX()!=desires.getX()){
            physical.setLayoutX(physical.getLayoutX()+1);
            try {
                sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
