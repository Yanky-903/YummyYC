package com.Yashraj.restaurantyummy.Controller;

import com.Yashraj.restaurantyummy.dto.ProductRequest;
import com.Yashraj.restaurantyummy.entity.Product;
import com.Yashraj.restaurantyummy.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequest.ProductCreateRequest productCreateRequest) {
        return ResponseEntity.ok(productService.createProduct(productCreateRequest));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProduct());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getAllProducts(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getById(id));
    }

    @GetMapping("/price-range")
    public ResponseEntity<List<Product>> getProductsByRange(@RequestParam BigDecimal lower,
                                                            @RequestParam BigDecimal upper) {
        System.out.println("this is low" + lower + " " + upper);
        return ResponseEntity.ok(productService.getAllByPriceRance(lower, upper));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody ProductRequest.ProductUpdateRequest productUpdateRequest) {

        Product product1 = null;
        try {
            product1 = productService.updateProduct(id, productUpdateRequest);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
        }
        if (product1 != null) {
            return new ResponseEntity<>("Product Updated Successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        int action = 0;
        try {
            action = productService.deleteProduct(id);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to Delete", HttpStatus.BAD_REQUEST);
        }
        if (action != 0) {
            return new ResponseEntity<>("Product Deleted Successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to delete", HttpStatus.BAD_REQUEST);
        }
    }

}
