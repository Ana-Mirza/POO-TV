package program.pages;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import program.actions.Action;
import program.util.Database;

import java.util.ArrayList;
import java.util.Arrays;

public class Register extends Page {
    // constructor
    public Register(Database data) {
        super("register");
        super.getActionsPermitted().add("register");
        super.getAccesiblePages().add("register");
    }

    public void accept(Action action, ObjectMapper mapper, ObjectNode node, Database database) {
        action.visit(this, mapper, node, database);
    }
}
