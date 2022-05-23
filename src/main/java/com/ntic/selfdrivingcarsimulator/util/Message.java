package com.ntic.selfdrivingcarsimulator.util;

import com.ntic.selfdrivingcarsimulator.gui.MapController;
import javafx.application.Platform;
import javafx.scene.control.Alert;

public class Message {

    public static void messageErrorThread(MapController context){
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                context.messageAlert("Can't Found other plan","Can't Found other plan", Alert.AlertType.ERROR);
            }
        });
    }

    public static void UIPetrolThread(MapController context,int petrolTank){
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                context.setEssenceUIValue(petrolTank);
            }
        });
    }

    public static void UISpeedThread(MapController context,double speed){
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                if(speed==1){
                    context.setSpeedUIValue(100);
                }else{
                    context.setSpeedUIValue(speed);
                }
            }
        });
    }
}
