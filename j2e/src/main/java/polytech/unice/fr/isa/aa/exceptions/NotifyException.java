package polytech.unice.fr.isa.aa.exceptions;

/**
 * Created by lucas on 05/04/16.
 */
public class NotifyException extends Exception {
    private String errorNotify;

    public NotifyException(String errorNotify) {
        this.errorNotify = errorNotify;
    }
}
