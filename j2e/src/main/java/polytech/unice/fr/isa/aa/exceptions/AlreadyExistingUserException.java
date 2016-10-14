package polytech.unice.fr.isa.aa.exceptions;

import java.io.Serializable;

/**
 * @author Nicolas HORY
 * @version 14/03/16.
 */
public class AlreadyExistingUserException extends Exception implements Serializable {
    private String alreadyKnownName;

    public AlreadyExistingUserException(String name) {
        alreadyKnownName = name;
    }
}
