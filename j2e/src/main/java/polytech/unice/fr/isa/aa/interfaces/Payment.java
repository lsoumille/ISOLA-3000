package polytech.unice.fr.isa.aa.interfaces;

import polytech.unice.fr.isa.aa.business.AbstractPass;
import polytech.unice.fr.isa.aa.business.Card;
import polytech.unice.fr.isa.aa.business.Pass;
import polytech.unice.fr.isa.aa.business.User;
import polytech.unice.fr.isa.aa.exceptions.PaymentException;
import polytech.unice.fr.isa.aa.exceptions.PriceNotFoundException;
import polytech.unice.fr.isa.aa.exceptions.UnknownCardException;
import polytech.unice.fr.isa.aa.exceptions.UnknownUserException;
import polytech.unice.fr.isa.aa.utils.BankAPI;

import javax.ejb.Local;
/**
 * Created by lucas on 15/03/16.
 */

@Local
public interface Payment {

    boolean payCardForUser(User user, Card card) throws PaymentException, UnknownUserException;

    boolean payCard(String numCreditCard, Card card) throws PaymentException;

    boolean payPassForUser(User user, AbstractPass pass) throws PaymentException, UnknownCardException, PriceNotFoundException;

    boolean payPass(String numCreditCard, AbstractPass pass) throws PaymentException, UnknownCardException, PriceNotFoundException;

    boolean paySubFidelicime(User user) throws PaymentException, UnknownCardException, PriceNotFoundException;

    void useBankReference(BankAPI bank);
}
