package controller.Search;

import model.Search;

public class SearchListener {

    private Search search;
    private SearchController searchController = new SearchController();

    public SearchListener(Search search){
        searchController.sendDataToServer(search);
        this.search = searchController.getUsersFound();
    }

    public Search getResult(){
        return search;
    }
}
