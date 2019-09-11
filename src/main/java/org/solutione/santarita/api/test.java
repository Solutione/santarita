package org.solutione.santarita.api;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class test {
    public static void main(String[] args) {
        ObservableList<History> history = new BDHistory().getHistory();
        ObservableList<Producto> products = new BDProductos().getProducts();
        ObservableList<Producto> productos = FXCollections.observableArrayList();


        for (History h : history) {
            if (productos.size() == 0) {
                for (Producto p : products) {
                    if (p.getCodigo().equals(h.getCode())) {
                        productos.add(new Producto(p.getCodigo(), p.getNombre(), 1, p.getMarca()));
                    }
                }
            }else{
                boolean exits = true;
                for (Producto p : productos){
                    if (p.getCodigo().equals(h.getCode())) {
                        p.setUnidades(p.getUnidades() + 1);
                    } else {
                        exits = false;
                    }
                }
                if (!exits){
                    for (Producto p : products) {
                        if (p.getCodigo().equals(h.getCode())) {
                            productos.add(new Producto(p.getCodigo(), p.getNombre(), 1, p.getMarca()));
                        }
                    }
                }
            }

        }

        for (Producto p:productos)
            System.out.println(p.getNombre()+" "+p.getUnidades());
    }

}
