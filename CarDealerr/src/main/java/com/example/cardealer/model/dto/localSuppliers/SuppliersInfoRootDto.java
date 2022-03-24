package com.example.cardealer.model.dto.localSuppliers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SuppliersInfoRootDto {
    @XmlElement(name = "supplier")
    List<SuppliersInfoDto> suppliers;

    public List<SuppliersInfoDto> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<SuppliersInfoDto> suppliers) {
        this.suppliers = suppliers;
    }
}
