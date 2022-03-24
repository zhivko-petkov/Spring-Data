package com.example.xmlexercise.service;

import com.example.xmlexercise.model.dto.productsInRange.ProductViewRootDto;
import com.example.xmlexercise.model.dto.seed.ProductSeedDto;

import java.util.List;

public interface ProductService {
    long getCount();

    void seedProducts(List<ProductSeedDto> products);

    ProductViewRootDto findProductsInRangeWithoutBuyer();
}
