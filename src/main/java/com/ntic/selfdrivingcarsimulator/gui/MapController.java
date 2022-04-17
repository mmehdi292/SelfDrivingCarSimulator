package com.ntic.selfdrivingcarsimulator.gui;


import com.ntic.selfdrivingcarsimulator.agent.BDI;
import com.ntic.selfdrivingcarsimulator.reasoning.Point;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class MapController {

    @FXML
    private Button addObstacle;

    @FXML
    private Circle aeroport;

    @FXML
    private Circle car;

    @FXML
    private Circle commune;

    @FXML
    private Circle hopital;

    @FXML
    private Rectangle intersection1;

    @FXML
    private Rectangle intersection2;

    @FXML
    private Rectangle intersection3;

    @FXML
    private Rectangle intersection4;

    @FXML
    private Rectangle intersection5;

    @FXML
    private Rectangle intersection6;

    @FXML
    private Rectangle intersection7;

    @FXML
    private Rectangle intersection8;

    @FXML
    private Rectangle intersection9;

    @FXML
    private Circle mall;

    @FXML
    private Circle marche;

    @FXML
    private Circle masjed;

    @FXML
    private ComboBox<?> placesComboBox;

    @FXML
    private Circle police;

    @FXML
    private Circle pommeA;

    @FXML
    private Circle pommeB;

    @FXML
    private Circle pommeC;

    @FXML
    private Circle pommeD;

    @FXML
    private Circle poste;

    @FXML
    private Circle protictionCivil;

    @FXML
    private Rectangle route1;

    @FXML
    private Rectangle route10;

    @FXML
    private Rectangle route11;

    @FXML
    private Rectangle route12;

    @FXML
    private Rectangle route13;

    @FXML
    private Rectangle route14;

    @FXML
    private Rectangle route15;

    @FXML
    private Rectangle route16;

    @FXML
    private Rectangle route17;

    @FXML
    private Rectangle route18;

    @FXML
    private Rectangle route19;

    @FXML
    private Rectangle route2;

    @FXML
    private Rectangle route20;

    @FXML
    private Rectangle route21;

    @FXML
    private Rectangle route22;

    @FXML
    private Rectangle route23;

    @FXML
    private Rectangle route24;

    @FXML
    private Rectangle route3;

    @FXML
    private Rectangle route4;

    @FXML
    private Rectangle route5;

    @FXML
    private Rectangle route6;

    @FXML
    private Rectangle route7;

    @FXML
    private Rectangle route8;

    @FXML
    private Rectangle route9;

    @FXML
    private Circle salleSport;

    @FXML
    private AnchorPane screen;

    @FXML
    private Circle sntv;

    @FXML
    private Circle stade;

    @FXML
    private Button start;

    @FXML
    private Circle universite;

    @FXML
    private Rectangle zone1;

    @FXML
    private Rectangle zone10;

    @FXML
    private Rectangle zone11;

    @FXML
    private Rectangle zone12;

    @FXML
    private Rectangle zone13;

    @FXML
    private Rectangle zone14;

    @FXML
    private Rectangle zone15;

    @FXML
    private Rectangle zone16;

    @FXML
    private Rectangle zone2;

    @FXML
    private Rectangle zone3;

    @FXML
    private Rectangle zone4;

    @FXML
    private Rectangle zone5;

    @FXML
    private Rectangle zone6;

    @FXML
    private Rectangle zone7;

    @FXML
    private Rectangle zone8;

    @FXML
    private Rectangle zone9;

    public BDI agent;


    @FXML
    public void initialize() {
        this.agent = new BDI(car,this);
        //animationTimer.start();
    }


    @FXML
    void CreateObstacle(ActionEvent event) {

    }

    @FXML
    void loadPlace(ActionEvent event) {
        changeCarDesire(placesComboBox.getValue().toString());
        agent.setHasNewDesires(true);
    }

    @FXML
    void startCar(ActionEvent event) {
        start.setDisable(true);
        agent.start();
    }
    /*
    AnimationTimer animationTimer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            checkCollision(car,intersection1);
        }
    };*/
    public void changeCarDesire(String place){
        System.out.println(place);
        Circle desire;
        switch(place){
            case "Poste":desire = poste; break;
            case "Commune": desire = commune; break;
            case "Stade": desire = stade; break;
            case "Hopital": desire = hopital; break;
            case "Universite": desire = universite; break;
            case "Police": desire = police; break;
            case "Marche": desire = marche; break;
            case "Salle de sport": desire = salleSport; break;
            case "Pomme d'esense A": desire = pommeA; break;
            case "Pomme d'esense B": desire = pommeB; break;
            case "Pomme d'esense C": desire = pommeC; break;
            case "Pomme d'esense D": desire = pommeD; break;
            case "Masjed": desire = masjed; break;
            case "Mall": desire = mall; break;
            case "Protiction Civil": desire = protictionCivil; break;
            case "SNTV": desire = sntv; break;
            case "Aeroport":desire = aeroport; break;
            //for error only
            default:desire = null;
        }
        Point point = new Point(desire.getLayoutX(),desire.getLayoutY());
        agent.setDesires(point);
    }


    public Boolean checkCollision(Circle circle,Rectangle rectangle){
        if(circle.getBoundsInParent().intersects(rectangle.getBoundsInParent())){
            return true;
        }
        return false;
    }

    public ArrayList<Rectangle> intersectionsList(){
        ArrayList<Rectangle> intersections = new ArrayList<Rectangle>();
        intersections.add(intersection1);
        intersections.add(intersection2);
        intersections.add(intersection3);
        intersections.add(intersection4);
        intersections.add(intersection5);
        intersections.add(intersection6);
        intersections.add(intersection7);
        intersections.add(intersection8);
        intersections.add(intersection9);
        return intersections;
    }

    public Boolean checkIntersection(Circle circle){
        ArrayList<Rectangle> zones = intersectionsList();
        for(Rectangle zone : zones){
            if(checkCollision(circle,zone)){
                System.out.println("---"+zone.getId());
                return true;
            }
        }
        return false;
    }

    public Boolean checkAbility(){
        ArrayList<Rectangle> zones = intersectionsList();
        for(Rectangle zone : zones){
            if(checkCollision(car,zone)){
                return false;
            }
        }
        return true;
    }

}

