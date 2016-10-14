package polytech.unice.fr.isa.aa.interfaces;

import polytech.unice.fr.isa.aa.business.Card;
import polytech.unice.fr.isa.aa.business.User;
import polytech.unice.fr.isa.aa.exceptions.AlreadyExistingUserException;
import polytech.unice.fr.isa.aa.exceptions.UnknownCardException;
import polytech.unice.fr.isa.aa.exceptions.UnknownUserException;

import javax.ejb.Local;

/**
 * Created by lucas on 07/03/16.
 */
@Local
public interface UserModifier {

    boolean add(User u) throws AlreadyExistingUserException;

    boolean update(User u, int newAge) throws UnknownUserException;

    boolean remove(User u) throws UnknownUserException;

    boolean addCardForUser(User u, Card c) throws UnknownCardException, UnknownUserException;
}
