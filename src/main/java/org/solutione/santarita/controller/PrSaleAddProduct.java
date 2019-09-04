package org.solutione.santarita.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.solutione.santarita.api.BDProductos;
import org.solutione.santarita.api.Producto;

public class PrSaleAddProduct {
    public TextField tfCode;
    public ImageView imgBtnTerminar;

    private ObservableList<Producto> productos;
    private Label lblTotal;

    private Stage thisst;
    @FXML
    void initialize(){}

    void initData(ObservableList<Producto> productos, Label lblTotal, Stage thisst) {
        this.productos = productos;
        this.lblTotal = lblTotal;
        this.thisst = thisst;
    }

    public void onEnter(ActionEvent actionEvent) {
        addProduct(tfCode.getText());
        thisst.close();
    }

    public void imgBtnTerminarMC(MouseEvent mouseEvent) {
        addProduct(tfCode.getText());
        thisst.close();
    }
    private void addProduct(String code){
        Producto product = null;
        for (Producto p : Principal.products)
            if (p.getCodigo().equals(code))
                product = p;
        assert product != null;
        double price = product.getPrecio();
        int units = 1;

        for (Producto value : productos) {
            if (value.getCodigo().equals(code)) {
                units += value.getUnidades();
                value.setUnidades(units);
                value.setSubtotal(price * units);
            }
        }

        if (units==1)
            productos.add(new Producto(product.getCodigo(),product.getNombre(),product.getPrecio(),units,product.getPrecio()));

        double total = 0;
        for (Producto producto : productos) total += producto.getSubtotal();
        lblTotal.setText(Double.toString(total));
    }
}
