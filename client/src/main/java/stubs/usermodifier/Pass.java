
package stubs.usermodifier;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour pass complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="pass">
 *   &lt;complexContent>
 *     &lt;extension base="{http://webservice.aa.isa.fr.unice.polytech/}abstractPass">
 *       &lt;sequence>
 *         &lt;element name="activated" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="gateList" type="{http://webservice.aa.isa.fr.unice.polytech/}gate" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="nbDays" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pass", propOrder = {
    "activated",
    "gateList",
    "nbDays"
})
public class Pass
    extends AbstractPass
{

    protected boolean activated;
    @XmlElement(nillable = true)
    protected List<Gate> gateList;
    protected double nbDays;

    /**
     * Obtient la valeur de la propriété activated.
     * 
     */
    public boolean isActivated() {
        return activated;
    }

    /**
     * Définit la valeur de la propriété activated.
     * 
     */
    public void setActivated(boolean value) {
        this.activated = value;
    }

    /**
     * Gets the value of the gateList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the gateList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGateList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Gate }
     * 
     * 
     */
    public List<Gate> getGateList() {
        if (gateList == null) {
            gateList = new ArrayList<Gate>();
        }
        return this.gateList;
    }

    /**
     * Obtient la valeur de la propriété nbDays.
     * 
     */
    public double getNbDays() {
        return nbDays;
    }

    /**
     * Définit la valeur de la propriété nbDays.
     * 
     */
    public void setNbDays(double value) {
        this.nbDays = value;
    }

}
