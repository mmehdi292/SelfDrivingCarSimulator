package com.ntic.selfdrivingcarsimulator.gui;


import com.ntic.selfdrivingcarsimulator.agent.BDI;
import com.ntic.selfdrivingcarsimulator.reasoning.Point;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
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

    public ArrayList<Rectangle> obstaclsList;


    @FXML
    public void initialize() {
        this.agent = new BDI(car,this);
        this.obstaclsList = new ArrayList<>();
        //animationTimer.start();
    }


    @FXML
    void CreateObstacle(ActionEvent event) {

        addObstacle.setDisable(true);

        screen.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                //for display error message
                boolean isCreated = false;

                double x = event.getSceneX();
                double y = event.getSceneY();

                // this circle for make tests only
                Circle clickedPoint = new Circle();
                clickedPoint.setLayoutX(x);
                clickedPoint.setLayoutY(y);

                ArrayList<Rectangle> routes = routesList();

                //now routes contains all routes and intersections
                routes.addAll(intersectionsList());

                for(Rectangle rectangle: routes){
                    if(checkCollision(clickedPoint,rectangle)){
                        Rectangle obstacle = new Rectangle();

                        //first fix width and height with length of obstacle
                        double obstacleWidth =10;
                        double obstacleHeight = 10;


                        if(rectangle.getId().contains("intersection")){
                            // if is intersection cover all zone with obstacle
                            obstacleWidth = rectangle.getWidth();
                            obstacleHeight = rectangle.getHeight();
                            x = rectangle.getLayoutX();
                            y = rectangle.getLayoutY();
                        }
                        else{
                            // changing width or height depending on the case
                            if(rectangle.getWidth()>rectangle.getHeight()){
                                obstacleHeight = rectangle.getHeight();
                                x = x-5;
                                y = rectangle.getLayoutY();
                            }
                            else{
                                obstacleWidth = rectangle.getWidth();
                                x = rectangle.getLayoutX();
                                y = y-5;
                            }
                        }


                        obstacle.setWidth(obstacleWidth);
                        obstacle.setHeight(obstacleHeight);

                        //for make click x and y in center [not in top right]
                        obstacle.setX(x);
                        obstacle.setY(y);

                        obstacle.setFill(Color.GOLD);

                        // add obstacle in list of getting access to it
                        obstaclsList.add(obstacle);

                        //add obstacle to screen
                        screen.getChildren().add(obstacle);

                        // changing display error condition
                        isCreated= true;
                    }
                }

                //display error message
                if(!isCreated){
                    messageAlert("Error de position!","Clickez sur un route ou une intersection SVP!", Alert.AlertType.ERROR);
                }

                // for make one click only
                screen.setOnMouseClicked(null);
                addObstacle.setDisable(false);
            }
        });
    }

    @FXML
    void loadPlace(ActionEvent event) {
        changeCarDesire(placesComboBox.getValue().toString());
        agent.setHasNewDesires(true);
        //agent.runAgain();
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

    public void messageAlert(String title, String content, Alert.AlertType type){
        Alert errorAlert = new Alert(type);
        errorAlert.setHeaderText(title);
        errorAlert.setContentText(content);
        errorAlert.showAndWait();
    }

    public ArrayList<Rectangle> intersectionsList(){
        ArrayList<Rectangle> intersections = new ArrayList<>();
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

    public ArrayList<Rectangle> routesList(){
        ArrayList<Rectangle> routes = new ArrayList<>();
        routes.add(route1);
        routes.add(route2);
        routes.add(route3);
        routes.add(route4);
        routes.add(route5);
        routes.add(route6);
        routes.add(route7);
        routes.add(route8);
        routes.add(route9);
        routes.add(route10);
        routes.add(route11);
        routes.add(route12);
        routes.add(route13);
        routes.add(route14);
        routes.add(route15);
        routes.add(route16);
        routes.add(route17);
        routes.add(route18);
        routes.add(route19);
        routes.add(route20);
        routes.add(route21);
        routes.add(route22);
        routes.add(route23);
        routes.add(route24);
        return routes;
    }
    public Boolean checkIntersection(Circle circle){
        ArrayList<Rectangle> zones = intersectionsList();
        for(Rectangle zone : zones){
            if(checkCollision(circle,zone)){
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