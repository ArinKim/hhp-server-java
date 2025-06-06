package kr.hhplus.be.server.infrastructure.product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpa extends JpaRepository<ProductEntity, Long> {
}