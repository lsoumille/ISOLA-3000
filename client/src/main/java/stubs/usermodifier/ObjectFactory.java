
package stubs.usermodifier;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the stubs.usermodifier package. 
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

    private final static QName _AlreadyExistingUserException_QNAME = new QName("http://webservice.aa.isa.fr.unice.polytech/", "AlreadyExistingUserException");
    private final static QName _Register_QNAME = new QName("http://webservice.aa.isa.fr.unice.polytech/", "register");
    private final static QName _GetHistoryResponse_QNAME = new QName("http://webservice.aa.isa.fr.unice.polytech/", "getHistoryResponse");
    private final static QName _GetHistory_QNAME = new QName("http://webservice.aa.isa.fr.unice.polytech/", "getHistory");
    private final static QName _RegisterResponse_QNAME = new QName("http://webservice.aa.isa.fr.unice.polytech/", "registerResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: stubs.usermodifier
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetHistoryResponse }
     * 
     */
    public GetHistoryResponse createGetHistoryResponse() {
        return new GetHistoryResponse();
    }

    /**
     * Create an instance of {@link GetHistory }
     * 
     */
    public GetHistory createGetHistory() {
        return new GetHistory();
    }

    /**
     * Create an instance of {@link RegisterResponse }
     * 
     */
    public RegisterResponse createRegisterResponse() {
        return new RegisterResponse();
    }

    /**
     * Create an instance of {@link AlreadyExistingUserException }
     * 
     */
    public AlreadyExistingUserException createAlreadyExistingUserException() {
        return new AlreadyExistingUserException();
    }

    /**
     * Create an instance of {@link Register }
     * 
     */
    public Register createRegister() {
        return new Register();
    }

    /**
     * Create an instance of {@link Pass }
     * 
     */
    public Pass createPass() {
        return new Pass();
    }

    /**
     * Create an instance of {@link AbstractPass }
     * 
     */
    public AbstractPass createAbstractPass() {
        return new AbstractPass();
    }

    /**
     * Create an instance of {@link BuyableObject }
     * 
     */
    public BuyableObject createBuyableObject() {
        return new BuyableObject();
    }

    /**
     * Create an instance of {@link Gate }
     * 
     */
    public Gate createGate() {
        return new Gate();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link Transaction }
     * 
     */
    public Transaction createTransaction() {
        return new Transaction();
    }

    /**
     * Create an instance of {@link Card }
     * 
     */
    public Card createCard() {
        return new Card();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AlreadyExistingUserException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.aa.isa.fr.unice.polytech/", name = "AlreadyExistingUserException")
    public JAXBElement<AlreadyExistingUserException> createAlreadyExistingUserException(AlreadyExistingUserException value) {
        return new JAXBElement<AlreadyExistingUserException>(_AlreadyExistingUserException_QNAME, AlreadyExistingUserException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Register }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.aa.isa.fr.unice.polytech/", name = "register")
    public JAXBElement<Register> createRegister(Register value) {
        return new JAXBElement<Register>(_Register_QNAME, Register.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetHistoryResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.aa.isa.fr.unice.polytech/", name = "getHistoryResponse")
    public JAXBElement<GetHistoryResponse> createGetHistoryResponse(GetHistoryResponse value) {
        return new JAXBElement<GetHistoryResponse>(_GetHistoryResponse_QNAME, GetHistoryResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetHistory }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.aa.isa.fr.unice.polytech/", name = "getHistory")
    public JAXBElement<GetHistory> createGetHistory(GetHistory value) {
        return new JAXBElement<GetHistory>(_GetHistory_QNAME, GetHistory.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.aa.isa.fr.unice.polytech/", name = "registerResponse")
    public JAXBElement<RegisterResponse> createRegisterResponse(RegisterResponse value) {
        return new JAXBElement<RegisterResponse>(_RegisterResponse_QNAME, RegisterResponse.class, null, value);
    }

}
