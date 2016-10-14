package polytech.unice.fr.isa.aa.business;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

/**
 * Created by lucas on 15/03/16.
 */
@Entity
@Table(name="transactionsPayment")
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @ManyToOne
    private User user;

    @NotNull
    private BuyableObject objects;

    @NotNull
    private float amount;

    public Transaction(User user, BuyableObject objects) {
        this.user = user;
        this.objects = objects;
    }

    public Transaction() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BuyableObject getObjects() {
        return objects;
    }

    public void setObjects(BuyableObject objects) {
        this.objects = objects;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        if (Float.compare(that.getAmount(), getAmount()) != 0) return false;
        if (getUser() != null ? !getUser().getName().equals(that.getUser().getName()) : that.getUser() != null) return false;
        return getObjects() != null ? getObjects().equals(that.getObjects()) : that.getObjects() == null;

    }

    @Override
    public int hashCode() {
        int result = getUser() != null ? getUser().getName().hashCode() : 0;
        result = 31 * result + (getObjects() != null ? getObjects().hashCode() : 0);
        result = 31 * result + (getAmount() != +0.0f ? Float.floatToIntBits(getAmount()) : 0);
        return result;
    }
}
