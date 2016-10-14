package polytech.unice.fr.isa.aa.interfaces;

import polytech.unice.fr.isa.aa.exceptions.NotifyException;

import javax.ejb.Local;

/**
 * Created by lucas on 05/04/16.
 */
@Local
public interface Notify {

    boolean notifyByAge(String object, String message, int ageMin, int ageMax) throws NotifyException;
}
