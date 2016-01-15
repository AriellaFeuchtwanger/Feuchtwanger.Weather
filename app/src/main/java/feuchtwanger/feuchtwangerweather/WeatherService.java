package feuchtwanger.feuchtwangerweather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Ariella on 1/9/2016.
 */
public interface WeatherService {
    @GET("forecast/daily?zip=equals&units=imperial&cnt=10&appid=2de143494c0b295cca9337e1e96b00e0")
    Call<CurrentWeather> getWeather(@Query("zip") String zip);

    @GET("weather?zip=equals,us&units=imperial&appid=2de143494c0b295cca9337e1e96b00e0")
    Call<TodayWeather> getTodayWeather(@Query("zip") String zip);

}
