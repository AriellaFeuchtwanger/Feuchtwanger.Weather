package feuchtwanger.feuchtwangerweather;

import java.net.MalformedURLException;
import java.net.URL;

public class WeatherURL {
    private String city;
    private final String originalURL1 = "http://api.openweathermap.org/data/2.5/forecast/daily?q=";
    private final String originalURL2 = "&units=imperial&cnt=16&appid=2de143494c0b295cca9337e1e96b00e0";

    public WeatherURL(String city){
        this.city = city;
    }

    public URL getURL() throws MalformedURLException{
        String theURL = originalURL1 + city + originalURL2;

        URL url = new URL(theURL);
        return url;
    }
}

