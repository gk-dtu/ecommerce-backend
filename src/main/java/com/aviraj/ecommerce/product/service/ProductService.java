package com.aviraj.ecommerce.product.service;

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

import java.util.List;


@Service
public class ProductService {

    private final ProductRepository productRepo;
    private final UserRepository userRepo;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepo, UserRepository userRepo, ProductMapper productMapper) {
        this.productRepo = productRepo;
        this.userRepo = userRepo;
        this.productMapper = productMapper;
    }

    public ProductResponseDto createProduct(ProductRequestDto dto) {

        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Product product = productMapper.toProduct(dto, user);
        Product saveProduct = productRepo.save(product);

        return productMapper.toProductResponseDto(saveProduct);
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
                .orElseThrow(() -> new UserNotFoundException("Product not found"));
        return productMapper.toProductResponseDto(product);
    }

    public void deleteProduct(Long id){
        Product product =  productRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Product not found, can't delete"));
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