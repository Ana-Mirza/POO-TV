package program.util.dependencies;

import fileio.FiltersInput;

public class Filters {
    private Sort sort;
    private Contains contains;

    public Filters(FiltersInput input) {
        if (input.getSort() != null) {
            sort = new Sort(input.getSort());
        }
        if (input.getContains() != null) {
            contains = new Contains(input.getContains());
        }
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public Contains getContains() {
        return contains;
    }

    public void setContains(Contains contains) {
        this.contains = contains;
    }

    public void sort() {

    }
}
