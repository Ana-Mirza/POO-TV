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
import program.pages.Logout;
import program.pages.Login;
import program.pages.Register;
import program.pages.Movies;
import program.pages.SeeDetails;
import program.pages.Upgrades;
import program.util.Database;

import java.util.ArrayList;

public class Search extends Feature implements Action {
    private final String startsWith;

    // constructor
    public Search(ActionsInput input) {
        super(input);
        startsWith = input.getStartsWith();
    }

    @Override
    public void visit(LoggedHomepage page, ObjectMapper mapper, ObjectNode node, Database database) {
        OutputError.set(node);
    }

    @Override
    public void visit(UnloggedHomepage page, ObjectMapper mapper, ObjectNode node, Database database) {
        OutputError.set(node);
    }

    @Override
    public void visit(Login page, ObjectMapper mapper, ObjectNode node, Database database) {
        OutputError.set(node);
    }

    @Override
    public void visit(Logout page, ObjectMapper mapper, ObjectNode node, Database database) {
        OutputError.set(node);
    }

    @Override
    public void visit(Movies page, ObjectMapper mapper, ObjectNode node, Database database) {
        // set user movies
        page.setUserMovies(new ArrayList<>(database.getUserMovies()));
        page.getUserMovies().removeIf((movie) -> !movie.getName().startsWith(startsWith));

        // save output
        StandardOutput.set(node, page);
    }

    @Override
    public void visit(Register page, ObjectMapper mapper, ObjectNode node, Database database) {
        OutputError.set(node);
    }

    @Override
    public void visit(Upgrades page, ObjectMapper mapper, ObjectNode node, Database database) {
        OutputError.set(node);
    }

    @Override
    public void visit(SeeDetails page, ObjectMapper mapper, ObjectNode node, Database database) {
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
