package program.actions.onPage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.ActionsInput;
import program.actions.Action;
import program.actions.output.OutputError;
import program.pages.LoggedHomepage;
import program.pages.UnloggedHomepage;
import program.pages.Login;
import program.pages.Logout;
import program.pages.Movies;
import program.pages.SeeDetails;
import program.pages.Upgrades;
import program.pages.Register;
import program.util.Database;

public class BuyPremiumAccount extends Feature implements Action {
    private final int premiumPrice;
    private boolean displayOutput;
    public BuyPremiumAccount(ActionsInput input) {
        super(input);
        displayOutput = false;
        premiumPrice = 10;
    }

    @Override
    public void visit(LoggedHomepage page, ObjectNode node) {
        OutputError.set(node);
        displayOutput = true;
    }

    @Override
    public void visit(UnloggedHomepage page, ObjectNode node) {
        OutputError.set(node);
        displayOutput = true;
    }

    @Override
    public void visit(Login page, ObjectNode node) {
        OutputError.set(node);
        displayOutput = true;
    }

    @Override
    public void visit(Logout page, ObjectNode node) {
        OutputError.set(node);
        displayOutput = true;
    }

    @Override
    public void visit(Movies page, ObjectNode node) {
        OutputError.set(node);
        displayOutput = true;
    }

    @Override
    public void visit(Register page, ObjectNode node) {
        OutputError.set(node);
        displayOutput = true;
    }

    @Override
    public void visit(Upgrades page, ObjectNode node) {
        // check if user has enough tokens to buy premium account
        int currentTokens = page.getCurrentUser().getTokensCount();
        if (currentTokens < premiumPrice) {
            OutputError.set(node);
            displayOutput = true;
        }

        // buy premium account
        page.getCurrentUser().setTokensCount(currentTokens - premiumPrice);
        page.getCurrentUser().setPremium(true);
        page.getCurrentUser().getCredentials().setAccountType("premium");
    }

    @Override
    public void visit(SeeDetails page, ObjectNode node) {
        OutputError.set(node);
        displayOutput = true;
    }

    @Override
    public void apply(Database data, ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        // visit page
        data.getCurrentPage().accept(this, node);
        if (displayOutput) {
            output.add(node);
        }
    }
}
