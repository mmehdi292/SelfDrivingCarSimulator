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
    protected void onStartButtonClick() {
        BDI agent = new BDI(car,new Point(endPoint.getLayoutX(),endPoint.getLayoutY()));
        agent.start();
    }






}
