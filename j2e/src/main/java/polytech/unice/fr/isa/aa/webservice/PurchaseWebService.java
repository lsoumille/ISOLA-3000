package polytech.unice.fr.isa.aa.webservice;

import polytech.unice.fr.isa.aa.business.AbstractPass;
import polytech.unice.fr.isa.aa.business.Pass;
import polytech.unice.fr.isa.aa.exceptions.*;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.List;

/**
 * Created by lucas on 19/03/16.
 */
@WebService
public interface PurchaseWebService {

    @WebMethod
    @WebResult(name = "id_card")
    String addCardForUser(@WebParam(name = "user_name") String name,
                          @WebParam(name = "user_firstname") String firstname) throws PaymentException, UnknownUserException, AlreadyExistingCard;

    @WebMethod
    void addPassForCard(@WebParam(name = "id_card") String idCard,
                        @WebParam(name = "age") String age,
                        @WebParam(name = "type") String type,
                        @WebParam(name = "zone") String zone) throws PaymentException, UnknownCardException, UnknownPass, PriceNotFoundException;

    @WebMethod
    void subscribeFidelicime(@WebParam(name = "id_card") String idCard) throws PaymentException, UnknownCardException, UnknownPass, PriceNotFoundException;


    @WebMethod
    @WebResult(name = "pass")
    List<AbstractPass> listAllPass();

    @WebMethod
    @WebResult(name = "id_card")
    String getCard(@WebParam(name = "num_creditcard") String numBank) throws PaymentException, UnknownUserException, AlreadyExistingCard;

    @WebMethod
    void addPassForCardWithCredential(@WebParam(name = "id_card") String idCard,
                        @WebParam(name = "age") String age,
                        @WebParam(name = "type") String type,
                        @WebParam(name = "zone") String zone,
                                      @WebParam(name = "num_creditcard") String numBank) throws PaymentException, UnknownCardException, UnknownPass, PriceNotFoundException;
}
