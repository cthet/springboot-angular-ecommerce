package com.ecommerce.port.drivers;

import com.ecommerce.model.product.Product;
import com.ecommerce.model.product.ProductsResponse;

import java.util.List;

public interface ProductDriverPort {

    Product getProductById(Long productId);

    ProductsResponse getProducts(int gender, List<Integer> brand, List<Integer> category, int page, int size, String[] sort);

    ProductsResponse getNewProducts(int gender, int page, int size);
}
