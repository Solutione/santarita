package org.solutione.santarita.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.solutione.santarita.api.BDProductos;
import org.solutione.santarita.api.Producto;

import java.io.IOException;

public class PrSaleFinishConfirm {
    public ImageView imgBtnAceptar;
    public ImageView imgBtnCancelar;

    private Stage stg;
    private Stage thisStage;
    private BorderPane bpPrincipal;
    private ObservableList<Producto> oblProductos;

    @FXML
    void initialize() {}

    void initData(ObservableList<Producto> oblProductos,BorderPane bpPrincipal, Stage thisStage, Stage stg) {
        this.stg = stg;
        this.thisStage = thisStage;
        this.bpPrincipal = bpPrincipal;
        this.oblProductos = oblProductos;
    }

    public void imgBtnAceptarAction(MouseEvent mouseEvent) {newSale();}

    public void imgBtnCancelarAction(MouseEvent mouseEvent) {
        thisStage.close();
    }

    public void onEnter(ActionEvent ae){
        newSale();
    }

    private void newSale(){
        for (Producto value : oblProductos) {
            ObservableList<Producto> productos = new BDProductos().getProducts();
            for (Producto v : productos)
                if (v.getCodigo().equals(value.getCodigo())){
                    int u = v.getUnidades() - value.getUnidades();
                    new BDProductos().setProduct(
                            v.getCodigo(),
                            v.getNombre(),
                            v.getCosto(),
                            v.getPrecio(),
                            u,
                            v.getMarca(),
                            v.getCaducidad());
                }
        }
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
        oblProductos.clear();
    }
}
