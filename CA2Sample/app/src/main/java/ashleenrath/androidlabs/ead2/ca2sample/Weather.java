package ashleenrath.androidlabs.ead2.ca2sample;

/**
 * Created by ashleenrath on 22/03/2015.
 */
public class Weather {


    private String city;
    private int temperature;
    private WeatherCondition weatherCondition;

    public String getCity() {
        return city;
    }
    public int getTemperature() {
        return temperature;
    }
    public WeatherCondition getWeatherCondition() {
        return weatherCondition;
    }

    public Weather(String city, int temperature, WeatherCondition weatherCondition)
    {
        this.city = city;
        this.temperature = temperature;
        this.weatherCondition = weatherCondition;
    }
}
