package polytech.unice.fr.isa.aa.webservice;

import polytech.unice.fr.isa.aa.business.Transaction;
import polytech.unice.fr.isa.aa.exceptions.AlreadyExistingUserException;
import polytech.unice.fr.isa.aa.exceptions.UnknownUserException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.Set;

/**
 * Created by lucas on 19/03/16.
 */
@WebService
public interface UserModifierWebService {

    @WebMethod
    void register(@WebParam(name="user_name") String name,
                  @WebParam(name="user_firstname") String firstname,
                  @WebParam(name="age") int age,
                  @WebParam(name="credit_card_number") String creditCard,
                  @WebParam(name="mail") String mail) throws AlreadyExistingUserException;

    @WebMethod
    @WebResult(name="alltransactions")
    Set<Transaction> getHistory(@WebParam(name="user_name") String name,
                                @WebParam(name="user_firstname") String firstname);
}
