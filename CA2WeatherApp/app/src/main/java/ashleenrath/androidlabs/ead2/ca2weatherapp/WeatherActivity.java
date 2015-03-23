package ashleenrath.androidlabs.ead2.ca2weatherapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;


public class WeatherActivity extends Activity implements AdapterView.OnItemSelectedListener{

    Spinner spinner;
    ImageView imageView;
    ArrayList<Weather> cities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

         spinner = (Spinner) findViewById(R.id.spinner);

         cities = new ArrayList<Weather>();

        cities.add(new Weather("Dublin",8,WeatherCondition.Sunny));
        cities.add(new Weather("New York",21,WeatherCondition.Cloudy));
        cities.add(new Weather("London",14,WeatherCondition.Rain));

        ArrayList<String>spinnernames = new ArrayList<String>();
        for(int i = 0; i < cities.size(); i++)
        {
            spinnernames.add(cities.get(i).getCity());
        }

        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, spinnernames);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String city = parent.getItemAtPosition(position).toString();
        TextView temp = (TextView) findViewById(R.id.temperature);
        imageView = (ImageView)findViewById(R.id.imageView);

        for(Weather w: cities)
        {
            if(w.getCity().matches(city))
            {
                if(w.getWeatherCondition() == WeatherCondition.Cloudy)
                {
                    temp.setText(w.getCity() + " Temperature: " + w.getTemperature() + "°C");
                    imageView.setImageResource(R.drawable.cloudy);
                }
                else if(w.getWeatherCondition() == WeatherCondition.Sunny)
                {
                    temp.setText(w.getCity() + " Temperature: " + w.getTemperature() + "°C");
                    imageView.setImageResource(R.drawable.sunny);
                }
                else if(w.getWeatherCondition() == WeatherCondition.Rain)
                {
                    temp.setText(w.getCity() + " Temperature: " + w.getTemperature() + "°C");
                    imageView.setImageResource(R.drawable.rain);
                }
            }
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
