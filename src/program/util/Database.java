package program.util;

import fileio.Input;
import program.pages.Page;
import program.pages.PageFactory;

import java.util.ArrayList;

/**
 * Database of the program. Contains information about
 * the users in the system and all the movies.
 */
public class Database {
    private final ArrayList<User> usersData;
    private final ArrayList<Movie> moviesData;
    private Page currentPage;
    private User currentUser;
    private ArrayList<Movie> userMovies;

    // constructor
    public Database(final Input input) {
        // add database of users
        usersData = new ArrayList<>();
        input.getUsers().forEach((user) ->
                usersData.add(new User(user)));
        // add database of movies
        moviesData = new ArrayList<>();
        input.getMovies().forEach((movie) ->
                moviesData.add(new Movie(movie)));
        currentPage = PageFactory.createPage("homepage neautentificat", this);
    }

    // getters and setters
    public final ArrayList<User> getUsersData() {
        return usersData;
    }

    public final ArrayList<Movie> getMoviesData() {
        return moviesData;
    }
    public final Page getCurrentPage() {
        return currentPage;
    }

    public final void setCurrentPage(final Page currentPage) {
        this.currentPage = currentPage;
    }

    public final User getCurrentUser() {
        return currentUser;
    }

    public final void setCurrentUser(final User currentUser) {
        this.currentUser = currentUser;
    }

    public final ArrayList<Movie> getUserMovies() {
        return userMovies;
    }

    /**
     * Initializes movies available to user
     * @param userMovies contains list of movies available
     */
    public final void setUserMovies(final ArrayList<Movie> userMovies) {
        this.userMovies = new ArrayList<>();
        this.userMovies.addAll(userMovies);
    }
}
