package program.pages;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import program.actions.Action;
import program.util.Database;

public class SeeDetails extends Page {
    public SeeDetails(Database data) {
        super("see details", data.getCurrentUser(),
                data.getCurrentPage().getUserMovies());
        super.getAccesiblePages().add("homepage autentificat");
        super.getAccesiblePages().add("movies");
        super.getAccesiblePages().add("upgrades");
        super.getAccesiblePages().add("logout");
        super.getAccesiblePages().add("see details");
    }

    public void accept(Action action, ObjectMapper mapper, ObjectNode node, Database database) {
        action.visit(this, node, database);
    }
}
