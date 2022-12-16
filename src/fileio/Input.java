package fileio;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public final class Input{

    private ArrayList<UsersInput> users;

    private ArrayList<MoviesInput> movies;

    private ArrayList<ActionsInput> actions = new ArrayList<>();

    public Input() {
    }

    public ArrayList<UsersInput> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<UsersInput> users) {
        this.users = users;
    }

    public ArrayList<MoviesInput> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<MoviesInput> movies) {
        this.movies = movies;
    }

    public ArrayList<ActionsInput> getActions() {
        return actions;
    }

    public void setActions(ArrayList<ActionsInput> actions) {
        this.actions = actions;
    }

    @Override
    public String toString() {
        return "Input{" +
                "users=" + users +
                ", movies=" + movies +
                ", actions=" + actions +
                '}';
    }
}
