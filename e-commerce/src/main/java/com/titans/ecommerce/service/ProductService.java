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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;


@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductFileRepository productFileRepository;

    public List<ProductVO> getProducts() {
        return convertProductsToProductVOs(productRepository.findAll());
    }

    public List<ProductVO> searchProducts(String name,String productCategory) {
        return convertProductsToProductVOs(productRepository.findByNameContainingIgnoreCaseAndProductCategory(name, productCategory));
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
