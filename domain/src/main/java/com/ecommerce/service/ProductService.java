package com.ecommerce.service;

import com.ecommerce.exception.ErrorInRequest;
import com.ecommerce.model.product.Product;
import com.ecommerce.model.product.ProductsResponse;
import com.ecommerce.port.drivers.ProductDriverPort;
import com.ecommerce.port.adapters.repositories.ProductRepositoryPort;
import com.ecommerce.util.message.ErrorMessages;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.List;

public class ProductService implements ProductDriverPort {

    private final ProductRepositoryPort productRepository;

    public ProductService(ProductRepositoryPort productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId);
    }

    @Override
    public ProductsResponse getProducts(int gender, List<Integer> category, List<Integer> brand,  int page, int size, String[] sort) {
        Pageable pagingSort = constructPageableWithSorting(page, size, sort);
        Page<Product> pageProduct = fetchProducts(gender, category, brand,  pagingSort);

        return constructProductsResponse(pageProduct);
    }

    @Override
    public ProductsResponse getNewProducts(int gender, int page, int size) {
        Pageable pagingSort = PageRequest.of(page, size);
        Page<Product> pageProduct = productRepository.findNewProductByGenderCategoryId(gender, pagingSort);

        return constructProductsResponse(pageProduct);
    }

    private Pageable constructPageableWithSorting(int page, int size, String[] sort) {
        Sort.Direction direction = getSortDirection(sort[1]);
        Sort.Order order = new Sort.Order(direction, sort[0]);
        return PageRequest.of(page, size, Sort.by(order));
    }

    public Sort.Direction getSortDirection (String direction){
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }
        return Sort.Direction.ASC;
    }

    private Page<Product> fetchProducts(int gender, List<Integer> category, List<Integer> brand, Pageable pagingSort) {
        Page<Product> pageProduct;

        if (gender != 0 && category.contains(0) && brand.contains(0)) {
            pageProduct = productRepository.findByGenderCategoryId(gender, pagingSort);
        } else if (gender != 0 && category.contains(0)) {
            pageProduct = productRepository.findByGenderCategoryIdAndBrandCategoryIdIn(gender, brand, pagingSort);
        } else if (gender != 0 && brand.contains(0)) {
            pageProduct = productRepository.findByGenderCategoryIdAndApparelCategoryIdIn(gender, category, pagingSort);
        } else if (gender != 0) {
            pageProduct = productRepository.findByGenderCategoryIdAndAndBrandCategoryIdInApparelCategoryIdIn(gender, brand, category, pagingSort);
        } else {
            throw new ErrorInRequest(ErrorMessages.ERROR_IN_REQUEST);
        }
        return pageProduct;
    }

    private ProductsResponse constructProductsResponse(Page<Product> pageProduct) {
        ProductsResponse productsResponse = new ProductsResponse();

        if (pageProduct == null || pageProduct.isEmpty()) {
            productsResponse.setProducts(Collections.emptyList());
            productsResponse.setCurrentPage(0);
            productsResponse.setSize(1);
            productsResponse.setTotalItems(0L);
            productsResponse.setTotalPages(1);
        } else {
            productsResponse.setProducts(pageProduct.getContent());
            productsResponse.setCurrentPage(pageProduct.getNumber());
            productsResponse.setSize(pageProduct.getSize());
            productsResponse.setTotalItems(pageProduct.getTotalElements());
            productsResponse.setTotalPages(pageProduct.getTotalPages());
        }
        return productsResponse;
    }

}
