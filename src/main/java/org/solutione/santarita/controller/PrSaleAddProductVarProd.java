package org.solutione.santarita.controller;

import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.solutione.santarita.api.Producto;

public class PrSaleAddProductVarProd {

    public TextField tfGramos;
    public TextField tfPrecio;
    public Label lblRes;
    public ImageView btnTerminar;
    public Label lblGramosPrecio;
    private ObservableList<Producto> productos;
    private Producto product;
    private Stage stage;
    private double units;
    private double price;

    public void initData(ObservableList<Producto> productos, Producto product, Stage stage) {
        this.productos = productos;
        this.product = product;
        this.stage = stage;
    }


    public void btnTerminarMC(MouseEvent mouseEvent) {
        boolean exists = false;
        for (Producto value : productos) {
            if (value.getCodigo().equals(product.getCodigo())) {
                value.setUnidades(value.getUnidades()+units);
                value.setSubtotal(product.getPrecio() * value.getUnidades());
                exists = true;
            }
        }

        if (!exists)
            productos.add(new Producto(product.getCodigo(),product.getNombre(),product.getPrecio(),units,price));

        double total = 0;
        for (Producto producto : productos) total += producto.getSubtotal();
        PrSale.dbTotal.set(total);
        stage.close();
    }


    public void tfGramosKT(KeyEvent keyEvent) {
        if (!tfGramos.getText().equals("")){
            tfPrecio.setText("");
            units = Double.parseDouble(tfGramos.getText())/1000;
            price = units*product.getPrecio();
            lblRes.setText(Double.toString(price));
            lblGramosPrecio.setText("Precio");
        }
    }

    public void tfPrecioKT(KeyEvent keyEvent) {
        if (!tfPrecio.getText().equals("")){
            tfGramos.setText("");
            units = (1/product.getPrecio())*Double.parseDouble(tfPrecio.getText());
            price = units*product.getPrecio();
            lblRes.setText(Double.toString(units));
            lblGramosPrecio.setText("Gramos");
        }
    }
}
