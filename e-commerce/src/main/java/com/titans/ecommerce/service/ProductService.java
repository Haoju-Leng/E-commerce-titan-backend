package com.titans.ecommerce.service;

import com.titans.ecommerce.models.dto.ProductDTO;
import com.titans.ecommerce.models.dto.ProductVO;
import com.titans.ecommerce.models.entity.BaseEntity;
import com.titans.ecommerce.models.entity.Product;
import com.titans.ecommerce.models.entity.ProductFile;
import com.titans.ecommerce.models.entity.User;
import com.titans.ecommerce.repository.ProductFileRepository;
import com.titans.ecommerce.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductFileRepository productFileRepository;

    public HashMap<String, Object> getProducts(Integer page, Integer size) {
        Pageable firstPage = PageRequest.of(page, size);
        Page<Product> pagedProducts = productRepository.findAll(firstPage);
        Integer pages = pagedProducts.getTotalPages();
        List<Product> list = pagedProducts.getContent();
        HashMap<String, Object> result = new HashMap<>();
        result.put("page", pages);
        result.put("size", size);
        result.put("data", convertProductsToProductVOs(list));
        return result;
    }

    public HashMap<String, Object> searchProducts(String name,String productCategory,Integer page, Integer size) {
        Pageable firstPage = PageRequest.of(page, size);
        List<Product> products = new ArrayList<>();
        List<Product> pagedProducts = new ArrayList<>();
        if (null == productCategory || productCategory.equals("")){
            products = productRepository.findByNameContainingIgnoreCase(name);
            pagedProducts = productRepository.findByNameContainingIgnoreCase(name, firstPage);

        }else{
            products = productRepository.findByNameContainingIgnoreCaseAndProductCategory(name, productCategory);
            pagedProducts = productRepository.findByNameContainingIgnoreCaseAndProductCategory(name, productCategory, firstPage);
        }

        HashMap<String, Object> result = new HashMap<>();
        result.put("page", products.size() / size + 1);
        result.put("size", products.size());
        result.put("data", convertProductsToProductVOs(pagedProducts));
        return result;
    }
    public List<ProductVO> getProductsByUser() {
        return convertProductsToProductVOs(productRepository.findAllBySellerId(getUser().getId()));
    }
    public ProductVO getProductById(Integer id) {
        return convertProductToProductVO(productRepository.findProductById(id));
    }

    @Transactional
    public ProductVO addProduct(ProductDTO productDTO, List<MultipartFile> imageList) {
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        product.setSellerId(getUser().getId());
        product.setState(Product.State.forSale);
        product.setCreateTime(new Date());
        try {
            product
                    .setProductFileList(saveProductImages(imageList));
        }catch (Exception e){
            e.printStackTrace();
        }
        product = productRepository.save(product);
        return convertProductToProductVO(product);
    }

    @Transactional
    public ProductVO editProduct(Integer id, ProductDTO productDTO, List<MultipartFile> imageList) {
        Product product = productRepository.findProductById(id);
        BeanUtils.copyProperties(productDTO, product);
        product.setSellerId(getUser().getId());
        try {
            product
                    .setProductFileList(saveProductImages(imageList));
        }catch (Exception e){
            e.printStackTrace();
        }
        product = productRepository.save(product);
        return convertProductToProductVO(product);
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }
    // internal

    public ProductFile getProductFile(Integer productFileId) {
        return productFileRepository
                .findById(productFileId)
                .orElse(null);
    }
    private List<ProductFile> saveProductImages(List<MultipartFile> imageList) throws IOException {
        return imageList
                .stream()
                .map(imageFile -> {
                    try {
                        return productFileRepository
                                .save(new ProductFile(imageFile.getBytes()));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList();
    }

    private User getUser() {
        return (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }


    private ProductVO convertProductToProductVO(Product product) {
        ProductVO vo = new ProductVO();
        BeanUtils
                .copyProperties(product, vo);
        List<Integer> productFileIdList = product
                .getProductFileList()
                .stream()
                .map(BaseEntity::getId)
                .toList();
        vo.setProductFileIdList(productFileIdList);
        return vo;
    }
    private List<ProductVO> convertProductsToProductVOs(List<Product> products) {
        return products
                .stream()
                .map(this::convertProductToProductVO)
                .toList();
    }

}
