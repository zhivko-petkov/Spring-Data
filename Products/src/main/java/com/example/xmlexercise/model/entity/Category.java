package com.example.xmlexercise.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Objects;
import java.util.Set;

@Entity(name = "categories")
public class Category extends BaseEntity{
    private String name;
    private Set<Product> productsInCategory;

    public Category() {

    }

    @Column(length = 15, nullable = false, unique = true)
    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
