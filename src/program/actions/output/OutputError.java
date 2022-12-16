package program.actions.output;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;

public class OutputError extends OutputStrategy {
    public static void set(ObjectNode node) {
        ObjectMapper mapper = new ObjectMapper();

        // set error output
        node.put("error", "Error");
        node.set("currentMoviesList", mapper.valueToTree(new ArrayList<String>()));
        node.set("currentUser", null);
    }
}
