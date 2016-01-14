package feuchtwanger.feuchtwangerweather;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Ariella on 1/13/2016.
 */
public class WeatherAsyncTask extends AsyncTask{
    private String zip;
    private RecyclerView recyclerView;
    private TodayWeather today;
    private CurrentWeather current;

    public WeatherAsyncTask(String zip, RecyclerView recyclerView){
        this.zip = zip;
        this.recyclerView = recyclerView;
    }
    @Override
    protected void onPostExecute(Object o) {
       // super.onPostExecute(o);
        WeatherAdapter adapter = new WeatherAdapter(today, current);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected Object doInBackground(Object[] params) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TodayWeatherService service = retrofit.create(TodayWeatherService.class);

        Call<TodayWeather> callToday = service.getTodayWeather(zip);
        try {
            today = callToday.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherService weatherService = retrofit2.create(WeatherService.class);
        Call<CurrentWeather> call = weatherService.getWeather(zip);

        try {
            current = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
        callToday.enqueue(new Callback<TodayWeather>() {
                              @Override
                              public void onResponse(Response<TodayWeather> response) {
                                  today = response.body();
                                  WeatherAdapter adapter = new WeatherAdapter(today);
                                  recyclerView.setAdapter(adapter);
                                  recyclerView.setBackgroundResource(R.drawable.weatherimage);
                                  /*
                                  Retrofit retrofit2 = new Retrofit.Builder()
                                          .baseUrl("http://api.openweathermap.org/data/2.5/")
                                          .addConverterFactory(GsonConverterFactory.create())
                                          .build();
                                  WeatherService weatherService = retrofit2.create(WeatherService.class);
                                  Call<CurrentWeather> call = weatherService.getWeather(zipcode);

                              }

                              @Override
                              public void onFailure(Throwable t) {
                                  t.printStackTrace();
                              }
                          }
        );
                */
        return null;
    }
}
