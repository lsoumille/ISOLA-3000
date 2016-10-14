package cli.commands;

import api.IsolaPublicAPI;
import cli.framework.Command;
import stubs.purchaseobjects.PaymentException_Exception;
import stubs.purchaseobjects.PriceNotFoundException_Exception;
import stubs.purchaseobjects.UnknownCardException_Exception;
import stubs.purchaseobjects.UnknownPass_Exception;

import java.util.List;

/**
 * @author Lucas MARTINEZ
 * @version 23/03/16
 */
public class AddPass extends Command<IsolaPublicAPI> {

    private String idCard, age, type, zone;

    @Override
    public String identifier() { return "addpass"; }

    @Override
    public void execute() {
        try {
            system.purchaser.addPassForCard(idCard, age, type, zone);
            System.out.println("The pass (" + age + ", " + type + ", " + zone +
                    ") has been added to the card #" + idCard);
        } catch (PaymentException_Exception | UnknownCardException_Exception e) {
            System.out.println("Error during the purchase of a pass for the card #"
                    + idCard);
            e.printStackTrace();
        } catch (UnknownPass_Exception e) {
            e.printStackTrace();
        } catch (PriceNotFoundException_Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void load(List<String> args) {
        idCard = args.get(0);
        age = args.get(1);
        type = args.get(2);
        zone = args.get(3);
    }

    @Override
    public String describe() {
        return "Add a pass on a card (addpass CARD_ID AGE_PASS TYPE_PASS ZONE_PASS)";
    }
}
