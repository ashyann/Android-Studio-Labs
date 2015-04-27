package com.ead2.x00089861.labs.ca3sample;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;


public class TollActivity extends ActionBarActivity {

    private static String SERVICE_URL = "http://m50tollrestservice.azurewebsites.net/M50REST/toll/";
    private static String TAG = "TollActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toll);

    }

    public void calculateTotal(View view) {

        final StringBuilder jsonData = new StringBuilder();

        Thread t = new Thread()
        {
            public void run()
            {
                try
                {
                    CheckBox checkBox = (CheckBox) findViewById(R.id.hasETagButton);
                    Spinner spinner = (Spinner) findViewById(R.id.tollSpinner);

                    //gets the string of the given enum
                    String tollRequestParameters = spinner.getSelectedItem().toString();

                    if(checkBox.isChecked())
                    {
                        tollRequestParameters += "/true";
                    }
                    else
                    {
                        tollRequestParameters += "/false";
                    }

                    URI uri = new URI(SERVICE_URL + tollRequestParameters);	// construct RESTful URL
                    Log.d(TAG,"Connecting to " + uri);

                    // issue a GET to Url
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpGet get = new HttpGet(uri);

                    HttpResponse response = httpClient.execute(get);

                    Log.d(TAG, response.getStatusLine().toString());

                    // get a reader to response content
                    InputStream is = response.getEntity().getContent();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));

                    String line = null;
                    try
                    {
                        while ((line = reader.readLine()) != null)	// read line by line
                        {
                            jsonData.append(line + "\n");
                            //Log.d(TAG,jsonData.toString());
                        }
                    }
                    catch (IOException e)
                    {
                        Log.d(TAG, e.toString());
                    }
                }
                catch(Exception exception)
                {
                     Log.d(TAG,exception.toString());
                }
            }
        };
        try
        {
            t.start();
            t.join();
            // strip off " "
            CharSequence text = "€ " + jsonData.toString();
            TextView message = (TextView) findViewById(R.id.totalChargeTextView);
            message.setText(text);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_toll, menu);
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
