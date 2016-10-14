
package stubs.usermodifier;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour agePass.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="agePass">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CHILDREN_UNDER_FIVE"/>
 *     &lt;enumeration value="CHILDREN_OVER_FIVE"/>
 *     &lt;enumeration value="TEENAGER"/>
 *     &lt;enumeration value="STUDENT"/>
 *     &lt;enumeration value="ADULT"/>
 *     &lt;enumeration value="GOLDEN_AGE"/>
 *     &lt;enumeration value="ELDER"/>
 *     &lt;enumeration value="FAMILY_PACK"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "agePass")
@XmlEnum
public enum AgePass {

    CHILDREN_UNDER_FIVE,
    CHILDREN_OVER_FIVE,
    TEENAGER,
    STUDENT,
    ADULT,
    GOLDEN_AGE,
    ELDER,
    FAMILY_PACK;

    public String value() {
        return name();
    }

    public static AgePass fromValue(String v) {
        return valueOf(v);
    }

}
