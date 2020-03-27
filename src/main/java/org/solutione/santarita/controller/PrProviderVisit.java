package org.solutione.santarita.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.solutione.santarita.api.BDProductos;
import org.solutione.santarita.api.BDProveedores;
import org.solutione.santarita.api.Producto;
import org.solutione.santarita.api.Proveedor;


import java.io.IOException;

public class PrProviderVisit {
   
    public ImageView btnCancelar;
    public ImageView btnGuardar;
    public Label lblBrand;
    

    private Stage thisStage;
    private BorderPane principalPane;
    private String provider;

    private boolean edit = false;

    @FXML
    private DatePicker dateVisit;
    void initialize(){
       
    }


    void initData(BorderPane principalPane, Stage thisStage, String provider) {
        this.thisStage = thisStage;
        this.principalPane =  principalPane;
        this.provider = provider;
        lblBrand.setText(provider);
        dateVisit.setStyle("-fx-background-color: #a49582; -fx-text-fill: #3f0d16; -fx-font: 18px \"Roboto Light\";");
    }

    public void btnGuardarMC(MouseEvent mouseEvent) {
        String date=null;
        try{
        date=dateVisit.getValue().toString();
        }catch(Exception e){

        }
        if(date!=null){
            new BDProveedores().addVisitProvider(provider,date);
            thisStage.close();
        }else{}
    }

    public void btnCancelarMC(MouseEvent mouseEvent) {
        thisStage.close();
    }

}
