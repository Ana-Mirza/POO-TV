package program.actions.output;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import program.pages.Page;

public class StandardOutput extends OutputStrategy {
    public static void set(ObjectNode node, Page page) {
        ObjectMapper mapper = new ObjectMapper();

        node.set("error", null);
        node.set("currentMoviesList", mapper.valueToTree(page.getUserMovies()));
        node.set("currentUser", mapper.valueToTree(page.getCurrentUser()));
    }
}
