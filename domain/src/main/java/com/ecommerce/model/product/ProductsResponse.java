package com.ecommerce.model.product;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductsResponse {

    private List<Product> products;

    private int currentPage;

    private int size;

    private Long totalItems;

    private int totalPages;

}
