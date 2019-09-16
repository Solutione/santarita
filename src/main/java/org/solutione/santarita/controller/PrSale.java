package org.solutione.santarita.controller;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.solutione.santarita.api.Producto;
import org.solutione.santarita.api.test;

import java.io.IOException;

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
    public ImageView btnFamily;
    public ImageView btnMoney;
    public ImageView imgBtnAgregar;

    private BorderPane bpPrincipal;

    private ObservableList<Producto> productos = FXCollections.observableArrayList();
    private ObservableList<Producto> allProducts;

    private boolean family = false;
    private boolean money = true;

    static SimpleDoubleProperty dbTotal;

    @FXML
    void initialize(){
        tcCodigo.setCellValueFactory(cellData -> cellData.getValue().codigoProperty());
        tcProducto.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        tcCantidad.setCellValueFactory(cellData -> cellData.getValue().unidadesProperty());
        tcPrecio.setCellValueFactory(cellData -> cellData.getValue().precioProperty());
        tcSubtotal.setCellValueFactory(cellData -> cellData.getValue().subtotalProperty());

        dbTotal = new SimpleDoubleProperty(0);
        lblTotal.textProperty().bind(dbTotal.asString());

        tvPrSale.setItems(productos);
        txtScanner.requestFocus();

        MenuItem miCancel = new MenuItem("Cancelar Producto");
        miCancel.setOnAction((ActionEvent event) -> {
            Producto item = tvPrSale.getSelectionModel().getSelectedItem();
            if (item.getUnidades()==1){
                productos.remove(item);
                double total = 0;
                for (Producto producto : productos) total += producto.getSubtotal();
                dbTotal.set(total);
            }else{
                double units = item.getUnidades()-1;
                item.setUnidades(units);
                item.setSubtotal(item.getPrecio() * units);
                double total = 0;
                for (Producto producto : productos) total += producto.getSubtotal();
                dbTotal.set(total);
            }
        });

        ContextMenu menu = new ContextMenu();
        menu.getItems().add(miCancel);
        tvPrSale.setContextMenu(menu);
    }

    void initData(BorderPane bpPrincipal) {
        this.bpPrincipal = bpPrincipal;
        this.allProducts = Principal.PRODUCTS;
    }

    private void addProduct(String code){
        Producto product = null;
        for (Producto p : allProducts)
            if (p.getCodigo().equals(code))
                product = p;

        double units = 1;
        for (Producto value : productos) {
            if (value.getCodigo().equals(code)) {
                units += value.getUnidades();
                value.setUnidades(units);
                assert product != null;
                value.setSubtotal(product.getPrecio() * units);
            }
        }

        if (units==1) {
            assert product != null;
            productos.add(new Producto(product.getCodigo(),product.getNombre(),product.getPrecio(),units,product.getPrecio()));
        }
        double total = 0;
        for (Producto producto : productos) total += producto.getSubtotal();
        dbTotal.set(total);
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
        controller.initData(productos,stage);

        stage.show();
        stage.toFront();
    }

    public void txtScannerAdd(ActionEvent actionEvent) {
        addProduct(txtScanner.getText());
        txtScanner.setText("");
    }

    public void btnFamilyMC(MouseEvent mouseEvent) {
        if(!family){
            family=true;
            btnFamily.setImage(new Image("org/solutione/santarita/image/family-b.png"));
            for (Producto value : productos)
                for (Producto v : Principal.PRODUCTS)
                    if (v.getCodigo().equals(value.getCodigo())){
                        value.setPrecio(v.getCosto());
                        value.setSubtotal(v.getCosto()*value.getUnidades());
                        double total = 0;
                        for (Producto producto : productos) total += producto.getSubtotal();
                        dbTotal.set(total);
                    }
        }else {
            family = false;
            btnFamily.setImage(new Image("org/solutione/santarita/image/family.png"));
            for (Producto value : productos)
                for (Producto v : Principal.PRODUCTS)
                    if (v.getCodigo().equals(value.getCodigo())){
                        value.setPrecio(v.getPrecio());
                        value.setSubtotal(v.getPrecio()*value.getUnidades());
                        double total = 0;
                        for (Producto producto : productos) total += producto.getSubtotal();
                        dbTotal.set(total);
                    }
        }
    }

    public void btnMoneyMC(MouseEvent mouseEvent) {
        if(money){
            money = false;
            btnMoney.setImage(new Image("org/solutione/santarita/image/efectivo-b.png"));
            dbTotal.set(0);
        }else{
            money = true;
            btnMoney.setImage(new Image("org/solutione/santarita/image/efectivo.png"));
            double total = 0;
            for (Producto producto : productos) total += producto.getSubtotal();
            dbTotal.set(total);
        }

    }

    public void imgBtnAgregarMC(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/solutione/santarita/view/PrSaleAddProduct.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        try {
            stage.setScene(new Scene((Pane) loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        PrSaleAddProduct controller = loader.<PrSaleAddProduct>getController();
        controller.initData(productos,stage);

        stage.show();
        stage.setAlwaysOnTop(true);
        stage.toFront();
    }

    public void focusAction(MouseEvent mouseEvent) {
        txtScanner.requestFocus();
    }

    public void cleanTable(MouseEvent mouseEvent) {
        productos.clear();
        dbTotal.set(0.0);
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
