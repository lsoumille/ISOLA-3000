
package stubs.purchaseobjects;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour listAllPassResponse complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="listAllPassResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pass" type="{http://webservice.aa.isa.fr.unice.polytech/}abstractPass" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listAllPassResponse", propOrder = {
    "pass"
})
public class ListAllPassResponse {

    protected List<AbstractPass> pass;

    /**
     * Gets the value of the pass property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pass property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPass().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AbstractPass }
     * 
     * 
     */
    public List<AbstractPass> getPass() {
        if (pass == null) {
            pass = new ArrayList<AbstractPass>();
        }
        return this.pass;
    }

}
