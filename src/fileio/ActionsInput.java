package fileio;

public final class ActionsInput {
    private String type;
    private String page;
    private String feature;
    private CredentialsInput credentials;
    private String movie;
    private String startsWith;
    private FiltersInput filters;
    private String count;
    private int rate;

    public ActionsInput() {
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {

        this.type = type;
    }

    public String getPage() {
        return page;
    }

    public void setPage(final String page) {
        this.page = page;
    }

    public CredentialsInput getCredentials() {
        return credentials;
    }

    public void setCredentials(final CredentialsInput credentials) {
        this.credentials = credentials;
    }

    public String getStartsWith() {
        return startsWith;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(final String movie) {
        this.movie = movie;
    }

    public FiltersInput getFilters() {
        return filters;
    }

    public void setFilters(final FiltersInput filters) {
        this.filters = filters;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(final String feature) {
        this.feature = feature;
    }

    public String getCount() {
        return count;
    }

    public void setCount(final String count) {
        this.count = count;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(final int rate) {
        this.rate = rate;
    }
}
