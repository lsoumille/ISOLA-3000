
package stubs.usermodifier;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour abstractPass complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="abstractPass">
 *   &lt;complexContent>
 *     &lt;extension base="{http://webservice.aa.isa.fr.unice.polytech/}buyableObject">
 *       &lt;sequence>
 *         &lt;element name="age" type="{http://webservice.aa.isa.fr.unice.polytech/}agePass" minOccurs="0"/>
 *         &lt;element name="type" type="{http://webservice.aa.isa.fr.unice.polytech/}typePass" minOccurs="0"/>
 *         &lt;element name="zone" type="{http://webservice.aa.isa.fr.unice.polytech/}zonePass" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "abstractPass", propOrder = {
    "age",
    "type",
    "zone"
})
@XmlSeeAlso({
    Pass.class
})
public class AbstractPass
    extends BuyableObject
{

    @XmlSchemaType(name = "string")
    protected AgePass age;
    @XmlSchemaType(name = "string")
    protected TypePass type;
    @XmlSchemaType(name = "string")
    protected ZonePass zone;

    /**
     * Obtient la valeur de la propriété age.
     * 
     * @return
     *     possible object is
     *     {@link AgePass }
     *     
     */
    public AgePass getAge() {
        return age;
    }

    /**
     * Définit la valeur de la propriété age.
     * 
     * @param value
     *     allowed object is
     *     {@link AgePass }
     *     
     */
    public void setAge(AgePass value) {
        this.age = value;
    }

    /**
     * Obtient la valeur de la propriété type.
     * 
     * @return
     *     possible object is
     *     {@link TypePass }
     *     
     */
    public TypePass getType() {
        return type;
    }

    /**
     * Définit la valeur de la propriété type.
     * 
     * @param value
     *     allowed object is
     *     {@link TypePass }
     *     
     */
    public void setType(TypePass value) {
        this.type = value;
    }

    /**
     * Obtient la valeur de la propriété zone.
     * 
     * @return
     *     possible object is
     *     {@link ZonePass }
     *     
     */
    public ZonePass getZone() {
        return zone;
    }

    /**
     * Définit la valeur de la propriété zone.
     * 
     * @param value
     *     allowed object is
     *     {@link ZonePass }
     *     
     */
    public void setZone(ZonePass value) {
        this.zone = value;
    }

}
