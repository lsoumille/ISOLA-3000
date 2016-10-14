package polytech.unice.fr.isa.aa.business;

import polytech.unice.fr.isa.aa.business.enums.AgePass;
import polytech.unice.fr.isa.aa.business.enums.TypePass;
import polytech.unice.fr.isa.aa.business.enums.ZonePass;
import polytech.unice.fr.isa.aa.exceptions.UnknownPass;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Nicolas HORY
 * @version 22/03/16.
 */
@Entity
public class AbstractPass extends BuyableObject implements Serializable{

    @Enumerated(EnumType.STRING)
    @NotNull
    private TypePass type;

    @Enumerated(EnumType.STRING)
    @NotNull
    private ZonePass zone;

    @Enumerated(EnumType.STRING)
    @NotNull
    private AgePass age;

    public AbstractPass(){};

    public AbstractPass(TypePass typePass, ZonePass zonePass, AgePass agePass) {
        this.type = typePass;
        this.zone = zonePass;
        this.age = agePass;
    }

    public AbstractPass(String typePass, String zonePass, String agePass) throws UnknownPass{
        for(TypePass t : TypePass.values())
            if(t.toString().equals(typePass))
                this.type = t;
        for(ZonePass z : ZonePass.values())
            if(z.toString().equals(zonePass))
                this.zone = z;
        for(AgePass a : AgePass.values())
            if(a.toString().equals(agePass))
                this.age = a;
        if(type == null || zone == null || age == null) throw new UnknownPass();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractPass that = (AbstractPass) o;

        if (getType() != that.getType()) return false;
        if (getZone() != that.getZone()) return false;
        return getAge() == that.getAge();

    }

    @Override
    public int hashCode() {
        int result = getType() != null ? getType().hashCode() : 0;
        result = 31 * result + (getZone() != null ? getZone().hashCode() : 0);
        result = 31 * result + (getAge() != null ? getAge().hashCode() : 0);
        return result;
    }

    public TypePass getType() {
        return type;
    }

    public void setType(TypePass type) {
        this.type = type;
    }

    public ZonePass getZone() {
        return zone;
    }

    public void setZone(ZonePass zone) {
        this.zone = zone;
    }

    public AgePass getAge() {
        return age;
    }

    public void setAge(AgePass age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "{ " +  type + "," +  zone + "," + age + " }\n";
    }
}
