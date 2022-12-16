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
import program.util.Movie;

import java.util.ArrayList;

public final class Like extends Feature implements Action {
    private ObjectNode node;
    public Like(final ActionsInput input) {
        super(input);
    }

    /**
     * Method saves an error as output. Like action is not
     * permitted on this page.
     * @param page stores Logged Homepage visited
     */
    @Override
    public void visit(final LoggedHomepage page) {
        OutputError.set(node);
    }

    /**
     * Method saves an error as output. Like action is not
     * permitted on this page.
     * @param page stores Unlogged Homepage visited
     */
    @Override
    public void visit(final UnloggedHomepage page) {
        OutputError.set(node);
    }

    /**
     * Method saves an error as output. Like action is not
     * permitted on this page.
     * @param page stores Login page visited
     */
    @Override
    public void visit(final Login page) {
        OutputError.set(node);
    }

    /**
     * Method saves an error as output. Like action is not
     * permitted on this page.
     * @param page stores Logout page visited
     */
    @Override
    public void visit(final Logout page) {
        OutputError.set(node);
    }

    /**
     * Method saves an error as output. Like action is not
     * permitted on this page.
     * @param page stores Movies page visited
     */
    @Override
    public void visit(final Movies page) {
        OutputError.set(node);
    }

    /**
     * Method saves an error as output. Like action is not
     * permitted on this page.
     * @param page stores Register page visited
     */
    @Override
    public void visit(final Register page) {
        OutputError.set(node);
    }

    /**
     * Method saves an error as output. Like action is not
     * permitted on this page.
     * @param page stores Upgrades page visited
     */
    @Override
    public void visit(final Upgrades page) {
        OutputError.set(node);
    }

    /**
     * Method adds liked movie in liked movies list of user,
     * if watched previously
     * @param page stores See Details page to be visited
     */
    @Override
    public void visit(final SeeDetails page) {
        // check if watched movie
        Movie movie = page.getUserMovies().get(0);
        if (!watchedMovie(movie, page.getCurrentUser().getWatchedMovies())) {
            OutputError.set(node);
            return;
        }

        // like movie
        page.getCurrentUser().getLikedMovies().add(movie);
        movie.setNumLikes(movie.getNumLikes() + 1);
        // save output
        StandardOutput.set(node, page);
    }

    /**
     * Method calls visit method of current page to apply
     * action and stores output.
     * @param data stores current status of system
     * @param output stores output to be displayed
     */
    @Override
    public void apply(final Database data, final ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        node = mapper.createObjectNode();
        // visit page
        data.getCurrentPage().accept(this);
        output.add(node);
    }

    /**
     * Method checks if movie was watched previously
     * @param movie to be checked
     * @param movies list of movies watched by user
     * @return
     */
    private boolean watchedMovie(final Movie movie, final ArrayList<Movie> movies) {
        return movies.contains(movie);
    }
}
