package kr.hhplus.be.server.interfaces.product;

import kr.hhplus.be.server.domain.product.model.Product;

public class ProductRequest {
    private String name;
    private int price;
    private int quantity;

    public ProductRequest() {}

    public ProductRequest(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Product toDomain() {
        return new Product(
                null, // id will be generated
                this.name,
                this.price,
                this.quantity,
                null, // createdAt will be set by the system
                null  // updatedAt will be set by the system
        );
    }

    // Getters and setters omitted for brevity
}