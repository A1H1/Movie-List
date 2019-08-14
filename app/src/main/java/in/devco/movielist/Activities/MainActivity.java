package in.devco.movielist.Activities;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.List;

import in.devco.movielist.Model.Item;
import in.devco.movielist.Adapter.ItemAdapter;
import in.devco.movielist.Presenter.IListPresenter;
import in.devco.movielist.Presenter.ListPresenter;
import in.devco.movielist.R;
import in.devco.movielist.View.IListView;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, IListView {
    private ListView listView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private IListPresenter listPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = findViewById(R.id.main_activity_srl);
        listView = findViewById(R.id.main_activity_lv);
        swipeRefreshLayout.setOnRefreshListener(this); //Refresh Listener

        listPresenter = new ListPresenter(this);
        listPresenter.fetchData();
    }

    @Override
    public void onRefresh() {
        listPresenter.fetchData();
    }

    @Override
    public void update(List<Item> items) {
        listView.setAdapter(new ItemAdapter(MainActivity.this, items));
        swipeRefreshLayout.setRefreshing(false);
    }
}
