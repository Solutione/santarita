package org.solutione.santarita.controller;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class Principal {
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


    public void bpSaleMC(MouseEvent mouseEvent) {
        pnlSale.setStyle(bgPnlSel);
        pnlProvider.setStyle(bgPnlNotSel);
        pnlInventory.setStyle(bgPnlNotSel);
        pnlFinance.setStyle(bgPnlNotSel);

        imgSale.setImage(new Image("org/solutione/santarita/image/venta-b.png"));
        imgProvider.setImage(new Image("org/solutione/santarita/image/proveedor.png"));
        imgInventory.setImage(new Image("org/solutione/santarita/image/inventario.png"));
        imgFinance.setImage(new Image("org/solutione/santarita/image/administracion.png"));
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
    }

    public void imgConfigMC(MouseEvent mouseEvent) {
    }

    public void lblClockMC(MouseEvent mouseEvent) {
    }
}
