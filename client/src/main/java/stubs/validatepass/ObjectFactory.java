
package stubs.validatepass;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the stubs.validatepass package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ValidatePassResponse_QNAME = new QName("http://webservice.aa.isa.fr.unice.polytech/", "validatePassResponse");
    private final static QName _ValidatePass_QNAME = new QName("http://webservice.aa.isa.fr.unice.polytech/", "validatePass");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: stubs.validatepass
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ValidatePass }
     * 
     */
    public ValidatePass createValidatePass() {
        return new ValidatePass();
    }

    /**
     * Create an instance of {@link ValidatePassResponse }
     * 
     */
    public ValidatePassResponse createValidatePassResponse() {
        return new ValidatePassResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidatePassResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.aa.isa.fr.unice.polytech/", name = "validatePassResponse")
    public JAXBElement<ValidatePassResponse> createValidatePassResponse(ValidatePassResponse value) {
        return new JAXBElement<ValidatePassResponse>(_ValidatePassResponse_QNAME, ValidatePassResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidatePass }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.aa.isa.fr.unice.polytech/", name = "validatePass")
    public JAXBElement<ValidatePass> createValidatePass(ValidatePass value) {
        return new JAXBElement<ValidatePass>(_ValidatePass_QNAME, ValidatePass.class, null, value);
    }

}
