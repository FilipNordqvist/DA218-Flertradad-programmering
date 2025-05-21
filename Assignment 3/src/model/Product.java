package model;

public class Product {

    String name;
    double price;
    int pieces;
    CategoryType category;

    public Product(String name, double price, int pieces, CategoryType category) {
        this.name=name;
        this.price=price;
        this.pieces=pieces;
        this.category=category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", pieces=" + pieces +
                ", category=" + category +
                '}';
    }
}
