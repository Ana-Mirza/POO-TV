package fileio;

public class UsersInput {
    private CredentialsInput credentials;

    public UsersInput() {
    }

    public CredentialsInput getCredentials() {
        return credentials;
    }

    public void setCredentials(final CredentialsInput credentials) {
        this.credentials = credentials;
    }

    @Override
    public String toString() {
        return "UsersInput{" +
                "credentials=" + credentials +
                '}';
    }
}
