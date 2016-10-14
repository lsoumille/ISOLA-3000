package polytech.unice.fr.isa.aa.components;

import polytech.unice.fr.isa.aa.business.Card;
import polytech.unice.fr.isa.aa.business.User;
import polytech.unice.fr.isa.aa.exceptions.ErrorPaymentService;
import polytech.unice.fr.isa.aa.exceptions.NotifyException;
import polytech.unice.fr.isa.aa.exceptions.PaymentException;
import polytech.unice.fr.isa.aa.interfaces.Notify;
import polytech.unice.fr.isa.aa.utils.BankAPI;
import polytech.unice.fr.isa.aa.utils.Database;
import polytech.unice.fr.isa.aa.utils.NotifyAPI;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.util.*;

/**
 * Created by lucas on 05/04/16.
 */
@Stateless
public class NotifyBean implements Notify {

    private NotifyAPI notify;

    @EJB
    private Database database;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean notifyByAge(String object, String message, int ageMin, int ageMax) throws NotifyException {
        List<String> usersToNotify = new ArrayList<>();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root =  criteria.from(User.class);
        criteria.select(root);
        TypedQuery<User> query = entityManager.createQuery(criteria);
        try {
            for (User u : query.getResultList()){
                if(u.getAge() > ageMin && u.getAge() < ageMax && u.getMail() != null)
                    usersToNotify.add(u.getMail());
            }
        } catch (NoResultException nre){
            System.out.println("No result");
        }
        return sendNotify(message, object, usersToNotify);
    }

    private boolean sendNotify(String message, String object, List<String> mails) throws NotifyException{
        boolean status = false;
        status = notify.sendNotify(message, object, mails);
        if(! status){
            throw new NotifyException("error notify service ");
        }
        return status;
    }

    @PostConstruct
    private void initializeRestPartnership() throws IOException {
        Properties prop = new Properties();
        prop.load(this.getClass().getResourceAsStream("/notify.properties"));
        notify = new NotifyAPI();
    }
}
