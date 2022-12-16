package program.util.dependencies;

import fileio.SortInput;

public class Sort {
    private String rating;
    private String duration;

    // constructor
    public Sort(SortInput input) {
        rating = input.getRating();
        duration = input.getDuration();
    }

    // getters and setters

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Sort{" +
                "rating='" + rating + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
