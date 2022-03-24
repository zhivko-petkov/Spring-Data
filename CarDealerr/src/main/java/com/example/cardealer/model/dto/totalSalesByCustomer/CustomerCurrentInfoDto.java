package com.example.cardealer.model.dto.totalSalesByCustomer;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerCurrentInfoDto {
    @XmlAttribute(name = "full-name")
    private String fullName;

    @XmlAttribute(name = "")
}
