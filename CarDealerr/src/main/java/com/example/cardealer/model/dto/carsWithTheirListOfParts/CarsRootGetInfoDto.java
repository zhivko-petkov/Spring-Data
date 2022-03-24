package com.example.cardealer.model.dto.carsWithTheirListOfParts;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarsRootGetInfoDto {
    @XmlElement(name = "car")
    private List<CarGetCurrentInfoDto> cars;

    public List<CarGetCurrentInfoDto> getCars() {
        return cars;
    }

    public void setCars(List<CarGetCurrentInfoDto> cars) {
        this.cars = cars;
    }
}
