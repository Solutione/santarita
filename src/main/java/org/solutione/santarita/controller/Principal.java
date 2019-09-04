package org.solutione.santarita.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.ObservableList;
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
import org.solutione.santarita.api.Producto;
import org.solutione.santarita.api.Proveedor;

import java.io.IOException;
import java.time.LocalDateTime;

public class Principal {

    private Stage primaryStage;
    private ObservableList<Producto> products;
    private ObservableList<Proveedor> providers;
    private ObservableList<BorderPane> panels;

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

    private BorderPane menuSale;
    private BorderPane menuProvider;
    private BorderPane menuInventory;
    private BorderPane menuFinance;

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

    public void initData(Stage primaryStage,
                         ObservableList<Producto> products,
                         ObservableList<Proveedor> providers) {
        this.primaryStage = primaryStage;
        this.products = products;
        this.providers = providers;

        //Sale
        Thread tsale = new Thread(){
            @Override
            public void run(){
            Platform.runLater(() -> {
                FXMLLoader loaderSale = new FXMLLoader(getClass().getResource("/org/solutione/santarita/view/PrSale.fxml"));
                try {
                    menuSale = loaderSale.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                PrSale controllerSale = loaderSale.getController();
                controllerSale.initData(BPPrincipal);
                BPPrincipal.setCenter(menuSale);
            });
            }
        };tsale.start();

        //Provider
        Thread tprovider = new Thread(){
            @Override
            public void run(){
            try {
                Thread.sleep(4000);
                Platform.runLater(() -> {
                    FXMLLoader loaderProvider = new FXMLLoader(getClass().getResource("/org/solutione/santarita/view/PrProvider.fxml"));
                    try {
                        menuProvider = loaderProvider.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    PrProvider controllerProvider = loaderProvider.<PrProvider>getController();
                    controllerProvider.initData(BPPrincipal);
                    BPPrincipal.setCenter(menuProvider);
                });
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            }
        };tprovider.start();

        //Inventory
        Thread tinventory = new Thread(){
            @Override
            public void run(){
            try {
                Thread.sleep(6000);

                Platform.runLater(() -> {
                    FXMLLoader loaderInventory = new FXMLLoader(getClass().getResource("/org/solutione/santarita/view/PrInventory.fxml"));
                    try {
                        menuInventory = loaderInventory.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    PrInventory controllerInventory = loaderInventory.<PrInventory>getController();
                    controllerInventory.initData(BPPrincipal);
                    BPPrincipal.setCenter(menuInventory);
                });
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            }
        };tinventory.start();

        //Finance
        Thread tfinance = new Thread(){
            @Override
            public void run(){
            try {
                Thread.sleep(11000);
                Platform.runLater(() -> {
                    FXMLLoader loaderFinance = new FXMLLoader(getClass().getResource("/org/solutione/santarita/view/PrFinance.fxml"));
                    try {
                        menuFinance = loaderFinance.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    PrFinance controllerFinance = loaderFinance.<PrFinance>getController();
                    controllerFinance.initData(BPPrincipal);
                    BPPrincipal.setCenter(menuFinance);
                });
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            }
        };tfinance.start();

        Thread t = new Thread(){
            @Override
            public void run(){
                try {
                    Thread.sleep(13000);
                    Platform.runLater(() -> {
                        BPPrincipal.setCenter(new BorderPane());
                    });
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        };t.start();

    }


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

        BPPrincipal.setCenter(menuSale);
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

        BPPrincipal.setCenter(menuProvider);
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

        BPPrincipal.setCenter(menuInventory);

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

        BPPrincipal.setCenter(menuFinance);
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
