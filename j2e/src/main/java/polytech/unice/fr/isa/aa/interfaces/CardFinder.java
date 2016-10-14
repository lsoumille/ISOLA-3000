package polytech.unice.fr.isa.aa.interfaces;

import polytech.unice.fr.isa.aa.business.Card;

import javax.ejb.Local;
import java.util.Optional;

/**
 * @author Lucas MARTINEZ
 * @version 19/03/16
 */

@Local
public interface CardFinder {

    Optional<Card> findById(String cardId);
}
