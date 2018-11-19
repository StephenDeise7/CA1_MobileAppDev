package CRUD;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import wit.ie.fitnessapp.R;



public class FavouriteList extends ArrayAdapter<Favourite> {
    private Activity context;
    List<Favourite> favourites;

    public FavouriteList(Activity context, List<Favourite> favourites) {
        super(context, R.layout.layout_favourite_list, favourites);
        this.context = context;
        this.favourites = favourites;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_favourite_list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewGenre = (TextView) listViewItem.findViewById(R.id.textViewGenre);

        Favourite favourite = favourites.get(position);
        textViewName.setText(favourite.getExerciseName());
        textViewGenre.setText(favourite.getExerciseGenre());

        return listViewItem;
    }
}
