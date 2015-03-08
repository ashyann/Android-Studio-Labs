package ead2labs.ashleenrath.helloworldlab1;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    //Tag for LogCat Debugging
    private static final String TAG = "CheckName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button click = (Button) findViewById(R.id.click);
        final EditText namefield = (EditText) findViewById(R.id.EnterNameField);

        //When the button is clicked say hello to the person who typed their name in the
        //text field
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String namepass = namefield.getText().toString();
                String message = "Hello ";

                //Toast
                Toast.makeText(MainActivity.this, message + String.valueOf(namepass), Toast.LENGTH_LONG).show();

                //Log at debug level
                Log.d(TAG, "Hello I am debugging and passing in my name " + namepass);
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
