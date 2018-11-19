package CRUD;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Menus.HomeMenu;
import Menus.ProfileActivity;
import wit.ie.fitnessapp.MainActivity;
import wit.ie.fitnessapp.MapsActivity;
import wit.ie.fitnessapp.R;
import wit.ie.fitnessapp.SettingsActivity;

public class MainFavouriteActivity extends AppCompatActivity {

    EditText editTextName;
    Spinner spinnerGenre;
    Button buttonAddExercise;
    ListView listViewFavourites;


    List<Favourite> favourites;


    DatabaseReference databaseFavourites;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favouritemain);


        databaseFavourites = FirebaseDatabase.getInstance().getReference("Favourites");


        editTextName = (EditText) findViewById(R.id.editTextName);
        spinnerGenre = (Spinner) findViewById(R.id.spinnerGenres);
        listViewFavourites = (ListView) findViewById(R.id.listViewExercise);

        buttonAddExercise = (Button) findViewById(R.id.buttonAddExercise);

        favourites = new ArrayList<>();

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.nav_home:
                        Intent intent = new Intent (MainFavouriteActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_search:
                        Intent intent1 = new Intent (MainFavouriteActivity.this, MapsActivity.class);
                        startActivity(intent1);
                        break;


                }
                return true;
            }
        });

        buttonAddExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addExercise();
            }
        });



        listViewFavourites.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Favourite favourite = favourites.get(i);
                showUpdateDeleteDialog(favourite.getExerciseId(), favourite.getExerciseName());
                return true;
            }
        });


    }

    private void showUpdateDeleteDialog(final String exerciseId, String exerciseName) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText editTextName = (EditText) dialogView.findViewById(R.id.editTextName);
        final Spinner spinnerGenre = (Spinner) dialogView.findViewById(R.id.spinnerGenres);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdateExercise);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttonDeleteExercise);

        dialogBuilder.setTitle(exerciseName);
        final AlertDialog b = dialogBuilder.create();
        b.show();


        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString().trim();
                String genre = spinnerGenre.getSelectedItem().toString();
                if (!TextUtils.isEmpty(name)) {
                    updateExercise(exerciseId, name, genre);
                    b.dismiss();
                }
            }
        });


        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deleteExercise(exerciseId);
                b.dismiss();
            }
        });
    }

    private boolean updateExercise(String id, String name, String genre) {

        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("Favourites").child(id);


        Favourite favourite = new Favourite(id, name, genre);
        dR.setValue(favourite);
        Toast.makeText(getApplicationContext(), "Exercise Updated", Toast.LENGTH_LONG).show();
        return true;
    }

    private boolean deleteExercise(String id) {

        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("Favourites").child(id);

      
        dR.removeValue();


        Toast.makeText(getApplicationContext(), "Exercise Deleted", Toast.LENGTH_LONG).show();

        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        //attaching value event listener
        databaseFavourites.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                favourites.clear();


                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    Favourite favourite = postSnapshot.getValue(Favourite.class);

                    favourites.add(favourite);
                }


                FavouriteList favouriteAdapter = new FavouriteList(MainFavouriteActivity.this, favourites);

                listViewFavourites.setAdapter(favouriteAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }






    private void addExercise() {
        String name = editTextName.getText().toString().trim();
        String genre = spinnerGenre.getSelectedItem().toString();

        if (!TextUtils.isEmpty(name)) {

 
 
            String id = databaseFavourites.push().getKey();


            Favourite favourite = new Favourite(id, name, genre);


            databaseFavourites.child(id).setValue(favourite);

            editTextName.setText("");

            Toast.makeText(this, "Exercise added", Toast.LENGTH_LONG).show();
        } else {

            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId())
        {
            case R.id.Home: startActivity(new Intent(this, MainActivity.class));
                break;

            case R.id.settings: startActivity(new Intent(this, SettingsActivity.class));
                break;

            case R.id.menuProfile: startActivity(new Intent(this, ProfileActivity.class));
                break;
            case R.id.menuLogout:
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(this, HomeMenu.class));


        }


        return super.onOptionsItemSelected(item);
    }

}
