package in.devco.movielist.View;

import java.util.List;

import in.devco.movielist.Model.Item;

public interface IListView {
    void update(List<Item> items);
    void updateFailed();
}
