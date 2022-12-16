package program.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fileio.UsersInput;
import program.util.dependencies.Credentials;

import java.util.ArrayList;

public final class User {
    private Credentials credentials;

    @JsonIgnore
    private boolean premium;
    private int tokensCount;
    private int numFreePremiumMovies;
    private ArrayList<Movie> purchasedMovies;
    private ArrayList<Movie> watchedMovies;
    private ArrayList<Movie> likedMovies;
    private ArrayList<Movie> ratedMovies;



    // constructor
    public User() { }
    public User(final UsersInput user) {
        credentials = new Credentials(user.getCredentials());
        premium = this.getAccount(credentials);
        tokensCount = 0;
        numFreePremiumMovies = 15;
        purchasedMovies = new ArrayList<>();
        watchedMovies = new ArrayList<>();
        likedMovies = new ArrayList<>();
        ratedMovies = new ArrayList<>();
    }
    public User(final Credentials credentials) {
        this.credentials = credentials;
        premium = this.getAccount(credentials);
        tokensCount = 0;
        numFreePremiumMovies = 15;
        purchasedMovies = new ArrayList<>();
        watchedMovies = new ArrayList<>();
        likedMovies = new ArrayList<>();
        ratedMovies = new ArrayList<>();
    }

    // getters and setters
    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(final Credentials credentials) {
        this.credentials = credentials;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(final boolean premium) {
        this.premium = premium;
    }

    public int getTokensCount() {
        return tokensCount;
    }

    public void setTokensCount(final int tokensCount) {
        this.tokensCount = tokensCount;
    }

    public int getNumFreePremiumMovies() {
        return numFreePremiumMovies;
    }

    public ArrayList<Movie> getPurchasedMovies() {
        return purchasedMovies;
    }

    public void setPurchasedMovies(final ArrayList<Movie> purchasedMovies) {
        this.purchasedMovies = purchasedMovies;
    }

    public ArrayList<Movie> getWatchedMovies() {
        return watchedMovies;
    }

    public void setWatchedMovies(final ArrayList<Movie> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }

    public ArrayList<Movie> getLikedMovies() {
        return likedMovies;
    }

    public void setLikedMovies(final ArrayList<Movie> likedMovies) {
        this.likedMovies = likedMovies;
    }

    public ArrayList<Movie> getRatedMovies() {
        return ratedMovies;
    }

    public void setRatedMovies(final ArrayList<Movie> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }

    // get type of account
    private boolean getAccount(final Credentials credentials) {
        String premium = "premium";
        return premium.equals(credentials.getAccountType());
    }

    public void setNumFreePremiumMovies(final int numFreePremiumMovies) {
        this.numFreePremiumMovies = numFreePremiumMovies;
    }
}
