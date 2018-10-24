package wit.ie.fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.AdapterView;

import android.widget.Toast;

import Exercises.Arms;
import Exercises.Back;
import Exercises.Chest;
import Exercises.Legs;

public class MainActivity extends AppCompatActivity {

    int[] images = {R.drawable.arms, R.drawable.back,R.drawable.chest,R.drawable.legs};

    String[] version = { "Arms", "Back","Chest","Legs"};

    String[] versionNumber = {"2 exercises", "2 exercises","2 exercises","2 exercises"};

    ListView lView;

    ListAdapter lAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lView = (ListView) findViewById(R.id.androidList);

        lAdapter = new ListAdapter(MainActivity.this, version, versionNumber, images);

        lView.setAdapter(lAdapter);

        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(MainActivity.this, version[i]+" "+versionNumber[i], Toast.LENGTH_SHORT).show();

                if (i==0)
                {
                    Intent intent = new Intent (view.getContext(), Arms.class);
                    startActivity(intent);
                }
                else if (i==1)
                {
                    Intent intent = new Intent (view.getContext(), Back.class);
                    startActivity(intent);
                }
                else if (i==2)
                {
                    Intent intent = new Intent (view.getContext(), Chest.class);
                    startActivity(intent);
                }
                else if (i==3)
                {
                    Intent intent = new Intent (view.getContext(), Legs.class);
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
}
