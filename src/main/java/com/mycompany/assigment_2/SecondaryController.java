
package com.mycompany.assigment_2;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;

public class SecondaryController {

    @FXML
    private ImageView carImage;
    private int currentImage = 0;
    private String[] carImages = {"car23.png", "car45.png", "car78.png","car88.png"};
    private Timeline slideshow;
    private MediaPlayer mediaPlayer;

    @FXML
    public void initialize() {
        showImage();

        slideshow = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
                currentImage++;

                if (currentImage >= carImages.length) {
                    currentImage = 0;
                }

                showImage();
            })
        );

        slideshow.setCycleCount(Timeline.INDEFINITE);
        slideshow.play();
        String musicPath = getClass().getResource("race-music.mp3").toExternalForm();
        Media music = new Media(musicPath);
        mediaPlayer = new MediaPlayer(music);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    private void showImage() {
        String imagePath = getClass().getResource(carImages[currentImage]).toExternalForm();
        Image image = new Image(imagePath);
        carImage.setImage(image);
    }

    @FXML
    private void switchToPrimary() throws IOException {
        slideshow.stop();
        mediaPlayer.stop();
        App.setRoot("primary");
    }
}
