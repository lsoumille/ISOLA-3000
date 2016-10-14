package cli.commands;

import api.IsolaPublicAPI;
import cli.framework.Command;
import stubs.usermodifier.AlreadyExistingUserException_Exception;

import java.util.List;

/**
 * @author Lucas MARTINEZ
 * @version 19/03/16
 */
public class Register extends Command<IsolaPublicAPI> {

    private String userName, userFirstName, creditCardNumber, mail;
    private int age;

    @Override
    public String identifier() { return "register"; }

    @Override
    public void execute() {
        try {
            system.userModifier.register(userName, userFirstName, age, creditCardNumber, mail);
        } catch (AlreadyExistingUserException_Exception e) {
            e.printStackTrace();
        }
        String str = "User " + userFirstName + " " + userName + ", aged " + age + " has been registered.\n" +
                "His credit card number is " + creditCardNumber + ".";
        System.out.println(str);
    }

    @Override
    public void load(List<String> args) {
        userName = args.get(0);
        userFirstName = args.get(1);
        age = Integer.parseInt(args.get(2));
        creditCardNumber = args.get(3);
    }

    @Override
    public String describe() {
        return "Register a user to the database (register NAME FIRSTNAME AGE CREDIT_CARD_NUMBER)";
    }
}
