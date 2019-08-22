package org.solutione.santarita.controller;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Splash {
    public ImageView spinner;

    @FXML
    private void initialize(){
        spinnerAnimation();
    }

    private void spinnerAnimation() {
        RotateTransition rt = new RotateTransition(Duration.millis(5000), spinner);
        rt.setByAngle(360);
        rt.setCycleCount(Animation.INDEFINITE);
        rt.setInterpolator(Interpolator.LINEAR);
        rt.play();
    }
}
