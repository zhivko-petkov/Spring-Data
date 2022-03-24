package com.example.cardealer.service.impl;

import com.example.cardealer.model.entity.Sale;
import com.example.cardealer.repository.SaleRepository;
import com.example.cardealer.service.CarService;
import com.example.cardealer.service.CustomerService;
import com.example.cardealer.service.SaleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final CustomerService customerService;
    private final CarService carService;

    public SaleServiceImpl(SaleRepository saleRepository, CustomerService customerService, CarService carService) {
        this.saleRepository = saleRepository;
        this.customerService = customerService;
        this.carService = carService;
    }

    @Override
    public void seedSales() {
        List<Sale> sales = new ArrayList<>();
        int randomCountOfSales = ThreadLocalRandom.current().nextInt(
                8, 50);

        for (int i = 0; i < randomCountOfSales; i++){
            Sale currentSale = new Sale();
            currentSale.setCar(carService.getRandomCar());
            currentSale.setCustomer(customerService.getRandomCustomer());
            currentSale.setDiscount(getRandomDiscount());
            sales.add(currentSale);
        }

        saleRepository.saveAll(sales);

    }

    @Override
    public double getRandomDiscount() {
        List<Double> discounts = new ArrayList<>();
        discounts.add(0.0);
        discounts.add(0.05);
        discounts.add(0.1);
        discounts.add(0.15);
        discounts.add(0.2);
        discounts.add(0.3);
        discounts.add(0.4);
        discounts.add(0.5);

        int randomItter = ThreadLocalRandom.current().nextInt(
                0, discounts.size()-1);

        return discounts.get(randomItter);
    }

    @Override
    public long getCount() {
        return saleRepository.count();
    }


}
