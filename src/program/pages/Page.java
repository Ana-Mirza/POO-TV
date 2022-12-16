package program.pages;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import program.actions.Action;
import program.util.Database;
import program.util.Movie;
import program.util.User;

import java.util.ArrayList;

public abstract class Page {
    private String name;
    private User currentUser;
    private ArrayList<Movie> userMovies;
    private ArrayList<String> accesiblePages;
    private ArrayList<String> actionsPermitted;

    // constructors
    public Page(String name) {
        this.name = name;
        currentUser = new User();
        userMovies = new ArrayList<>();
        accesiblePages = new ArrayList<>();
        actionsPermitted = new ArrayList<>();
    }

    public Page(String name, User currentUser) {
        this.name = name;
        this.currentUser = currentUser;
        userMovies = new ArrayList<>();
        accesiblePages = new ArrayList<>();
        actionsPermitted = new ArrayList<>();
    }

    public Page(String name, User currentUser, ArrayList<Movie> movies) {
        this.name = name;
        this.currentUser = currentUser;
        userMovies = new ArrayList<>(movies);
        accesiblePages = new ArrayList<>();
        actionsPermitted = new ArrayList<>();
    }

    // getters and setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public User getCurrentUser() {
        return currentUser;
    }
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
    public ArrayList<String> getAccesiblePages() {
        return accesiblePages;
    }
    public ArrayList<String> getActionsPermitted() {
        return actionsPermitted;
    }
    public void setUserMovies(ArrayList<Movie> userMovies) {
        this.userMovies = userMovies;
    }
    public ArrayList<Movie> getUserMovies() {
        return userMovies;
    }


    // method to accept action visitors
    public void accept(Action action, ObjectMapper mapper, ObjectNode node, Database data) { }
}
