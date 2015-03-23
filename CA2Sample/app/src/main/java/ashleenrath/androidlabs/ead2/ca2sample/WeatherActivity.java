package ashleenrath.androidlabs.ead2.ca2sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;


public class WeatherActivity extends Activity implements AdapterView.OnItemSelectedListener {


    private ArrayList<Weather> cities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        Spinner citiesSelect = (Spinner) findViewById(R.id.spinner);

        cities = new ArrayList<Weather>();
        cities.add(new Weather("Dublin",8,WeatherCondition.Sunny));
        cities.add(new Weather("New York",21,WeatherCondition.Rain));
        cities.add(new Weather("London",14,WeatherCondition.Cloudy));

        ArrayList<String> citynames = new ArrayList<>();
        for (int i = 0; i < cities.size(); i++)
        {
            citynames.add(cities.get(i).getCity());
        }


        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, citynames);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        citiesSelect.setAdapter(adapter);
        citiesSelect.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String city = parent.getItemAtPosition(position).toString();
        TextView temp = (TextView) findViewById(R.id.temperature);
        ImageView condition = (ImageView) findViewById(R.id.weatherConditionImg);

        for(Weather w: cities)
        {
            if(w.getCity().equalsIgnoreCase(city));
            {
                temp.setText(w.getTemperature() + "℃");
                if(w.getWeatherCondition() == WeatherCondition.Sunny)
                {
                    condition.setImageResource(R.drawable.sunny);
                }
                else if(w.getWeatherCondition() == WeatherCondition.Cloudy)
                {
                    condition.setImageResource(R.drawable.cloudy);
                }
                else if(w.getWeatherCondition() == WeatherCondition.Rain)
                {
                    condition.setImageResource(R.drawable.rain);
                }
            }
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
