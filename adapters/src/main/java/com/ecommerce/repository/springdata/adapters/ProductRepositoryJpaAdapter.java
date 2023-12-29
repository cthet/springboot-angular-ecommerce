package com.ecommerce.repository.springdata.adapters;

import com.ecommerce.exception.ProductNotFound;
import com.ecommerce.model.product.Product;
import com.ecommerce.port.adapters.repositories.ProductRepositoryPort;
import com.ecommerce.repository.springdata.entity.ProductEntity;
import com.ecommerce.repository.springdata.mappers.ProductMapper;
import com.ecommerce.repository.springdata.repository.ProductJpaRepository;
import com.ecommerce.util.message.ErrorMessages;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepositoryJpaAdapter implements ProductRepositoryPort {
    private ProductJpaRepository productJpaRepository;
    private ProductMapper productMapper;

    public ProductRepositoryJpaAdapter(ProductJpaRepository productJpaRepository, ProductMapper productMapper) {
        this.productJpaRepository = productJpaRepository;
        this.productMapper =productMapper;
    }

    @Override
    public Page<Product> findByGenderCategoryId(int gender, Pageable pageable) {
        Page<ProductEntity> pageProductEntity = productJpaRepository.findByGenderCategoryId(gender, pageable);
        return pageProductEntity.map(productEntity -> productMapper.productEntityToProduct(productEntity));
    }

    @Override
    public Page<Product> findByGenderCategoryIdAndBrandCategoryIdIn(int gender, List<Integer> brand, Pageable pageable) {
        Page<ProductEntity> pageProductEntity = productJpaRepository.findByGenderCategoryIdAndBrandCategoryIdIn(gender, brand, pageable);
        return pageProductEntity.map(productEntity -> productMapper.productEntityToProduct(productEntity));
    }

    @Override
    public Page<Product> findByGenderCategoryIdAndApparelCategoryIdIn(int gender, List<Integer> category, Pageable pageable) {
        Page<ProductEntity> pageProductEntity = productJpaRepository.findByGenderCategoryIdAndApparelCategoryIdIn(gender, category, pageable);
        return pageProductEntity.map(productEntity -> productMapper.productEntityToProduct(productEntity));
    }

    @Override
    public Page<Product> findByGenderCategoryIdAndAndBrandCategoryIdInApparelCategoryIdIn(int gender, List<Integer> brand, List<Integer> category, Pageable pageable) {
        Page<ProductEntity> pageProductEntity = productJpaRepository.findByGenderCategoryIdAndApparelCategoryIdInAndBrandCategoryIdIn(gender, category, brand, pageable);
        return pageProductEntity.map(productEntity -> productMapper.productEntityToProduct(productEntity));
    }

    @Override
    public Page<Product> findNewProductByGenderCategoryId(int genderId, Pageable pageable) {
        Page<ProductEntity> pageProductEntity = productJpaRepository.findNewProductByGenderCategoryId(genderId, pageable);
        return pageProductEntity.map(productEntity -> productMapper.productEntityToProduct(productEntity));
    }

    @Override
    public Product findById(Long productId) {
        ProductEntity productEntity = productJpaRepository.findById(productId).orElseThrow(() -> new ProductNotFound(ErrorMessages.PRODUCT_NOT_FOUND));
        return productMapper.productEntityToProduct(productEntity);
    }
}
