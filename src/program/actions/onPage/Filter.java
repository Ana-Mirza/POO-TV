package program.actions.onPage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.ActionsInput;
import program.actions.Action;
import program.actions.output.OutputError;
import program.actions.output.StandardOutput;
import program.pages.LoggedHomepage;
import program.pages.UnloggedHomepage;
import program.pages.Login;
import program.pages.Logout;
import program.pages.Register;
import program.pages.Movies;
import program.pages.SeeDetails;
import program.pages.Upgrades;
import program.util.Database;
import program.util.dataprocessing.ContentStrategy;
import program.util.dataprocessing.SortStrategy;
import program.util.dependencies.Filters;

import java.util.ArrayList;

public class Filter extends Feature implements Action {
    private final Filters filters;

    // constructor
    public Filter(ActionsInput input) {
        super(input);
        filters = new Filters(input.getFilters());
    }

    @Override
    public void visit(LoggedHomepage page, ObjectNode node, Database data) {
        OutputError.set(node);
    }

    @Override
    public void visit(UnloggedHomepage page, ObjectNode node, Database data) {
        OutputError.set(node);
    }

    @Override
    public void visit(Login page, ObjectNode node, Database data) {
        OutputError.set(node);
    }

    @Override
    public void visit(Logout page, ObjectNode node, Database data) {
        OutputError.set(node);
    }

    @Override
    public void visit(Movies page, ObjectNode node, Database database) {
        // set user movies
        if (page.getUserMovies().size() == 0) {
            page.setUserMovies(new ArrayList<>(database.getUserMovies()));
        }

        // filter by input
        page.filter(new ContentStrategy(), filters);
        // sort by rating
        page.filter(new SortStrategy(), filters);

        // set output node
        StandardOutput.set(node, page);
    }

    @Override
    public void visit(Register page, ObjectNode node, Database data) {
        OutputError.set(node);
    }

    @Override
    public void visit(Upgrades page, ObjectNode node, Database data) {
        OutputError.set(node);
    }

    @Override
    public void visit(SeeDetails page, ObjectNode node, Database data) {
        OutputError.set(node);
    }

    @Override
    public void apply(Database data, ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        // visit page
        data.getCurrentPage().accept(this, mapper, node, data);
        output.add(node);
    }
}
