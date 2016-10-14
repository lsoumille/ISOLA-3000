package polytech.unice.fr.isa.aa.components;

import polytech.unice.fr.isa.aa.business.*;
import polytech.unice.fr.isa.aa.exceptions.*;
import polytech.unice.fr.isa.aa.interceptors.PurchaseCounter;
import polytech.unice.fr.isa.aa.interfaces.CardModifier;
import polytech.unice.fr.isa.aa.interfaces.Catalog;
import polytech.unice.fr.isa.aa.interfaces.Payment;
import polytech.unice.fr.isa.aa.interfaces.UserModifier;
import polytech.unice.fr.isa.aa.utils.BankAPI;

import javax.annotation.PostConstruct;
import javax.ejb.AccessTimeout;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.interceptor.Interceptors;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by lucas on 15/03/16.
 */

@Stateless
public class PurchaseBean implements Payment {

    private BankAPI bank;

    @EJB
    private CardModifier updateCard;

    @EJB
    private UserModifier updateUser;

    @EJB
    private Catalog catalog;

    /**
     * Cash the card for the user
     * @param user
     * @param card
     * @return
     * @throws PaymentException
     * @throws UnknownUserException
     */
    @Override
    @Interceptors({PurchaseCounter.class})
    public boolean payCardForUser(User user, Card card) throws PaymentException, UnknownUserException {
        card.setPrice(catalog.getPriceCard());
        doPayment(user.getCreditCard(), catalog.getPriceCard());
        Transaction trans = new Transaction(user, card);
        trans.setAmount(catalog.getPriceCard());
        user.addTransaction(trans);
        return true;
    }

    @Override
    @Interceptors({PurchaseCounter.class})
    public boolean payCard(String numCreditCard, Card card) throws PaymentException {
        card.setPrice(catalog.getPriceCard());
        doPayment(numCreditCard, catalog.getPriceCard());
        return true;
    }

    /**
     * Cash the pass for the user
     * @param user
     * @param pass
     * @return
     * @throws PaymentException
     */
    @Override
    @Interceptors({PurchaseCounter.class})
    public boolean payPassForUser(User user, AbstractPass pass) throws PaymentException, PriceNotFoundException {
        double price = catalog.getPriceForPass(pass);
        doPayment(user.getCreditCard(), price);
        Transaction trans = new Transaction(user, pass);
        trans.setAmount(catalog.getPriceForPass(pass));
        user.addTransaction(trans);
        return true;
    }

    @Override
    @Interceptors({PurchaseCounter.class})
    public boolean payPass(String numCreditCard, AbstractPass pass) throws PaymentException, UnknownCardException, PriceNotFoundException {
        double price = catalog.getPriceForPass(pass);
        doPayment(numCreditCard, price);
        return true;
    }

    @Override
    @Interceptors({PurchaseCounter.class})
    public boolean paySubFidelicime(User user) throws PaymentException, UnknownCardException, PriceNotFoundException {
        float price = catalog.getPriceSubFidelicime();
        doPayment(user.getCreditCard(), price);
        Transaction trans = new Transaction(user, new FideliCimePass());
        trans.setAmount(price);
        user.addTransaction(trans);
        return true;
    }

    /**
     * call the bank API
     * @param amount
     * @throws PaymentException
     */
    private void doPayment(String numCard, double amount) throws PaymentException{
        boolean status = false;
        try {
            status = bank.performPayment(numCard, amount);
        } catch (ErrorPaymentService errorPaymentService) {
            throw new PaymentException("Error during the transaction");
        }
        if(! status){
            throw new PaymentException("Credit card isn't allowed");
        }
    }

    @Override
    public void useBankReference(BankAPI bank) { this.bank = bank; }

    @PostConstruct
    private void initializeRestPartnership() throws IOException {
        Properties prop = new Properties();
        prop.load(this.getClass().getResourceAsStream("/bank.properties"));
        bank = new BankAPI();
    }
}
