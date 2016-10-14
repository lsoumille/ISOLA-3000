package polytech.unice.fr.isa.aa.interfaces;

import polytech.unice.fr.isa.aa.business.User;
import polytech.unice.fr.isa.aa.exceptions.UnknownUserException;

import javax.ejb.Local;
import java.util.Optional;

/**
 * Created by lucas on 19/03/16.
 */

@Local
public interface UserFinder {

     Optional<User> getByNameAndFirstName(String name, String firstname);

     Optional<User> getByIdCard(String idCard);
}
