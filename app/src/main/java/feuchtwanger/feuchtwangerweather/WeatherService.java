package feuchtwanger.feuchtwangerweather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Ariella on 1/9/2016.
 */
public interface WeatherService {
    @GET("/data/2.5/forecast/daily?zip={zipcode}&units=imperial&cnt=10&appid=2de143494c0b295cca9337e1e96b00e0")
    Call<CurrentWeather> getWeather(@Path("zipcode") String zipcode);

    @GET("/data/2.5/weather?zip={zipcode},us&units=imperial&appid=2de143494c0b295cca9337e1e96b00e0")
    Call<TodayWeather> getTodayWeather(@Path("zipcode") String zipcode);
}
