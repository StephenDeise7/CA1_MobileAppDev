package wit.ie.fitnessapp;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class FavouritesMenu extends AppCompatActivity {

    ListView lv;
    ArrayAdapter<String> adapter;
    CRUD crud=new CRUD();
    Dialog d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);


        lv= (ListView) findViewById(R.id.lv);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(d != null) {
                    if(!d.isShowing())
                    {
                        displayInputDialog(i);
                    }else
                    {
                        d.dismiss();
                    }
                }
            }
        });

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                displayInputDialog(-1);
            }
        });
    }
    private void displayInputDialog(final int pos)
    {
        d=new Dialog(this);
        d.setTitle("LISTVIEW CRUD");
        d.setContentView(R.layout.inputdialog);

        final EditText nameEditTxt= (EditText) d.findViewById(R.id.nameEditText);
        Button addBtn= (Button) d.findViewById(R.id.addBtn);
        Button updateBtn= (Button) d.findViewById(R.id.updateBtn);
        Button deleteBtn= (Button) d.findViewById(R.id.deleteBtn);

        if(pos== -1)
        {
            addBtn.setEnabled(true);
            updateBtn.setEnabled(false);
            deleteBtn.setEnabled(false);
        }else
        {
            addBtn.setEnabled(true);
            updateBtn.setEnabled(true);
            deleteBtn.setEnabled(true);
            nameEditTxt.setText(crud.getNames().get(pos));
        }

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //GET DATA
                String name=nameEditTxt.getText().toString();

                //VALIDATE
                if(name.length()>0 && name != null)
                {
                    //save
                    crud.save(name);
                    nameEditTxt.setText("");
                    adapter=new ArrayAdapter<String>(FavouritesMenu.this,android.R.layout.simple_list_item_1,crud.getNames());
                    lv.setAdapter(adapter);

                }else
                {
                    Toast.makeText(FavouritesMenu.this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //GET DATA
                String newName=nameEditTxt.getText().toString();

                //VALIDATE
                if(newName.length()>0 && newName != null)
                {
                    //save
                    if(crud.update(pos,newName))
                    {
                        nameEditTxt.setText(newName);
                        adapter=new ArrayAdapter<String>(FavouritesMenu.this,android.R.layout.simple_list_item_1,crud.getNames());
                        lv.setAdapter(adapter);
                    }

                }else
                {
                    Toast.makeText(FavouritesMenu.this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //DELETE
                if( crud.delete(pos))
                {
                    nameEditTxt.setText("");
                    adapter=new ArrayAdapter<String>(FavouritesMenu.this,android.R.layout.simple_list_item_1,crud.getNames());
                    lv.setAdapter(adapter);
                }
            }
        });

        d.show();
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