package org.solutione.santarita.controller;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import org.solutione.santarita.api.BDHistory;
import org.solutione.santarita.api.History;
import org.solutione.santarita.api.Producto;
import org.solutione.santarita.api.Reporte;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class PrFinance {
    public BorderPane BPFincance;
    public Label tfNoBenefit;
    public Label tfBenefit;
    public Pane pnlLunes;
    public Pane pnlMartes;
    public Pane pnlMiercoles;
    public Pane pnlJueves;
    public Pane pnlViernes;
    public Pane pnlSabado;
    public Pane pnlDomingo;

    public TableView<Producto> tvTopProducts;
    public TableColumn<Producto, String> tcName;
    public TableColumn<Producto, Number> tcUnits;
    public TableColumn<Producto, String> tcBrand;

    private ObservableList<Producto> productos = FXCollections.observableArrayList();

    @FXML
    void initialize(){
        tfBenefit.textProperty().bind(Principal.BENEFIT.asString());
        tfNoBenefit.textProperty().bind(Principal.TOTAL.asString());
        pnlLunes.prefWidthProperty().bind(Principal.LUNES);
        pnlMartes.prefWidthProperty().bind(Principal.MARTES);
        pnlMiercoles.prefWidthProperty().bind(Principal.MIERCOLES);
        pnlJueves.prefWidthProperty().bind(Principal.JUEVES);
        pnlViernes.prefWidthProperty().bind(Principal.VIERNES);
        pnlSabado.prefWidthProperty().bind(Principal.SABADO);
        pnlDomingo.prefWidthProperty().bind(Principal.DOMINGO);

        tcName.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        tcUnits.setCellValueFactory(cellData -> cellData.getValue().unidadesProperty());
        tcBrand.setCellValueFactory(cellData -> cellData.getValue().marcaProperty());

        tvTopProducts.setItems(productos);

        Producto pr1 = null,pr2 = null,pr3 = null,pr4 = null,pr5 = null;

        ObservableList<History> tmpHistory = FXCollections.observableArrayList();

        for (History h : Principal.HISTORY) {
            if (productos.size() == 0) {
                for (Producto p : Principal.PRODUCTS)
                    if (p.getCodigo().equals(h.getCode()))
                        productos.add(new Producto(p.getCodigo(), p.getNombre(), 1, p.getMarca()));
                break;
            }
            for (Producto p : productos){
                if (p.getCodigo().equals(h.getCode())) p.setUnidades(p.getUnidades() + 1);
                else
                    for (Producto pa : Principal.PRODUCTS)
                        if (pa.getCodigo().equals(h.getCode()))
                            productos.add(new Producto(pa.getCodigo(), pa.getNombre(), 1, pa.getMarca()));
            }
        }

        updateData();
    }
    public void initData(BorderPane bpPrincipal) {
    }
    public static void updateData(){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat dfDay = new SimpleDateFormat("EEEE");
        ObservableList<Reporte> reportes = FXCollections.observableArrayList();

        for (int i = 0; i < 7; i++) {
            reportes.add(new Reporte(df.format(c.getTime()),dfDay.format(c.getTime()),0.0));
            c.add(Calendar.DATE, 1);
        }

        ObservableList<History> history =  new BDHistory().getHistory();

        double bigger = 0.0;
        for(Reporte r: reportes) {
            for (History h : history)
                if (h.getDate().equals(r.getDate()))
                    r.setMount(r.getMount() + h.getBenefit());

            if (bigger<r.getMount())
                bigger = r.getMount();
        }
        Principal.LUNES.set((int) ((reportes.get(0).getMount()*400)/bigger));
        Principal.MARTES.set((int) ((reportes.get(1).getMount()*400)/bigger));
        Principal.MIERCOLES.set((int) ((reportes.get(2).getMount()*400)/bigger));
        Principal.JUEVES.set((int) ((reportes.get(3).getMount()*400)/bigger));
        Principal.VIERNES.set((int) ((reportes.get(4).getMount()*400)/bigger));
        Principal.SABADO.set((int) ((reportes.get(5).getMount()*400)/bigger));
        Principal.DOMINGO.set((int) ((reportes.get(6).getMount()*400)/bigger));
    }
    public static void updateData(SimpleDoubleProperty day){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat dfDay = new SimpleDateFormat("EEEE");
        ObservableList<Reporte> reportes = FXCollections.observableArrayList();

        for (int i = 0; i < 7; i++) {
            reportes.add(new Reporte(df.format(c.getTime()),dfDay.format(c.getTime()),0.0));
            c.add(Calendar.DATE, 1);
        }

        ObservableList<History> history =  new BDHistory().getHistory();

        double bigger = 0.0;
        for(Reporte r: reportes) {
            for (History h : history)
                if (h.getDate().equals(r.getDate()))
                    r.setMount(r.getMount() + h.getBenefit());

            if (bigger<r.getMount())
                bigger = r.getMount();
        }

        day.set((int) ((reportes.get(0).getMount()*400)/bigger));

    }
}
