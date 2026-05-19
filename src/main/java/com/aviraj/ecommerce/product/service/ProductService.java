package com.aviraj.ecommerce.product.service;

import com.aviraj.ecommerce.common.exception.ProductNotFoundException;
import com.aviraj.ecommerce.common.exception.UserNotFoundException;
import com.aviraj.ecommerce.product.dto.ProductRequestDto;
import com.aviraj.ecommerce.product.dto.ProductResponseDto;
import com.aviraj.ecommerce.product.entity.Product;
import com.aviraj.ecommerce.product.repository.ProductRepository;
import com.aviraj.ecommerce.user.entity.User;
import com.aviraj.ecommerce.user.repository.UserRepository;
import com.aviraj.ecommerce.product.mapper.ProductMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


@Service
public class ProductService {

    private final ProductRepository productRepo;
    private final UserRepository userRepo;
    private final ProductMapper productMapper;
    private final Logger logger = LoggerFactory.getLogger(ProductService.class);


    public ProductService(ProductRepository productRepo, UserRepository userRepo, ProductMapper productMapper) {
        this.productRepo = productRepo;
        this.userRepo = userRepo;
        this.productMapper = productMapper;
    }

    public ProductResponseDto createProduct(ProductRequestDto dto) {

        logger.info("Creating product for userId: {}", dto.getUserId());
        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> {
                    logger.error("User not found with id: {}", dto.getUserId());
                    return new UserNotFoundException("User not found");
                });

        Product product = productMapper.toProduct(dto, user);
        Product savedProduct = productRepo.save(product);

        logger.info("Product created with id: {}", savedProduct.getId());
        return productMapper.toProductResponseDto(savedProduct);
    }

    public List<ProductResponseDto> getAllProducts(){
        return productMapper.toProductResponseDto(productRepo.findAll());
    }

    public Page<ProductResponseDto> getAllProducts(int page, int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("price").descending());

        Page<Product> productPage = productRepo.findAll(pageable);

        return productPage.map(productMapper::toProductResponseDto);
    }

    public ProductResponseDto getById(Long id){
        Product product =  productRepo.findById(id)
                .orElseThrow(() -> {
                    logger.info("Product not found with id: {}", id);
                    return new ProductNotFoundException("Product not found with id: " + id);
                });
        logger.info("Product found with id: {}", id);
        return productMapper.toProductResponseDto(product);
    }

    public void deleteProduct(Long id){
        Product product =  productRepo.findById(id)
                .orElseThrow(() -> {
                    logger.info("Product not found with id: {} can't delete", id);
                    return new ProductNotFoundException("Product not found, can't delete");
                });
        logger.info("Product deleted successfully with id: {}", id);
        productRepo.delete(product);
    }

    public List<ProductResponseDto> searchProducts(String keyword) {

        return productRepo
                .findByNameContainingIgnoreCase(keyword)
                .stream()
                .map(productMapper::toProductResponseDto)
                .toList();
    }
}