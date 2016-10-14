package demos;

import stubs.purchaseobjects.*;

import javax.xml.ws.BindingProvider;
import java.net.URL;

/**
 * Created by lucas on 22/03/16.
 */
public class PurchaseAndAddPassDemo {

    public static PurchaseWebService initialize(String host, String port){
        System.out.println("#### Loading the WSDL contract");
        URL wsdlLocation = PurchaseAndAddPassDemo.class.getResource("/PurchaseWS.wsdl");
        System.out.println("#### Instantiating the WS Proxy");
        PurchaseWebServiceImplService factory = new PurchaseWebServiceImplService(wsdlLocation);
        PurchaseWebService ws = factory.getPurchaseWebServiceImplPort();
        System.out.println("#### Updating the endpoint address dynamically");
        String address = "http://"+host+":"+port+"/j2e-1.0-SNAPSHOT/webservices/PurchaseWS";
        ((BindingProvider) ws).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
        return ws;
    }

    public static void main(String[] args) {
        PurchaseWebService ws = initialize("localhost", "8080");
        try {
            String idCard = ws.addCardForUser("LUCAS","LUCAS");
            System.out.println(idCard);
            for(AbstractPass pass : ws.listAllPass())
                System.out.println(pass.toString());
            ws.addPassForCard(idCard, "ADULT","ONE_DAY","BEGINNER");
            //TODO : ajouter un pass à une carte
        } catch (PaymentException_Exception e) {
            System.out.println("erreur paiement");
        } catch (UnknownUserException_Exception e) {
            e.printStackTrace();
        } catch (UnknownPass_Exception e) {
            e.printStackTrace();
        } catch (UnknownCardException_Exception e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
