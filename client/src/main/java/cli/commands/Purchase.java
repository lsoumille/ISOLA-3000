package cli.commands;

import api.IsolaPublicAPI;
import cli.framework.Command;
import stubs.purchaseobjects.AlreadyExistingCard_Exception;
import stubs.purchaseobjects.PaymentException_Exception;
import stubs.purchaseobjects.UnknownUserException_Exception;

import java.util.List;

/**
 * @author Lucas MARTINEZ
 * @version 23/03/16
 */
public class Purchase extends Command<IsolaPublicAPI> {

    private String userName, userFirstName;

    @Override
    public String identifier() { return "purchase"; }

    @Override
    public void execute() {
        String idCard;
        try {
            idCard = system.purchaser.addCardForUser(userName, userFirstName);
            System.out.println("Card #" + idCard + " has been purchased for the user "
                    + userFirstName + " " + userName);
        } catch (PaymentException_Exception | UnknownUserException_Exception e) {
            System.out.println("Error during the purchase of the card for "
                    + userFirstName + " " + userName);
            e.printStackTrace();
        } catch (AlreadyExistingCard_Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void load(List<String> args) {
        userName = args.get(0);
        userFirstName = args.get(1);
    }

    @Override
    public String describe() {
        return "Purchase a card for a user (purchase NAME FIRSTNAME)";
    }
}
