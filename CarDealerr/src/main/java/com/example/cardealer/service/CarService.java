package com.example.cardealer.service;

import com.example.cardealer.model.dto.carsFromMakeToyota.CarsInfoRootDto;
import com.example.cardealer.model.dto.carsWithTheirListOfParts.CarsRootGetInfoDto;
import com.example.cardealer.model.dto.seed.CarSeedDto;
import com.example.cardealer.model.entity.Car;

import java.util.List;

public interface CarService {
    void seedCars(List<CarSeedDto> cars);
    Car getRandomCar();
    long getCount();
    CarsInfoRootDto getCarInfoToyota();
    CarsRootGetInfoDto getAllCarsFullInfo();
}
