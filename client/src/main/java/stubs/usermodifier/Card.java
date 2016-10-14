
package stubs.usermodifier;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour card complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="card">
 *   &lt;complexContent>
 *     &lt;extension base="{http://webservice.aa.isa.fr.unice.polytech/}buyableObject">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pass" type="{http://webservice.aa.isa.fr.unice.polytech/}pass" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "card", propOrder = {
    "id",
    "pass"
})
public class Card
    extends BuyableObject
{

    protected String id;
    protected Pass pass;

    /**
     * Obtient la valeur de la propriété id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Définit la valeur de la propriété id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Obtient la valeur de la propriété pass.
     * 
     * @return
     *     possible object is
     *     {@link Pass }
     *     
     */
    public Pass getPass() {
        return pass;
    }

    /**
     * Définit la valeur de la propriété pass.
     * 
     * @param value
     *     allowed object is
     *     {@link Pass }
     *     
     */
    public void setPass(Pass value) {
        this.pass = value;
    }

}
