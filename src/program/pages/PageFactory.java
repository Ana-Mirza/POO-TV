package program.pages;

import program.util.Database;

public abstract class PageFactory {
    public static Page createPage(String page, Database data) {
        switch (page) {
            case "homepage neautentificat": return new UnloggedHomepage(data);
            case "login": return new Login(data);
            case "register": return new Register(data);
            case "homepage autentificat": return new LoggedHomepage(data);
            case "movies": return new Movies(data);
            case "see details": return new SeeDetails(data);
            case "upgrades": return new Upgrades(data);
            case "logout": return new Logout(data);
        }
        return null;
    }
}
