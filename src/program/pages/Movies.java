package program.pages;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import program.actions.Action;
import program.util.Database;
import program.util.dataprocessing.FilterStrategy;
import program.util.dependencies.Filters;

public class Movies extends Page {
    public Movies(Database data) {
        super("movies", data.getCurrentUser(), data.getUserMovies());
        super.getAccesiblePages().add("homepage autentificat");
        super.getAccesiblePages().add("see details");
        super.getAccesiblePages().add("logout");
        super.getAccesiblePages().add("movies");
        super.getActionsPermitted().add("search");
        super.getActionsPermitted().add("filter");
    }

    public void accept(Action action, ObjectMapper mapper, ObjectNode node, Database database) {
        action.visit(this, node, database);
    }

    public void filter(FilterStrategy filterStrategy, Filters filter) {
        filterStrategy.filter(filter, this);
    }
}
