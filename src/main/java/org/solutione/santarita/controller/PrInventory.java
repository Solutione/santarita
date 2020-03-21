package org.solutione.santarita.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.solutione.santarita.api.BDProductos;
import org.solutione.santarita.api.Producto;

import java.io.IOException;

public class PrInventory {
    public BorderPane BPInventory;
    public TableView<Producto> tvProducts;
    public TableColumn<Producto, String> tcName;
    public TableColumn<Producto, Number> tcUnits;
    public TableColumn<Producto, Number> tcCost;
    public TableColumn<Producto, Number> tcPrice;
    public TableColumn<Producto, String> tcBrand;
    public TextField txtScanner;
    public TextField tfName;
    public TextField tfCost;
    public TextField tfPrice;
    public TextField tfUnits;
    public TextField tfBrand;
    public TextField tfExpiration;
    public TextField tfCode;
    public BorderPane bpProduct;

    private ObservableList<Producto> productos = FXCollections.observableArrayList();

    @FXML 
    private void initialize(){
        tcName.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        tcUnits.setCellValueFactory(cellData -> cellData.getValue().unidadesProperty());
        tcCost.setCellValueFactory(cellData -> cellData.getValue().costoProperty());
        tcPrice.setCellValueFactory(cellData -> cellData.getValue().precioProperty());
        tcBrand.setCellValueFactory(cellData -> cellData.getValue().marcaProperty());

        for (Producto p : Principal.PRODUCTS)
            if (p.getUnidades()>0)
                productos.add(p);
        tvProducts.setItems(productos);

        tvProducts.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            Producto pr = tvProducts.getSelectionModel().getSelectedItem();
            bpProduct.setVisible(true);
            tfName.setText(pr.getNombre());
            tfCost.setText(Double.toString(pr.getCosto()));
            tfPrice.setText(Double.toString(pr.getPrecio()));
            tfUnits.setText(Double.toString(pr.getUnidades()));
            tfBrand.setText(pr.getMarca());
            tfExpiration.setText(pr.getCaducidad());
            tfCode.setText(pr.getCodigo());
        });
    }

    public void initData(BorderPane bpPrincipal) {
    }

    public void btnAgregarMC(MouseEvent mouseEvent) {
        txtScanner.setVisible(true);
        txtScanner.requestFocus();
    }

    public void txtScannerAddProduct(ActionEvent actionEvent) {
        addProduct(txtScanner.getText());
        txtScanner.setText("");
    }
    private void addProduct(String code){
        boolean exits = false;
        for (Producto value : Principal.PRODUCTS) {
            if (value.getCodigo().equals(code)) {
                exits = true;
                double u = value.getUnidades()+1;
                new BDProductos().updateProduct(code,u);
                value.setUnidades(value.getUnidades()+1);
                productos.clear();
                for (Producto p : Principal.PRODUCTS)
                    if (p.getUnidades()>0)
                        productos.add(p);
                tvProducts.getSelectionModel().select(value);
            }
        }
        if (!exits){
            tfName.setText("");
            tfCost.setText("");
            tfPrice.setText("");
            tfUnits.setText("");
            tfBrand.setText("");
            tfExpiration.setText("");
            tfCode.setText(code);
            bpProduct.setVisible(true);
        }
    }

    public void btnGSave(MouseEvent mouseEvent) {
        boolean exits = false;
        String  tCode   =   tfCode.getText();
        String  tName   =   tfName.getText();
        double  tCost   =   Double.parseDouble(tfCost.getText());
        double  tPrice  =   Double.parseDouble(tfPrice.getText());
        double     tUnits  =   Double.parseDouble(tfUnits.getText());
        String  tBrand  =   tfBrand.getText();
        String  tExpiration =   tfExpiration.getText();

        for (Producto value : Principal.PRODUCTS)
            if (value.getCodigo().equals(tCode)){
                exits = true;
                new BDProductos().setProduct(tCode,tName,tCost,tPrice,tUnits,tBrand,tExpiration);
                value.setAll(tCode,tName,tCost,tPrice,tUnits,tBrand,tExpiration);
                productos.clear();
                for (Producto p : Principal.PRODUCTS)
                    if (p.getUnidades()>0)
                        productos.add(p);
            }
        if (!exits){
            new BDProductos().addProduct(tCode,tName,tCost,tPrice,tUnits,tBrand,tExpiration);
            bpProduct.setVisible(false);
            tfName.setText("");
            tfCost.setText("");
            tfPrice.setText("");
            tfUnits.setText("");
            tfBrand.setText("");
            tfExpiration.setText("");
            tfCode.setText("");
            Principal.PRODUCTS.add(new Producto(tCode,tName,tCost,tPrice,tUnits,tBrand,tExpiration));
            productos.clear();
            for (Producto p : Principal.PRODUCTS)
                if (p.getUnidades()>0)
                    productos.add(p);
        }
        bpProduct.setVisible(false);
    }

    public void btnEDelete(MouseEvent mouseEvent) {
        String  tCode   =   tfCode.getText();
        String  tName   =   tfName.getText();
        double  tCost   =   Double.parseDouble(tfCost.getText());
        double  tPrice  =   Double.parseDouble(tfPrice.getText());
        double     tUnits  =   0;
        String  tBrand  =   tfBrand.getText();
        String  tExpiration =   tfExpiration.getText();
        System.out.println(tCode);

       for (Producto value : Principal.PRODUCTS)
            if (value.getCodigo().equals(tCode)){
                value.setUnidades(tUnits);
                productos.clear();
                for (Producto p : Principal.PRODUCTS)
                    if (p.getUnidades()>0)
                        productos.add(p);
            }
        bpProduct.setVisible(false);
        //new BDProductos().setProduct(tCode,tName,tCost,tPrice,tUnits,tBrand,tExpiration);*/
        new BDProductos().deleteProduct(tCode);
    }

    public void searchMC(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/solutione/santarita/view/Search.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        try {
            stage.setScene(new Scene((Pane) loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Search controller = loader.<Search>getController();
        controller.initData(stage);

        stage.show();
        stage.setAlwaysOnTop(true);
        stage.toFront();
    }
}
