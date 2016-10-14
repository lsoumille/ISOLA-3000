package cli.framework;

import java.util.List;

/**
 * @author Sébastien MOSSER, Lucas MARTINEZ
 * @version 14/03/16
 */
abstract public class Command<T> {
    abstract public String identifier();
    abstract public void execute() throws Exception;
    abstract public String describe();

    public boolean shouldContinue() { return true; }  // default implementation
    public void load(List<String> args) {  }          // default implementation


    protected T system;

    public void withSystem(T system)                    { this.system = system;   }

    public boolean process(List<String> args) throws Exception {
        try { load(args); }
        catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
        execute();
        return shouldContinue();
    }
}
