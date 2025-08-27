package com.skillnest.arka.controller;

import com.skillnest.arka.dto.ProductDTO;
import com.skillnest.arka.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping()
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        List<ProductDTO> productDTOS = productService.getAllProducts();
        return new ResponseEntity<>(productDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<ProductDTO> getProduct (@PathVariable Long id){
        ProductDTO productDTO= productService.getProductById(id);
        if (productDTO== null){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<ProductDTO>> getProductsForCategory(@PathVariable Long id){
        List<ProductDTO> productDTOS = productService.getProductForCategory(id);
        return  new ResponseEntity<>(productDTOS, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDTO){
       ProductDTO newProduct = productService.createProduct(productDTO);
       return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateUserById(@PathVariable Long id, @Valid @RequestBody ProductDTO productDTO){
        ProductDTO updateProduct = productService.updateProduct(id, productDTO);
        if (updateProduct == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updateProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct (@PathVariable Long id){
        if (productService.deleteProduct(id)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/search/{term}")
    public ResponseEntity<List<ProductDTO>> searchProducts(@PathVariable("term") String searchTerm) {
        List<ProductDTO> products = productService.searchProducts(searchTerm);
        if (products.isEmpty()) {
            // Opcional: Devolver 204 No Content o simplemente 200 OK con lista vacía.
            // Devolver 200 OK con lista vacía es común y simple.
            return ResponseEntity.ok(products);
        }
        return ResponseEntity.ok(products);
    }
}
