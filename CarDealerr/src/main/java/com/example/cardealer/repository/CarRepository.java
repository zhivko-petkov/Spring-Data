package com.example.cardealer.repository;

import com.example.cardealer.model.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    @Query("SELECT c FROM Car c " +
            "WHERE c.make = 'Toyota' " +
            "ORDER BY c.model, c.travelledDistance DESC")
    List<Car> getToytaMakeCars();

    @Query("SELECT c FROM Car c")
    List<Car> getAllCars();
}
