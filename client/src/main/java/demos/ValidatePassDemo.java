package demos;

import stubs.validatepass.ValidatePassWebService;
import stubs.validatepass.ValidatePassWebServiceImplService;

import javax.xml.ws.BindingProvider;
import java.net.URL;

/**
 * @author Lucas MARTINEZ
 * @version 19/03/16
 */
public class ValidatePassDemo {

    private static ValidatePassWebService initialize(String host, String port) {
        System.out.println("#### Loading the WSDL contract");
        URL wsdlLocation = ValidatePassDemo.class.getResource("/ValidatePassWS.wsdl");
        System.out.println("#### Instantiating the WS Proxy");
        ValidatePassWebServiceImplService factory = new ValidatePassWebServiceImplService(wsdlLocation);
        ValidatePassWebService ws = factory.getValidatePassWebServiceImplPort();
        System.out.println("#### Updating the endpoint address dynamically");
        String address = "http://"+host+":"+port+"/j2e-1.0-SNAPSHOT/webservices/ValidatePassWS";
        ((BindingProvider) ws).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
        return ws;
    }

    public static void main(String[] args) {
        ValidatePassWebService ws = initialize("localhost", "8080");
        // validatePass(card_id, gate_id)
        System.out.println(ws.validatePass("0", 0)); //always false
        System.out.println(ws.validatePass("1", 1)); // false à part si on a décommenté dans le constructeur de Database
    }
}
