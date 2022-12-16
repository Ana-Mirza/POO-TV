package fileio;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public final class CredentialsInput {

    private String name;

    private String password;

    private String accountType;

    private String country;

    private String balance;

    public CredentialsInput() {
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(final String accountType) {
        this.accountType = accountType;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(final String balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Credentials{"
                + "name: " + this.name + "\n"
                + "password: " + this.password + "\n"
                + "account type: " + this.accountType + "\n"
                + "country: " + this.country + "\n"
                + "balance: " + this.balance + "}" + "\n";
    }
}