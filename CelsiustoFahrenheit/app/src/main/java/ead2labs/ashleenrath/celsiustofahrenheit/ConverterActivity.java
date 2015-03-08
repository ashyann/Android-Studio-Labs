package ead2labs.ashleenrath.celsiustofahrenheit;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;


public class ConverterActivity extends ActionBarActivity {

    private static String TAG = "DebugOnClick";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);

        //To Convert from C to F = multiply by 9, divide by 5 and +32
        //To Convert from F to C = -32, multiply by 5 and divide by 9

        final EditText celcius = (EditText) findViewById(R.id.celciusEdit);
        final EditText fahrenheit = (EditText) findViewById(R.id.fahrenheitEdit);

        final Button convert = (Button) findViewById(R.id.converttoButton);
        final TextView result = (TextView) findViewById(R.id.result);

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String celciusStr = celcius.getText().toString();
                final String fahrenheitStr = fahrenheit.getText().toString();
                DecimalFormat df = new DecimalFormat("#.##");

                if(celciusStr != null)
                {
                    Double celciusValue = Double.parseDouble(celcius.getText().toString());
                    Double resultC2F = ((celciusValue * 9)/5) + 32;
                    result.setText(celciusStr +  "℃ =" + df.format(resultC2F).toString() + "℉");
                    Log.d(TAG, celciusValue +  "℃ =" + df.format(resultC2F).toString() + "℉");

                }

                else
                {
                    Double fahrenheitValue = Double.parseDouble(fahrenheit.getText().toString());
                    Double resultF2C = ((fahrenheitValue -32)*5)/9;
                    result.setText(fahrenheitStr +  "℉ =" + df.format(resultF2C).toString() + "℃");
                    Log.d(TAG, fahrenheitValue +  "℉ =" + resultF2C.toString() + "℃");
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_converter, menu);
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
