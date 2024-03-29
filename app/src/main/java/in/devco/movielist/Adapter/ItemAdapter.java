package in.devco.movielist.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import in.devco.movielist.Model.Item;
import in.devco.movielist.R;

public class ItemAdapter  extends ArrayAdapter<Item> {
    private Context context;

    public ItemAdapter(Context context, List<Item> items) {
        super(context, R.layout.item_view, items);
        this.context=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Item item = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_view, parent, false);
            viewHolder.title = convertView.findViewById(R.id.item_view_title);
            viewHolder.rating = convertView.findViewById(R.id.item_view_rating);
            viewHolder.genre = convertView.findViewById(R.id.item_view_genre);
            viewHolder.releaseYear = convertView.findViewById(R.id.item_view_year);
            viewHolder.image = convertView.findViewById(R.id.item_view_image);

            convertView.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) convertView.getTag();

        if (item != null) {
            viewHolder.title.setText(item.getTitle());

            String rating = "Rating: " + item.getRating(); //Rating
            viewHolder.rating.setText(rating);

            StringBuilder genre = new StringBuilder();
            List<String> genres = item.getGenre();
            for (int j = 0; j<genres.size(); j++) {     //Generating genres
                genre.append(genres.get(j));
                if (j < genres.size() - 1)
                    genre.append(", ");
            }
            viewHolder.genre.setText(genre);

            viewHolder.releaseYear.setText(String.valueOf(item.getReleaseYear()));

            Glide.with(context).load(item.getImage()) //Setting Image using Glide
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .error(R.drawable.ic_placeholder_wallpaper)
                    .placeholder(R.drawable.ic_placeholder_wallpaper)
                    .into(viewHolder.image);
        }
        return convertView;
    }

    private static class ViewHolder {
        TextView title;
        TextView rating;
        TextView genre;
        TextView releaseYear;
        ImageView image;
    }
}
