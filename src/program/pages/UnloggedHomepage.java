package program.pages;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.ActionsInput;
import program.actions.Action;
import program.util.Database;
import program.util.User;

import java.util.ArrayList;
import java.util.Arrays;

public class UnloggedHomepage extends Page{
    // constructor
    public UnloggedHomepage(Database data) {
        super("homepage neautentificat");
        super.getAccesiblePages().add("login");
        super.getAccesiblePages().add("register");
        super.getAccesiblePages().add("homepage neautentificat");
    }

    public void accept(Action action, ObjectMapper mapper, ObjectNode node, Database database) {
        action.visit(this, mapper, node, database);
    }
}
