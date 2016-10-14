package polytech.unice.fr.isa.aa.business;

import polytech.unice.fr.isa.aa.business.enums.ZonePass;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Lucas MARTINEZ
 * @version 29/02/16
 */
@Entity
public class Gate implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    private String station;

    @NotNull
    private String name;

    @NotNull
    private ZonePass zone;

    public ZonePass getZone() {
        return zone;
    }

    public void setZone(ZonePass zone) {
        this.zone = zone;
    }

    public Gate() {}

    public Gate(String station, String name) {
        this.station = station;
        this.name = name;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Gate gate = (Gate) o;

        if (getId() != gate.getId()) return false;
        if (getStation() != null ? !getStation().equals(gate.getStation()) : gate.getStation() != null) return false;
        if (getName() != null ? !getName().equals(gate.getName()) : gate.getName() != null) return false;
        return getZone() == gate.getZone();

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getStation() != null ? getStation().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getZone() != null ? getZone().hashCode() : 0);
        return result;
    }
}
