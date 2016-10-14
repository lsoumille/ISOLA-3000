
package stubs.purchaseobjects;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the stubs.purchaseobjects package. 
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

    private final static QName _GetCardResponse_QNAME = new QName("http://webservice.aa.isa.fr.unice.polytech/", "getCardResponse");
    private final static QName _UnknownUserException_QNAME = new QName("http://webservice.aa.isa.fr.unice.polytech/", "UnknownUserException");
    private final static QName _UnknownPass_QNAME = new QName("http://webservice.aa.isa.fr.unice.polytech/", "UnknownPass");
    private final static QName _ListAllPass_QNAME = new QName("http://webservice.aa.isa.fr.unice.polytech/", "listAllPass");
    private final static QName _AddPassForCardResponse_QNAME = new QName("http://webservice.aa.isa.fr.unice.polytech/", "addPassForCardResponse");
    private final static QName _AlreadyExistingCard_QNAME = new QName("http://webservice.aa.isa.fr.unice.polytech/", "AlreadyExistingCard");
    private final static QName _UnknownCardException_QNAME = new QName("http://webservice.aa.isa.fr.unice.polytech/", "UnknownCardException");
    private final static QName _AddPassForCardWithCredentialResponse_QNAME = new QName("http://webservice.aa.isa.fr.unice.polytech/", "addPassForCardWithCredentialResponse");
    private final static QName _AddCardForUser_QNAME = new QName("http://webservice.aa.isa.fr.unice.polytech/", "addCardForUser");
    private final static QName _AddCardForUserResponse_QNAME = new QName("http://webservice.aa.isa.fr.unice.polytech/", "addCardForUserResponse");
    private final static QName _SubscribeFidelicime_QNAME = new QName("http://webservice.aa.isa.fr.unice.polytech/", "subscribeFidelicime");
    private final static QName _SubscribeFidelicimeResponse_QNAME = new QName("http://webservice.aa.isa.fr.unice.polytech/", "subscribeFidelicimeResponse");
    private final static QName _GetCard_QNAME = new QName("http://webservice.aa.isa.fr.unice.polytech/", "getCard");
    private final static QName _AddPassForCardWithCredential_QNAME = new QName("http://webservice.aa.isa.fr.unice.polytech/", "addPassForCardWithCredential");
    private final static QName _ListAllPassResponse_QNAME = new QName("http://webservice.aa.isa.fr.unice.polytech/", "listAllPassResponse");
    private final static QName _PaymentException_QNAME = new QName("http://webservice.aa.isa.fr.unice.polytech/", "PaymentException");
    private final static QName _PriceNotFoundException_QNAME = new QName("http://webservice.aa.isa.fr.unice.polytech/", "PriceNotFoundException");
    private final static QName _AddPassForCard_QNAME = new QName("http://webservice.aa.isa.fr.unice.polytech/", "addPassForCard");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: stubs.purchaseobjects
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UnknownCardException }
     * 
     */
    public UnknownCardException createUnknownCardException() {
        return new UnknownCardException();
    }

    /**
     * Create an instance of {@link AddCardForUser }
     * 
     */
    public AddCardForUser createAddCardForUser() {
        return new AddCardForUser();
    }

    /**
     * Create an instance of {@link AddCardForUserResponse }
     * 
     */
    public AddCardForUserResponse createAddCardForUserResponse() {
        return new AddCardForUserResponse();
    }

    /**
     * Create an instance of {@link AddPassForCardWithCredentialResponse }
     * 
     */
    public AddPassForCardWithCredentialResponse createAddPassForCardWithCredentialResponse() {
        return new AddPassForCardWithCredentialResponse();
    }

    /**
     * Create an instance of {@link SubscribeFidelicime }
     * 
     */
    public SubscribeFidelicime createSubscribeFidelicime() {
        return new SubscribeFidelicime();
    }

    /**
     * Create an instance of {@link UnknownUserException }
     * 
     */
    public UnknownUserException createUnknownUserException() {
        return new UnknownUserException();
    }

    /**
     * Create an instance of {@link GetCardResponse }
     * 
     */
    public GetCardResponse createGetCardResponse() {
        return new GetCardResponse();
    }

    /**
     * Create an instance of {@link UnknownPass }
     * 
     */
    public UnknownPass createUnknownPass() {
        return new UnknownPass();
    }

    /**
     * Create an instance of {@link ListAllPass }
     * 
     */
    public ListAllPass createListAllPass() {
        return new ListAllPass();
    }

    /**
     * Create an instance of {@link AlreadyExistingCard }
     * 
     */
    public AlreadyExistingCard createAlreadyExistingCard() {
        return new AlreadyExistingCard();
    }

    /**
     * Create an instance of {@link AddPassForCardResponse }
     * 
     */
    public AddPassForCardResponse createAddPassForCardResponse() {
        return new AddPassForCardResponse();
    }

    /**
     * Create an instance of {@link ListAllPassResponse }
     * 
     */
    public ListAllPassResponse createListAllPassResponse() {
        return new ListAllPassResponse();
    }

    /**
     * Create an instance of {@link PaymentException }
     * 
     */
    public PaymentException createPaymentException() {
        return new PaymentException();
    }

    /**
     * Create an instance of {@link PriceNotFoundException }
     * 
     */
    public PriceNotFoundException createPriceNotFoundException() {
        return new PriceNotFoundException();
    }

    /**
     * Create an instance of {@link AddPassForCard }
     * 
     */
    public AddPassForCard createAddPassForCard() {
        return new AddPassForCard();
    }

    /**
     * Create an instance of {@link SubscribeFidelicimeResponse }
     * 
     */
    public SubscribeFidelicimeResponse createSubscribeFidelicimeResponse() {
        return new SubscribeFidelicimeResponse();
    }

    /**
     * Create an instance of {@link GetCard }
     * 
     */
    public GetCard createGetCard() {
        return new GetCard();
    }

    /**
     * Create an instance of {@link AddPassForCardWithCredential }
     * 
     */
    public AddPassForCardWithCredential createAddPassForCardWithCredential() {
        return new AddPassForCardWithCredential();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCardResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.aa.isa.fr.unice.polytech/", name = "getCardResponse")
    public JAXBElement<GetCardResponse> createGetCardResponse(GetCardResponse value) {
        return new JAXBElement<GetCardResponse>(_GetCardResponse_QNAME, GetCardResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnknownUserException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.aa.isa.fr.unice.polytech/", name = "UnknownUserException")
    public JAXBElement<UnknownUserException> createUnknownUserException(UnknownUserException value) {
        return new JAXBElement<UnknownUserException>(_UnknownUserException_QNAME, UnknownUserException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnknownPass }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.aa.isa.fr.unice.polytech/", name = "UnknownPass")
    public JAXBElement<UnknownPass> createUnknownPass(UnknownPass value) {
        return new JAXBElement<UnknownPass>(_UnknownPass_QNAME, UnknownPass.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListAllPass }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.aa.isa.fr.unice.polytech/", name = "listAllPass")
    public JAXBElement<ListAllPass> createListAllPass(ListAllPass value) {
        return new JAXBElement<ListAllPass>(_ListAllPass_QNAME, ListAllPass.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddPassForCardResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.aa.isa.fr.unice.polytech/", name = "addPassForCardResponse")
    public JAXBElement<AddPassForCardResponse> createAddPassForCardResponse(AddPassForCardResponse value) {
        return new JAXBElement<AddPassForCardResponse>(_AddPassForCardResponse_QNAME, AddPassForCardResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AlreadyExistingCard }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.aa.isa.fr.unice.polytech/", name = "AlreadyExistingCard")
    public JAXBElement<AlreadyExistingCard> createAlreadyExistingCard(AlreadyExistingCard value) {
        return new JAXBElement<AlreadyExistingCard>(_AlreadyExistingCard_QNAME, AlreadyExistingCard.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnknownCardException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.aa.isa.fr.unice.polytech/", name = "UnknownCardException")
    public JAXBElement<UnknownCardException> createUnknownCardException(UnknownCardException value) {
        return new JAXBElement<UnknownCardException>(_UnknownCardException_QNAME, UnknownCardException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddPassForCardWithCredentialResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.aa.isa.fr.unice.polytech/", name = "addPassForCardWithCredentialResponse")
    public JAXBElement<AddPassForCardWithCredentialResponse> createAddPassForCardWithCredentialResponse(AddPassForCardWithCredentialResponse value) {
        return new JAXBElement<AddPassForCardWithCredentialResponse>(_AddPassForCardWithCredentialResponse_QNAME, AddPassForCardWithCredentialResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddCardForUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.aa.isa.fr.unice.polytech/", name = "addCardForUser")
    public JAXBElement<AddCardForUser> createAddCardForUser(AddCardForUser value) {
        return new JAXBElement<AddCardForUser>(_AddCardForUser_QNAME, AddCardForUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddCardForUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.aa.isa.fr.unice.polytech/", name = "addCardForUserResponse")
    public JAXBElement<AddCardForUserResponse> createAddCardForUserResponse(AddCardForUserResponse value) {
        return new JAXBElement<AddCardForUserResponse>(_AddCardForUserResponse_QNAME, AddCardForUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubscribeFidelicime }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.aa.isa.fr.unice.polytech/", name = "subscribeFidelicime")
    public JAXBElement<SubscribeFidelicime> createSubscribeFidelicime(SubscribeFidelicime value) {
        return new JAXBElement<SubscribeFidelicime>(_SubscribeFidelicime_QNAME, SubscribeFidelicime.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubscribeFidelicimeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.aa.isa.fr.unice.polytech/", name = "subscribeFidelicimeResponse")
    public JAXBElement<SubscribeFidelicimeResponse> createSubscribeFidelicimeResponse(SubscribeFidelicimeResponse value) {
        return new JAXBElement<SubscribeFidelicimeResponse>(_SubscribeFidelicimeResponse_QNAME, SubscribeFidelicimeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCard }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.aa.isa.fr.unice.polytech/", name = "getCard")
    public JAXBElement<GetCard> createGetCard(GetCard value) {
        return new JAXBElement<GetCard>(_GetCard_QNAME, GetCard.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddPassForCardWithCredential }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.aa.isa.fr.unice.polytech/", name = "addPassForCardWithCredential")
    public JAXBElement<AddPassForCardWithCredential> createAddPassForCardWithCredential(AddPassForCardWithCredential value) {
        return new JAXBElement<AddPassForCardWithCredential>(_AddPassForCardWithCredential_QNAME, AddPassForCardWithCredential.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListAllPassResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.aa.isa.fr.unice.polytech/", name = "listAllPassResponse")
    public JAXBElement<ListAllPassResponse> createListAllPassResponse(ListAllPassResponse value) {
        return new JAXBElement<ListAllPassResponse>(_ListAllPassResponse_QNAME, ListAllPassResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PaymentException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.aa.isa.fr.unice.polytech/", name = "PaymentException")
    public JAXBElement<PaymentException> createPaymentException(PaymentException value) {
        return new JAXBElement<PaymentException>(_PaymentException_QNAME, PaymentException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PriceNotFoundException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.aa.isa.fr.unice.polytech/", name = "PriceNotFoundException")
    public JAXBElement<PriceNotFoundException> createPriceNotFoundException(PriceNotFoundException value) {
        return new JAXBElement<PriceNotFoundException>(_PriceNotFoundException_QNAME, PriceNotFoundException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddPassForCard }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.aa.isa.fr.unice.polytech/", name = "addPassForCard")
    public JAXBElement<AddPassForCard> createAddPassForCard(AddPassForCard value) {
        return new JAXBElement<AddPassForCard>(_AddPassForCard_QNAME, AddPassForCard.class, null, value);
    }

}
