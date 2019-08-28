package org.solutione.santarita;
 
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.solutione.santarita.controller.PrSaleFinish;
import org.solutione.santarita.controller.Principal;
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
        splash.setScene(new Scene(root, 800, 640));
        splash.setMaximized(true);
        splash.initStyle(StageStyle.UNDECORATED);
        splash.show();

        Thread t = new Thread(){
            @Override
            public void run(){
                try {
                    Thread.sleep(5000);

                    Platform.runLater(() -> {

                        splash.close();

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/solutione/santarita/view/Principal.fxml"));
                        primaryStage.setMaximized(true);
                        primaryStage.initStyle(StageStyle.UNDECORATED);
                        try {
                            primaryStage.setScene(new Scene((Pane) loader.load(),800, 600));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        Principal controller = loader.<Principal>getController();
                        controller.initData(primaryStage);

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
