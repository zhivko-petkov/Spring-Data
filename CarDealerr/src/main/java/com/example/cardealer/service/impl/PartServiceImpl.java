package com.example.cardealer.service.impl;

import com.example.cardealer.model.dto.seed.PartSeedDto;
import com.example.cardealer.model.entity.Part;
import com.example.cardealer.repository.PartRepository;
import com.example.cardealer.service.PartService;
import com.example.cardealer.service.SupplierService;
import com.example.cardealer.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class PartServiceImpl implements PartService {
    private final PartRepository partRepository;
    private final SupplierService supplierService;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;


    public PartServiceImpl(PartRepository partRepository, SupplierService supplierService, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.partRepository = partRepository;
        this.supplierService = supplierService;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedParts(List<PartSeedDto> parts) {
        parts.stream()
                .filter(validationUtil::isValid)
                .map(partSeedDto -> {
                    Part part = modelMapper.map(partSeedDto, Part.class);
                    part.setSupplier(supplierService.getRandomSupplier());
                    return  part;
                }).forEach(partRepository::save);
    }

    @Override
    public long getCount() {
        return partRepository.count();
    }

    @Override
    public Part getRandomPart() {
        long randomId = ThreadLocalRandom.current().nextLong(
                1, partRepository.count() + 1);

        //long quantity = partRepository.findById(randomId).get().getQuantity();

        return partRepository
                .findById(randomId)
                .orElse(null);
    }
}
