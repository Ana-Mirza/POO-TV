package program.actions;

import com.fasterxml.jackson.databind.ObjectMapper;
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
    public void visit(LoggedHomepage page, ObjectMapper mapper, ObjectNode node, Database data);
    public void visit(UnloggedHomepage page, ObjectMapper mapper, ObjectNode node, Database data);
    public void visit(Login page, ObjectMapper mapper, ObjectNode node, Database data);
    public void visit(Logout page, ObjectMapper mapper, ObjectNode node, Database data);
    public void visit(Movies page, ObjectMapper mapper, ObjectNode node, Database data);
    public void visit(Register page, ObjectMapper mapper, ObjectNode node, Database data);
    public void visit(Upgrades page, ObjectMapper mapper, ObjectNode node, Database data);
    public void visit(SeeDetails page, ObjectMapper mapper, ObjectNode node, Database data);
    public void apply(Database data, ArrayNode output);
}
