package program.pages;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import program.actions.Action;
import program.util.Database;

public class UnloggedHomepage extends Page{
    // constructor
    public UnloggedHomepage() {
        super("homepage neautentificat");
        super.getAccesiblePages().add("login");
        super.getAccesiblePages().add("register");
        super.getAccesiblePages().add("homepage neautentificat");
    }

    public void accept(Action action, ObjectMapper mapper, ObjectNode node, Database database) {
        action.visit(this, node, database);
    }
}
