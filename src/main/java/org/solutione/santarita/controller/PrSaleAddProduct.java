package org.solutione.santarita.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.solutione.santarita.api.BDVarProducts;
import org.solutione.santarita.api.Producto;

import java.io.IOException;

public class PrSaleAddProduct {
    public TextField tfCode;
    public ImageView imgBtnTerminar;

    private ObservableList<Producto> productos;

    private Stage thisst;
    @FXML
    void initialize(){}

    void initData(ObservableList<Producto> productos, Stage thisst) {
        this.productos = productos;
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
        boolean vproduct = false;

        for (Producto p : new BDVarProducts().getProducts())
            if (p.getCodigo().equals(code))
                vproduct = true;
        for (Producto p : Principal.PRODUCTS)
            if (p.getCodigo().equals(code))
                product = p;

        if (!vproduct){
            assert product != null;
            double price = product.getPrecio();
            double units = 1;

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
            PrSale.dbTotal.set(total);

        }else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/solutione/santarita/view/PrSaleAddProductVarProd.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            try {
                stage.setScene(new Scene((Pane) loader.load()));
            } catch (IOException e) {
                e.printStackTrace();
            }

            PrSaleAddProductVarProd controller = loader.<PrSaleAddProductVarProd>getController();
            controller.initData(productos,product,stage);

            stage.show();
            stage.toFront();
            stage.alwaysOnTopProperty();
        }
    }
}
