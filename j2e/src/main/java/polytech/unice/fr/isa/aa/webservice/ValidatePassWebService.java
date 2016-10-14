package polytech.unice.fr.isa.aa.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * @author Lucas MARTINEZ
 * @version 19/03/16
 */

@WebService
public interface ValidatePassWebService {

    @WebMethod
    @WebResult(name = "boolean-result")
    boolean validatePass(@WebParam(name = "card_id") String cardId,
                     @WebParam(name = "gate_id") int gateId);
}
