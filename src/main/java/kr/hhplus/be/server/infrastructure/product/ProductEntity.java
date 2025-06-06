package kr.hhplus.be.server.infrastructure.product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.hhplus.be.server.domain.product.model.Product;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    int price;
    int quantity;
    LocalDateTime createdAt;
    Date updatedAt;

    public Product toDomain() {
        return new Product(
                this.id,
                this.name,
                this.price,
                this.quantity,
                this.createdAt,
                this.updatedAt
        );
    }
}