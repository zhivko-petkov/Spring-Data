package com.example.cardealer.model.dto.totalSalesByCustomer;

import org.springframework.data.jpa.repository.Query;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomersInfoRootDto {
    @XmlElement(name = "customers")
    List<CustomerCurrentInfoDto> customers;

    public List<CustomerCurrentInfoDto> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerCurrentInfoDto> customers) {
        this.customers = customers;
    }
}
