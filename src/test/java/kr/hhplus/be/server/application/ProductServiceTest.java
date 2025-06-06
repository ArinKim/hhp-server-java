package kr.hhplus.be.server.application;

import kr.hhplus.be.server.application.product.ProductService;
import kr.hhplus.be.server.domain.product.model.Product;
import kr.hhplus.be.server.domain.product.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    // 테스트 코드 작성
    @DisplayName("상품 조회 테스트")
    @Test
    void testFindProductById() {
        // given
        Long productId = 1L;
        Product mockProduct = new Product(productId, "Test Product", 1000, 10, null, null);
        org.mockito.Mockito.when(productRepository.findById(productId)).thenReturn(mockProduct);

        // when
        Product product = productService.getProductDetails(productId);

        // then
        assertNotNull(product);
        assertEquals(productId, product.getId());
        assertEquals("Test Product", product.getName());
        assertEquals(1000, product.getPrice());
        assertEquals(10, product.getQuantity());
    }
}