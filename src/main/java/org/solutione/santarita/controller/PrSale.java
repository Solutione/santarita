package org.solutione.santarita.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import org.solutione.santarita.api.Producto;

public class PrSale {
    public BorderPane BPSale;
    public TableView<Producto> tvPrSale;
    public TableColumn<Producto, String> tcProducto;
    public TableColumn<Producto, String> tcCantidad;
    public TableColumn<Producto, String> tcPrecio;
    public TableColumn<Producto,String> tcSubtotal;

    @FXML
    private void initialize(){
        fillTable();
    }

    private void fillTable(){
        tcProducto.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());

        ObservableList<Producto> datos = FXCollections.observableArrayList();
        datos.add(new Producto("0000001","Rocko",1,1.0,.99,"Marinela","00/00/00"));
        tvPrSale.setItems(datos);
    }
}
