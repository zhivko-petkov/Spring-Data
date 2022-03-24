package com.example.cardealer.service;

import com.example.cardealer.model.entity.Sale;

import java.util.List;

public interface SaleService {
    void seedSales();
    double getRandomDiscount();
    long getCount();
}
