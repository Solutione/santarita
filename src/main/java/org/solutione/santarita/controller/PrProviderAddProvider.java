package org.solutione.santarita.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.solutione.santarita.api.BDProductos;
import org.solutione.santarita.api.BDProveedores;
import org.solutione.santarita.api.Producto;
import org.solutione.santarita.api.Proveedor;

import java.io.IOException;

public class PrProviderAddProvider {
    public ListView<String> lvMarcas;
    public ListView<String> lvProductos;
    public ImageView btnCancelar;
    public ImageView btnGuardar;
    public ImageView imgLogo;
    public TextField tfNombre;

    private ObservableList<String> marcas = FXCollections.observableArrayList();
    private ObservableList<String> productos = FXCollections.observableArrayList();

    private Stage thisStage;
    private BorderPane principalPane;
    private String name;

    private boolean edit = false;

    @FXML
    void initialize(){
        ObservableList<Producto> bdProductos = new BDProductos().getProducts();
        for (Producto producto : bdProductos) {
            if (!marcas.isEmpty()){
                int m =  marcas.size();
                for(int i= 0; i<m;i++)
                    if (!marcas.contains(producto.getMarca()))marcas.add(producto.getMarca());
            }else {
                marcas.add(producto.getMarca());
            }
        }
        lvMarcas.setItems(marcas);
        lvMarcas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            String pr = lvMarcas.getSelectionModel().getSelectedItem();
            productos.clear();
            for (Producto producto : bdProductos)
                if (producto.getMarca().equals(pr))productos.add(producto.getNombre());

            lvProductos.setItems(productos);
            lvProductos.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        });
        lvProductos.setCellFactory(alv -> {
            ListCell<String> cell = new ListCell<>();
            cell.textProperty().bind(cell.itemProperty());
            cell.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
                lvProductos.requestFocus();
                if(!cell.isEmpty()){
                    int index = cell.getIndex();
                    if (lvProductos.getSelectionModel().getSelectedIndices().contains(index)){
                        lvProductos.getSelectionModel().clearSelection(index);
                    }else {
                        lvProductos.getSelectionModel().select(index);
                    }
                    event.consume();
                }
            });
            return cell;
        });
    }

    void initData(BorderPane principalPane,Stage thisStage) {
        this.thisStage = thisStage;
        this.principalPane =  principalPane;
    }

    void initData(BorderPane principalPane, Stage thisStage, String name) {
        this.thisStage = thisStage;
        this.principalPane =  principalPane;
        this.name = name;
        tfNombre.setText(name);
        Proveedor prov = new BDProveedores().getProvider(name);
        lvMarcas.getSelectionModel().select(prov.getBrand());
        String[] cds = prov.getCodes().split(",");
        for (String s: cds){
            lvProductos.getSelectionModel().select(new BDProductos().getProduct(s)[1]);
        }
        edit = true;
    }

    public void btnGuardarMC(MouseEvent mouseEvent) {
        ObservableList <String> prod = lvProductos.getSelectionModel().getSelectedItems();
        StringBuilder cds = new StringBuilder();
        for (String prName : prod) {
            if (cds.toString().equals("")) {
                cds = new StringBuilder(new BDProductos().getCode(prName));
            }else {
                cds.append(",").append(new BDProductos().getCode(prName));
            }
        }
        String brand = lvMarcas.getSelectionModel().getSelectedItem();

        if (!tfNombre.getText().equals(""))
            if (!edit) new BDProveedores().addProvider(tfNombre.getText(), brand, cds.toString(), "");
            else new BDProveedores().setProvider(name,tfNombre.getText(), brand, cds.toString(), "");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/solutione/santarita/view/PrProvider.fxml"));
        BorderPane bp = null;
        try {
            bp = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        principalPane.setCenter(bp);

        PrProvider controller = loader.<PrProvider>getController();
        controller.initData(principalPane);

        thisStage.close();
    }

    public void btnCancelarMC(MouseEvent mouseEvent) {
        thisStage.close();
    }

}
