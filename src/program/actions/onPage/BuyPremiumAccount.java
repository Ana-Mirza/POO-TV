package program.actions.onPage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.ActionsInput;
import program.actions.Action;
import program.pages.*;
import program.util.Database;

import java.util.ArrayList;

public class BuyPremiumAccount extends Feature implements Action {
    private int premiumPrice;
    private boolean hasOutput;
    public BuyPremiumAccount(ActionsInput input) {
        super(input);
        hasOutput = false;
        premiumPrice = 10;
    }

    @Override
    public void visit(LoggedHomepage page, ObjectMapper mapper, ObjectNode node, Database data) {
        node.put("error", "Error");
        node.set("currentMoviesList", mapper.valueToTree(new ArrayList<String>()));
        node.set("currentUser", null);
        hasOutput = true;
    }

    @Override
    public void visit(UnloggedHomepage page, ObjectMapper mapper, ObjectNode node, Database data) {
        node.put("error", "Error");
        node.set("currentMoviesList", mapper.valueToTree(new ArrayList<String>()));
        node.set("currentUser", null);
        hasOutput = true;
    }

    @Override
    public void visit(Login page, ObjectMapper mapper, ObjectNode node, Database data) {
        node.put("error", "Error");
        node.set("currentMoviesList", mapper.valueToTree(new ArrayList<String>()));
        node.set("currentUser", null);
        hasOutput = true;
    }

    @Override
    public void visit(Logout page, ObjectMapper mapper, ObjectNode node, Database data) {
        node.put("error", "Error");
        node.set("currentMoviesList", mapper.valueToTree(new ArrayList<String>()));
        node.set("currentUser", null);
        hasOutput = true;
    }

    @Override
    public void visit(Movies page, ObjectMapper mapper, ObjectNode node, Database data) {
        node.put("error", "Error");
        node.set("currentMoviesList", mapper.valueToTree(new ArrayList<String>()));
        node.set("currentUser", null);
        hasOutput = true;
    }

    @Override
    public void visit(Register page, ObjectMapper mapper, ObjectNode node, Database data) {
        node.put("error", "Error");
        node.set("currentMoviesList", mapper.valueToTree(new ArrayList<String>()));
        node.set("currentUser", null);
        hasOutput = true;
    }

    @Override
    public void visit(Upgrades page, ObjectMapper mapper, ObjectNode node, Database data) {
        // check if user has enough tokens to buy premium account
        int currentTokens = page.getCurrentUser().getTokensCount();
        if (currentTokens < premiumPrice) {
            node.put("error", "Error");
            node.set("currentMoviesList", mapper.valueToTree(new ArrayList<String>()));
            node.set("currentUser", null);
            hasOutput = true;
        }

        // buy premium account
        page.getCurrentUser().setTokensCount(currentTokens - premiumPrice);
        page.getCurrentUser().setPremium(true);
        page.getCurrentUser().getCredentials().setAccountType("premium");
    }

    @Override
    public void visit(SeeDetails page, ObjectMapper mapper, ObjectNode node, Database data) {
        node.put("error", "Error");
        node.set("currentMoviesList", mapper.valueToTree(new ArrayList<String>()));
        node.set("currentUser", null);
        hasOutput = true;
    }

    @Override
    public void apply(Database data, ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        // visit page
        data.getCurrentPage().accept(this, mapper, node, data);
        if (hasOutput) {
            output.add(node);
        }
    }
}
