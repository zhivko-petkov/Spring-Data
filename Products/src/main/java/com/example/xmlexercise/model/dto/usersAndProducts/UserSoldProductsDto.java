package com.example.xmlexercise.model.dto.usersAndProducts;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class UserSoldProductsDto {
    @XmlAttribute(name = "count")
    private Long count;

    @XmlElement(name = "product")
    private List<UserSoldProductsFullInfoDto> products;

    public List<UserSoldProductsFullInfoDto> getProducts() {
        return products;
    }

    public void setProducts(List<UserSoldProductsFullInfoDto> products) {
        this.products = products;
    }


    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
