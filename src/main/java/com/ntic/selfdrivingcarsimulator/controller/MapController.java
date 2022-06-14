package com.ntic.selfdrivingcarsimulator.controller;


import com.ntic.selfdrivingcarsimulator.model.BDI;
import com.ntic.selfdrivingcarsimulator.model.Walkway;
import com.ntic.selfdrivingcarsimulator.service.FeuxManager;
import com.ntic.selfdrivingcarsimulator.model.Feux;
import com.ntic.selfdrivingcarsimulator.model.Point;
import com.ntic.selfdrivingcarsimulator.service.HumanManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class MapController {

    @FXML
    private Circle feux1;

    @FXML
    private Circle feux10;

    @FXML
    private Circle feux11;

    @FXML
    private Circle feux12;

    @FXML
    private Circle feux2;

    @FXML
    private Circle feux3;

    @FXML
    private Circle feux4;

    @FXML
    private Circle feux5;

    @FXML
    private Circle feux6;

    @FXML
    private Circle feux7;

    @FXML
    private Circle feux8;

    @FXML
    private Circle feux9;

    @FXML
    private Rectangle passage1;

    @FXML
    private Rectangle passage10;

    @FXML
    private Rectangle passage11;

    @FXML
    private Rectangle passage12;

    @FXML
    private Rectangle passage2;

    @FXML
    private Rectangle passage3;

    @FXML
    private Rectangle passage4;

    @FXML
    private Rectangle passage5;

    @FXML
    private Rectangle passage6;

    @FXML
    private Rectangle passage7;

    @FXML
    private Rectangle passage8;

    @FXML
    private Rectangle passage9;

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

    @FXML
    private Rectangle plaque1_30;

    @FXML
    private Rectangle plaque2_30;

    @FXML
    private Rectangle plaque_50;

    @FXML
    private Rectangle stop1;

    @FXML
    private Rectangle stop2;

    @FXML
    private Rectangle stop3;

    @FXML
    private Rectangle stop4;

    @FXML
    private Rectangle stop5;

    @FXML
    private Rectangle stop6;

    @FXML
    private Rectangle stop7;

    @FXML
    private Rectangle stop8;

    @FXML
    private Label speedUIValue;

    @FXML
    public Label essenceUIValue;

    @FXML
    private Rectangle walkway_1;

    @FXML
    private Rectangle walkway_2;

    @FXML
    private Rectangle walkway_3;

    @FXML
    private Rectangle walkway_4;

    @FXML
    private Circle human_1;
    @FXML
    private Circle human_2;
    @FXML
    private Circle human_3;
    @FXML
    private Circle human_4;

    @FXML
    private Circle car2;


    public BDI agent;

    public BDI agentNotSelected;

    public ArrayList<Rectangle> obstaclsList;
    public ArrayList<Rectangle> passageLightList;
    public ArrayList<Feux> listFeux;
    public ArrayList<Walkway> listWalkways;



    @FXML
    public void initialize() {
        this.agent = new BDI(car,this);
        this.agentNotSelected = new BDI(car2,this,true);
        this.obstaclsList = new ArrayList<>();
        this.listWalkways = new ArrayList<>();
        listFeux = new ArrayList<>();
        listFeuxInit();
        FeuxManager feuxManager = new FeuxManager(this);
        feuxManager.start();
        passageLightList = passageList();

        HumanManager hm = new HumanManager(this);
        hm.start();

        this.walkways();

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
            case "Bank":desire = poste; break;
            case "Commune": desire = commune; break;
            case "Stadium": desire = stade; break;
            case "Hospital": desire = hopital; break;
            case "University": desire = universite; break;
            case "POLICE": desire = police; break;
            case "Market": desire = marche; break;
            case "Sports Hall": desire = salleSport; break;
            case "Fuel Pump A": desire = pommeA; break;
            case "Fuel Pump B": desire = pommeB; break;
            case "Fuel Pump C": desire = pommeC; break;
            case "Fuel Pump D": desire = pommeD; break;
            case "Masjed": desire = masjed; break;
            case "Mall": desire = mall; break;
            case "Fire Station": desire = protictionCivil; break;
            case "SNTV": desire = sntv; break;
            case "Airport":desire = aeroport; break;
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

    public Boolean checkCollision(Circle circle,Circle circle2){
        if(circle.getBoundsInParent().intersects(circle2.getBoundsInParent())){
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


    public ArrayList<Circle> feuxList(){
        ArrayList<Circle> list = new ArrayList<>();
        list.add(feux1);
        list.add(feux2);
        list.add(feux3);
        list.add(feux4);
        list.add(feux5);
        list.add(feux6);
        list.add(feux7);
        list.add(feux8);
        list.add(feux9);
        list.add(feux10);
        list.add(feux11);
        list.add(feux12);
        return list;
    }

    public Feux getFeuxById(int id){
        for(Feux f : listFeux){
            if(("feux"+id).equals(f.getUIComponent().getId())){
                return f;
            }
        }
        return null;
    }

    public void listFeuxInit(){
        listFeux.add(new Feux(feux1,"GREEN"));
        listFeux.add(new Feux(feux2,"RED"));
        listFeux.add(new Feux(feux3,"GREEN"));
        listFeux.add(new Feux(feux4,"RED"));
        listFeux.add(new Feux(feux5,"RED"));
        listFeux.add(new Feux(feux6,"GREEN"));
        listFeux.add(new Feux(feux7,"RED"));
        listFeux.add(new Feux(feux8,"GREEN"));
        listFeux.add(new Feux(feux9,"RED"));
        listFeux.add(new Feux(feux10,"GREEN"));
        listFeux.add(new Feux(feux11,"RED"));
        listFeux.add(new Feux(feux12,"GREEN"));

    }


    public ArrayList<Rectangle> passageList(){
        ArrayList<Rectangle> list = new ArrayList<>();
        list.add(passage1);
        list.add(passage2);
        list.add(passage3);
        list.add(passage4);
        list.add(passage5);
        list.add(passage6);
        list.add(passage7);
        list.add(passage8);
        list.add(passage9);
        list.add(passage10);
        list.add(passage11);
        list.add(passage12);
        return list;
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

    public ArrayList<Rectangle> plaquesVitasse(){
        ArrayList<Rectangle> list = new ArrayList<>();
        list.add(plaque_50);
        list.add(plaque1_30);
        list.add(plaque2_30);
        return list;
    }

    public ArrayList<Rectangle> stopPlaquesList(){
        ArrayList<Rectangle> list = new ArrayList<>();
        list.add(stop1);
        list.add(stop2);
        list.add(stop3);
        list.add(stop4);
        list.add(stop5);
        list.add(stop6);
        list.add(stop7);
        list.add(stop8);
        return list;
    }

    public void setSpeedUIValue(double value){
        speedUIValue.setText(value+"");
    }

    public void setEssenceUIValue(int value){
        essenceUIValue.setText(value+"");
    }

    public ArrayList<Circle> feulStations(){
        ArrayList<Circle> list = new ArrayList<>();
        list.add(pommeA);
        list.add(pommeB);
        list.add(pommeC);
        list.add(pommeD);
        return  list;

    }
    public ArrayList<Circle> humans(){
        ArrayList<Circle> list = new ArrayList<>();
        list.add(human_1);
        list.add(human_2);
        list.add(human_3);
        list.add(human_4);
        return list;
    }

    public void walkways(){
        this.listWalkways.add(new Walkway(walkway_1));
        this.listWalkways.add(new Walkway(walkway_2));
        this.listWalkways.add(new Walkway(walkway_3));
        this.listWalkways.add(new Walkway(walkway_4));
    }

    public ArrayList<Circle> listPlaces(){
        ArrayList<Circle> list = new ArrayList<>();
        list.add(pommeA);
        list.add(pommeB);
        list.add(pommeC);
        list.add(pommeD);
        list.add(salleSport);
        list.add(marche);
        list.add(police);
        list.add(universite);
        list.add(hopital);
        list.add(stade);
        list.add(commune);
        list.add(poste);
        list.add(masjed);
        list.add(mall);
        list.add(protictionCivil);
        list.add(sntv);
        list.add(aeroport);
        return list;
    }

    public Point randomePoint(){
        ArrayList<Circle> list = this.listPlaces();
        int randomNumber = (int) (Math.random() * list.size());
        Circle randomPlace = list.get(randomNumber);
        Point point = new Point(randomPlace.getLayoutX(),randomPlace.getLayoutY());
        return point;
    }

}