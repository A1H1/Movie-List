package in.devco.movielist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements Callback<List<Item>> {
    private static final String TAG = "MainActivity";
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.main_activity_lv);

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

            listView.setAdapter(new ItemAdapter(MainActivity.this, items));
        }
    }

    @Override
    public void onFailure(@NonNull Call<List<Item>> call, @NonNull Throwable t) {
        Log.e(TAG, t.getMessage());
    }
}
