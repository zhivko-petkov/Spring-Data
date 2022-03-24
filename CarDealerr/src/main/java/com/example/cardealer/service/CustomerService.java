package com.example.cardealer.service;

import com.example.cardealer.model.dto.orderedCustomers.CustomersInfoDto;
import com.example.cardealer.model.dto.orderedCustomers.CustomersInfoRootDto;
import com.example.cardealer.model.dto.seed.CustomerSeedDto;
import com.example.cardealer.model.entity.Customer;

import java.util.List;

public interface CustomerService {
    void seedCustomers(List<CustomerSeedDto> customers);
    Customer getRandomCustomer();
    long getCount();
    CustomersInfoRootDto getCustomers();
}
