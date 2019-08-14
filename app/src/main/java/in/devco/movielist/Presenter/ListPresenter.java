package in.devco.movielist.Presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import in.devco.movielist.API;
import in.devco.movielist.Config;
import in.devco.movielist.Model.Item;
import in.devco.movielist.View.IListView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListPresenter implements Callback<List<Item>>, IListPresenter {
    private IListView iListView;
    public static final String TAG = "Retrofit";

    public ListPresenter(IListView iListView) {
        this.iListView = iListView;
    }

    @Override
    public void fetchData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API apiParser = retrofit.create(API.class);
        Call<List<Item>> call = apiParser.getItems();
        call.enqueue(this);
    }

    @Override
    public void onResponse(@NonNull Call<List<Item>> call, @NonNull Response<List<Item>> response) {
        if (!response.isSuccessful() || response.body() == null)
            Log.e(TAG, "Code: " + response.code());
        else{
            List<Item> items = response.body();
            iListView.update(items);
        }
    }

    @Override
    public void onFailure(@NonNull Call<List<Item>> call, @NonNull Throwable t) {
        iListView.updateFailed();
        Log.e(TAG, t.getMessage());
    }
}
