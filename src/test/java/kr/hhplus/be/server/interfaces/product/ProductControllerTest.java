package kr.hhplus.be.server.interfaces.product;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import kr.hhplus.be.server.application.product.ProductService;
import kr.hhplus.be.server.domain.product.model.Product;
import kr.hhplus.be.server.interfaces.product.ProductController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductControllerTest {

    private ProductService productService;
    private ProductController productController;

    @BeforeEach
    void setUp() {
        productService = mock(ProductService.class);
        productController = new ProductController(productService);
    }

    @Test
    void getProductById() {
        // Given
        Long productId = 1L;
        Product mockProduct = new Product(productId, "Test Product", 1000, 10, null, null);
        when(productService.getProductDetails(productId)).thenReturn(mockProduct);

        // When
        ProductResponse response = productController.getProductById(productId);

        // Then
        assertNotNull(response);
        assertEquals(productId, response.getId());
        assertEquals("Test Product", response.getName());
        assertEquals(1000, response.getPrice());
        assertEquals(10, response.getQuantity());
    }
}