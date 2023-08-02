package sg.edu.rp.c346.id22022868.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {

    Button btnPG13;
    ListView lvMovies;
    ArrayList<Movie> movies;
    CustomAdapter adapter;
    //ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        btnPG13 = findViewById(R.id.btnPG13);
        lvMovies = findViewById(R.id.lvMovies);

        //setting listview components
        movies = new ArrayList<Movie>();
        adapter = new CustomAdapter(ListViewActivity.this, R.layout.row, movies);
        //adapter = new ArrayAdapter(ListViewActivity.this, android.R.layout.simple_list_item_1, movies);
        lvMovies.setAdapter(adapter);

        // Create the DBHelper object, passing in the activity's Context
        DBHelper db = new DBHelper(ListViewActivity.this);

        movies.clear();

        //Adding Song objects to the arraylist
        movies.addAll(db.getMovieDetails());

        //Closing database connection
        db.close();

        btnPG13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                movies.clear();

                movies.addAll(db.filterPG13("PG13"));

                //songs = rated5;

                adapter.notifyDataSetChanged();

            }
        });

        lvMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Getting the object
                Movie target = movies.get(position);

                //Retrieving the Song details by using getters
                int passId = movies.get(position).getId();
                String passTitle = movies.get(position).getTitle();
                String passGenre = movies.get(position).getGenre();
                int passYear = movies.get(position).getYear();
                String passRating = movies.get(position).getRating();

                Intent third = new Intent(ListViewActivity.this, EditActivity.class);

                third.putExtra("data", (Serializable) target);
                third.putExtra("id", passId);
                third.putExtra("title", passTitle);
                third.putExtra("genre", passGenre);
                third.putExtra("year", passYear);
                third.putExtra("rating", passRating);

                startActivity(third);
            }
        });





    }

    @Override
    protected void onResume() {
        super.onResume();
        DBHelper db = new DBHelper(ListViewActivity.this);

        movies.clear();

        movies.addAll(db.getMovieDetails());

        adapter.notifyDataSetChanged();

    }





}