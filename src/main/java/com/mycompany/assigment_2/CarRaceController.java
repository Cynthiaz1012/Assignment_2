package com.mycompany.assigment_2;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class CarRaceController {

    @FXML
    private Button startButton;
    @FXML
    private Button pauseButton;
    @FXML
    private Button resetButton;
    @FXML
    private Label speedLabel;
    @FXML
    private Slider speedSlider;
    @FXML
    private Label speedValueLabel;
    @FXML
    private Label raceFinishedLabel;
    @FXML
    private Label winnerLabel;
    @FXML
    private Label resultsTitleLabel;
    @FXML
    private Label firstPlaceLabel;
    @FXML
    private Label secondPlaceLabel;
    @FXML
    private Label thirdPlaceLabel;
    @FXML
    private Label fourthPlaceLabel;
    @FXML
    private Pane racePane;
    
    private ArrayList<CarRaceModel> cars = new ArrayList<>();

    private ArrayList<Group> carGroups = new ArrayList<>();
    
    private ArrayList<TranslateTransition> animations = new ArrayList<>();

    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
    
    public void initialize() {
        cars.add(new CarRaceModel("Sports Car", 23, 60, 20, 1.5, Color.BLUE));
        cars.add(new CarRaceModel("Sports Car", 45, 60, 95, 1.8, Color.RED));
        cars.add(new CarRaceModel("Sports Car", 78, 60, 170, 1.6, Color.GREEN));
        cars.add(new CarRaceModel("Sports Car", 88, 60, 245, 2.0, Color.YELLOW));

        for (int i = 0; i < cars.size(); i++) {
            Group car = createCar(cars.get(i));
            carGroups.add(car);
            racePane.getChildren().add(car);
        }
        createAnimations();
    }
    
    private Group createCar(CarRaceModel car) {
        Group group = new Group();

        // Car body
        Polygon body = new Polygon(0, 14, 12, 5, 65, 5, 80, 14, 80, 25, 0, 25);
        body.setFill(car.getColor());
        body.setStroke(Color.BLACK);

        // Car roof
        Polygon roof = new Polygon(20, 5, 28, 0, 55, 0, 65, 5);
        roof.setFill(car.getColor());
        roof.setStroke(Color.BLACK);

        // Windows
        Rectangle window1 = new Rectangle(30, 1, 12, 9);
        Rectangle window2 = new Rectangle(45, 1, 12, 9);
        window1.setFill(Color.LIGHTBLUE);
        window2.setFill(Color.LIGHTBLUE);

        // Wheels
        Circle wheel1 = new Circle(18, 25, 7);
        Circle wheel2 = new Circle(62, 25, 7);
        wheel1.setFill(Color.BLACK);
        wheel2.setFill(Color.BLACK);

        // Wheel lines
        Line line1 = new Line(18, 25, 18, 19);
        Line line2 = new Line(62, 25, 62, 19);
        line1.setStroke(Color.WHITE);
        line2.setStroke(Color.WHITE);

        // Car number
        Text number = new Text(34, 20, String.valueOf(car.getNumber()));
        number.setFill(Color.WHITE);
        group.getChildren().addAll(body, roof, window1, window2, wheel1, wheel2, line1, line2, number);
        group.setLayoutX(car.getX());
        group.setLayoutY(car.getY());

        return group;
    }
    
    // Create animations for all cars
    private void createAnimations() {
    for (int i = 0; i < carGroups.size(); i++) {
        TranslateTransition animation = new TranslateTransition();
        animation.setNode(carGroups.get(i));
        animation.setDuration(Duration.seconds(10 / cars.get(i).getSpeed()));
        animation.setByX(500);

        animations.add(animation);
        }
    }
    
    @FXML
    private void startRace() {
        for (int i = 0; i < animations.size(); i++) {
            animations.get(i).play();
        }
    }
    
     @FXML
    private void pauseRace() {
        for (int i = 0; i < animations.size(); i++) {
            animations.get(i).pause();
        }
    }
}
