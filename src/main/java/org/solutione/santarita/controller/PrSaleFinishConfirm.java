package org.solutione.santarita.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class PrSaleFinishConfirm {
    public ImageView imgBtnAceptar;
    public ImageView imgBtnCancelar;

    private Stage stg;
    private Stage thisStage;
    private BorderPane bpPrincipal;

    @FXML
    void initialize() {}

    void initData(BorderPane bpPrincipal,Stage thisStage, Stage stg) {
        this.stg = stg;
        this.thisStage = thisStage;
        this.bpPrincipal = bpPrincipal;
    }

    public void imgBtnAceptarAction(MouseEvent mouseEvent) {newSale();}

    public void imgBtnCancelarAction(MouseEvent mouseEvent) {
        thisStage.close();
    }

    public void onEnter(ActionEvent ae){
        newSale();
    }

    private void newSale(){
        thisStage.close();
        stg.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/solutione/santarita/view/PrSale.fxml"));
        BorderPane bp = null;
        try {
            bp = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        bpPrincipal.setCenter(bp);

        PrSale controller = loader.<PrSale>getController();
        controller.initData(bpPrincipal);
    }
}
