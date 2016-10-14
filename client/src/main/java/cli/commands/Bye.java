package cli.commands;

import api.IsolaPublicAPI;
import cli.framework.Command;

/**
 * @author Lucas MARTINEZ
 * @version 14/03/16
 */
public class Bye extends Command<IsolaPublicAPI> {
    @Override
    public String identifier() { return "bye"; }

    @Override
    public void execute() { }

    @Override
    public String describe() {
        return "Exit Isola3000";
    }

    @Override
    public boolean shouldContinue() { return false; }
}
