package org.solutione.santarita.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.solutione.santarita.api.BDProveedores;
import org.solutione.santarita.api.Proveedor;

import java.io.IOException;
import java.util.ArrayList;

public class PrProvider {
    public BorderPane BPProvider;
    public FlowPane fwPnlProviders;

    private BorderPane bpPrincipal;

    @FXML
    void initialize(){
        ObservableList<Proveedor> bdProviders = new BDProveedores().getProviders();
        ArrayList<VBox> vBoxProviders = new ArrayList<VBox>();
        for (Proveedor prov: bdProviders) {
            VBox bx =  new VBox();
            bx.setPrefHeight(80);
            bx.setPrefWidth(160);
            bx.setStyle("-fx-background-color:#bc7837;");
            Label lbl =  new Label(prov.getName());
            lbl.setStyle("-fx-text-fill:#3f0d16;-fx-font-size:30px};");
            bx.getChildren().add(lbl);
            Label lblEdit = new Label("Editar");
            lblEdit.setStyle("-fx-text-fill:#3f0d16;-fx-font-size:30px};");
            bx.getChildren().add(lblEdit);
            bx.setAlignment(Pos.CENTER);
            fwPnlProviders.getChildren().add(bx);
            fwPnlProviders.setVgap(10);
            fwPnlProviders.setHgap(10);
            bx.setOnMouseClicked((e) ->{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/solutione/santarita/view/PrProviderManager.fxml"));

                BorderPane bp = null;
                try {
                    bp = (BorderPane) loader.load();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                PrProviderManager controller = loader.<PrProviderManager>getController();
                controller.initData(bpPrincipal,lbl.getText());
                bpPrincipal.setCenter(bp);
            });
            lblEdit.setOnMouseClicked((e)->{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/solutione/santarita/view/PrProviderAddProvider.fxml"));
                Stage stage = new Stage(StageStyle.DECORATED);
                try {
                    stage.setScene(new Scene((Pane) loader.load()));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                PrProviderAddProvider controller = loader.<PrProviderAddProvider>getController();
                controller.initData(stage,prov.getName());

                stage.show();
            });
        }
    }
    void initData(BorderPane bpPrincipal) {
        this.bpPrincipal = bpPrincipal;
    }
    public void btnAddMC(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/solutione/santarita/view/PrProviderAddProvider.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        try {
            stage.setScene(new Scene((Pane) loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        PrProviderAddProvider controller = loader.<PrProviderAddProvider>getController();
        controller.initData(stage);

        stage.show();
    }
}
