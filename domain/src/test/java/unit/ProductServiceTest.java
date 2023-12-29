package unit;


import com.ecommerce.exception.ErrorInRequest;
import com.ecommerce.exception.ProductNotFound;
import com.ecommerce.model.product.Product;
import com.ecommerce.model.product.ProductsResponse;
import com.ecommerce.port.adapters.repositories.ProductRepositoryPort;
import com.ecommerce.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepositoryPort productRepository;


    @Test
    @DisplayName("getProductById returns a product when found")
    void givenProductId_whenProductExists_thenProductIsReturned() {
        Long productId = 1L;
        Product mockProduct = new Product(1L, "product_name", new BigDecimal("1000"), "productImageUrl", true, 100);
        when(productRepository.findById(productId)).thenReturn(mockProduct);

        ProductService productService = new ProductService(productRepository);
        Product result = productService.getProductById(productId);

        assertNotNull(result);
        assertEquals(mockProduct, result);
    }

    @Test
    @DisplayName("getProductById throws ProductNotFound when product is not found")
    void givenProductId_whenProductDoesNotExist_thenThrowProductNotFound() {
        Long productId = 99L;
        when(productRepository.findById(productId)).thenReturn(null);

        ProductService productService = new ProductService(productRepository);

        assertThrows(ProductNotFound.class, () -> productService.getProductById(productId));
    }

    @Test
    @DisplayName("getProducts returns products based on given criteria")
    void givenCriteria_whenProductsExist_thenProductsAreReturned() {
        int gender = 1;
        List<Integer> brands = List.of(1, 2);
        List<Integer> categories = List.of(3, 4);
        int page = 0;
        int size = 1;
        int totalItems = 1;
        int totalPages = 1;
        String[] sort = {"id", "asc"};

        Product mockProduct = new Product(1L, "product_name", new BigDecimal("1000"), "productImageUrl", true, 100);
        List<Product> products = new ArrayList<>();
        products.add(mockProduct);
        Page<Product> pageProducts = new PageImpl<>(products);

        given(productRepository.findByGenderCategoryIdAndAndBrandCategoryIdInApparelCategoryIdIn(
                gender, categories, brands, PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id"))))
                .willReturn(pageProducts);

        ProductsResponse productsResponse = productService.getProducts(gender, brands, categories, page, size, sort);

        assertEquals(products, productsResponse.getProducts());
        assertEquals(page, productsResponse.getCurrentPage());
        assertEquals(size, productsResponse.getSize());
        assertEquals(totalItems, productsResponse.getTotalItems());
        assertEquals(totalPages, productsResponse.getTotalPages());
    }

    @Test
    @DisplayName("getProducts throws ErrorInRequest when request is invalid")
    void givenInvalidCriteria_whenFetchingProducts_thenThrowErrorInRequest() {
        int gender = 0;
        List<Integer> brands = List.of();
        List<Integer> categories = List.of();
        int page = 0;
        int size = 5;
        String[] sort = {"name", "asc"};

        ProductService productService = new ProductService(productRepository);

        assertThrows(ErrorInRequest.class, () -> productService.getProducts(gender, brands, categories, page, size, sort));
    }

    @Test
    void whenGetNewProductsIsCalled_thenReturnNonEmptyProductsResponse() {
        int gender = 1;
        int page = 0;
        int size = 5;
        Pageable pageable = PageRequest.of(page, size);
        List<Product> productList = List.of(new Product(1L, "product_name", new BigDecimal("1000"), "productImageUrl", true, 100));
        Page<Product> productPage = new PageImpl<>(productList, pageable, productList.size());

        when(productRepository.findNewProductByGenderCategoryId(gender, pageable)).thenReturn(productPage);

        ProductsResponse response = productService.getNewProducts(gender, page, size);

        assertNotNull(response);
        assertFalse(response.getProducts().isEmpty());
        assertEquals(productList.size(), response.getTotalItems());
        assertEquals(1, response.getTotalPages());
    }

    @Test
    void whenGetNewProductsIsCalledWithNoProducts_thenReturnEmptyProductsResponse() {
        int gender = 1;
        int page = 0;
        int size = 5;
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = new PageImpl<>(Collections.emptyList(), pageable, 0);

        when(productRepository.findNewProductByGenderCategoryId(gender, pageable)).thenReturn(productPage);

        ProductsResponse response = productService.getNewProducts(gender, page, size);

        assertNotNull(response);
        assertTrue(response.getProducts().isEmpty());
        assertEquals(0, response.getTotalItems());
        assertEquals(1, response.getTotalPages());
    }
}
