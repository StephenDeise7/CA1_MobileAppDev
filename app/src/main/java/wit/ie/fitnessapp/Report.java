package wit.ie.fitnessapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


import wit.ie.fitnessapp.R;
import wit.ie.fitnessapp.DonateMenu;

public class Report extends Base
{
    ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        listView = (ListView) findViewById(R.id.reportList);
        DonationAdapter adapter = new DonationAdapter(this,  donations);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
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

class DonationAdapter extends ArrayAdapter<Donation>
{
    private Context        context;
    public  List<Donation> donations;

    public DonationAdapter(Context context, List<Donation> donations)
    {
        super(context, R.layout.row_donate, donations);
        this.context   = context;
        this.donations = donations;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View     view       = inflater.inflate(R.layout.row_donate, parent, false);
        Donation donation   = donations.get(position);
        TextView amountView = (TextView) view.findViewById(R.id.row_amount);
        TextView methodView = (TextView) view.findViewById(R.id.row_method);

        amountView.setText("$" + donation.amount);
        methodView.setText(donation.method);

        return view;
    }

    @Override
    public int getCount()
    {
        return donations.size();
    }
}