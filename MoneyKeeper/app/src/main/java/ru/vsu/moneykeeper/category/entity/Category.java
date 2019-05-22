package ru.vsu.moneykeeper.category.entity;

import java.util.Objects;

public class Category {
    private Long Id;
    private String name;
    private Float Constraint;

    public Category() {
    }

    public Category(Long id, String name, Float constraint) {
        Id = id;
        this.name = name;
        Constraint = constraint;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getConstraint() {
        return Constraint;
    }

    public void setConstraint(Float constraint) {
        Constraint = constraint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(Id, category.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }

    @Override
    public String toString() {
        return name;
    }
}
