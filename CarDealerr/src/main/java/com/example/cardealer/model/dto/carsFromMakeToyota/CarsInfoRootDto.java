package com.example.cardealer.model.dto.carsFromMakeToyota;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarsInfoRootDto {
    @XmlElement(name = "car")
    private List<CarsInfoDto> cars;

    public List<CarsInfoDto> getCars() {
        return cars;
    }

    public void setCars(List<CarsInfoDto> cars) {
        this.cars = cars;
    }
}
