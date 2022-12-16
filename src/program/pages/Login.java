package program.pages;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import program.actions.Action;
import program.util.Database;


public class Login extends Page {
    public Login() {
        super("login");
        super.getActionsPermitted().add("login");
        super.getAccesiblePages().add("login");
    }

    public void accept(Action action, ObjectMapper mapper, ObjectNode node, Database database) {
        action.visit(this, mapper, node, database);
    }
}
