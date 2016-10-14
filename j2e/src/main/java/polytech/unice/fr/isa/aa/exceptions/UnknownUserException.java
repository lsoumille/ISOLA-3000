package polytech.unice.fr.isa.aa.exceptions;

import java.io.Serializable;

/**
 * Created by lucas on 07/03/16.
 */
public class UnknownUserException extends Exception implements Serializable {
    private String firstName;

    public UnknownUserException(String name)  {this.firstName = name;}
}
