package program;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import fileio.Input;
import program.actions.Action;
import program.actions.ActionFactory;
import program.util.Database;

public class Program {
    // Singleton Pattern
    private static Program instance = null;
    private Program() { }

    /**
     * Instantiates program class using Singleton Pattern
     *
     * @return returns unique instance of program class
     */
    public static Program getInstance() {
        if (instance == null) {
            instance = new Program();
        }
        return instance;
    }

    /**
     * Entry point of the program. It runs the actions specified
     * by user
     *
     * @param inputData stores input data from input file
     * @param output stores output of actions
     */
    public void start(final Input inputData, final ArrayNode output) {
        Database data = new Database(inputData);

        // parse actions
        for (ActionsInput action: inputData.getActions()) {
            Action myAction = ActionFactory.createAction(action);
            assert myAction != null;
            myAction.apply(data, output);
        }
    }
}
