package com.ntic.selfdrivingcarsimulator.gui;

import com.ntic.selfdrivingcarsimulator.App;
import com.ntic.selfdrivingcarsimulator.agent.BDI;
import com.ntic.selfdrivingcarsimulator.reasoning.Point;
import eu.hansolo.tilesfx.addons.Switch;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.shape.Circle;

import java.io.IOException;

public class ControlPanelController {



    @FXML
    private ComboBox<?> placesComboBox;

    @FXML
    private Button startButton;

    @FXML
    void loadPlacesData(ActionEvent event) throws IOException {
        System.out.println(placesComboBox.getValue().toString());

        // change disared value


    }
    public  ControlPanelController (){


    }

    @FXML
    public void initialize() {

    }

    @FXML
    void startAgent(ActionEvent event) {
        //MapController.agent.start();

    }

}

