package com.ntic.selfdrivingcarsimulator.service;

import com.ntic.selfdrivingcarsimulator.model.Feux;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Transformation {

    public static String stripNonDigits(final CharSequence input ){
        final StringBuilder sb = new StringBuilder(input.length());
        for(int i = 0; i < input.length(); i++){
            final char c = input.charAt(i);
            if(c > 47 && c < 58){
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static void updateScreenInX(Circle physical,int value){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                physical.setLayoutX(physical.getLayoutX() + value);
            }
        });
    }

    public static void updateScreenInY(Circle physical,int value){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                physical.setLayoutY(physical.getLayoutY() + value);
            }
        });
    }

    public static void updateUIsetColor(Circle circle, String color){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if(color.equals("GREEN"))
                    circle.setFill(Color.GREEN);
                else
                    circle.setFill(Color.RED);
            }
        });
    }

}
