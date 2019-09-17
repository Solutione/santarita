package org.solutione.santarita.controller;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.solutione.santarita.api.BDProductos;
import org.solutione.santarita.api.BDUsers;

import javax.swing.*;

public class Permissions {
    private Stage stage;
    BorderPane bpSale;
    BorderPane bpProvider;
    BorderPane bpInventory;
    BorderPane bpFinance;
    ImageView imgConfig;

    public ImageView btnTerminar;

    public HBox sales;
    public HBox providers;
    public HBox products;
    public HBox finance;
    public HBox config;

    public ImageView cbSale;
    public ImageView cbProviders;
    public ImageView cbProducts;
    public ImageView cbFinance;
    public ImageView cbConfig;

    public void initData(Stage stage, BorderPane bpSale, BorderPane bpProvider, BorderPane bpInventory, BorderPane bpFinance, ImageView imgConfig) {
        this.stage = stage;
        this.bpSale = bpSale;
        this.bpProvider = bpProvider;
        this.bpInventory = bpInventory;
        this.bpFinance = bpFinance;
        this.imgConfig = imgConfig;
        if (Principal.isSalePM)
            cbSale.setImage(new Image("org/solutione/santarita/image/btnRadioSelected.png"));
        if (Principal.isProviderPM)
            cbProviders.setImage(new Image("org/solutione/santarita/image/btnRadioSelected.png"));
        if (Principal.isInventoryPM)
            cbProducts.setImage(new Image("org/solutione/santarita/image/btnRadioSelected.png"));
        if (Principal.isFinancePM)
            cbFinance.setImage(new Image("org/solutione/santarita/image/btnRadioSelected.png"));
        if (Principal.isConfigPM)
            cbConfig.setImage(new Image("org/solutione/santarita/image/btnRadioSelected.png"));

    }

    public void btnTerminarMC(MouseEvent mouseEvent) {
        if (JOptionPane.showInputDialog(null,"Contraseña").equals(new BDUsers().getPassword())){
            if (Principal.isSalePM) bpSale.setVisible(true);
            else bpSale.setVisible(false);
            if (Principal.isProviderPM) bpProvider.setVisible(true);
            else bpProvider.setVisible(false);
            if (Principal.isInventoryPM) bpInventory.setVisible(true);
            else  bpInventory.setVisible(false);
            if (Principal.isFinancePM) bpFinance.setVisible(true);
            else  bpFinance.setVisible(false);
            if (Principal.isConfigPM) imgConfig.setVisible(true);
            else  imgConfig.setVisible(false);
            stage.close();
        }
    }

    public void salesMC(MouseEvent mouseEvent) {
        if (Principal.isSalePM){
            cbSale.setImage(new Image("org/solutione/santarita/image/btnRadioNotSelected.png"));
            Principal.isSalePM = false;
        }else{
            cbSale.setImage(new Image("org/solutione/santarita/image/btnRadioSelected.png"));
            Principal.isSalePM = true;
        }
    }

    public void providersMC(MouseEvent mouseEvent) {
        if (Principal.isProviderPM){
            cbProviders.setImage(new Image("org/solutione/santarita/image/btnRadioNotSelected.png"));
            Principal.isProviderPM = false;
        }else{
            cbProviders.setImage(new Image("org/solutione/santarita/image/btnRadioSelected.png"));
            Principal.isProviderPM = true;
        }
    }

    public void productsMC(MouseEvent mouseEvent) {
        if (Principal.isInventoryPM){
            cbProducts.setImage(new Image("org/solutione/santarita/image/btnRadioNotSelected.png"));
            Principal.isInventoryPM = false;
        }else{
            cbProducts.setImage(new Image("org/solutione/santarita/image/btnRadioSelected.png"));
            Principal.isInventoryPM = true;
        }
    }

    public void financeMC(MouseEvent mouseEvent) {
        if (Principal.isFinancePM){
            cbFinance.setImage(new Image("org/solutione/santarita/image/btnRadioNotSelected.png"));
            Principal.isFinancePM = false;
        }else{
            cbFinance.setImage(new Image("org/solutione/santarita/image/btnRadioSelected.png"));
            Principal.isFinancePM = true;
        }
    }

    public void configMC(MouseEvent mouseEvent) {
        if (Principal.isConfigPM){
            cbConfig.setImage(new Image("org/solutione/santarita/image/btnRadioNotSelected.png"));
            Principal.isConfigPM = false;
        }else{
            cbConfig.setImage(new Image("org/solutione/santarita/image/btnRadioSelected.png"));
            Principal.isConfigPM = true;
        }
    }
}
