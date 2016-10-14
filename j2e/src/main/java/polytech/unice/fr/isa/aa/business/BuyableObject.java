package polytech.unice.fr.isa.aa.business;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by lucas on 15/03/16.
 */
@Embeddable
public class BuyableObject implements Serializable {

    private float price;

    public BuyableObject() {
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BuyableObject that = (BuyableObject) o;

        return Float.compare(that.getPrice(), getPrice()) == 0;

    }

    @Override
    public int hashCode() {
        return (getPrice() != +0.0f ? Float.floatToIntBits(getPrice()) : 0);
    }
}
