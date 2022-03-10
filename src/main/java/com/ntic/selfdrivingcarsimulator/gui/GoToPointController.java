package com.ntic.selfdrivingcarsimulator.gui;

import com.ntic.selfdrivingcarsimulator.agent.BDI;
import com.ntic.selfdrivingcarsimulator.reasoning.Point;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;

public class GoToPointController {

    @FXML
    private Button start;

    @FXML
    private Button createObstacle;

    @FXML
    private Circle car;

    @FXML
    private  Circle endPoint;

    @FXML
    private  Circle endPoint1;

    @FXML
    private  Circle endPoint2;

    public BDI agent;

    @FXML
    protected void onStartButtonClick() {
        agent = new BDI(car,new Point(endPoint.getLayoutX(),endPoint.getLayoutY()));
        agent.start();
    }

    @FXML
    protected void onP1ButtonClick() {
        agent.setDesires(new Point(endPoint.getLayoutX(),endPoint.getLayoutY()));
    }

    @FXML
    protected void onP2ButtonClick() {
        agent.setDesires(new Point(endPoint1.getLayoutX(),endPoint1.getLayoutY()));
    }


    @FXML
    protected void onP3ButtonClick() {
        agent.setDesires(new Point(endPoint2.getLayoutX(),endPoint2.getLayoutY()));
    }






}
