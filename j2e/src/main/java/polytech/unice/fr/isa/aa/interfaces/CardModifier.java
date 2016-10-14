package polytech.unice.fr.isa.aa.interfaces;

import polytech.unice.fr.isa.aa.business.Card;
import polytech.unice.fr.isa.aa.business.Pass;
import polytech.unice.fr.isa.aa.exceptions.AlreadyExistingCard;
import polytech.unice.fr.isa.aa.exceptions.UnknownCardException;

import javax.ejb.Local;
import java.util.Set;

/**
 * Created by lucas on 29/02/16.
 */
@Local
public interface CardModifier {

    /**
     * create the card in the database
     * @param c
     */
    void create(Card c) throws AlreadyExistingCard;

    /**
     * Adds a pass to a card
     * @param c
     * @param p
     * @return
     * @throws UnknownCardException
     */
    boolean add(Card c, Pass p) throws UnknownCardException;

    /**
     * Removes a pass from a card
     * @param c
     * @param p
     * @return
     * @throws UnknownCardException
     */
    boolean remove(Card c, Pass p) throws UnknownCardException;

    /**
     * Gets the pass associated to the wanted card
     * @param c
     * @return
     * @throws UnknownCardException
     */
    Pass contents(Card c) throws UnknownCardException;
}
