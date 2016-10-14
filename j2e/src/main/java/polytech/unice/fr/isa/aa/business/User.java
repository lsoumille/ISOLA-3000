package polytech.unice.fr.isa.aa.business;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by lucas on 07/03/16.
 */
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    private String name;
    @NotNull
    private String firstName;
    @NotNull
    private int age;
    @OneToOne(cascade = {CascadeType.ALL})
    private Card card;

    private String creditCard;
    private String mail;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "user")
    private Set<Transaction> allTransaction = new HashSet<>();

    public User() {
    }

    public User(String name, String firstName, int age, String creditCard, String mail){
        this.name = name;
        this.firstName = firstName;
        this.age = age;
        this.creditCard = creditCard;
        this.mail = mail;
    }

    public User(String name, String firstName, int age, Card card, String mail) {
        this.name = name;
        this.firstName = firstName;
        this.age = age;
        this.card = card;
        this.mail = mail;
    }

    public Card getCard() {
        return card;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstname) {
        this.firstName = firstname;
    }

    public void setAge(int newAge) {
        this.age = newAge;
    }

    public int getAge() {
        return this.age;
    }

    public void setCard(Card c) {
        this.card = c;
    }

    public void addTransaction(Transaction trans){
        allTransaction.add(trans);
    }

    public String getName() {
        return name;
    }

    public Set<Transaction> getAllTransaction() {
        return allTransaction;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAllTransaction(Set<Transaction> allTransaction) {
        this.allTransaction = allTransaction;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (getAge() != user.getAge()) return false;
        if (getName() != null ? !getName().equals(user.getName()) : user.getName() != null) return false;
        if (getFirstName() != null ? !getFirstName().equals(user.getFirstName()) : user.getFirstName() != null)
            return false;
        if (getCard() != null ? !getCard().equals(user.getCard()) : user.getCard() != null) return false;
        if (getCreditCard() != null ? !getCreditCard().equals(user.getCreditCard()) : user.getCreditCard() != null)
            return false;;
        if (getMail() != null ? !getMail().equals(user.getMail()) : user.getMail() != null) return false;
        return getAllTransaction() != null ? getAllTransaction().equals(user.getAllTransaction()) : user.getAllTransaction() == null;

    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + getAge();
        result = 31 * result + (getCard() != null ? getCard().hashCode() : 0);
        result = 31 * result + (getCreditCard() != null ? getCreditCard().hashCode() : 0);
        result = 31 * result + (getMail() != null ? getMail().hashCode() : 0);
        result = 31 * result + (getAllTransaction() != null ? getAllTransaction().hashCode() : 0);
        return result;
    }
}
