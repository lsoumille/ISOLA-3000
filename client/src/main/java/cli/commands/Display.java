package cli.commands;

import api.IsolaPublicAPI;
import cli.framework.Command;
import stubs.purchaseobjects.AbstractPass;

import java.util.List;

/**
 * @author Lucas MARTINEZ
 * @version 24/03/16
 */
public class Display extends Command<IsolaPublicAPI> {

    @Override
    public String identifier() { return "display"; }

    @Override
    public void execute() {
        List<AbstractPass> passes = system.purchaser.listAllPass();
        for (AbstractPass p : passes){
            System.out.println("{" +  p.getType() + ", " +  p.getZone() + ", " + p.getAge() + "}\n");
        }
    }

    @Override
    public void load(List<String> args) {}

    @Override
    public String describe() {
        return "Display all passes (display)";
    }
}

