package program.util;

import fileio.Input;
import fileio.MoviesInput;
import fileio.UsersInput;
import program.Program;
import program.pages.Page;
import program.pages.PageFactory;
import program.pages.UnloggedHomepage;

import java.util.ArrayList;

public class Database {
    private ArrayList<User> usersData;
    private ArrayList<Movie> moviesData;
    private Page currentPage;
    private User currentUser;
    private ArrayList<Movie> userMovies;
    private static Database instance = null;

    // constructor
    public Database(Input input) {
        usersData = new ArrayList<>();
        for (UsersInput user: input.getUsers()) {
            usersData.add(new User(user));
        }
        moviesData = new ArrayList<>();
        for (MoviesInput movie: input.getMovies()) {
            moviesData.add(new Movie(movie));
        }
        currentPage = PageFactory.createPage("homepage neautentificat", this);
    }
    //private Database() { }

    /**
     * Instantiates program class using Singleton Pattern
     *
     * @return returns unique instance of program class
     */
//    public static Database getInstance() {
//        if (instance == null) {
//            instance = new Database();
//        }
//        return instance;
//    }

    // getters and setters
    public ArrayList<User> getUsersData() {
        return usersData;
    }

    public void setUsersData(ArrayList<User> usersData) {
        this.usersData = usersData;
    }

    public ArrayList<Movie> getMoviesData() {
        return moviesData;
    }

    public void setMoviesData(ArrayList<Movie> moviesData) {
        this.moviesData = moviesData;
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
        for (Movie movie: userMovies) {
            this.userMovies.add(movie);
        }
    }
}
