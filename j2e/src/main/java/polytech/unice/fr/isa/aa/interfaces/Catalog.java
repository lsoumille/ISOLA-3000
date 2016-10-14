package polytech.unice.fr.isa.aa.interfaces;

import polytech.unice.fr.isa.aa.business.AbstractPass;
import polytech.unice.fr.isa.aa.business.enums.AgePass;
import polytech.unice.fr.isa.aa.business.enums.TypePass;
import polytech.unice.fr.isa.aa.business.enums.ZonePass;
import polytech.unice.fr.isa.aa.exceptions.PriceNotFoundException;

import javax.ejb.Local;
import java.util.List;

/**
 * @author Nicolas HORY
 * @version 22/03/16.
 */
@Local
public interface Catalog {
    /**
     * Creates a list containing every combination possible of ZonePass, AgePass and TypePass
     * @return listAllPasses
     */
    List<AbstractPass> getAllTypesOfPass();

    /**
     * Gets a price for an AsbtractPass
     * @param pass
     * @return price
     */
    float getPriceForPass(AbstractPass pass) throws PriceNotFoundException;

    /**
     * Gets the price of Cime Card
     * @return price
     */
    float getPriceCard();

    float getPriceSubFidelicime();
}
