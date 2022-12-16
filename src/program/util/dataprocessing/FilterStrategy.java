package program.util.dataprocessing;

import program.pages.Page;
import program.util.dependencies.Filters;


public interface FilterStrategy {
    public void filter(Filters input, Page page);
}
