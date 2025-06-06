package kr.hhplus.be.server.infrastructure.product;

import kr.hhplus.be.server.domain.product.model.Product;
import kr.hhplus.be.server.domain.product.repository.ProductRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ProductJpaRepository implements ProductRepository {
    private final ProductJpa jpa;

    public ProductJpaRepository(ProductJpa jpa) {
        this.jpa = jpa;
    }

    @Override
    public Product findById(Long id) {
        return jpa.findById(id)
                .map(entity -> entity.toDomain())
                .orElse(null); // Return null if not found
    }

    @Override
    public Product save(Product product) {
        ProductEntity entity = toEntity(product);
        ProductEntity saved = jpa.save(entity);
        product.assignId(saved.id); // Assign the generated ID back to the domain object
        return product;
    }

    private ProductEntity toEntity(Product product) {
        if (product == null) return null;

        ProductEntity e = new ProductEntity();
        e.name = product.getName();
        e.price = product.getPrice();
        e.quantity = product.getQuantity();
        e.createdAt = product.getCreatedAt();
        e.updatedAt = product.getUpdatedAt();
        return e;
    }
}