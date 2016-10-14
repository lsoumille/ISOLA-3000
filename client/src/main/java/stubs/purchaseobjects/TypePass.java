
package stubs.purchaseobjects;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour typePass.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="typePass">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="HALF_DAY"/>
 *     &lt;enumeration value="ONE_DAY"/>
 *     &lt;enumeration value="TWO_DAYS"/>
 *     &lt;enumeration value="THREE_DAYS"/>
 *     &lt;enumeration value="FOUR_DAYS"/>
 *     &lt;enumeration value="FIVE_DAYS"/>
 *     &lt;enumeration value="SIX_DAYS"/>
 *     &lt;enumeration value="SEVEN_DAYS"/>
 *     &lt;enumeration value="EIGHT_DAYS"/>
 *     &lt;enumeration value="MORE_THAN_NINE_DAYS"/>
 *     &lt;enumeration value="FIDELICIMES"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "typePass")
@XmlEnum
public enum TypePass {

    HALF_DAY,
    ONE_DAY,
    TWO_DAYS,
    THREE_DAYS,
    FOUR_DAYS,
    FIVE_DAYS,
    SIX_DAYS,
    SEVEN_DAYS,
    EIGHT_DAYS,
    MORE_THAN_NINE_DAYS,
    FIDELICIMES;

    public String value() {
        return name();
    }

    public static TypePass fromValue(String v) {
        return valueOf(v);
    }

}
