package com.androidpractise.ash.test.mypractiseappwidgettest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner)findViewById(R.id.spinner);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.populatedlist,R.layout.support_simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    public void onRadioButtonClicked(View view)
    {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        final TextView message = (TextView) findViewById(R.id.textViewradio);

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioButton:
                if (checked)

                new AlertDialog.Builder(this)
                        .setTitle("Alert 1 CLicked")
                        .setMessage("You you want to continue?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                message.setText("Radio Button Clicked");
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                    break;
            case R.id.radioButton2:
                if (checked)

                new AlertDialog.Builder(this)
                        .setTitle("Alert 2 UnCLicked")
                        .setMessage("You you want to continue?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                message.setText("Radio Button Clicked Again!");
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                    break;
        }
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onCheckBoxClicked(View view) {
        // Is the button now checked?
        boolean checked = ((CheckBox) view).isChecked();

        final TextView message = (TextView) findViewById(R.id.checkboxmessage);

        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.checkBox:
                if(checked)
                {
                    message.setText("Check me out :D I'm checked yay");
                }
                else
                {
                    message.setText("why u no check :'(");
                }

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        final TextView selected = (TextView) findViewById(R.id.selectedmessage);

        Log.d("Selected",getString(R.string.choice1));
        Log.d("Selected",getString(R.string.choice2));
        Log.d("Selected",getString(R.string.choice3));


        if(parent.getSelectedItem().toString().trim().equals(getString(R.string.choice1)))
        {
            Toast.makeText(MainActivity.this,getString(R.string.choice1) + " has been selected!",Toast.LENGTH_LONG).show();
            selected.setText(getString(R.string.choice1) + " has been selected!");
        }
        if(parent.getSelectedItem().toString().trim().equals(getString(R.string.choice2)))
        {
            Toast.makeText(MainActivity.this,getString(R.string.choice2) + " has been selected!",Toast.LENGTH_LONG).show();
            selected.setText(getString(R.string.choice2) + " has been selected!");
        }
        if(parent.getSelectedItem().toString().trim().equals(getString(R.string.choice3)))
        {
            new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.choice3) + " has been selected!")
                    .setMessage("You you want to continue?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            selected.setText(getString(R.string.choice3) + " has been selected!");
                        }
                    })
                    .setNegativeButton(android.R.string.no,new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            selected.setText(getString(R.string.choice3) + " doesn't want to be selected!");
                        }
                    }).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
