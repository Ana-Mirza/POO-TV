package program.actions.onPage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.ActionsInput;
import program.actions.Action;
import program.pages.*;
import program.util.Database;
import program.util.Movie;
import program.util.User;
import program.util.dependencies.Contains;
import program.util.dependencies.Filters;
import program.util.dependencies.Sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Filter extends Feature implements Action {
    private Filters filters;

    // constructor
    public Filter(ActionsInput input) {
        super(input);
        filters = new Filters(input.getFilters());
    }

    @Override
    public void visit(LoggedHomepage page, ObjectMapper mapper, ObjectNode node, Database data) {
        node.put("error", "Error");
        node.set("currentMoviesList", mapper.valueToTree(new ArrayList<String>()));
        node.set("currentUser", null);
    }

    @Override
    public void visit(UnloggedHomepage page, ObjectMapper mapper, ObjectNode node, Database data) {
        node.put("error", "Error");
        node.set("currentMoviesList", mapper.valueToTree(new ArrayList<String>()));
        node.set("currentUser", null);
    }

    @Override
    public void visit(Login page, ObjectMapper mapper, ObjectNode node, Database data) {
        node.put("error", "Error");
        node.set("currentMoviesList", mapper.valueToTree(new ArrayList<String>()));
        node.set("currentUser", null);
    }

    @Override
    public void visit(Logout page, ObjectMapper mapper, ObjectNode node, Database data) {
        node.put("error", "Error");
        node.set("currentMoviesList", mapper.valueToTree(new ArrayList<String>()));
        node.set("currentUser", null);
    }

    @Override
    public void visit(Movies page, ObjectMapper mapper, ObjectNode node, Database database) {
        //Database database = Database.getInstance();

        // set user movies
        if (page.getUserMovies().size() == 0) {
            page.setUserMovies(new ArrayList<>(database.getUserMovies()));
        }

        // filter by input
        Contains.filter(page, filters.getContains());
        // sort by rating
        Sort.movies(page, filters.getSort());

        // set output node
        node.set("error", null);
        node.set("currentMoviesList", mapper.valueToTree(page.getUserMovies()));
        node.set("currentUser", mapper.valueToTree(page.getCurrentUser()));
    }

    @Override
    public void visit(Register page, ObjectMapper mapper, ObjectNode node, Database data) {
        node.put("error", "Error");
        node.set("currentMoviesList", mapper.valueToTree(new ArrayList<String>()));
        node.set("currentUser", null);
    }

    @Override
    public void visit(Upgrades page, ObjectMapper mapper, ObjectNode node, Database data) {
        node.put("error", "Error");
        node.set("currentMoviesList", mapper.valueToTree(new ArrayList<String>()));
        node.set("currentUser", null);
    }

    @Override
    public void visit(SeeDetails page, ObjectMapper mapper, ObjectNode node, Database data) {
        node.put("error", "Error");
        node.set("currentMoviesList", mapper.valueToTree(new ArrayList<String>()));
        node.set("currentUser", null);
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
