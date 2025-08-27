package com.skillnest.arka.service;

import com.skillnest.arka.dto.ProductDTO;
import com.skillnest.arka.mapper.ProductMapper;
import com.skillnest.arka.model.Brand;
import com.skillnest.arka.model.Category;
import com.skillnest.arka.model.Product;
import com.skillnest.arka.repository.BrandRepository;
import com.skillnest.arka.repository.CategoryRepository;
import com.skillnest.arka.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Data
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryService categoryService;
    private  final BrandService brandService;

    public List<ProductDTO> getAllProducts(){
        List<Product> products = productRepository.findAll();
        return productMapper.productsToProductDtos(products);
    }

    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
        return productMapper.productToProductDTO(product);
    }

    public List<ProductDTO> getProductForCategory (Long id){
        List<Product> products = productRepository.findByCategoryId(id);
        return productMapper.productsToProductDtos(products);
    }

    public ProductDTO createProduct (ProductDTO productDTO){
        Product product = productMapper.productDTOToProduct(productDTO);
        //cargar entidades de categoria y marca
        if (productDTO.getCategory() != null && productDTO.getCategory().getId() != null){
            Category category = categoryService.getCategoryEntityById(productDTO.getCategory().getId());
            product.setCategory(category);
        }

        if (productDTO.getBrand() != null && productDTO.getBrand().getId() != null){
            Brand brand = brandService.getBrandEntityById(productDTO.getBrand().getId());
            product.setBrand(brand);
        }
        productRepository.save(product);
        return  productMapper.productToProductDTO(product);
    }

    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + id));

        productMapper.updateProductFromDto(productDTO, existingProduct);

        if (productDTO.getCategory() == null || productDTO.getCategory().getId() == null) {
            throw new IllegalArgumentException("The category is mandatory to update the product");
        }
        Category category = categoryService.getCategoryEntityById(productDTO.getCategory().getId());
        existingProduct.setCategory(category);

        if (productDTO.getBrand() == null || productDTO.getBrand().getId() == null) {
            throw new IllegalArgumentException("The brand is mandatory to update the product");
        }
        Brand brand = brandService.getBrandEntityById(productDTO.getBrand().getId());
        existingProduct.setBrand(brand);

        Product updatedProduct = productRepository.save(existingProduct);

        return productMapper.productToProductDTO(updatedProduct);
    }

    public  boolean deleteProduct (Long id){
        Optional<Product> existProduct= productRepository.findById(id);
        if (existProduct.isPresent()){
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<ProductDTO> searchProducts(String searchTerm) {
        // Es buena idea manejar el caso de un término de búsqueda vacío o nulo
        if (!StringUtils.hasText(searchTerm)) { // StringUtils.hasText verifica null, vacío y solo espacios
            // Podrías devolver todos los productos, una lista vacía, o lanzar una excepción.
            // Devolver una lista vacía es a menudo lo más simple para el cliente.
            return Collections.emptyList();
            // O si quieres devolver todos cuando no hay término:
            // List<Product> allProducts = productRepository.findAll();
            // return productMapper.productsToProductDTOs(allProducts);
        }

        List<Product> foundProducts = productRepository.searchByNameOrDescription(searchTerm.trim());

        return productMapper.productsToProductDtos(foundProducts);
    }
}
