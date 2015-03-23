package ashleenrath.androidlabs.ead2.gradeclient;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;


public class GradeActivity extends Activity {

    // URL for RESTful service which returns JSON
    private static String SERVICE_URL = "http://grades-rest-89861.azurewebsites.net/grades";
    private static String TAG = "GradeClient";
    JSONObject jsonObject;
    private static final String TAG_GRADE = "GradeLetter";
    JSONArray jsonArray;
    ArrayList<HashMap<String, String>> arraylist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);

        Button calculate = (Button) findViewById(R.id.calculateButton);
        final TextView outputTextView = (TextView) findViewById(R.id.gradeResult);
        final EditText percentagefield = (EditText) findViewById(R.id.enterGrade);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (percentagefield.getText().toString().matches("")) {
                    outputTextView.setTextColor(Color.RED);
                    outputTextView.setText("Please enter a grade!");
                }
                else
                {
                    final int percent = Integer.parseInt(percentagefield.getText().toString());
                    final StringBuilder jsonData = new StringBuilder();
                    Thread t = new Thread()
                    {
                        public void run()														// anonymous class
                        {
                            try
                            {
                                URI uri = new URI(SERVICE_URL + "/result/" + percent);					    // create URL
                                Log.d(TAG, "Connecting to" + uri.toString());					// debug log message

                                // issue a GET to uri
                                HttpClient httpClient = new DefaultHttpClient();
                                HttpGet get = new HttpGet(uri);									// or HttpPost
                                HttpResponse response = httpClient.execute(get);

                                // get a reader to response content
                                InputStream is = response.getEntity().getContent();
                                BufferedReader reader = new BufferedReader(new InputStreamReader(is));

                                String line = null;
                                Log.d(TAG, "Retrieving data");
                                try
                                {
                                    while ((line = reader.readLine()) != null)					// read line by line
                                    {
                                        Log.d(TAG, "appending" + line);
                                        jsonData.append(line + "\n");
                                    }
                                }
                                catch (IOException e)
                                {
                                    Log.d(TAG, e.toString());
                                    outputTextView.setText(e.toString());
                                }
                                finally
                                {
                                    try
                                    {
                                        is.close();
                                    }
                                    catch (IOException e)
                                    {
                                        Log.d(TAG, e.toString());
                                        outputTextView.setText(e.toString());
                                    }
                                }
                                Log.d(TAG, "Data retrieved" + jsonData);

                            }
                            catch (Exception e)
                            {
                                Log.d(TAG, e.toString());
                                outputTextView.setText(e.toString());
                            }
                        }
                    };

                    try {
                        t.start();                        // kick off thread
                        t.join();                        // wait for thread to finish
                        outputTextView.setText(jsonData.toString());
                    }
                    catch (Exception e)
                    {
                        Log.d(TAG, e.toString());
                        outputTextView.setText(e.toString());
                    }
                }
            }
        });

    }
}