package kr.hhplus.be.server.interfaces.product;

import kr.hhplus.be.server.domain.product.model.Product;
import lombok.Getter;

@Getter
public class ProductResponse {
    private Long id;
    private String name;
    private int price;
    private int quantity;

    public ProductResponse(Long id, String name, int price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public static ProductResponse from(Product product) {
        if (product == null) return null;
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getQuantity()
        );
    }

    // Getters and setters omitted for brevity
}