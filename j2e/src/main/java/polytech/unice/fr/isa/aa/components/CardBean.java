package polytech.unice.fr.isa.aa.components;

import polytech.unice.fr.isa.aa.business.Card;
import polytech.unice.fr.isa.aa.business.Gate;
import polytech.unice.fr.isa.aa.business.Pass;
import polytech.unice.fr.isa.aa.business.User;
import polytech.unice.fr.isa.aa.business.enums.ZonePass;
import polytech.unice.fr.isa.aa.exceptions.AlreadyExistingCard;
import polytech.unice.fr.isa.aa.exceptions.UnknownCardException;
import polytech.unice.fr.isa.aa.interfaces.CardFinder;
import polytech.unice.fr.isa.aa.interfaces.CardModifier;
import polytech.unice.fr.isa.aa.interfaces.Catalog;
import polytech.unice.fr.isa.aa.interfaces.GateFinder;
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
import java.util.*;

/**
 * @author Nicolas HORY
 * @version 29/02/16.
 */

@Stateless(name="card")
public class CardBean implements CardModifier, CardFinder {
    @EJB
    private Database database;

    @EJB
    private GateFinder finder;

    @EJB
    private Catalog catalog;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Card c) throws AlreadyExistingCard{
        if(findById(c.getId()).isPresent()){
            throw new AlreadyExistingCard(c.getId());
        }
        c.setPrice(catalog.getPriceCard());
        entityManager.persist(c);

    }

    /**
     * Adds a pass to a card
     * @param c
     * @param p
     * @return
     * @throws UnknownCardException
     */
    public boolean add(Card c, Pass p) throws UnknownCardException {
        if (p.isFidelicime()) {
            p.setGateList(finder.getGatesByZone(ZonePass.ALL.toString()));
        }
        if(! findById(c.getId()).isPresent()){
            throw new UnknownCardException();
        }
        c.setPass(p);
        entityManager.persist(c);
        return true;
    }

    /**
     * Removes a pass from a card
     * @param c
     * @param p
     * @return
     * @throws UnknownCardException
     */
    public boolean remove(Card c, Pass p) throws UnknownCardException{
        if(! findById(c.getId()).isPresent()){
            throw new UnknownCardException();
        }
        c.setPass(null);
        entityManager.merge(c);
        return true;
    }

    /**
     * Gets the pass associated to the wanted card
     * @param c
     * @return
     * @throws UnknownCardException
     */
    public Pass contents(Card c) throws UnknownCardException{
        if (database.getCards().containsKey(c)) {
            return database.getCards().get(c); // Récupère le pass associé a la carte
        } else { // Si cette carte n'est pas dans la database
            throw new UnknownCardException();
        }
    }

    /** CardFinder Implementation **/


    /**
     * Gets the card associated to the cardId in parameter
     * @param cardId
     * @return
     */
    @Override
    public Optional<Card> findById(String cardId){
        for (Map.Entry<Card, Date> entry : database.getCacheCard().entrySet()) {
            if (entry.getKey().equals(cardId)){
                return Optional.of(entry.getKey());
            }
        }
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Card> crit = builder.createQuery(Card.class);
        Root<Card> root2 =  crit.from(Card.class);
        Predicate restriction = builder.equal(root2.get("id"), cardId);
        crit.select(root2).where(restriction);
        TypedQuery<Card> queryCard = entityManager.createQuery(crit);
        try {
            Card c = queryCard.getSingleResult();
            database.addCardToCache(c);
            return Optional.of(queryCard.getSingleResult());
        } catch (NoResultException nre){
            return Optional.empty();
        }
    }
}
