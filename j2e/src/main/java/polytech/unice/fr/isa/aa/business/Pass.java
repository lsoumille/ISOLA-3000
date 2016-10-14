package polytech.unice.fr.isa.aa.business;

//import com.sun.xml.internal.ws.developer.MemberSubmissionAddressing;
import polytech.unice.fr.isa.aa.business.enums.AgePass;
import polytech.unice.fr.isa.aa.business.enums.TypePass;
import polytech.unice.fr.isa.aa.business.enums.ZonePass;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Duration;
import java.util.Date;
import java.util.List;

/**
 * @author Lucas MARTINEZ
 * @version 29/02/16
 */
@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public class Pass extends AbstractPass implements Serializable{

    @ManyToMany
    //@NotNull
    private List<Gate> gateList;

    @NotNull
    private boolean isActivated;

    @NotNull
    private boolean isFidelicime;

    @NotNull
    private double nbDays;

    public Pass() {}

    public Pass(List<Gate> gateList, TypePass typePass, ZonePass zonePass, AgePass agePass) {
        super(typePass, zonePass, agePass);
        this.gateList = gateList;
        isActivated = false;
        this.nbDays = typePass.getNbDays();
        this.isFidelicime = false;
    }

    public double getNbDays() {
        return nbDays;
    }

    public void setNbDays(double nbDays) {
        this.nbDays = nbDays;
    }

    public boolean isFidelicime() {
        return isFidelicime;
    }

    public void setFidelicime(boolean fidelicime) {
        isFidelicime = fidelicime;
    }

    public List<Gate> getGateList() {
        return gateList;
    }

    public void setGateList(List<Gate> gateList) {
        this.gateList = gateList;
    }

    public boolean getActivated() {
        return this.isActivated;
    }

    public void setActivated(boolean active) {
        this.isActivated = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Pass pass = (Pass) o;

        if (getActivated() != pass.getActivated()) return false;
        if (Double.compare(pass.getNbDays(), getNbDays()) != 0) return false;
        return getGateList() != null ? getGateList().equals(pass.getGateList()) : pass.getGateList() == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + (getGateList() != null ? getGateList().hashCode() : 0);
        result = 31 * result + (getActivated() ? 1 : 0);
        temp = Double.doubleToLongBits(getNbDays());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
