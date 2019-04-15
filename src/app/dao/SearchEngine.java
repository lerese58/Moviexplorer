package app.dao;

import app.services.SearchRequest;

import java.util.ArrayList;
import java.util.List;

public class SearchEngine {
    //todo: use custom ResultList or List<Result> instead of List<Object>
    public List<Object> search(SearchRequest query) {
        //realisation of db-search
        return new ArrayList<>();
    }

}
