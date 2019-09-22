package org.solutione.santarita.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.solutione.santarita.api.BDProductos;
import org.solutione.santarita.api.Producto;

public class Search {

    public BorderPane BPSearch;
    public BorderPane bpProduct;

    public TableView<Producto> tvProducts;
    public TableColumn<Producto, String> tcName;
    public TableColumn<Producto, Number> tcUnits;
    public TableColumn<Producto, Number> tcPrice;
    public TableColumn<Producto, String> tcBrand;
    
    public TextField tfName;
    public TextField tfCost;
    public TextField tfPrice;
    public TextField tfUnits;
    public TextField tfBrand;
    public TextField tfExpiration;
    public TextField tfCode;
    public TextField tfSearchBar;
    public ImageView btnAgregar;

    private Stage stage;

    private ObservableList<Producto> data = FXCollections.observableArrayList();
    private ObservableList<Producto> productos = null;

    boolean modeSale = false;

    @FXML
    private void initialize(){
        tcName.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        tcUnits.setCellValueFactory(cellData -> cellData.getValue().unidadesProperty());
        tcPrice.setCellValueFactory(cellData -> cellData.getValue().precioProperty());
        tcBrand.setCellValueFactory(cellData -> cellData.getValue().marcaProperty());
        tvProducts.setItems(data);

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

        tfSearchBar.requestFocus();
    }

    public void initData(Stage stage) {
        this.stage =  stage;
        tfSearchBar.requestFocus();
    }

    public void initData(Stage stage,ObservableList<Producto> productos) {
        this.stage =  stage;
        this.productos = productos;
        tfSearchBar.requestFocus();
        modeSale = true;

    }

    public void tfSearchBarMC(KeyEvent keyEvent) {
        data.clear();
        for (Producto p: Principal.PRODUCTS)
            if (p.getNombre().toLowerCase().contains(tfSearchBar.getText().toLowerCase()))
                data.add(p);

    }

    public void btnAgregarMC(MouseEvent mouseEvent) {
        if (productos!=null) {
            Producto p = new BDProductos().getProduct(tfCode.getText());
            p.setSubtotal(p.getPrecio());
            productos.add(p);
            PrSale.dbTotal.set(PrSale.dbTotal.get()+p.getSubtotal());
            stage.close();
        }

    }

    public void addProd(KeyEvent keyEvent) {
        if (productos!=null && tfCode.getText()!=null){
            Producto p = new BDProductos().getProduct(tfCode.getText());
            p.setSubtotal(p.getPrecio());
            p.setUnidades(1);
            productos.add(p);
            PrSale.dbTotal.set(PrSale.dbTotal.get()+p.getSubtotal());
            stage.close();
        }
    }
}
