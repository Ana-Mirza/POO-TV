package program.actions;

import fileio.ActionsInput;
import program.actions.changePage.ChangePage;
import program.actions.onPage.*;
import program.pages.Upgrades;

public abstract class ActionFactory {
    public static Action createAction(ActionsInput actions) {
        switch (actions.getType()) {
            case "change page": return new ChangePage(actions);
            case "on page":
                switch (actions.getFeature()) {
                    case "register": return new Registers(actions);
                    case "login": return new Logins(actions);
                    case "search": return new Search(actions);
                    case "filter": return new Filter(actions);
                    case "purchase": return new Purchase(actions);
                    case "watch": return new Watch(actions);
                    case "like": return new Like(actions);
                    case "rate": return new Rate(actions);
                    case "buy premium account": return new BuyPremiumAccount(actions);
                    case "buy tokens": return new BuyTokens(actions);
                }
        }
        return null;
    }
}
