package org.solutione.santarita;
 
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.solutione.santarita.controller.Splash;

import java.io.IOException;

public class  Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Santa Rita");

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/org/solutione/santarita/view/Splash.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage splash = new Stage();
        splash.setScene(new Scene(root, 600, 500));
        splash.setMaximized(true);
        splash.initStyle(StageStyle.UNDECORATED);
        splash.show();

        Thread t = new Thread(){
            @Override
            public void run(){
                try {
                    Thread.sleep(5000);

                    Platform.runLater(() -> {

                        Parent root = null;
                        try {
                            root = FXMLLoader.load(getClass().getResource("/org/solutione/santarita/view/Principal.fxml"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        splash.close();
                        primaryStage.setScene(new Scene(root, 300, 250));
                        primaryStage.setMaximized(true);
                        primaryStage.initStyle(StageStyle.UNDECORATED);

                        primaryStage.show();
                    });
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        };
        t.start();


    }
}
