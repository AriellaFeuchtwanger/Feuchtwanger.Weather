package feuchtwanger.feuchtwangerweather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

public class WeatherJSONFile {

    private URL url;
    private CurrentWeather weather;

    WeatherJSONFile(URL url) throws IOException {
        this.url = url;
        getJSONFile();

    }

    public void getJSONFile() throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        InputStream in = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        Gson gson = new Gson();
        CurrentWeather weather = gson.fromJson(reader, CurrentWeather.class);
        this.weather = weather;
    }

    public double getTempMin(int day) {
        return weather.getTempMin(day);
    }

    public double getTempMax(int day) {
        return weather.getTempMax(day);
    }

    public String getIcon(int day) {
        return weather.getIcon(day);
    }

    public String getDescription(int day) {
        return weather.getDescription(day);
    }

    public String getMain(int day) {
        return weather.getMain(day);
    }

    public String getCity() {
        return weather.getName();
    }

    public String getCountry() {
        return weather.getCountry();
    }
}

