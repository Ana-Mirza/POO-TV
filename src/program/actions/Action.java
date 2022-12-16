package program.actions;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import program.pages.LoggedHomepage;
import program.pages.UnloggedHomepage;
import program.pages.Login;
import program.pages.Logout;
import program.pages.Movies;
import program.pages.Register;
import program.pages.Upgrades;
import program.pages.SeeDetails;
import program.util.Database;


public interface Action {
    /**
     * Method visits Logged Homepage and executes action
     *
     * @param page page to be visited
     * @param node stores ouptut of action
     */
    void visit(LoggedHomepage page, ObjectNode node);

    /**
     *
     * Method visits Unlogged Homepage and executes action
     *
     * @param page page to be visited
     * @param node stores output of action
     */
    void visit(UnloggedHomepage page, ObjectNode node);

    /**
     * Method visits Login page and executes action
     *
     * @param page page to be visited
     * @param node stores output of action
     */
    void visit(Login page, ObjectNode node);

    /**
     * Method visits Logout page an dexecutes action
     *
     * @param page page to be visited
     * @param node stores output of action
     */
    void visit(Logout page, ObjectNode node);

    /**
     * Method visits Movies page and executes action
     *
     * @param page page to ve visited
     * @param node stores output of action
     */
    void visit(Movies page, ObjectNode node);

    /**
     * Method visits Register page and executes action
     *
     * @param page page to be visited
     * @param node stores output of action
     */
    void visit(Register page, ObjectNode node);

    /**
     * Method visits Upgrades page and executes action
     *
     * @param page page to be visited
     * @param node stores output of action
     */
    void visit(Upgrades page, ObjectNode node);
    /**
     * Method visits See Details page and executes action
     *
     * @param page page to be visited
     * @param node stores output of action
     */
    void visit(SeeDetails page, ObjectNode node);

    /**
     * Method calls visit method for specific page of current action
     * and stores output to be displayed
     *
     * @param data stores current status of system
     * @param output stores output to be displayed
     */
    void apply(Database data, ArrayNode output);
}
