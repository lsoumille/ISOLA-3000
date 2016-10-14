package cli.framework;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Sébastien MOSSER, modifications by Lucas MARTINEZ
 * @version 14/03/16
 */
public class Shell<T> {
    protected T system;
    protected String invite;
    private Map<String, Class<? extends Command<T>>> commands = new HashMap<>();
    private String HELP = "help";

    public final void run() {
        System.out.println("Interactive shell started. Type " + HELP + " for help.\n");
        Scanner scanner = new Scanner(System.in);
        boolean shouldContinue = true;
        while(shouldContinue) {
            System.out.flush();
            System.out.print(invite + " > ");
            String keyword = scanner.next();

            List<String> args;
            if (scanner.hasNextLine()) {
                args = Arrays.asList(scanner.nextLine().split(" "))
                        .stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());
            } else { args = new LinkedList<>(); }

            if (keyword.equals(HELP)) {
                help();
            } else {
                try {
                    shouldContinue = processCommand(keyword, args);
                } catch (IllegalArgumentException iae) {
                    System.err.println("Illegal arguments for command "+keyword+": " + args);
                } catch (Exception e) {
                    System.err.println("Exception caught while processing command:\n  " + e);
                }
            }
        }
    }

    private boolean processCommand(String keyword, List<String> args) throws Exception {
        if (!commands.containsKey(keyword)) {
            System.out.println("Unknown command: " + keyword);
            return true;
        }

        Class<? extends Command<T>> command = commands.get(keyword);
        try {
            Command inst = command.newInstance();
            inst.withSystem(system);
            return inst.process(args);

        } catch(InstantiationException|IllegalAccessException e) {
            System.err.println("Unable to instantiate command " + command.toString());
            e.printStackTrace();
            return true;
        }
    }

    @SafeVarargs
    protected final void register(Class<? extends Command<T>>... commands) {
        for(Class<? extends Command<T>> c: commands) {
            registerCommand(c);
        }
    }

    private void registerCommand(Class<? extends Command<T>> command) {
        try {
            String identifier = command.newInstance().identifier();
            if (identifier.contains(" "))
                throw new IllegalArgumentException("Identifier cannot contain whitespace");
            commands.put(identifier, command);
        } catch(InstantiationException|IllegalAccessException|IllegalArgumentException e) {
            System.err.println("Unable to register command " + command.toString());
            e.printStackTrace();
        }
    }

    private void help(){
        for (Class<? extends Command<T>> entry : commands.values()){
            try {
                Command c = entry.newInstance();
                System.out.println("  - " + c.identifier() + ": " + c.describe());
            } catch (InstantiationException | IllegalAccessException e) {
                System.err.println("Unable to print help for registered command " + entry);
                e.printStackTrace();
            }
        }
    }


}
