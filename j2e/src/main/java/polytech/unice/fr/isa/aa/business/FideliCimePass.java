package polytech.unice.fr.isa.aa.business;

import polytech.unice.fr.isa.aa.business.enums.AgePass;
import polytech.unice.fr.isa.aa.business.enums.TypePass;
import polytech.unice.fr.isa.aa.business.enums.ZonePass;
import polytech.unice.fr.isa.aa.components.GateBean;
import polytech.unice.fr.isa.aa.interfaces.GateFinder;

import javax.ejb.EJB;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nicolas HORY
 * @version 30/04/16.
 */
@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public class FideliCimePass extends Pass implements Serializable {

    @NotNull
    private float toPay; // Montant a payer

    @NotNull
    private int daysConsumed;

    public FideliCimePass(){}

    public FideliCimePass(AgePass age) {
        super(new ArrayList<Gate>(), TypePass.ONE_DAY , ZonePass.ALL, age);
        this.setFidelicime(true);
    }

    /**
     *Add an amount to the current one to pay
     * @param toPay
     */
    public void addToPay(float toPay) {
        setToPay(this.toPay + toPay);
    }

    public int getDaysConsumed() {
        return this.daysConsumed;
    }

    public float getToPay() {
        return toPay;
    }

    public void setToPay(float toPay) {
        this.toPay = toPay;
    }

    public void setDaysConsumed(int daysConsumed) {
        this.daysConsumed = daysConsumed;
    }

    public void addConsumedDay() {
        setDaysConsumed(this.daysConsumed++);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        FideliCimePass that = (FideliCimePass) o;

        if (Float.compare(that.getToPay(), getToPay()) != 0) return false;
        return getDaysConsumed() == that.getDaysConsumed();

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getToPay() != +0.0f ? Float.floatToIntBits(getToPay()) : 0);
        result = 31 * result + getDaysConsumed();
        return result;
    }
}
