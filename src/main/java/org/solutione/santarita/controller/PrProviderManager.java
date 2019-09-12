package org.solutione.santarita.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import org.solutione.santarita.api.BDProductos;
import org.solutione.santarita.api.BDProveedores;
import org.solutione.santarita.api.Producto;

import java.io.IOException;

public class PrProviderManager {
    public BorderPane BPProviderManager;
    public Label lblBrand;
    public Label lblPorcent;
    public TableView<Producto> tvNoProducts;
    public Label lblTotal;
    public TableView<Producto> tvProducts;
    public ImageView btnAtras;
    public TableColumn <Producto, String> tcNpName;
    public TableColumn <Producto, Number>  tcNpUnits;
    public TableColumn <Producto, Number> tcNpCost;
    public TableColumn <Producto, Number> tcNpPrice;
    public TableColumn <Producto, Number> tcNpGanance;
    public TableColumn <Producto, String> tcNpPop;
    public TableColumn <Producto, String> tcPName;
    public TableColumn <Producto, Number> tcPUnits;
    public TableColumn <Producto, Number> tcPCost;
    public TableColumn <Producto, Number> tcPPrice;
    public TableColumn <Producto, Number> tcPGanance;
    public TableColumn <Producto, String> tcPPop;

    private BorderPane bpPrincipal;
    private String provider;

    private ObservableList<Producto> products = FXCollections.observableArrayList();
    private ObservableList<Producto> noproducts = FXCollections.observableArrayList();

    @FXML
    void initialize(){

    }

    void initData(BorderPane bpPrincipal, String provider) {
        this.bpPrincipal = bpPrincipal;
        this.provider = provider;

        lblBrand.setText(provider);

        double st = 0;

        String[] codes = new BDProveedores().getProvider(provider).getCodes().split(",");
        ObservableList<Producto> prod = new BDProductos().getProducts();
        for (String c:codes)
            for (Producto p:prod)
                if (p.getCodigo().equals(c))
                    if (p.getUnidades()>0)
                        products.add(p);
                    else{
                        noproducts.add(p);
                        st+=p.getCosto();
                    }


        tcNpName.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        tcNpUnits.setCellValueFactory(cellData -> cellData.getValue().unidadesProperty());
        tcNpCost.setCellValueFactory(cellData -> cellData.getValue().costoProperty());
        tcNpPrice.setCellValueFactory(cellData -> cellData.getValue().precioProperty());

        tcPName.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        tcPUnits.setCellValueFactory(cellData -> cellData.getValue().unidadesProperty());
        tcPCost.setCellValueFactory(cellData -> cellData.getValue().costoProperty());
        tcPPrice.setCellValueFactory(cellData -> cellData.getValue().precioProperty());

        int porcent = products.size()*(100/(noproducts.size() + products.size()));

        lblPorcent.setText(porcent+"%");
        lblTotal.setText(""+st*2);

        tvNoProducts.setItems(noproducts);
        tvProducts.setItems(products);
    }

    public void btnBackMC(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/solutione/santarita/view/PrProvider.fxml"));

        BorderPane bp = null;
        try {
            bp = (BorderPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        PrProvider controller = loader.<PrProvider>getController();
        controller.initData(bpPrincipal);
        bpPrincipal.setCenter(bp);
    }
}
