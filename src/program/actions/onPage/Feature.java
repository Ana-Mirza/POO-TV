package program.actions.onPage;

import fileio.ActionsInput;

public abstract class Feature {
    private String type;
    private String page;
//    private Credentials credentials;
//    private String startsWith;
//    private Filters filters;
//    private String count;

    public Feature(ActionsInput input) {
        type = input.getType();
        page = input.getPage();

//        credentials = new Credentials(input.getCredentials());
//        startsWith = input.getStartsWith();
//        filters = new Filters(input.getFilters());
//        count = input.getCount();
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

    //    // Builder Pattern
//    public static class Builder {
//        private String type;
//        private String page;
//        private Credentials credentials;
//        private String startsWith;
//        private Filters filters;
//        private String count;
//
//        public Builder(String type, String page) {
//            this.type = type;
//            this.page = page;
//        }
//
//        public Builder credentials(Credentials credentials) {
//            this.credentials = credentials;
//            return this;
//        }
//
//        public Builder startsWith(String startsWith) {
//            this.startsWith = startsWith;
//            return this;
//        }
//
//        public Builder filters(Filters filters) {
//            this.filters = filters;
//            return this;
//        }
//
//        public Builder count(String count) {
//            this.count = count;
//            return this;
//        }
//    }
//
//    private Feature (Builder builder) {
//        this.count = builder.count;
//        this.filters = builder.filters;
//        this.credentials = builder.credentials;
//        this.startsWith = builder.startsWith;
//    }
}
