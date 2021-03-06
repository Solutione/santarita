package org.solutione.santarita.controller;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.solutione.santarita.api.BDUsers;
import org.solutione.santarita.api.BDVarProducts;
import org.solutione.santarita.api.Producto;

import javax.swing.*;
import java.io.IOException;

public class PrConfig {

    public BorderPane BPConfig;
    public TableView<Producto> tvPrVar;
    public TableColumn<Producto, String> tcCode;
    public TableColumn<Producto, String> tcName;
    public TextField pass;
    public TextField newPass;
    public TextField repNewPass;
    public ImageView btnApagar;

    private ObservableList<Producto> productos = FXCollections.observableArrayList();

    @FXML
    void initialize(){
        tcCode.setCellValueFactory(cellData -> cellData.getValue().codigoProperty());
        tcName.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());


        productos.setAll(new BDVarProducts().getProducts());
        tvPrVar.setItems(productos);

        MenuItem miCancel = new MenuItem("Quitar Producto");
        miCancel.setOnAction((ActionEvent event) -> {
            Producto item = tvPrVar.getSelectionModel().getSelectedItem();
            new BDVarProducts().deleteProduct(item.getCodigo());
            productos.remove(item);
        });

        ContextMenu menu = new ContextMenu();
        menu.getItems().add(miCancel);
        tvPrVar.setContextMenu(menu);
    }

    public void initData(BorderPane bpPrincipal) {
    }

    public void bntNewMC(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/solutione/santarita/view/PrConfigAdd.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        try {
            stage.setScene(new Scene((Pane) loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        PrConfigAdd controller = loader.<PrConfigAdd>getController();
        controller.initData(productos,stage);

        stage.show();
        stage.toFront();
    }

    public void searchMC(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/solutione/santarita/view/Search.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        try {
            stage.setScene(new Scene((Pane) loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Search controller = loader.<Search>getController();
        controller.initData(stage);

        stage.show();
        stage.setAlwaysOnTop(true);
        stage.toFront();
    }

    public void btnSavePassMC(MouseEvent mouseEvent) {
       
    }

    private void newPassword() {
      
    }

    public void btnApagarMC(MouseEvent mouseEvent) {
        int op = JOptionPane.showConfirmDialog(null,
                "El equipo se apagara!!", "Apagar Equipo", JOptionPane.OK_CANCEL_OPTION);
        if (op==0){
            String comando= "";
            if(System.getProperty("os.name").equals("Windows"))
                comando= "shutdown -s";
            if(System.getProperty("os.name").equals("Linux"))
                comando= "shutdown now";
            try{
                Runtime.getRuntime().exec(comando);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
