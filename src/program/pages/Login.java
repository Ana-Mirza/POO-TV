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

    public void accept(Action action, ObjectNode node) {
        action.visit(this, node);
    }
}
