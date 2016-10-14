package polytech.unice.fr.isa.aa.webservice;

import polytech.unice.fr.isa.aa.business.Transaction;
import polytech.unice.fr.isa.aa.business.User;
import polytech.unice.fr.isa.aa.exceptions.AlreadyExistingUserException;
import polytech.unice.fr.isa.aa.exceptions.UnknownUserException;
import polytech.unice.fr.isa.aa.interfaces.UserFinder;
import polytech.unice.fr.isa.aa.interfaces.UserModifier;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Created by lucas on 19/03/16.
 */
@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/aa")
@Stateless(name = "UserModifierWS")
public class UserModifierWebServiceImpl implements UserModifierWebService {

    @EJB
    private UserModifier registry;
    @EJB
    private UserFinder finder;

    /**
     * add a new user
     * @param name
     * @param firstname
     * @param age
     * @param creditCard
     * @throws AlreadyExistingUserException
     */
    @Override
    public void register(String name, String firstname, int age, String creditCard, String mail) throws AlreadyExistingUserException {
        User user = new User(name, firstname, age, creditCard, mail);
        registry.add(user);
    }

    /**
     * return all the transactions for the user
     * @param name
     * @param firstname
     * @return
     */
    @Override
    public Set<Transaction> getHistory(String name, String firstname) {
        Optional<User> user = finder.getByNameAndFirstName(name, firstname);
        if(user.isPresent()){
            System.out.println("GET HOSTORY : " + user.get().getAllTransaction().size());
            return user.get().getAllTransaction();
        }
        return new HashSet<>();
    }
}
