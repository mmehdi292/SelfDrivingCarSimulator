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
}
