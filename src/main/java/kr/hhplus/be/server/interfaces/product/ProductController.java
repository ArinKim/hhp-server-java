package kr.hhplus.be.server.interfaces.product;

import kr.hhplus.be.server.application.product.ProductService;
import kr.hhplus.be.server.domain.product.model.Product;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable Long id) {
        Product product = productService.getProductDetails(id);
        return ProductResponse.from(product);
    }

}