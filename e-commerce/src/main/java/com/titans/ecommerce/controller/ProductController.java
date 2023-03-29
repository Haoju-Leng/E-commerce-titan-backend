package com.titans.ecommerce.controller;

import com.titans.ecommerce.models.dto.ProductDTO;
import com.titans.ecommerce.models.dto.ProductVO;
import com.titans.ecommerce.models.entity.Product;
import com.titans.ecommerce.models.entity.ProductFile;
import com.titans.ecommerce.models.entity.User;
import com.titans.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    @GetMapping( "/actuator/info")
    ResponseEntity<String> info() {
        // Indicate the request succeeded and return the application
        // name.
        StringBuilder resp = new StringBuilder();
        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        resp
                .append("Hello User! Your info:")
                .append("\n")
                .append(user.toString())
                .append("\n")
                .append("Application is alive and running on ")
                .append(Thread.currentThread())
                .append("\n");

        return ResponseEntity
                .ok(resp.toString());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductVO> getProductById (@PathVariable("id") Integer id) {
        ProductVO product = productService.getProductById(id);
        if (null != product) {
            logger.info("Product found: " + product);
            return ResponseEntity.ok(product);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductVO>> getAllProducts () {
        List<ProductVO> productList = productService.getProducts();
        if (null != productList) {
            logger.info("Product found: " + productList);
            return ResponseEntity.ok(productList);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/userAll")
    public ResponseEntity<List<ProductVO>> getUserAllProducts () {
        List<ProductVO> productList = productService.getProductsByUser();
        if (null != productList) {
            logger.info("Product found: " + productList);
            return ResponseEntity.ok(productList);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping(value = "/", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<HashMap<String, Integer>> addProduct (@RequestPart("productInfo") ProductDTO productDTO,
                                                 @RequestPart(name = "image0", required = false) MultipartFile image0,
                                                 @RequestPart(name = "image1", required = false) MultipartFile image1,
                                                 @RequestPart(name = "image2", required = false) MultipartFile image2,
                                                 @RequestPart(name = "image3", required = false) MultipartFile image3,
                                                 @RequestPart(name = "image4", required = false) MultipartFile image4
                                               ) {
        MultipartFile[] images = {image0, image1, image2, image3, image4};

        List<MultipartFile> imageList = Arrays.stream(images)
                .filter(Objects::nonNull)
                .toList();
        ProductVO newlyCreatedProduct = productService
                .addProduct(productDTO, imageList);
        HashMap<String, Integer> returnMap = new HashMap<>();
        returnMap.put("id", newlyCreatedProduct.getId());
        return ResponseEntity
                .ok(returnMap);
    }

    @PutMapping(value = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<HashMap<String, Integer>> editProduct (@RequestParam("id") Integer id,
                                                                 @RequestPart("productInfo") ProductDTO productDTO,
                                                                @RequestPart(name = "image0", required = false) MultipartFile image0,
                                                                @RequestPart(name = "image1", required = false) MultipartFile image1,
                                                                @RequestPart(name = "image2", required = false) MultipartFile image2,
                                                                @RequestPart(name = "image3", required = false) MultipartFile image3,
                                                                @RequestPart(name = "image4", required = false) MultipartFile image4
    ) {
        MultipartFile[] images = {image0, image1, image2, image3, image4};

        List<MultipartFile> imageList = Arrays.stream(images)
                .filter(Objects::nonNull)
                .toList();
        ProductVO newlyCreatedProduct = productService
                .editProduct(id, productDTO, imageList);
        HashMap<String, Integer> returnMap = new HashMap<>();
        returnMap.put("id", newlyCreatedProduct.getId());
        return ResponseEntity
                .ok(returnMap);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ProductVO> deleteProduct(@RequestParam("id") Integer id) {
        productService
                .deleteProduct(id);
        return ResponseEntity.ok(null);
    }

    @RequestMapping(value = "/file/{id}", method = RequestMethod.GET)
    public ResponseEntity<Resource> getProductImageById(@PathVariable("id") Integer id) {
        ProductFile file = productService.getProductFile(id);

        if (null == file) {
            return ResponseEntity
                    .notFound()
                    .build();
        } else {
            ByteArrayResource resource = new ByteArrayResource(file.getData());
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .contentLength(resource.contentLength())
                    .body(resource);
        }
    }
}
