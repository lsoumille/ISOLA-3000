package api;

import stubs.purchaseobjects.PurchaseWebService;
import stubs.purchaseobjects.PurchaseWebServiceImplService;
import stubs.usermodifier.UserModifierWebService;
import stubs.usermodifier.UserModifierWebServiceImplService;
import stubs.validatepass.ValidatePassWebService;
import stubs.validatepass.ValidatePassWebServiceImplService;

import javax.xml.ws.BindingProvider;
import java.net.URL;

/**
 * @author Lucas MARTINEZ
 * @version 19/03/16
 */
public class IsolaPublicAPI {

    public ValidatePassWebService validator;
    public UserModifierWebService userModifier;
    public PurchaseWebService purchaser;

    public IsolaPublicAPI(String host, String port) {
        initValidatePass(host, port);
        initUserModifier(host, port);
        initPurchase(host, port);
    }

    private void initValidatePass(String host, String port) {
        URL wsdlLocation = IsolaPublicAPI.class.getResource("/ValidatePassWS.wsdl");
        ValidatePassWebServiceImplService factory = new ValidatePassWebServiceImplService(wsdlLocation);
        validator = factory.getValidatePassWebServiceImplPort();
        String address = "http://"+host+":"+port+"/j2e-1.0-SNAPSHOT/webservices/ValidatePassWS";
        ((BindingProvider) validator).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
    }

    private void initUserModifier(String host, String port) {
        URL wsdlLocation = IsolaPublicAPI.class.getResource("/UserModifierWS.wsdl");
        UserModifierWebServiceImplService factory = new UserModifierWebServiceImplService(wsdlLocation);
        userModifier = factory.getUserModifierWebServiceImplPort();
        String address = "http://"+host+":"+port+"/j2e-1.0-SNAPSHOT/webservices/UserModifierWS";
        ((BindingProvider) userModifier).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
    }

    private void initPurchase(String host, String port) {
        URL wsdlLocation = IsolaPublicAPI.class.getResource("/PurchaseWS.wsdl");
        PurchaseWebServiceImplService factory = new PurchaseWebServiceImplService(wsdlLocation);
        purchaser = factory.getPurchaseWebServiceImplPort();
        String address = "http://"+host+":"+port+"/j2e-1.0-SNAPSHOT/webservices/PurchaseWS";
        ((BindingProvider) purchaser).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
    }
}
