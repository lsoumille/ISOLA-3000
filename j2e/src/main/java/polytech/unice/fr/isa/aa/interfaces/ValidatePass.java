package polytech.unice.fr.isa.aa.interfaces;

import polytech.unice.fr.isa.aa.business.Card;
import polytech.unice.fr.isa.aa.business.Gate;

import javax.ejb.Local;

/**
 * Created by lucas on 29/02/16.
 */
@Local
public interface ValidatePass {

    String validate(Card c, Gate g);
}
