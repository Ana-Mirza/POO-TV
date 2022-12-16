package program.actions.onPage;

import fileio.ActionsInput;

public abstract class Feature {
    private String type;
    private String page;

    public Feature(ActionsInput input) {
        type = input.getType();
        page = input.getPage();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
