package polytech.unice.fr.isa.aa.business;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by lucas on 30/04/16.
 */
@Entity
public class GateStat extends AbstractStat {

    @NotNull
    @OneToOne (cascade = {CascadeType.ALL})
    private Gate gate;

    @NotNull
    private int hour;

    @NotNull
    private int minutes;

    @NotNull
    private int counter;

    public GateStat() {
    }

    public Gate getGate() {
        return gate;
    }

    public void setGate(Gate gate) {
        this.gate = gate;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
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

        GateStat gateStat = (GateStat) o;

        if (getHour() != gateStat.getHour()) return false;
        if (getMinutes() != gateStat.getMinutes()) return false;
        if (getCounter() != gateStat.getCounter()) return false;
        return getGate() != null ? getGate().equals(gateStat.getGate()) : gateStat.getGate() == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getGate() != null ? getGate().hashCode() : 0);
        result = 31 * result + getHour();
        result = 31 * result + getMinutes();
        result = 31 * result + getCounter();
        return result;
    }
}
