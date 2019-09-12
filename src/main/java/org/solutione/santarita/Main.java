package org.solutione.santarita;
 
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.solutione.santarita.api.*;
import org.solutione.santarita.controller.PrSale;
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
        splash.setAlwaysOnTop(true);

        Thread t2 = new Thread(){
            @Override
            public void run(){
            Platform.runLater(() -> {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/solutione/santarita/view/Principal.fxml"));
                primaryStage.setMaximized(true);
                primaryStage.initStyle(StageStyle.UNDECORATED);
                try {
                    primaryStage.setScene(new Scene((Pane) loader.load(),800, 600));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                ObservableList<Producto> products = new BDProductos().getProducts();
                ObservableList<Proveedor> providers = new BDProveedores().getProviders();
                ObservableList<History> history = new BDHistory().getHistory();
                double total = new BDData_F().getTotal();
                double benefit = new BDData_F().getBenefit();

                Principal controller = loader.<Principal>getController();
                controller.initData(primaryStage,products,providers,history,total,benefit,new Double(total-benefit).shortValue());

                primaryStage.show();
                primaryStage.setOpacity(0.0);
            });
            }
        };t2.start();

        Thread t = new Thread(){
            @Override
            public void run(){
            try {
                Thread.sleep(16000);

                Platform.runLater(() -> {
                    primaryStage.setOpacity(1.0);
                    primaryStage.toFront();
                    splash.close();
                });
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            }
        };t.start();
    }
}
