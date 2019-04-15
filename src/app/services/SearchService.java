package app.services;

import app.dao.SearchEngine;

import java.util.List;
import java.util.regex.Pattern;

public class SearchService {

    private SearchEngine se = new SearchEngine();
    private Pattern strToSearch;
    private SearchRequest query;

    public void setQuery(SearchRequest query) {
        this.query = query;
    }

    private List<Object> getResultList() {
        return se.search(query);
    }

}
