
package stubs.purchaseobjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour buyableObject complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="buyableObject">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "buyableObject", propOrder = {
    "price"
})
@XmlSeeAlso({
    AbstractPass.class
})
public class BuyableObject {

    protected float price;

    /**
     * Obtient la valeur de la propriété price.
     * 
     */
    public float getPrice() {
        return price;
    }

    /**
     * Définit la valeur de la propriété price.
     * 
     */
    public void setPrice(float value) {
        this.price = value;
    }

}
