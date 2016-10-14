package polytech.unice.fr.isa.aa.webservice;

import polytech.unice.fr.isa.aa.business.Card;
import polytech.unice.fr.isa.aa.business.Gate;
import polytech.unice.fr.isa.aa.interfaces.CardFinder;
import polytech.unice.fr.isa.aa.interfaces.GateFinder;
import polytech.unice.fr.isa.aa.interfaces.ValidatePass;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

/**
 * @author Lucas MARTINEZ
 * @version 19/03/16
 */

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/aa")
@Stateless(name = "ValidatePassWS")
public class ValidatePassWebServiceImpl implements ValidatePassWebService{

    @EJB private ValidatePass validator;
    @EJB private CardFinder cardFinder;
    @EJB private GateFinder gateFinder;

    @Override
    public boolean validatePass(String cardId, int gateId){
        Card card;
        Gate gate;
        if (cardFinder.findById(cardId).isPresent() && gateFinder.findById(gateId).isPresent()){
            card = cardFinder.findById(cardId).get();
            gate = gateFinder.findById(gateId).get();
            String result = validator.validate(card, gate);
            return (result.equals("OK"));
        }

        return false;
    }
}
