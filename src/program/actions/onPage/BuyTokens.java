package program.actions.onPage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.ActionsInput;
import program.actions.Action;
import program.actions.output.OutputError;
import program.pages.LoggedHomepage;
import program.pages.UnloggedHomepage;
import program.pages.Logout;
import program.pages.Login;
import program.pages.Register;
import program.pages.Movies;
import program.pages.SeeDetails;
import program.pages.Upgrades;
import program.util.Database;

public class BuyTokens extends Feature implements Action {
    private final int count;
    private boolean displayOutput;
    public BuyTokens(ActionsInput input) {
        super(input);
        displayOutput = false;
        count = Integer.parseInt(input.getCount());
    }

    @Override
    public void visit(LoggedHomepage page, ObjectMapper mapper, ObjectNode node, Database data) {
        OutputError.set(node);
        displayOutput = true;
    }

    @Override
    public void visit(UnloggedHomepage page, ObjectMapper mapper, ObjectNode node, Database data) {
        OutputError.set(node);
        displayOutput = true;
    }

    @Override
    public void visit(Login page, ObjectMapper mapper, ObjectNode node, Database data) {
        OutputError.set(node);
        displayOutput = true;
    }

    @Override
    public void visit(Logout page, ObjectMapper mapper, ObjectNode node, Database data) {
        OutputError.set(node);
        displayOutput = true;
    }

    @Override
    public void visit(Movies page, ObjectMapper mapper, ObjectNode node, Database data) {
        OutputError.set(node);
        displayOutput = true;
    }

    @Override
    public void visit(Register page, ObjectMapper mapper, ObjectNode node, Database data) {
        OutputError.set(node);
        displayOutput = true;
    }

    @Override
    public void visit(Upgrades page, ObjectMapper mapper, ObjectNode node, Database data) {
        int totalBalance = Integer.parseInt(
                page.getCurrentUser().getCredentials().getBalance());

        // check balance
        if (totalBalance < count) {
            OutputError.set(node);
            displayOutput = true;
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
        OutputError.set(node);
        displayOutput = true;
    }

    @Override
    public void apply(Database data, ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        // visit page
        data.getCurrentPage().accept(this, mapper, node, data);
        if (displayOutput) {
            output.add(node);
        }
    }
}
