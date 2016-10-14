
package stubs.purchaseobjects;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour zonePass.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="zonePass">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="BEGINNER"/>
 *     &lt;enumeration value="ISOLA2000"/>
 *     &lt;enumeration value="AURON"/>
 *     &lt;enumeration value="SNOWPARK"/>
 *     &lt;enumeration value="ALL"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "zonePass")
@XmlEnum
public enum ZonePass {

    BEGINNER("BEGINNER"),
    @XmlEnumValue("ISOLA2000")
    ISOLA_2000("ISOLA2000"),
    AURON("AURON"),
    SNOWPARK("SNOWPARK"),
    ALL("ALL");
    private final String value;

    ZonePass(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ZonePass fromValue(String v) {
        for (ZonePass c: ZonePass.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
