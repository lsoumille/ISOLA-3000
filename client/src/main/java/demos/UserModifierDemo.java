package demos;

import stubs.usermodifier.AlreadyExistingUserException_Exception;
import stubs.usermodifier.UserModifierWebService;
import stubs.usermodifier.UserModifierWebServiceImplService;

import javax.xml.ws.BindingProvider;
import java.net.URL;

/**
 * @author Lucas MARTINEZ
 * @version 19/03/16
 */
public class UserModifierDemo {
    private static UserModifierWebService initialize(String host, String port) {
        System.out.println("#### Loading the WSDL contract");
        URL wsdlLocation = ValidatePassDemo.class.getResource("/UserModifierWS.wsdl");
        System.out.println("#### Instantiating the WS Proxy");
        UserModifierWebServiceImplService factory = new UserModifierWebServiceImplService(wsdlLocation);
        UserModifierWebService ws = factory.getUserModifierWebServiceImplPort();
        System.out.println("#### Updating the endpoint address dynamically");
        String address = "http://"+host+":"+port+"/j2e-1.0-SNAPSHOT/webservices/UserModifierWS";
        ((BindingProvider) ws).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
        return ws;
    }

    public static void main(String[] args) {
        UserModifierWebService ws = initialize("localhost", "8080");

        try {
            ws.register("martinez", "lucas", 22, "201294920131","aigritude@gmail.com");
            ws.getHistory("martinez", "lucas").size();
            ws.getHistory("lucas", "martinez").size();
        } catch (AlreadyExistingUserException_Exception e) {
            e.printStackTrace();
        }
    }
}
