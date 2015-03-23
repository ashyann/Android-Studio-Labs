package ashleenrath.androidlabs.ead2.azurestorageplan;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;


public class AzureActivity extends Activity implements AdapterView.OnItemSelectedListener {

    Double firsttb;
    Double aftertb;
    Double price;
    Spinner azureselect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_azure);

        azureselect = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.azurestoragechoices,R.layout.support_simple_spinner_dropdown_item);

        azureselect.setAdapter(adapter);
        azureselect.setOnItemSelectedListener(this);

        Button calculate = (Button) findViewById(R.id.calculateButton);
        final EditText storage = (EditText) findViewById(R.id.storageEdit);
        final TextView result = (TextView) findViewById(R.id.pricepermonth);


        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(storage.getText().toString().matches(""))
                {
                    result.setText("Please enter valid storage");
                    result.setTextColor(Color.RED);
                }
                else
                {
                    Double storagenum = Double.valueOf(storage.getText().toString());
                    DecimalFormat df = new DecimalFormat("#");
                    if(storagenum <= 0)
                    {
                        result.setText("Cannot have storage less than or equal to 0");
                    }
                    else if(storagenum > 1000)
                    {
                        Double afterTBSize = storagenum - 1000;
                        price = (afterTBSize * aftertb) + (1000 * firsttb);
                        Log.d("Size", afterTBSize + " " + price);
                        result.setText(df.format(storagenum ) + "GB " + azureselect.getSelectedItem().toString() + " $" + price + " per month");
                    }
                    else if(storagenum <= 1000)
                    {
                        price = (storagenum * firsttb);
                        Log.d("Size", storagenum + " " + price);
                        result.setText(df.format(storagenum ) + "GB " + azureselect.getSelectedItem().toString() + " $" + price + " per month");

                    }
                }
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView selected = (TextView) view;
        if (azureselect.getSelectedItem().toString().trim().equals("Geographically redundant"))
        {
            firsttb = 0.125;
            aftertb = 0.11;
            Toast.makeText(this,"Price " + firsttb + " and " + aftertb,Toast.LENGTH_LONG).show();
        }
        else
        {
            firsttb = 0.093;
            aftertb = 0.083;
            Toast.makeText(this,"Price " + firsttb + " and " + aftertb,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
