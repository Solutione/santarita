package org.solutione.santarita.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.solutione.santarita.api.BDProductos;
import org.solutione.santarita.api.Producto;

public class PrSaleAddProduct {
    public TextField tfCode;
    public ImageView imgBtnTerminar;

    private ObservableList<Producto> productos;
    private Label lblTotal;

    @FXML
    void initialize(){}

    void initData(ObservableList<Producto> productos,Label lblTotal) {
        this.productos = productos;
        this.lblTotal = lblTotal;
    }

    public void onEnter(ActionEvent actionEvent) {
        addProduct(tfCode.getText());
    }

    public void imgBtnTerminarMC(MouseEvent mouseEvent) {
        addProduct(tfCode.getText());
    }
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
}
