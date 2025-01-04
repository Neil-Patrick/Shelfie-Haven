package org.example;

public class CatalogItem {
    private int id;
    private String title;
    private int quantity;

    public CatalogItem(int id, String title, int quantity) {
        this.id = id;
        this.title = title;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getQuantity() {
        return quantity;
    }
}
