package com.example.cardealer.service.impl;

import com.example.cardealer.model.dto.localSuppliers.SuppliersInfoDto;
import com.example.cardealer.model.dto.localSuppliers.SuppliersInfoRootDto;
import com.example.cardealer.model.dto.seed.SupplierSeedDto;
import com.example.cardealer.model.dto.seed.SupplierSeedRootDto;
import com.example.cardealer.model.entity.Supplier;
import com.example.cardealer.repository.SupplierRepository;
import com.example.cardealer.service.SupplierService;
import com.example.cardealer.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public SupplierServiceImpl(SupplierRepository supplierRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public long getCount() {
        return supplierRepository.count();
    }

    @Override
    public void seedSuppliers(List<SupplierSeedDto> suppliers) {
        if(supplierRepository.count() == 0){
            for (SupplierSeedDto supplierSeedDto : suppliers) {
                if (validationUtil.isValid(supplierSeedDto)) {
                    Supplier supplier = modelMapper
                            .map(supplierSeedDto, Supplier.class);
                    supplierRepository.save(supplier);
                }
            }
        }

    }

    @Override
    public Supplier getRandomSupplier() {
        long randomId = ThreadLocalRandom.current().nextLong(
                1, supplierRepository.count() + 1);

        return supplierRepository
                .findById(randomId)
                .orElse(null);
    }

    @Override
    public SuppliersInfoRootDto getSupplierInfo() {
        SuppliersInfoRootDto rootDto = new SuppliersInfoRootDto();
        rootDto
                .setSuppliers(supplierRepository
                        .getLocalSuppliers()
                        .stream()
                        .map(supplier -> {
                            SuppliersInfoDto suppliersInfoDto = modelMapper
                                    .map(supplier, SuppliersInfoDto.class);
                            return suppliersInfoDto;
                        }).collect(Collectors.toList()));
        return rootDto;
    }
}
