package program.util;

import fileio.Input;
import program.pages.Page;
import program.pages.PageFactory;

import java.util.ArrayList;

public class Database {
    private final ArrayList<User> usersData;
    private final ArrayList<Movie> moviesData;
    private Page currentPage;
    private User currentUser;
    private ArrayList<Movie> userMovies;

    // constructor
    public Database(Input input) {
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
    public ArrayList<User> getUsersData() {
        return usersData;
    }

    public ArrayList<Movie> getMoviesData() {
        return moviesData;
    }
    public Page getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Page currentPage) {
        this.currentPage = currentPage;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public ArrayList<Movie> getUserMovies() {
        return userMovies;
    }

    public void setUserMovies(ArrayList<Movie> userMovies) {
        this.userMovies = new ArrayList<>();
        this.userMovies.addAll(userMovies);
    }
}
