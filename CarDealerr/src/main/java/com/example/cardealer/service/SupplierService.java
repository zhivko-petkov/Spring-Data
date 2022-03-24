package com.example.cardealer.service;

import com.example.cardealer.model.dto.localSuppliers.SuppliersInfoRootDto;
import com.example.cardealer.model.dto.seed.SupplierSeedDto;
import com.example.cardealer.model.dto.seed.SupplierSeedRootDto;
import com.example.cardealer.model.entity.Supplier;

import java.util.List;

public interface SupplierService {
    long getCount();
    void seedSuppliers(List<SupplierSeedDto> suppliers);
    Supplier getRandomSupplier();
    SuppliersInfoRootDto getSupplierInfo();

}
