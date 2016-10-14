package polytech.unice.fr.isa.aa.exceptions;

/**
 * @author Lucas MARTINEZ
 * @version 07/03/16
 */
public class UnknownGateException extends Exception {
    private int unknownId;

    public UnknownGateException(int id) {
        unknownId = id;
    }
}
