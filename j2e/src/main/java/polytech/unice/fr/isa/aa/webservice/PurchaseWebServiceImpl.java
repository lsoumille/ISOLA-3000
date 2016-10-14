package polytech.unice.fr.isa.aa.webservice;

import polytech.unice.fr.isa.aa.business.*;
import polytech.unice.fr.isa.aa.business.enums.AgePass;
import polytech.unice.fr.isa.aa.exceptions.*;
import polytech.unice.fr.isa.aa.interfaces.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import java.util.List;
import java.util.Optional;

/**
 * Created by lucas on 19/03/16.
 */
@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/aa")
@Stateless(name = "PurchaseWS")
public class PurchaseWebServiceImpl implements PurchaseWebService {

    @EJB
    private Payment purchase;
    @EJB
    private CardModifier cardModifier;
    @EJB
    private UserFinder userFinder;
    @EJB
    private UserModifier userModifier;
    @EJB
    private CardFinder cardFinder;
    @EJB
    private GateFinder gateFinder;
    @EJB
    private Catalog catalog;


    /**
     * Method which perform payment and add card for the user
     * @param name
     * @param firstname
     * @return card id
     * @throws PaymentException
     * @throws UnknownUserException
     */
    @Override
    public String addCardForUser(String name, String firstname) throws PaymentException, UnknownUserException,AlreadyExistingCard {
        Card newCard = new Card();
        User user;
        if(! (userFinder.getByNameAndFirstName(name, firstname).isPresent())) {
            throw new UnknownUserException(name);
        }
        user = userFinder.getByNameAndFirstName(name, firstname).get();
        //process paiement
        purchase.payCardForUser(user, newCard);
        //ajout de la carte a la db
        cardModifier.create(newCard);
        //link carte et utilisateur
        try {
            userModifier.addCardForUser(user, newCard);
        } catch (UnknownCardException e) {
            System.err.println("error unknown card");//ne devrait jamais arriver vu que l'on vient de creer la carte
        }
        return newCard.getId();
    }

    /**
     * Method which perform the payment for a pass and load it in the card
     * @param idCard
     * @param age
     * @param type
     * @param zone
     * @throws PaymentException
     * @throws UnknownCardException
     * @throws UnknownPass
     */
    @Override
    public void addPassForCard(String idCard, String age, String type, String zone) throws PaymentException, UnknownCardException, UnknownPass, PriceNotFoundException {
        Optional<Card>  card = cardFinder.findById(idCard);
        if(! card.isPresent())
            throw new UnknownCardException();
        Optional<User> user = userFinder.getByIdCard(idCard);
        AbstractPass pass = new AbstractPass(type,zone,age);
        purchase.payPassForUser(user.get(), pass);
        Pass passWithGates = new Pass();
        passWithGates.setAge(pass.getAge());
        passWithGates.setType(pass.getType());
        passWithGates.setZone(pass.getZone());
        passWithGates.setGateList(gateFinder.getGatesByZone(zone));
        passWithGates.setNbDays(pass.getType().getNbDays());
        cardModifier.add(card.get(), passWithGates);
    }

    @Override
    public List<AbstractPass> listAllPass() {
        return catalog.getAllTypesOfPass();
    }

    @Override
    public String getCard(String numBank) throws PaymentException, UnknownUserException, AlreadyExistingCard {
        Card newCard = new Card();
        purchase.payCard(numBank, newCard);
        cardModifier.create(newCard);
        return newCard.getId();
    }

    @Override
    public void addPassForCardWithCredential(String idCard, String age, String type, String zone, String numBank) throws PaymentException, UnknownCardException, UnknownPass, PriceNotFoundException {
        Optional<Card>  card = cardFinder.findById(idCard);
        if(! card.isPresent())
            throw new UnknownCardException();
        AbstractPass pass = new AbstractPass(type,zone,age);
        purchase.payPass(numBank, pass);
        Pass passWithGates = new Pass();
        passWithGates.setAge(pass.getAge());
        passWithGates.setType(pass.getType());
        passWithGates.setZone(pass.getZone());
        for(Gate g : gateFinder.getGatesByZone(zone)){
            System.out.println(g.toString());
        }
        passWithGates.setGateList(gateFinder.getGatesByZone(zone));
        passWithGates.setNbDays(pass.getType().getNbDays());
        cardModifier.add(card.get(), passWithGates);
    }

    @Override
    public void subscribeFidelicime(String idCard) throws PaymentException, UnknownCardException, UnknownPass, PriceNotFoundException {
        Optional<Card> card = cardFinder.findById(idCard);
        if (!card.isPresent())
            throw new UnknownCardException();
        Optional<User> user = userFinder.getByIdCard(idCard);
        AgePass ap = AgePass.ADULT;
        for(AgePass a : AgePass.values())
            if(a.toString().equals(user.get().getAge()))
                ap = a;
        FideliCimePass fPass = new FideliCimePass(ap);
        purchase.paySubFidelicime(user.get());
        cardModifier.add(card.get(), fPass);
    }
}
