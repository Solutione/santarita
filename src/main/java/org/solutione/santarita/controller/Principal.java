package org.solutione.santarita.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDateTime;

public class Principal {

    public BorderPane BPPrincipal;

    public BorderPane bpSale;
    public BorderPane bpProvider;
    public BorderPane bpInventory;
    public BorderPane bpFinance;

    public Pane pnlSale;
    public Pane pnlProvider;
    public Pane pnlInventory;
    public Pane pnlFinance;

    public ImageView imgSale;
    public ImageView imgProvider;
    public ImageView imgInventory;
    public ImageView imgFinance;


    public ImageView imgConfig;

    public Label lblClock;

    private String bgPnlSel = "-fx-background-color: rgba(246, 245, 250, 1);";
    private String bgPnlNotSel = "-fx-background-color: rgba(63, 13, 22, 1);";

    @FXML
    void initialize() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            int second = LocalDateTime.now().getSecond();
            int minute = LocalDateTime.now().getMinute();
            int hour = LocalDateTime.now().getHour();
            lblClock.setText(hour + ":" + (minute) + ":" + second);
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    public void initData(Stage primaryStage) {}


    public void bpSaleMC(MouseEvent mouseEvent) {
        pnlSale.setStyle(bgPnlSel);
        pnlProvider.setStyle(bgPnlNotSel);
        pnlInventory.setStyle(bgPnlNotSel);
        pnlFinance.setStyle(bgPnlNotSel);

        imgSale.setImage(new Image("org/solutione/santarita/image/venta-b.png"));
        imgProvider.setImage(new Image("org/solutione/santarita/image/proveedor.png"));
        imgInventory.setImage(new Image("org/solutione/santarita/image/inventario.png"));
        imgFinance.setImage(new Image("org/solutione/santarita/image/administracion.png"));
        imgConfig.setImage(new Image("org/solutione/santarita/image/ajustes.png"));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/solutione/santarita/view/PrSale.fxml"));
        BorderPane bp = null;
        try {
            bp = (BorderPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BPPrincipal.setCenter(bp);

        PrSale controller = loader.<PrSale>getController();
        controller.initData(BPPrincipal);
    }

    public void bpProviderMC(MouseEvent mouseEvent) {
        pnlSale.setStyle(bgPnlNotSel);
        pnlProvider.setStyle(bgPnlSel);
        pnlInventory.setStyle(bgPnlNotSel);
        pnlFinance.setStyle(bgPnlNotSel);

        imgSale.setImage(new Image("org/solutione/santarita/image/venta.png"));
        imgProvider.setImage(new Image("org/solutione/santarita/image/proveedor-b.png"));
        imgInventory.setImage(new Image("org/solutione/santarita/image/inventario.png"));
        imgFinance.setImage(new Image("org/solutione/santarita/image/administracion.png"));
        imgConfig.setImage(new Image("org/solutione/santarita/image/ajustes.png"));


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/solutione/santarita/view/PrProvider.fxml"));

        BorderPane bp = null;
        try {
            bp = (BorderPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        PrProvider controller = loader.<PrProvider>getController();
        controller.initData(BPPrincipal);
        BPPrincipal.setCenter(bp);
    }

    public void bpInventoryMC(MouseEvent mouseEvent) {
        pnlSale.setStyle(bgPnlNotSel);
        pnlProvider.setStyle(bgPnlNotSel);
        pnlInventory.setStyle(bgPnlSel);
        pnlFinance.setStyle(bgPnlNotSel);

        imgSale.setImage(new Image("org/solutione/santarita/image/venta.png"));
        imgProvider.setImage(new Image("org/solutione/santarita/image/proveedor.png"));
        imgInventory.setImage(new Image("org/solutione/santarita/image/inventario-b.png"));
        imgFinance.setImage(new Image("org/solutione/santarita/image/administracion.png"));
        imgConfig.setImage(new Image("org/solutione/santarita/image/ajustes.png"));

        BorderPane bp = null;
        try {
            bp = (BorderPane) FXMLLoader.load(getClass().getResource("/org/solutione/santarita/view/PrInventory.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        BPPrincipal.setCenter(bp);

    }

    public void bpFinanceMC(MouseEvent mouseEvent) {
        pnlSale.setStyle(bgPnlNotSel);
        pnlProvider.setStyle(bgPnlNotSel);
        pnlInventory.setStyle(bgPnlNotSel);
        pnlFinance.setStyle(bgPnlSel);

        imgSale.setImage(new Image("org/solutione/santarita/image/venta.png"));
        imgProvider.setImage(new Image("org/solutione/santarita/image/proveedor.png"));
        imgInventory.setImage(new Image("org/solutione/santarita/image/inventario.png"));
        imgFinance.setImage(new Image("org/solutione/santarita/image/administracion-b.png"));
        imgConfig.setImage(new Image("org/solutione/santarita/image/ajustes.png"));

        BorderPane bp = null;
        try {
            bp = (BorderPane) FXMLLoader.load(getClass().getResource("/org/solutione/santarita/view/PrFinance.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        BPPrincipal.setCenter(bp);
    }

    public void imgConfigMC(MouseEvent mouseEvent) {

        pnlSale.setStyle(bgPnlNotSel);
        pnlProvider.setStyle(bgPnlNotSel);
        pnlInventory.setStyle(bgPnlNotSel);
        pnlFinance.setStyle(bgPnlNotSel);

        imgSale.setImage(new Image("org/solutione/santarita/image/venta.png"));
        imgProvider.setImage(new Image("org/solutione/santarita/image/proveedor.png"));
        imgInventory.setImage(new Image("org/solutione/santarita/image/inventario.png"));
        imgFinance.setImage(new Image("org/solutione/santarita/image/administracion.png"));
        imgConfig.setImage(new Image("org/solutione/santarita/image/ajustes-b.png"));

        BorderPane bp = null;
        try {
            bp = (BorderPane) FXMLLoader.load(getClass().getResource("/org/solutione/santarita/view/PrConfig.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        BPPrincipal.setCenter(bp);
    }

    public void lblClockMC(MouseEvent mouseEvent) {
    }
}
