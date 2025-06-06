package kr.hhplus.be.server.domain.product.repository;

import kr.hhplus.be.server.domain.product.model.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository {
    // 예시 메서드
    Product findById(Long id);

    Product save(Product product);

}