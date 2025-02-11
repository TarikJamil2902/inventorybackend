package com.tj.inventorySpringBoot.service;

import com.tj.inventorySpringBoot.dto.ProductDTO;
import com.tj.inventorySpringBoot.entity.Category;
import com.tj.inventorySpringBoot.entity.Product;
import com.tj.inventorySpringBoot.entity.Supplier;
import com.tj.inventorySpringBoot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Method to create a new product
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = convertToEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        return convertToDTO(savedProduct);
    }

    // Method to update an existing product
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setName(productDTO.getName());
            product.setDescription(productDTO.getDescription());
            product.setSize(productDTO.getSize());
            product.setColor(productDTO.getColor());
            product.setBrand(productDTO.getBrand());
            product.setUnitPrice(productDTO.getUnitPrice());  // Updated field for unit price
            product.setCostPrice(productDTO.getCostPrice());  // Updated field for cost price
            product.setQuantityInStock(productDTO.getQuantityInStock());  // Updated field for quantity in stock
            product.setReorderLevel(productDTO.getReorderLevel());  // Updated field for reorder level

            product.setBarcode(productDTO.getBarcode());  // Updated field for barcode
            product.setStatus(productDTO.getStatus());  // Updated field for status
            product.setProductId(productDTO.getProductId());  // Updated field for product ID
              // Updated field for product ID
            // Update category if provided
            if (productDTO.getCategoryId() != null) {
                Category category = new Category();
                category.setCategoryId(productDTO.getCategoryId());
                product.setCategory(category);
            }

            Product updatedProduct = productRepository.save(product);
            return convertToDTO(updatedProduct);
        }
        return null; // Return null or throw exception if product is not found
    }

    // Method to get all products
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return convertToDTOList(products);
    }

    // Method to get a product by its ID
    public ProductDTO getProductById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            return convertToDTO(productOptional.get());
        }
        return null; // Return null or handle with exception
    }

    // Method to delete a product by its ID
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    // Method to convert ProductDTO to Product entity
    private Product convertToEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setProductId(productDTO.getProductId());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setSize(productDTO.getSize());
        product.setColor(productDTO.getColor());
        product.setBrand(productDTO.getBrand());
        product.setUnitPrice(productDTO.getUnitPrice());  // Updated field for unit price
        product.setCostPrice(productDTO.getCostPrice());  // Updated field for cost price
        product.setQuantityInStock(productDTO.getQuantityInStock());  // Updated field for quantity in stock
        product.setReorderLevel(productDTO.getReorderLevel());  // Updated field for reorder level

        product.setBarcode(productDTO.getBarcode());  // Updated field for barcode
        product.setStatus(productDTO.getStatus());  // Updated field for status

        // Set category if available
        if (productDTO.getCategoryId() != null) {
            Category category = new Category();
            category.setCategoryId(productDTO.getCategoryId());
            product.setCategory(category);
        }

        if (productDTO.getSupplierId() != null) {
            Supplier supplier = new Supplier();
            supplier.setSupplierId(productDTO.getSupplierId());
            product.setSupplier(supplier);
        }
        return product;
    }

    // Method to convert Product entity to ProductDTO
    private ProductDTO convertToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(product.getProductId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setSize(product.getSize());
        productDTO.setColor(product.getColor());
        productDTO.setBrand(product.getBrand());
        productDTO.setUnitPrice(product.getUnitPrice());  // Updated field for unit price
        productDTO.setCostPrice(product.getCostPrice());  // Updated field for cost price
        productDTO.setQuantityInStock(product.getQuantityInStock());  // Updated field for quantity in stock
        productDTO.setReorderLevel(product.getReorderLevel());  // Updated field for reorder level
      // Updated field for SKU
        productDTO.setBarcode(product.getBarcode());  // Updated field for barcode
        productDTO.setStatus(product.getStatus());  // Updated field for status

        // Set categoryId from associated category
        if (product.getCategory() != null) {
            productDTO.setCategoryId(product.getCategory().getCategoryId());
        }
        if (product.getSupplier() != null) {
            productDTO.setSupplierId(product.getSupplier().getSupplierId());
        }
        return productDTO;
    }

    // Method to convert a list of Product entities to a list of ProductDTOs
    private List<ProductDTO> convertToDTOList(List<Product> products) {
        return products.stream()
                .map(this::convertToDTO)
                .toList();
    }
}
