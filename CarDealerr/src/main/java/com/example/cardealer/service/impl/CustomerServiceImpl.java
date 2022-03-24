package com.example.cardealer.service.impl;

import com.example.cardealer.model.dto.orderedCustomers.CustomersInfoDto;
import com.example.cardealer.model.dto.orderedCustomers.CustomersInfoRootDto;
import com.example.cardealer.model.dto.seed.CustomerSeedDto;
import com.example.cardealer.model.dto.seed.SupplierSeedDto;
import com.example.cardealer.model.entity.Customer;
import com.example.cardealer.repository.CustomerRepository;
import com.example.cardealer.service.CustomerService;
import com.example.cardealer.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void seedCustomers(List<CustomerSeedDto> customers) {
        if(customerRepository.count() == 0){
            for (CustomerSeedDto customerSeedDto : customers){
                if(validationUtil.isValid(customerSeedDto)){
                    Customer customer = modelMapper
                            .map(customerSeedDto, Customer.class);
                    //1990-10-04T00:00:00
                    LocalDateTime bDate = LocalDateTime.parse(customerSeedDto.getDateOfBirth());

                    customer.setDateOfBirth(bDate.toLocalDate());

                    customerRepository.save(customer);
                }
            }
        }
    }

    @Override
    public Customer getRandomCustomer() {
        long randomId = ThreadLocalRandom.current().nextLong(
                1, customerRepository.count() + 1);

        return customerRepository
                .findById(randomId)
                .orElse(null);

    }

    @Override
    public long getCount() {
        return customerRepository.count();
    }

    @Override
    public CustomersInfoRootDto getCustomers() {
        CustomersInfoRootDto rootDto = new CustomersInfoRootDto();
        rootDto
                .setCustomers(customerRepository
                        .getAllCustomersBdateAsc()
                        .stream()
                        .map(customer -> {
                            CustomersInfoDto customersInfoDto = modelMapper
                                    .map(customer, CustomersInfoDto.class);
                            customersInfoDto.setDateOfBirth(customer.getDateOfBirth().toString());
                            return customersInfoDto;
                        }).collect(Collectors.toList()));

        return rootDto;
    }
}
