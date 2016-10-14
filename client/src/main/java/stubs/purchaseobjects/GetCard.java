
package stubs.purchaseobjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour getCard complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="getCard">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="num_creditcard" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getCard", propOrder = {
    "numCreditcard"
})
public class GetCard {

    @XmlElement(name = "num_creditcard")
    protected String numCreditcard;

    /**
     * Obtient la valeur de la propriété numCreditcard.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumCreditcard() {
        return numCreditcard;
    }

    /**
     * Définit la valeur de la propriété numCreditcard.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumCreditcard(String value) {
        this.numCreditcard = value;
    }

}
