package org.solutione.santarita.api;

import javafx.beans.property.SimpleStringProperty;

public class Proveedor {
    private SimpleStringProperty name;
    private SimpleStringProperty brand;
    private SimpleStringProperty codes;
    private SimpleStringProperty img;


    public Proveedor(String name, String brand, String codes, String img){
        this.name = new SimpleStringProperty(name);
        this.brand = new SimpleStringProperty(brand);
        this.codes = new SimpleStringProperty(codes);
        this.img = new SimpleStringProperty(img);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getBrand() {
        return brand.get();
    }

    public SimpleStringProperty brandProperty() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand.set(brand);
    }

    public String getCodes() {
        return codes.get();
    }

    public SimpleStringProperty codesProperty() {
        return codes;
    }

    public void setCodes(String codes) {
        this.codes.set(codes);
    }

    public String getImg() {
        return img.get();
    }

    public SimpleStringProperty imgProperty() {
        return img;
    }

    public void setImg(String img) {
        this.img.set(img);
    }
}