/*
import com.ntic.selfdrivingcarsimulator.App;
import com.ntic.selfdrivingcarsimulator.agent.BDI;
import com.ntic.selfdrivingcarsimulator.reasoning.Point;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MapController {

    @FXML
    private Button addObstacle;

    @FXML
    private Circle sensor;

    @FXML
    private Circle aeroport;

    @FXML
    private Circle car;

    @FXML
    private Circle commune;

    @FXML
    private Circle hopital;

    @FXML
    private Circle mall;

    @FXML
    private Circle marche;

    @FXML
    private Circle masjed;

    @FXML
    private Circle police;

    @FXML
    private Circle pommeA;

    @FXML
    private Circle pommeB;

    @FXML
    private Circle pommeC;

    @FXML
    private Circle pommeD;

    @FXML
    private Circle poste;

    @FXML
    private Circle protictionCivil;

    @FXML
    private Circle salleSport;

    @FXML
    private AnchorPane screen;

    @FXML
    private Circle sntv;

    @FXML
    private Circle stade;

    @FXML
    private Button start;

    @FXML
    private Circle universite;

    @FXML
    private Rectangle zone1;

    @FXML
    private Rectangle zone10;

    @FXML
    private Rectangle zone11;

    @FXML
    private Rectangle zone12;

    @FXML
    private Rectangle zone13;

    @FXML
    private Rectangle zone14;

    @FXML
    private Rectangle zone15;

    @FXML
    private Rectangle zone16;

    @FXML
    private Rectangle zone2;

    @FXML
    private Rectangle zone3;

    @FXML
    private Rectangle zone4;

    @FXML
    private Rectangle zone5;

    @FXML
    private Rectangle zone6;

    @FXML
    private Rectangle zone7;

    @FXML
    private Rectangle zone8;

    @FXML
    private Rectangle zone9;

    @FXML
    void CreateObstacle(ActionEvent event) {
        System.out.println(car.getLayoutY()+"---"+car.getLayoutY());
    }

    public static BDI agent;

    @FXML
    void startCar(ActionEvent event) {
        /*Parent root;
        try {
            root = FXMLLoader.load(App.class.getResource("controlPanel.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Control panel");
            stage.setScene(new Scene(root, 200, 155));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.show();
            agent = new BDI(car);
            agent.setDesires(new Point(aeroport.getLayoutX(),aeroport.getLayoutY()));
            agent.start();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        agent = new BDI(car,sensor);
        agent.setDesires(new Point(aeroport.getLayoutX(),aeroport.getLayoutY()));
        agent.start();
    }

    public void changeCarDesire(String place){
        Circle desire;
        switch(place){
            case "Poste":desire = poste; break;
            case "Commune": desire = commune; break;
            case "Stade": desire = stade; break;
            case "Hopital": desire = hopital; break;
            case "Universite": desire = universite; break;
            case "Police": desire = police; break;
            case "Marche": desire = marche; break;
            case "Salle de sport": desire = salleSport; break;
            case "Pomme d'esense A": desire = pommeA; break;
            case "Pomme d'esense B": desire = pommeB; break;
            case "Pomme d'esense C": desire = pommeC; break;
            case "Pomme d'esense D": desire = pommeD; break;
            case "Masjed": desire = masjed; break;
            case "Mall": desire = mall; break;
            case "Protiction Civil": desire = protictionCivil; break;
            case "SNTV": desire = sntv; break;
            case "Aeroport":desire = aeroport; break;
            //for error only
            default:desire = null;
        }
        Point point = new Point(desire.getLayoutX(),desire.getLayoutY());
        agent.setDesires(point);
    }

}
*/