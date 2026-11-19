package com.mycompany.assigment_2;

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
import javafx.animation.RotateTransition;
import javafx.animation.Interpolator;

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
    
    private ArrayList<CarRaceModel> finishOrder = new ArrayList<>();
    
    private ArrayList<RotateTransition> wheelAnimations = new ArrayList<>();
    
    private boolean raceFinished = false;
  
    public void initialize() {
        cars.add(new CarRaceModel("Sports Car", 23, 60, 20, 2.0, Color.BLUE));
        cars.add(new CarRaceModel("Sports Car", 45, 60, 95, 1.8, Color.RED));
        cars.add(new CarRaceModel("Sports Car", 78, 60, 170, 1.6, Color.GREEN));
        cars.add(new CarRaceModel("Sports Car", 88, 60, 245, 1.5, Color.YELLOW));

        for (int i = 0; i < cars.size(); i++) {
            Group car = createCar(cars.get(i));
            carGroups.add(car);
            racePane.getChildren().add(car);
        }
        createAnimations();
        setupSpeedSlider();
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
        
        Group wheelGroup1 = new Group(wheel1, line1);
        Group wheelGroup2 = new Group(wheel2, line2);

        RotateTransition rotate1 = new RotateTransition(Duration.seconds(0.5), wheelGroup1);
        rotate1.setByAngle(360);
        rotate1.setCycleCount(RotateTransition.INDEFINITE);
        rotate1.setInterpolator(Interpolator.LINEAR);

        RotateTransition rotate2 = new RotateTransition(Duration.seconds(0.5), wheelGroup2);
        rotate2.setByAngle(360);
        rotate2.setCycleCount(RotateTransition.INDEFINITE);
        rotate2.setInterpolator(Interpolator.LINEAR);

        wheelAnimations.add(rotate1);
        wheelAnimations.add(rotate2);

        // Car number
        Text number = new Text(34, 20, String.valueOf(car.getNumber()));
        number.setFill(Color.WHITE);
        group.getChildren().addAll(body, roof, window1, window2, wheelGroup1, wheelGroup2, number);
        group.setLayoutX(car.getX());
        group.setLayoutY(car.getY());

        return group;
    }
    
    // Create animations for all cars
    private void createAnimations() {
        for (int i = 0; i < carGroups.size(); i++) {
            TranslateTransition animation = new TranslateTransition();
            CarRaceModel car = cars.get(i);
            animation.setNode(carGroups.get(i));
            animation.setDuration(Duration.seconds(10 / car.getSpeed()));
            animation.setByX(705);

            animation.setOnFinished(e -> {
                finishOrder.add(car);

                if (finishOrder.size() == cars.size()) {
                    raceFinished = true;

                    for (int j = 0; j < wheelAnimations.size(); j++) {
                        wheelAnimations.get(j).stop();
                    }
                    showResults();
                }
            });

            animations.add(animation);
        }
    }
    
    @FXML
    private void startRace() {
        // Do not start again if the race is finished
        if (raceFinished) {
            return;
        }

        // Start all car animations
        for (int i = 0; i < animations.size(); i++) {
            animations.get(i).play();
        }

        // Start all wheel animations
        for (int i = 0; i < wheelAnimations.size(); i++) {
            wheelAnimations.get(i).play();
        }
    }
    
    
    @FXML
    private void pauseRace() {
        // Pause all car animations
        for (int i = 0; i < animations.size(); i++) {
            animations.get(i).pause();
        }

         // Pause all wheel animations
        for (int i = 0; i < wheelAnimations.size(); i++) {
            wheelAnimations.get(i).pause();
        }
    }
    
    @FXML
    private void resetRace() {
        // Reset car positions
        for (int i = 0; i < animations.size(); i++) {
            animations.get(i).stop();
            animations.get(i).getNode().setTranslateX(0);
        }

        // Reset wheel rotations
        for (int i = 0; i < wheelAnimations.size(); i++) {
            wheelAnimations.get(i).stop();
            wheelAnimations.get(i).getNode().setRotate(0);
        }

        // Reset all result labels
        finishOrder.clear();
        raceFinished = false;
        resultsTitleLabel.setText("Race Results");
        firstPlaceLabel.setText("1st Place: ");
        secondPlaceLabel.setText("2nd Place: ");
        thirdPlaceLabel.setText("3rd Place: ");
        fourthPlaceLabel.setText("4th Place: ");
        winnerLabel.setText("Winner: ");
        raceFinishedLabel.setText("");
    }
    
    // Show race results
    private void showResults() {
        resultsTitleLabel.setText("Race Results");
        firstPlaceLabel.setText("1st Place: Car " + finishOrder.get(0).getNumber());
        secondPlaceLabel.setText("2nd Place: Car " + finishOrder.get(1).getNumber());
        thirdPlaceLabel.setText("3rd Place: Car " + finishOrder.get(2).getNumber());
        fourthPlaceLabel.setText("4th Place: Car " + finishOrder.get(3).getNumber());
        winnerLabel.setText("Winner: Car " + finishOrder.get(0).getNumber());
        raceFinishedLabel.setText("Race Finished!");
    }
    
    // Set up the speed slider
    private void setupSpeedSlider() {
        speedSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            double speed = newValue.doubleValue();
            speedValueLabel.setText(speed + "x");
            
             // Update the duration of each car animation
            for (int i = 0; i < animations.size(); i++) {
                animations.get(i).setDuration(
                    Duration.seconds(10 / (cars.get(i).getSpeed() * speed))
                );
            }
        });
    }
}
