package org.solutione.santarita.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.solutione.santarita.api.BDVarProducts;
import org.solutione.santarita.api.Producto;

public class PrConfigAdd {

    public TextField tfCode;

    private ObservableList<Producto> productos;
    private Stage stage;

    public void initData(ObservableList<Producto> productos, Stage stage) {
        this.productos = productos;
        this.stage = stage;
    }

    public void onEnter(ActionEvent actionEvent) {
        addProduct(tfCode.getText());
    }

    public void imgBtnTerminarMC(MouseEvent mouseEvent) {
        addProduct(tfCode.getText());
    }

    private void addProduct(String code){
        boolean exists = false;
        for(Producto p : productos)
            if (p.getCodigo().equals(code))
                exists = true;

        if (!exists)
            for (Producto p : Principal.PRODUCTS)
                if (p.getCodigo().equals(code)){
                    productos.add(p);
                    new BDVarProducts().addProduct(p.getCodigo(),p.getNombre());
                    stage.close();
                }

    }

}
