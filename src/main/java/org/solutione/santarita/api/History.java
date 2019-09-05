package org.solutione.santarita.api;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class History {

    private SimpleStringProperty code;
    private SimpleStringProperty name;
    private SimpleDoubleProperty cost;
    private SimpleDoubleProperty price;
    private SimpleDoubleProperty benefit;
    private SimpleStringProperty date;

    public History(String code,String name, double cost, double price, double benefit, String date){
        this.code = new SimpleStringProperty(code);
        this.name = new SimpleStringProperty(name);
        this.cost = new SimpleDoubleProperty(cost);
        this.price = new SimpleDoubleProperty(price);
        this.benefit = new SimpleDoubleProperty(benefit);
        this.date = new SimpleStringProperty(date);
    }

    public String getCode() {
        return code.get();
    }

    public SimpleStringProperty codeProperty() {
        return code;
    }

    public void setCode(String code) {
        this.code.set(code);
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

    public double getCost() {
        return cost.get();
    }

    public SimpleDoubleProperty costProperty() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost.set(cost);
    }

    public double getPrice() {
        return price.get();
    }

    public SimpleDoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public double getBenefit() {
        return benefit.get();
    }

    public SimpleDoubleProperty benefitProperty() {
        return benefit;
    }

    public void setBenefit(double benefit) {
        this.benefit.set(benefit);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }
}
