package cli.commands;

import api.IsolaPublicAPI;
import cli.framework.Command;

import java.util.List;

/**
 * @author Lucas MARTINEZ
 * @version 19/03/16
 */
public class Validate extends Command<IsolaPublicAPI> {

    private String cardId;
    private int gateId;

    @Override
    public String identifier() { return "validate"; }

    @Override
    public void execute() {
        boolean result = system.validator.validatePass(cardId, gateId);
        String str = result ? "Pass has been validated." : "Pass has not been validated.";
        System.out.println(str);
    }

    @Override
    public void load(List<String> args) {
        cardId = args.get(0);
        gateId = Integer.parseInt(args.get(1));
    }

    @Override
    public String describe() {
        return "Validate a pass on a card to a gate (validate CARD_ID GATE_ID)";
    }

}
