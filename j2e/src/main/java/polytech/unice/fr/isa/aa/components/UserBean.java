package polytech.unice.fr.isa.aa.components;

import polytech.unice.fr.isa.aa.business.Card;
import polytech.unice.fr.isa.aa.business.User;
import polytech.unice.fr.isa.aa.exceptions.AlreadyExistingUserException;
import polytech.unice.fr.isa.aa.exceptions.UnknownCardException;
import polytech.unice.fr.isa.aa.exceptions.UnknownUserException;
import polytech.unice.fr.isa.aa.interfaces.UserFinder;
import polytech.unice.fr.isa.aa.interfaces.UserModifier;
import polytech.unice.fr.isa.aa.utils.Database;

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
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Map;
import java.util.Optional;

/**
 * Created by lucas on 07/03/16.
 */

@Stateless(name="usermodifier")
public class UserBean implements UserModifier, UserFinder {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * add a new user in the database
     * @param u
     * @return
     */
    @Override
    public boolean add(User u) throws AlreadyExistingUserException {
        if(getByNameAndFirstName(u.getName(), u.getFirstName()).isPresent()){
            throw new AlreadyExistingUserException(u.getFirstName());
        }
        entityManager.persist(u);
        return true;

    }

    /**
     * Update the user in param
     * @param u
     * @param newAge
     * @return
     */
    @Override
    public boolean update(User u, int newAge) throws UnknownUserException {
        if(! getByNameAndFirstName(u.getName(), u.getFirstName()).isPresent()){
            throw new UnknownUserException(u.getFirstName());
        }
        Optional<User> userToUpdate = getByNameAndFirstName(u.getName(), u.getFirstName());
        userToUpdate.get().setAge(newAge);
        entityManager.merge(userToUpdate.get());
        return true;
    }

    /**
     * delete the user in param
     * @param u
     * @return
     * @throws UnknownUserException
     */
    @Override
    public boolean remove(User u) throws UnknownUserException {
        if(! getByNameAndFirstName(u.getName(), u.getFirstName()).isPresent()){
            throw new UnknownUserException(u.getFirstName());
        }
        entityManager.remove(getByNameAndFirstName(u.getName(), u.getFirstName()).get());
        return true;
    }

    /**
     * add a card for the user
     * @param u
     * @param c
     * @return
     * @throws UnknownCardException
     * @throws UnknownUserException
     */
    @Override
    public boolean addCardForUser(User u, Card c) throws UnknownCardException, UnknownUserException {
       /* if (!database.getCards().containsKey(c)) {// If the card doesn't exist in database
            throw new UnknownCardException();
        }
        if (!database.getUsers().containsKey(u)) {
            throw new UnknownUserException(u.getFirstName());
        }
        u.setCard(c);
        database.getUsers().put(u, c);*/
        if(! getByNameAndFirstName(u.getName(), u.getFirstName()).isPresent()){
            throw new UnknownUserException(u.getFirstName());
        }
        u.setCard(c);
        entityManager.persist(u);
        return true;
    }

    /**
     * Return the user corresponding to the name and the first name
     * @param name
     * @param firstname
     * @return
     * @throws UnknownUserException
     */
    @Override
    public Optional<User> getByNameAndFirstName(String name, String firstname) {
//        for(Map.Entry<User, Card> entry : database.getUsers().entrySet()){
//            if(entry.getKey().getFirstName().equals(firstname) && entry.getKey().getName().equals(name))
//                return Optional.of(entry.getKey());
//        }
//        return Optional.empty();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root =  criteria.from(User.class);
        Predicate restriction = builder.and(builder.equal(root.get("name"), name),builder.equal(root.get("firstName"), firstname));
        criteria.select(root).where(restriction);
        TypedQuery<User> query = entityManager.createQuery(criteria);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException nre){
            return Optional.empty();
        }
    }

    /**
     * Return the user with the idcard in param
     * @param idCard
     * @return
     */
    @Override
    public Optional<User> getByIdCard(String idCard) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root =  criteria.from(User.class);
        //QueryCard
        CriteriaQuery<Card> crit = builder.createQuery(Card.class);
        Root<Card> root2 =  crit.from(Card.class);
        Predicate restriction = builder.equal(root2.get("id"),idCard);
        crit.select(root2).where(restriction);
        TypedQuery<Card> queryCard = entityManager.createQuery(crit);
        Card selectedCard = new Card();
        try {
            selectedCard = queryCard.getSingleResult();
        } catch (NoResultException nre){
            return Optional.empty();
        }
        criteria.select(root).where(builder.equal(root.get("card"), selectedCard));
        TypedQuery<User> queryUser = entityManager.createQuery(criteria);
        try {
            return Optional.of(queryUser.getSingleResult());
        } catch (NoResultException nre){
            return Optional.empty();
        }
    }

}
