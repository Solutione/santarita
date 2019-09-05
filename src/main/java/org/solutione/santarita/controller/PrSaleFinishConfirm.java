package org.solutione.santarita.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.solutione.santarita.api.BDData_F;
import org.solutione.santarita.api.BDHistory;
import org.solutione.santarita.api.BDProductos;
import org.solutione.santarita.api.Producto;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PrSaleFinishConfirm {
    public ImageView imgBtnAceptar;
    public ImageView imgBtnCancelar;

    private Stage stg;
    private Stage thisStage;
    private BorderPane bpPrincipal;
    private ObservableList<Producto> oblProductos;

    @FXML
    void initialize() {}

    void initData(ObservableList<Producto> oblProductos, Stage thisStage, Stage stg) {
        this.stg = stg;
        this.thisStage = thisStage;
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
        double total = 0;
        double benefits = 0;
        for (Producto value : oblProductos) {
            for (Producto v : Principal.products)
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
                    double benefit = v.getPrecio()-v.getCosto();
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDateTime now = LocalDateTime.now();
                    String date = dtf.format(now);
                    for (int i = 0; i < value.getUnidades(); i++){
                        new BDHistory().addHistory(v.getCodigo(),v.getNombre(),v.getCosto(),v.getPrecio(),benefit,date);
                        total += value.getPrecio();
                        benefits += benefit;
                    }
                }
        }
        new BDData_F().updateTotal(new BDData_F().getTotal()+total);
        new BDData_F().updateBenefit(new BDData_F().getBenefit()+benefits);
        thisStage.close();
        stg.close();
        oblProductos.clear();
        Principal.products.setAll(new BDProductos().getProducts());
    }
}
