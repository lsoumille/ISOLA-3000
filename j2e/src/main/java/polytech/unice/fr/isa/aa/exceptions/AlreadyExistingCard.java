package polytech.unice.fr.isa.aa.exceptions;

import java.io.Serializable;

/**
 * Created by lucas on 26/04/16.
 */
public class AlreadyExistingCard extends Exception implements Serializable {
    private String alreadyKnownName;

    public AlreadyExistingCard(String name) {
        alreadyKnownName = name;
    }
}
