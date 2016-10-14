package polytech.unice.fr.isa.aa.components;

import polytech.unice.fr.isa.aa.business.*;
import polytech.unice.fr.isa.aa.business.enums.TypePass;
import polytech.unice.fr.isa.aa.business.enums.ZonePass;
import polytech.unice.fr.isa.aa.interceptors.GateCounter;
import polytech.unice.fr.isa.aa.interceptors.PurchaseCounter;
import polytech.unice.fr.isa.aa.interfaces.ValidatePass;
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
import java.time.LocalDate;
import java.util.*;
import javax.ejb.Timeout;
import javax.interceptor.Interceptors;
import java.util.Date;

/**
 * @author Nicolas HORY
 * @version 29/02/16.
 */

@Stateless(name="validatepass")
public class ValidatePassBean implements ValidatePass {
    @EJB
    private Database database;

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Checks if the pass can be used for the gate
     * @param pass
     * @param gate
     * @return
     */
    public String checkGatesForPass(Pass pass, Gate gate) {
        for (Gate g : pass.getGateList()) {
            if (g.equals(gate)) {
                return "OK";
            }
        }
        return "KO";
    }

    /**
     * Manage validation for an activated pass, checking timeout and accessibility
     * @param c
     * @param canAccess
     * @return
     */
    public String manageActivatedPass(Card c, String canAccess) {
        if (database.getTimeoutCards().containsKey(c)) { // If the card already validated once
            if (canAccess.equals("OK")) { // If the pass can validate for this gate
                Date dateWithEndedTimeout = database.getTimeoutCards().get(c); // Get last validation date
                long timeDate = dateWithEndedTimeout.getTime() + 60000; // Add three minutes to this date
                if (timeDate <= new Date().getTime()) { // If current time is more than three minutes after the last validation
                    database.getTimeoutCards().put(c, new Date()); // Add the new date in the map
                    return "OK"; // Validate the passage
                } else {
                    return "KO"; // Refuses the passage because timeout didn't end
                }
            } else {
                return "KO"; // Pass can't validate at this gate
            }
        } else { // If the pass is not in timeout map
            if (canAccess.equals("OK")) { // If the pass can validate this gate
                database.getTimeoutCards().put(c, new Date()); // Add the new date in the map
            }
            return canAccess; // Return the validation string
        }
    }

    /**
     * Validates the passing for a gate with a card
     * @param c
     * @param g
     * @return
     */
    @Interceptors({GateCounter.class})
    public String validate(Card c, Gate g) {
        database.updateDate(); //TODO
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        Pass pass = c.getPass();

        if(pass == null)
            return "KO";


        if (pass.isFidelicime()) {
            if (!pass.getActivated()) { // Unactivated fidelicimePass
                float price; // Price to add to the amount
                CriteriaQuery<AbstractPass> criteriaPrice = builder.createQuery(AbstractPass.class);
                Root<AbstractPass> rootPrice =  criteriaPrice.from(AbstractPass.class);
                Predicate restrictionPrice = builder.and(
                        builder.equal(rootPrice.get("zone"), ZonePass.ALL),
                        builder.equal(rootPrice.get("type"), TypePass.ONE_DAY),
                        builder.equal(rootPrice.get("age"), pass.getAge()),
                        builder.notEqual(rootPrice.get("price"), 0));
                criteriaPrice.select(rootPrice).where(restrictionPrice);
                TypedQuery<AbstractPass> queryPrice = entityManager.createQuery(criteriaPrice);
                List<AbstractPass> abstractPassList = new ArrayList<>();
                try {
                    price = queryPrice.getSingleResult().getPrice();
                } catch (NoResultException nre){
                    return "KO";
                }
                ((FideliCimePass)pass).addToPay(price); // Adds the price of the pass to the amount to pay of the pass
                ((FideliCimePass)pass).addConsumedDay(); // Adds a day to the pass
                database.getPasses().put(pass, (java.sql.Date.valueOf(LocalDate.now().plusDays(1)))); // Sets the end date of the pass
                pass.setActivated(true); // Activate the pass
            } else {
                return "OK";
            }
        }
        String acceptablePass = checkGatesForPass(pass, g);
        if(pass.getActivated()) { // If the pass is activated
            return manageActivatedPass(c, acceptablePass);
        }
        // If the pass on the card is not activated
        if(acceptablePass.equals("OK")) { // If the pass can validate at this gate
            if (pass.getNbDays() > 0) { // If the card still has days on it
                pass.setActivated(true); // Activate the pass
                pass.setNbDays(pass.getNbDays() - 1); // Decrement number of days left
                database.getPasses().put(pass, (java.sql.Date.valueOf(LocalDate.now().plusDays(1)))); // Sets the end date of the pass
                database.getTimeoutCards().put(c, new Date()); // Add current date in timeout map
                return "OK";
            }
        }
        return "KO";
    }
}
