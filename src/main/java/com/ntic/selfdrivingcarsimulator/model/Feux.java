package com.ntic.selfdrivingcarsimulator.model;

import com.ntic.selfdrivingcarsimulator.service.Transformation;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Feux{

    private Circle UIComponent;
    private String color;

    public Feux(Circle UIComponent, String color) {
        this.UIComponent = UIComponent;
        this.color = color;
        if(color.equals("GREEN"))
            this.UIComponent.setFill(Color.GREEN);
        else
            this.UIComponent.setFill(Color.RED);
    }


    public void changeColor(){
        Transformation.updateUIsetColor(this.UIComponent,this.color);
    }

    public Circle getUIComponent() {
        return UIComponent;
    }

    public void setUIComponent(Circle UIComponent) {
        this.UIComponent = UIComponent;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
