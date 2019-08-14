package in.devco.movielist;

import java.util.List;

import in.devco.movielist.Model.Item;
import retrofit2.Call;
import retrofit2.http.GET;

public interface API {
    @GET("5d4d3c3f330000d43f3376b0")
    Call<List<Item>> getItems();
}
