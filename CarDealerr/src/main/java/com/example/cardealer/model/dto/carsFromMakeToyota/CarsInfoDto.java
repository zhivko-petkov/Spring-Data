package com.example.cardealer.model.dto.carsFromMakeToyota;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarsInfoDto {
    @XmlAttribute(name = "id")
    private long id;
    @XmlAttribute(name = "make")
    private String make;
    @XmlAttribute(name = "travelled-distance")
    private long travelledDistance;

    public CarsInfoDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public long getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }
}
