package Exercises;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import wit.ie.fitnessapp.DonateMenu;
import wit.ie.fitnessapp.FavouritesMenu;
import wit.ie.fitnessapp.ListAdapter;
import wit.ie.fitnessapp.MainActivity;
import wit.ie.fitnessapp.R;
import wit.ie.fitnessapp.Report;
import wit.ie.fitnessapp.SearchForGym;
import wit.ie.fitnessapp.SettingsActivity;

public class Chest extends AppCompatActivity {

    int[] images = {R.drawable.barbellchest, R.drawable.dumbbellchest};

    String[] version = { "Barbell press", "Dumbbell press"};

    String[] versionNumber = {"", ""};

    ListView lView;

    ListAdapter lAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chest);
        lView = (ListView) findViewById(R.id.androidList);

        lAdapter = new ListAdapter(Chest.this, version, versionNumber, images);

        lView.setAdapter(lAdapter);

        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(Chest.this, version[i]+" ", Toast.LENGTH_SHORT).show();

                if (i==0)
                {
                    Intent intent = new Intent (view.getContext(), ChestPress.class);
                    startActivity(intent);
                }
                else if (i==1)
                {
                    Intent intent = new Intent (view.getContext(), DumbbellChestPress.class);
                    startActivity(intent);
                }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId())
        {
            case R.id.Home: startActivity(new Intent(this, MainActivity.class));
                break;

            case R.id.settings: startActivity(new Intent(this, SettingsActivity.class));
                break;

            case R.id.menuDonate: startActivity(new Intent(this, DonateMenu.class));
                break;

            case R.id.menuReport: startActivity(new Intent(this, Report.class));
                break;

        }


        return super.onOptionsItemSelected(item);
    }

    public void go2Home (View view){
        Intent intent = new Intent (this, MainActivity.class);
        startActivity(intent);
    }

    public void go2Search (View view){
        Intent intent = new Intent (this, SearchForGym.class);
        startActivity(intent);
    }

    public void go2Favourites (View view){
        Intent intent = new Intent (this, FavouritesMenu.class);
        startActivity(intent);
    }
    public void go2Donate (View view){
        Intent intent = new Intent (this, DonateMenu.class);
        startActivity(intent);
    }

    public static class ChestPress extends YouTubeBaseActivity
    {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.content_chest_press);




            YouTubePlayerView youTubePlayerView =
                    (YouTubePlayerView) findViewById(R.id.player);

            youTubePlayerView.initialize("AIzaSyA1NYwzq9Gtei6chLZKT3uifMIl3SlpD1g",
                    new YouTubePlayer.OnInitializedListener() {
                        @Override
                        public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                            YouTubePlayer youTubePlayer, boolean b) {

                            // do any work here to cue video, play video, etc.
                            youTubePlayer.cueVideo("4aVy2Xj6wYs");
                        }
                        @Override
                        public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                            YouTubeInitializationResult youTubeInitializationResult) {

                        }
                    });
        }


        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            switch(item.getItemId())
            {
                case R.id.Home: startActivity(new Intent(this, MainActivity.class));
                    break;

                case R.id.settings: startActivity(new Intent(this, SettingsActivity.class));
                    break;

                case R.id.menuDonate: startActivity(new Intent(this, DonateMenu.class));
                    break;

                case R.id.menuReport: startActivity(new Intent(this, Report.class));
                    break;

            }


            return super.onOptionsItemSelected(item);
        }

        public void go2Home (View view){
            Intent intent = new Intent (this, MainActivity.class);
            startActivity(intent);
        }

        public void go2Search (View view){
            Intent intent = new Intent (this, SearchForGym.class);
            startActivity(intent);
        }

        public void go2Favourites (View view){
            Intent intent = new Intent (this, FavouritesMenu.class);
            startActivity(intent);
        }
        public void go2Donate (View view){
            Intent intent = new Intent (this, DonateMenu.class);
            startActivity(intent);
        }

    }
    public static class DumbbellChestPress extends YouTubeBaseActivity
    {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.content_dumbbell_chest_press);




            YouTubePlayerView youTubePlayerView =
                    (YouTubePlayerView) findViewById(R.id.player);

            youTubePlayerView.initialize("AIzaSyA1NYwzq9Gtei6chLZKT3uifMIl3SlpD1g",
                    new YouTubePlayer.OnInitializedListener() {
                        @Override
                        public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                            YouTubePlayer youTubePlayer, boolean b) {

                            // do any work here to cue video, play video, etc.
                            youTubePlayer.cueVideo("ZOwwBk642SI");
                        }
                        @Override
                        public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                            YouTubeInitializationResult youTubeInitializationResult) {

                        }
                    });
        }


        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            switch(item.getItemId())
            {
                case R.id.Home: startActivity(new Intent(this, MainActivity.class));
                    break;

                case R.id.settings: startActivity(new Intent(this, SettingsActivity.class));
                    break;

                case R.id.menuDonate: startActivity(new Intent(this, DonateMenu.class));
                    break;

                case R.id.menuReport: startActivity(new Intent(this, Report.class));
                    break;

            }


            return super.onOptionsItemSelected(item);
        }

        public void go2Home (View view){
            Intent intent = new Intent (this, MainActivity.class);
            startActivity(intent);
        }

        public void go2Search (View view){
            Intent intent = new Intent (this, SearchForGym.class);
            startActivity(intent);
        }

        public void go2Favourites (View view){
            Intent intent = new Intent (this, FavouritesMenu.class);
            startActivity(intent);
        }
        public void go2Donate (View view){
            Intent intent = new Intent (this, DonateMenu.class);
            startActivity(intent);
        }

    }
}