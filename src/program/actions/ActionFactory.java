package program.actions;

import fileio.ActionsInput;
import program.actions.changePage.ChangePage;
import program.actions.onPage.Registers;
import program.actions.onPage.Logins;
import program.actions.onPage.Search;
import program.actions.onPage.Purchase;
import program.actions.onPage.Filter;
import program.actions.onPage.Watch;
import program.actions.onPage.Like;
import program.actions.onPage.Rate;
import program.actions.onPage.BuyPremiumAccount;
import program.actions.onPage.BuyTokens;

public abstract class ActionFactory {
    /**
     * @param actions contains actions to be instanced
     * @return instance of specific action
     */
    public static Action createAction(final ActionsInput actions) {
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
                    default: return null;
                }
        }
        return null;
    }
}
