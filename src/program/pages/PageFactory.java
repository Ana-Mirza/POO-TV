package program.pages;

import program.util.Database;

public abstract class PageFactory {
    public static Page createPage(String page, Database data) {
        return switch (page) {
            case "homepage neautentificat" -> new UnloggedHomepage();
            case "login" -> new Login();
            case "register" -> new Register();
            case "homepage autentificat" -> new LoggedHomepage(data);
            case "movies" -> new Movies(data);
            case "see details" -> new SeeDetails(data);
            case "upgrades" -> new Upgrades(data);
            case "logout" -> new Logout();
            default -> null;
        };
    }
}
