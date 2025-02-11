package com.tj.inventorySpringBoot.controller;

import com.tj.inventorySpringBoot.dto.ProductDTO;
import com.tj.inventorySpringBoot.entity.FileData;
import com.tj.inventorySpringBoot.entity.Product;
import com.tj.inventorySpringBoot.repository.ProductRepository;
import com.tj.inventorySpringBoot.service.FileDataService;
import com.tj.inventorySpringBoot.service.ProductService;
import io.jsonwebtoken.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private FileDataService fileDataService;

    // Endpoint to create a new product
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO createdProduct = productService.createProduct(productDTO);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

//    @PostMapping
//    public ProductDTO createProduct(
//
//            @RequestParam (required = false) String name,
//            @RequestParam (required = false) String description,
//            @RequestParam (required = false) String size,
//            @RequestParam (required = false) String color,
//            @RequestParam (required = false) String brand,
//            @RequestParam (required = false) Double unitPrice,
//            @RequestParam (required = false) Double costPrice,
//            @RequestParam (required = false) Integer quantityInStock,
//            @RequestParam (required = false) Integer reorderLevel,
//            @RequestParam (required = false) String barcode,
//            @RequestParam (required = false) String status
////            @RequestParam (required = false) String imageUrl,
////            @RequestParam (required = false) MultipartFile[] fileDatas
//
//
//
//            ) throws IOException {
//
//        try {
//            Product pro = new Product();
//            pro.setName(name);
//            pro.setDescription(description);
//            pro.setSize(size);
//            pro.setColor(color);
//            pro.setBrand(brand);
//            pro.setUnitPrice(unitPrice);
//            pro.setCostPrice(costPrice);
//            pro.setQuantityInStock(quantityInStock);
//            pro.setReorderLevel(reorderLevel);
//            pro.setBarcode(barcode);
//            pro.setStatus(status);
//
////            List<FileData> fileData = new ArrayList<>();
////            for (MultipartFile multipartFile : fileDatas) {
////                FileData fileData1 = fileDataService.uploadFile(multipartFile);
////                fileData.add(fileData1);
////            }
////            pro.setFileDatas(fileData);
//
//            pro = productRepository.save(pro);
//
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//return null;
//    }

    // Endpoint to update an existing product
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        ProductDTO updatedProduct = productService.updateProduct(id, productDTO);
        if (updatedProduct != null) {
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Product not found
    }

    // Endpoint to get all products
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> productDTOs = productService.getAllProducts();
        return new ResponseEntity<>(productDTOs, HttpStatus.OK);
    }

    // Endpoint to get a product by its ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        ProductDTO productDTO = productService.getProductById(id);
        if (productDTO != null) {
            return new ResponseEntity<>(productDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Product not found
    }

    // Endpoint to delete a product by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // No content to return
    }
}
