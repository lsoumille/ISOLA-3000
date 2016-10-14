import api.IsolaPublicAPI;
import cli.commands.*;
import cli.framework.Shell;

/**
 * @author Lucas MARTINEZ
 * @version 19/03/16
 */
public class Main extends Shell<IsolaPublicAPI> {

    public Main(String host, String port) {

        this.system  = new IsolaPublicAPI(host, port);
        this.invite  = "Isola3000";

        // Registering the command available for the user
        register(
                // Getting out of here
                Bye.class,
                Validate.class,
                Register.class,
                Purchase.class,
                AddPass.class,
                Display.class
        );
    }

    public static void main(String[] args) {
        String host = ( args.length == 0 ? "localhost" : args[0] );
        String port = ( args.length < 2  ? "8080"      : args[1] );
        System.out.println("\n\nStarting Isola3000 by aa");
        System.out.println("  - Remote server: " + host);
        System.out.println("  - Port number:   " + port);
        Main main = new Main(host, port);
        main.run();
        System.out.println("Exiting Isola3000 by aa\n\n");
    }

}
