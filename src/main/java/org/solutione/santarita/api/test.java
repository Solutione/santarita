package org.solutione.santarita.api;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Comparator;

public class test {
    public static void main(String[] args) {
        ObservableList<History> history = new BDHistory().getHistory();
        ObservableList<Producto> products = new BDProductos().getProducts();

        ObservableList<Producto> productos = FXCollections.observableArrayList();
        Producto top1,top2,top3,top4,top5;

        for (History h : history) {
            if (productos.size() == 0) {
                for (Producto p : products) {
                    if (p.getCodigo().equals(h.getCode())) {
                        productos.add(new Producto(p.getCodigo(), p.getNombre(), 1, p.getMarca()));
                    }
                }
            }else{
                boolean exits = false;
                for (Producto p : productos){
                    if (p.getCodigo().equals(h.getCode())) {
                        p.setUnidades(p.getUnidades() + 1);
                        exits = true;
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
        productos.sort(Comparator.comparing(Producto::getUnidades));
        int size = productos.size();
        top1 = productos.get(size-1);
        top2 = productos.get(size-2);
        top3 = productos.get(size-3);
        top4 = productos.get(size-4);
        top5 = productos.get(size-5);
        System.out.println(top5.getNombre()+" "+top5.getUnidades());
    }

}
