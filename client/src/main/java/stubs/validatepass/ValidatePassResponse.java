
package stubs.validatepass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour validatePassResponse complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="validatePassResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="boolean-result" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "validatePassResponse", propOrder = {
    "booleanResult"
})
public class ValidatePassResponse {

    @XmlElement(name = "boolean-result")
    protected boolean booleanResult;

    /**
     * Obtient la valeur de la propriété booleanResult.
     * 
     */
    public boolean isBooleanResult() {
        return booleanResult;
    }

    /**
     * Définit la valeur de la propriété booleanResult.
     * 
     */
    public void setBooleanResult(boolean value) {
        this.booleanResult = value;
    }

}
