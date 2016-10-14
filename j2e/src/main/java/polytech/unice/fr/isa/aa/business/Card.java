package polytech.unice.fr.isa.aa.business;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.UUID;

/**
 * @author Lucas MARTINEZ
 * @version 29/02/16
 */
@Entity
public class Card extends BuyableObject implements Serializable {

    @Id
    private String id;

    @OneToOne (cascade = {CascadeType.ALL})
    private Pass pass;

    //par défaut on met un id aléatoire
    public Card() {
        id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Pass getPass() {
        return pass;
    }

    public void setPass(Pass p) {
        this.pass = p;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Card card = (Card) o;

        if (getId() != null ? !getId().equals(card.getId()) : card.getId() != null) return false;
        return getPass() != null ? getPass().equals(card.getPass()) : card.getPass() == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getId() != null ? getId().hashCode() : 0);
        result = 31 * result + (getPass() != null ? getPass().hashCode() : 0);
        return result;
    }
}
