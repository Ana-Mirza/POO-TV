package program.actions.onPage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.ActionsInput;
import fileio.Input;
import program.actions.Action;
import program.pages.*;
import program.util.Database;

import java.util.ArrayList;

public class BuyTokens extends Feature implements Action {
    private int count;
    private boolean hasOutput;
    public BuyTokens(ActionsInput input) {
        super(input);
        hasOutput = false;
        count = Integer.parseInt(input.getCount());
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
        int totalBalance = Integer.parseInt(
                page.getCurrentUser().getCredentials().getBalance());

        // check balance
        if (totalBalance < count) {
            node.put("error", "Error");
            node.set("currentMoviesList", mapper.valueToTree(new ArrayList<String>()));
            node.set("currentUser", null);
            hasOutput = true;
            return;
        }
        // update balance
        totalBalance -= count;
        page.getCurrentUser().getCredentials().setBalance(String.valueOf(totalBalance));

        // update tokens
        int currentTokens = page.getCurrentUser().getTokensCount();
        page.getCurrentUser().setTokensCount(currentTokens + count);
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
