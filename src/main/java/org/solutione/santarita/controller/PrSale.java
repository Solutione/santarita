package org.solutione.santarita.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.solutione.santarita.api.BDProductos;
import org.solutione.santarita.api.Producto;

import java.io.IOException;
import java.util.Scanner;

public class PrSale {

    public BorderPane BPSale;
    public TableView<Producto> tvPrSale;
    public TableColumn<Producto, String> tcCodigo;
    public TableColumn<Producto, String> tcProducto;
    public TableColumn<Producto, Number> tcCantidad;
    public TableColumn<Producto, Number> tcPrecio;
    public TableColumn<Producto, Number> tcSubtotal;
    public Label lblTotal;
    public Label lblFinish;
    public TextField txtScanner;

    private BorderPane bpPrincipal;

    private ObservableList<Producto> productos = FXCollections.observableArrayList();

    @FXML
    void initialize(){
        tcCodigo.setCellValueFactory(cellData -> cellData.getValue().codigoProperty());
        tcProducto.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        tcCantidad.setCellValueFactory(cellData -> cellData.getValue().unidadesProperty());
        tcPrecio.setCellValueFactory(cellData -> cellData.getValue().precioProperty());
        tcSubtotal.setCellValueFactory(cellData -> cellData.getValue().subtotalProperty());

        tvPrSale.setItems(productos);
        txtScanner.requestFocus();
    }

    void initData(BorderPane bpPrincipal) {this.bpPrincipal = bpPrincipal;}

    private void addProduct(String code){
        String[] product = new BDProductos().getProduct(code);
        double price = Double.parseDouble(product[3]);
        int units = 1;

        for (Producto value : productos) {
            if (value.getCodigo().equals(code)) {
                units += value.getUnidades();
                value.setUnidades(units);
                value.setSubtotal(price * units);
            }
        }

        if (units==1)
            productos.add(new Producto(product[0],product[1],Double.parseDouble(product[3]),units,(price*units)));

        double total = 0;
        for (Producto producto : productos) total += producto.getSubtotal();
        lblTotal.setText(Double.toString(total));
    }

    public void lblFinishMouseClick(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/solutione/santarita/view/PrSaleFinish.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        try {
            stage.setScene(new Scene((Pane) loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        PrSaleFinish controller = loader.<PrSaleFinish>getController();
        controller.initData(productos,bpPrincipal,stage,lblTotal.getText());

        stage.show();
    }

    public void txtScannerAdd(ActionEvent actionEvent) {
        addProduct(txtScanner.getText());
        txtScanner.setText("");
    }
}
