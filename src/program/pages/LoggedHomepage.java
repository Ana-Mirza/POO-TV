package program.pages;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import program.actions.Action;
import program.util.Database;

public class LoggedHomepage extends Page {
    // constructor
    public LoggedHomepage(Database data) {
        super("homepage autentificat", data.getCurrentUser());
        super.getAccesiblePages().add("movies");
        super.getAccesiblePages().add("upgrades");
        super.getAccesiblePages().add("logout");
        super.getAccesiblePages().add("homepage autentificat");
    }

    public void accept(Action action, ObjectNode node) {
        action.visit(this, node);
    }
}
