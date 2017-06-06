package by.hotelreservation.bean.entity;

import by.hotelreservation.builder.DiscountBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "discount")
public class Discount extends AbstractEntity {
    private String name;

    public Discount(){super();}

    public Discount(DiscountBuilder discountBuilder){
        this.id = discountBuilder.getId();
        this.name = discountBuilder.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Discount discount = (Discount) o;

        return name.equals(discount.name);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        return result;
    }
}
