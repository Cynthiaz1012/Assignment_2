
package com.mycompany.assigment_2;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class SecondaryController {

    @FXML
    private ImageView carImageView;

    private int currentCar = 0;

    private String[] carImages = {
        "car23.png",
        "car45.png",
        "car78.png",
        "car88.png"
    };

    private Timeline slideshow;
    private MediaPlayer musicPlayer;

    @FXML
    public void initialize() {
        showCar();

        slideshow = new Timeline(new KeyFrame(Duration.seconds(3), e -> {

                if (currentCar < carImages.length - 1) {
                    currentCar++;
                    showCar();
                }
            })
        );

        slideshow.setCycleCount(carImages.length);
        slideshow.setOnFinished(e -> {
            try {
                switchToPrimary();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        String musicPath = getClass().getResource("/com/mycompany/assigment_2/car_race_music.wav").toExternalForm();
        Media music = new Media(musicPath);
        musicPlayer = new MediaPlayer(music);
        slideshow.play();
        musicPlayer.play();
    }

    private void showCar() {
        String imagePath = getClass().getResource("/com/mycompany/assigment_2/" + carImages[currentCar]).toExternalForm();
        Image image = new Image(imagePath);
        carImageView.setImage(image);
    }

    @FXML
    private void switchToPrimary() throws IOException {
        slideshow.stop();
        musicPlayer.stop();
        App.setRoot("primary");
    }
}
