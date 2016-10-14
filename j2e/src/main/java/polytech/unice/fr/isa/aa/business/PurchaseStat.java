package polytech.unice.fr.isa.aa.business;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.ws.rs.DefaultValue;
import java.util.Date;

/**
 * Created by lucas on 29/04/16.
 */
@Entity
public class PurchaseStat extends AbstractStat {

    @NotNull
    private int counter;

    public PurchaseStat() {
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PurchaseStat that = (PurchaseStat) o;

        return getCounter() == that.getCounter();

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getCounter();
        return result;
    }
}
