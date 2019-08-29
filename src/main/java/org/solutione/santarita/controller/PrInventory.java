package org.solutione.santarita.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import org.solutione.santarita.api.BDProductos;
import org.solutione.santarita.api.Producto;

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
        fillTable();
    }

    private void fillTable(){
        tcName.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        tcUnits.setCellValueFactory(cellData -> cellData.getValue().unidadesProperty());
        tcCost.setCellValueFactory(cellData -> cellData.getValue().costoProperty());
        tcPrice.setCellValueFactory(cellData -> cellData.getValue().precioProperty());
        tcBrand.setCellValueFactory(cellData -> cellData.getValue().marcaProperty());

        productos = new BDProductos().getProducts();
        tvProducts.setItems(productos);

        tvProducts.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            Producto pr = tvProducts.getSelectionModel().getSelectedItem();
            bpProduct.setVisible(true);
            tfName.setText(pr.getNombre());
            tfCost.setText(Double.toString(pr.getCosto()));
            tfPrice.setText(Double.toString(pr.getPrecio()));
            tfUnits.setText(Integer.toString(pr.getUnidades()));
            tfBrand.setText(pr.getMarca());
            tfExpiration.setText(pr.getCaducidad());
            tfCode.setText(pr.getCodigo());
        });
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
        for (Producto value : productos) {
            if (value.getCodigo().equals(code)) {
                exits = true;
                int u = value.getUnidades()+1;
                new BDProductos().updateProduct(code,u);
                value.setUnidades(value.getUnidades()+1);
            }
        }
        if (!exits){
            bpProduct.setVisible(true);
            tfCode.setText(code);
        }
    }

    public void btnGSave(MouseEvent mouseEvent) {
        boolean exits = false;
        for (Producto value : productos) if (value.getCodigo().equals(tfCode.getText()))exits = true;
        if (!exits){
            new BDProductos().addProduct(tfCode.getText(),tfName.getText(),
                    Double.parseDouble(tfCost.getText()),Double.parseDouble(tfPrice.getText()),
                    Integer.parseInt(tfUnits.getText()),tfBrand.getText(),tfExpiration.getText());
            bpProduct.setVisible(false);
            tfName.setText("");
            tfCost.setText("");
            tfPrice.setText("");
            tfUnits.setText("");
            tfBrand.setText("");
            tfExpiration.setText("");
            tfCode.setText("");
            productos = new BDProductos().getProducts();
            tvProducts.setItems(productos);
        }else{
            new BDProductos().setProduct(
                    tfCode.getText(),
                    tfName.getText(),
                    Double.parseDouble(tfCost.getText()),
                    Double.parseDouble(tfPrice.getText()),
                    Integer.parseInt(tfUnits.getText()),
                    tfBrand.getText(),
                    tfExpiration.getText());
            productos = new BDProductos().getProducts();
            tvProducts.setItems(productos);
        }
        bpProduct.setVisible(false);
    }
}