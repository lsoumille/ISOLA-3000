
package stubs.purchaseobjects;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "PurchaseWebServiceImplService", targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/aa", wsdlLocation = "file:///home/lucas/Isola3000/aa/client/src/main/resources/PurchaseWS.wsdl")
public class PurchaseWebServiceImplService
    extends Service
{

    private final static URL PURCHASEWEBSERVICEIMPLSERVICE_WSDL_LOCATION;
    private final static WebServiceException PURCHASEWEBSERVICEIMPLSERVICE_EXCEPTION;
    private final static QName PURCHASEWEBSERVICEIMPLSERVICE_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/aa", "PurchaseWebServiceImplService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:///home/lucas/Isola3000/aa/client/src/main/resources/PurchaseWS.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        PURCHASEWEBSERVICEIMPLSERVICE_WSDL_LOCATION = url;
        PURCHASEWEBSERVICEIMPLSERVICE_EXCEPTION = e;
    }

    public PurchaseWebServiceImplService() {
        super(__getWsdlLocation(), PURCHASEWEBSERVICEIMPLSERVICE_QNAME);
    }

    public PurchaseWebServiceImplService(WebServiceFeature... features) {
        super(__getWsdlLocation(), PURCHASEWEBSERVICEIMPLSERVICE_QNAME, features);
    }

    public PurchaseWebServiceImplService(URL wsdlLocation) {
        super(wsdlLocation, PURCHASEWEBSERVICEIMPLSERVICE_QNAME);
    }

    public PurchaseWebServiceImplService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, PURCHASEWEBSERVICEIMPLSERVICE_QNAME, features);
    }

    public PurchaseWebServiceImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public PurchaseWebServiceImplService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns PurchaseWebService
     */
    @WebEndpoint(name = "PurchaseWebServiceImplPort")
    public PurchaseWebService getPurchaseWebServiceImplPort() {
        return super.getPort(new QName("http://www.polytech.unice.fr/si/4a/isa/aa", "PurchaseWebServiceImplPort"), PurchaseWebService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns PurchaseWebService
     */
    @WebEndpoint(name = "PurchaseWebServiceImplPort")
    public PurchaseWebService getPurchaseWebServiceImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://www.polytech.unice.fr/si/4a/isa/aa", "PurchaseWebServiceImplPort"), PurchaseWebService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (PURCHASEWEBSERVICEIMPLSERVICE_EXCEPTION!= null) {
            throw PURCHASEWEBSERVICEIMPLSERVICE_EXCEPTION;
        }
        return PURCHASEWEBSERVICEIMPLSERVICE_WSDL_LOCATION;
    }

}