package polytech.unice.fr.isa.aa.exceptions;

import java.io.Serializable;

/**
 * Created by lucas on 15/03/16.
 */
public class PaymentException extends Exception implements Serializable {

    private String errorPayment;

    public PaymentException(String errorPayment) {
        this.errorPayment = errorPayment;
    }
}
