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
     * @param data stores curret status of system
     */
    void visit(LoggedHomepage page, ObjectNode node, Database data);

    /**
     *
     * Method visits Unlogged Homepage and executes action
     *
     * @param page page to be visited
     * @param node stores output of action
     * @param data stores current status of system
     */
    void visit(UnloggedHomepage page, ObjectNode node, Database data);

    /**
     * Method visits Login page and executes action
     *
     * @param page page to be visited
     * @param node stores output of action
     * @param data stores current status of system
     */
    void visit(Login page, ObjectNode node, Database data);

    /**
     * Method visits Logout page an dexecutes action
     *
     * @param page page to be visited
     * @param node stores output of action
     * @param data stores current status of system
     */
    void visit(Logout page, ObjectNode node, Database data);

    /**
     * Method visits Movies page and executes action
     *
     * @param page page to ve visited
     * @param node stores output of action
     * @param data stores current status of system
     */
    void visit(Movies page, ObjectNode node, Database data);

    /**
     * Method visits Register page and executes action
     *
     * @param page page to be visited
     * @param node stores output of action
     * @param data stores current status of system
     */
    void visit(Register page, ObjectNode node, Database data);

    /**
     * Method visits Upgrades page and executes action
     *
     * @param page page to be visited
     * @param node stores output of action
     * @param data stores current status of system
     */
    void visit(Upgrades page, ObjectNode node, Database data);
    /**
     * Method visits See Details page and executes action
     *
     * @param page page to be visited
     * @param node stores output of action
     * @param data stores current status of system
     */
    void visit(SeeDetails page, ObjectNode node, Database data);

    /**
     * Method calls visit method for specific page of current action
     * and stores output to be displayed
     *
     * @param data stores current status of system
     * @param output stores output to be displayed
     */
    void apply(Database data, ArrayNode output);
}
