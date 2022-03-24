package com.example.cardealer.service;

import com.example.cardealer.model.dto.seed.PartSeedDto;
import com.example.cardealer.model.entity.Part;

import java.util.List;

public interface PartService {
    void seedParts(List<PartSeedDto> parts);

    long getCount();

    Part getRandomPart();
}
