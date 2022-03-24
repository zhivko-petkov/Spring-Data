package com.example.cardealer.service.impl;

import com.example.cardealer.model.dto.carsFromMakeToyota.CarsInfoDto;
import com.example.cardealer.model.dto.carsFromMakeToyota.CarsInfoRootDto;
import com.example.cardealer.model.dto.carsWithTheirListOfParts.CarGetCurrentInfoDto;
import com.example.cardealer.model.dto.carsWithTheirListOfParts.CarsRootGetInfoDto;
import com.example.cardealer.model.dto.seed.CarSeedDto;
import com.example.cardealer.model.entity.Car;
import com.example.cardealer.model.entity.Part;
import com.example.cardealer.repository.CarRepository;
import com.example.cardealer.service.CarService;
import com.example.cardealer.service.PartService;
import com.example.cardealer.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final PartService partService;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public CarServiceImpl(CarRepository carRepository, PartService partService, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.carRepository = carRepository;
        this.partService = partService;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    @Transactional
    public void seedCars(List<CarSeedDto> cars) {
        cars.stream()
                .filter(validationUtil::isValid)
                .map(carSeedDto -> {
                    Car car = modelMapper.map(carSeedDto, Car.class);
                    int randomCountOfParts = ThreadLocalRandom.current().nextInt(
                            10, 20);
                    List<Part> randomParts = new ArrayList<>();
                    for (int i = 0; i < randomCountOfParts; i++){
                        randomParts.add(partService.getRandomPart());
                        List<Car> currentListCarInPart = randomParts.get(i).getCars();
                        currentListCarInPart.add(car);
                        randomParts.get(i).setCars(currentListCarInPart);
                    }

                    car.setParts(randomParts);

                    return car;
                }).forEach(carRepository::save);
    }

    @Override
    public Car getRandomCar() {
        long randomId = ThreadLocalRandom.current().nextLong(
                1, carRepository.count() + 1);

        return carRepository
                .findById(randomId)
                .orElse(null);

    }

    @Override
    public long getCount() {
        return carRepository.count();
    }

    @Override
    public CarsInfoRootDto getCarInfoToyota() {
        CarsInfoRootDto rootDto = new CarsInfoRootDto();
        rootDto
                .setCars(carRepository
                        .getToytaMakeCars()
                        .stream()
                        .map(car -> {
                            CarsInfoDto carsInfoDto = modelMapper
                                    .map(car, CarsInfoDto.class);
                            return carsInfoDto;
                        }).collect(Collectors.toList()));
        return rootDto;
    }

    @Override
    @Transactional
    public CarsRootGetInfoDto getAllCarsFullInfo() {
        CarsRootGetInfoDto rootDto = new CarsRootGetInfoDto();
        rootDto
                .setCars(carRepository
                        .getAllCars()
                        .stream()
                        .map(car -> {
                            CarGetCurrentInfoDto carGetCurrentInfoDto = modelMapper
                                    .map(car, CarGetCurrentInfoDto.class);
                            return carGetCurrentInfoDto;
                        }).collect(Collectors.toList()));

        return rootDto;
    }
}
