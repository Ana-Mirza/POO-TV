package program;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.ActionsInput;
import fileio.Input;
import fileio.MoviesInput;
import fileio.UsersInput;
import program.actions.Action;
import program.actions.ActionFactory;
import program.pages.PageFactory;
import program.util.Database;
import program.util.Movie;
import program.util.User;

import javax.xml.crypto.Data;
import java.util.ArrayList;

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
     * Entry point of the program. It runs the actions specified in input
     *
     * @param inputData stores input data from input file
     * @param output stores output of actions
     */
    public void start(final Input inputData, final ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        Database data = new Database(inputData);

        for (ActionsInput action: inputData.getActions()) {
            Action myAction = ActionFactory.createAction(action);
            myAction.apply(data, output);
        }
    }
}
