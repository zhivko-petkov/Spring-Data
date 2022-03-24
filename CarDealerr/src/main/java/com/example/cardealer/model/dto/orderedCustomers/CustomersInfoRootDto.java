package com.example.cardealer.model.dto.orderedCustomers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomersInfoRootDto {
    @XmlElement(name = "customer")
    private List<CustomersInfoDto> customers;

    public List<CustomersInfoDto> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomersInfoDto> customers) {
        this.customers = customers;
    }
}
