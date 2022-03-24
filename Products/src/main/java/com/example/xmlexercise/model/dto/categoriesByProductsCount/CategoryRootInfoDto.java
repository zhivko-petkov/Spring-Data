package com.example.xmlexercise.model.dto.categoriesByProductsCount;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryRootInfoDto {

    @XmlElement(name = "category")
    private List<CategoryInfoDto> category;

    public CategoryRootInfoDto() {
    }

    public List<CategoryInfoDto> getCategory() {
        return category;
    }

    public void setCategory(List<CategoryInfoDto> category) {
        this.category = category;
    }
}
