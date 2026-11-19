/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assigment_2;

import javafx.scene.paint.Color;

/**
 *
 * @author Cynthia Zhang
 * github:https://github.com/Cynthiaz1012/Assignment_2
 */
public class CarRaceModel {
    private String type;
    private int number;
    private double x;
    private double y;
    private double speed;
    private Color color;
    
    public CarRaceModel(String type, int number, double x, double y, double speed, Color color) {
        this.type = type;
        this.number = number;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.color = color;
    }
    
    
    // Getter methods
    public String getType() {
        return type;
    }

    public int getNumber() {
        return number;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getSpeed() {
        return speed;
    }

    public Color getColor() {
        return color;
    }

    // Setter methods
    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
    
    public void updatePosition() {
        x += speed;
    }
}
