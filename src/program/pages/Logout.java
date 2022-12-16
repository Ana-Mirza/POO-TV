package program.pages;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import program.actions.Action;
import program.util.Database;

public class Logout extends Page {
    // constructor
    public Logout() {
        super("logout");
    }

    public void accept(Action action, ObjectMapper mapper, ObjectNode node, Database database) {
        action.visit(this, node, database);
    }
}
