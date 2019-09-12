package org.solutione.santarita.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.solutione.santarita.api.*;

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
            for (Producto v : Principal.PRODUCTS)
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
                        String tcode = v.getCodigo();
                        String tname = v.getNombre();
                        double tcost = v.getCosto();
                        double tprice = v.getPrecio();
                        new BDHistory().addHistory(tcode,tname,tcost,tprice,benefit,date);
                        Principal.HISTORY.add(new History(tcode,tname,tcost,tprice,benefit,date));
                        total += value.getPrecio();
                        benefits += benefit;
                    }
                }
        }
        thisStage.close();
        stg.close();
        oblProductos.clear();
        PrSale.dbTotal.set(0);
        Principal.TOTAL.set(Principal.TOTAL.get()+total);
        Principal.BENEFIT.set(Principal.BENEFIT.get()+benefits);
        Principal.NOBENEFIT.set(Principal.TOTAL.get() - Principal.BENEFIT.get());
        new BDData_F().updateTotal(Principal.TOTAL.get());
        new BDData_F().updateBenefit(Principal.BENEFIT.get());
        Principal.PRODUCTS.setAll(new BDProductos().getProducts());
        PrFinance.updateData();
    }
}
