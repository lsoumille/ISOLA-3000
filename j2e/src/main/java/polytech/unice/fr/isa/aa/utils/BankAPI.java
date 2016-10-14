package polytech.unice.fr.isa.aa.utils;

import polytech.unice.fr.isa.aa.business.User;
import org.apache.cxf.jaxrs.client.WebClient;
import org.json.JSONObject;
import polytech.unice.fr.isa.aa.exceptions.ErrorPaymentService;


import javax.ws.rs.core.MediaType;

/**
 * Created by lucas on 14/03/16.
 */
public class BankAPI {
    private String url = "http://localhost:9090";

    public boolean performPayment(User customer, double value) throws ErrorPaymentService {

        JSONObject request = new JSONObject().put("CreditCard", customer.getCreditCard()).put("Amount", value);

        Integer id;
        try {
            String str = WebClient.create("http://localhost:9090").path("/withdraw")
                    .accept(MediaType.APPLICATION_JSON_TYPE).header("Content-Type", MediaType.APPLICATION_JSON)
                    .post(request.toString(), String.class);
            id = Integer.parseInt(str);
        } catch (Exception e) {
            throw new ErrorPaymentService();
        }

        JSONObject payment;
        try {
            String response = WebClient.create(url).path("/payments/" + id).get(String.class);
            payment = new JSONObject(response);
        } catch (Exception e) {
            throw new ErrorPaymentService();
        }

        return (payment.getInt("Status") == 0);
    }

    /**
     * Payment with only the credit card number
     * @param value
     * @return
     * @throws ErrorPaymentService
     */
    public boolean performPayment(String numCard, double value) throws ErrorPaymentService {
        System.out.println(numCard);
        JSONObject request = new JSONObject().put("CreditCard", numCard).put("Amount", value);

        Integer id;
        try {
            String str = WebClient.create("http://localhost:9090").path("/withdraw")
                    .accept(MediaType.APPLICATION_JSON_TYPE).header("Content-Type", MediaType.APPLICATION_JSON)
                    .post(request.toString(), String.class);
            id = Integer.parseInt(str);
        } catch (Exception e) {
            throw new ErrorPaymentService();
        }

        JSONObject payment;
        try {
            String response = WebClient.create(url).path("/payments/" + id).get(String.class);
            payment = new JSONObject(response);
        } catch (Exception e) {
            throw new ErrorPaymentService();
        }

        return (payment.getInt("Status") == 0);
    }
}
