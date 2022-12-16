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
        return switch (actions.getType()) {
            case "change page" -> new ChangePage(actions);
            case "on page" -> switch (actions.getFeature()) {
                case "register" -> new Registers(actions);
                case "login" -> new Logins(actions);
                case "search" -> new Search(actions);
                case "filter" -> new Filter(actions);
                case "purchase" -> new Purchase(actions);
                case "watch" -> new Watch(actions);
                case "like" -> new Like(actions);
                case "rate" -> new Rate(actions);
                case "buy premium account" -> new BuyPremiumAccount(actions);
                case "buy tokens" -> new BuyTokens(actions);
                default -> null;
            };
            default -> null;
        };
    }
}
