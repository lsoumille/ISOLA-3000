
package stubs.validatepass;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ValidatePassWebService", targetNamespace = "http://webservice.aa.isa.fr.unice.polytech/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ValidatePassWebService {


    /**
     * 
     * @param gateId
     * @param cardId
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(name = "boolean-result", targetNamespace = "")
    @RequestWrapper(localName = "validatePass", targetNamespace = "http://webservice.aa.isa.fr.unice.polytech/", className = "stubs.validatepass.ValidatePass")
    @ResponseWrapper(localName = "validatePassResponse", targetNamespace = "http://webservice.aa.isa.fr.unice.polytech/", className = "stubs.validatepass.ValidatePassResponse")
    public boolean validatePass(
        @WebParam(name = "card_id", targetNamespace = "")
        String cardId,
        @WebParam(name = "gate_id", targetNamespace = "")
        int gateId);

}