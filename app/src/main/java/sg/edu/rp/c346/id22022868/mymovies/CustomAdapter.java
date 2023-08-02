package sg.edu.rp.c346.id22022868.mymovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter {

    Context parent_context;
    int layout_id;
    ArrayList<Movie> movieList;

    public CustomAdapter(@NonNull Context context, int resource, ArrayList<Movie> objects) {
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        movieList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater) parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvTitle = rowView.findViewById(R.id.tvTitle);
        TextView tvGenre = rowView.findViewById(R.id.tvGenre);
        TextView tvYear = rowView.findViewById(R.id.tvYear);
        ImageView ivRating = rowView.findViewById(R.id.ivRating);

        // Obtain the [Android Version information] based on the position - replace [] with wtv data
        Movie currentMovie = movieList.get(position);

        //set tvs according to what is gotten
        tvTitle.setText(currentMovie.getTitle());
        tvGenre.setText(currentMovie.getGenre());
        tvYear.setText(String.valueOf(currentMovie.getYear()));

        // Set img depending on rating

        if (currentMovie.getRating().equals("G")) {
            ivRating.setImageResource(R.drawable.rating_g);
        }
        else if (currentMovie.getRating().equals("PG")) {
            ivRating.setImageResource(R.drawable.rating_pg);
        }
        else if (currentMovie.getRating().equals("PG13")) {
            ivRating.setImageResource(R.drawable.rating_pg13);
        }
        else if (currentMovie.getRating().equals("NC16")) {
            ivRating.setImageResource(R.drawable.rating_nc16);
        }
        else if (currentMovie.getRating().equals("M18")) {
            ivRating.setImageResource(R.drawable.rating_m18);
        }
        else {
            ivRating.setImageResource(R.drawable.rating_r21);
        }




        return rowView;










    }
}
