package feuchtwanger.feuchtwangerweather;

import android.os.AsyncTask;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Ariella on 1/13/2016.
 */
public class WeatherPagerAdapter extends PagerAdapter{
    private ArrayList<String> zipcodes;
    private TodayWeather today;
    private CurrentWeather current;
    private RecyclerView recyclerView;

    public WeatherPagerAdapter(ArrayList<String> zipcodes){
        this.zipcodes = zipcodes;
    }

    @Override
    public int getCount() {
        return zipcodes.size();
    }

    @Override
    public Object instantiateItem(final ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        View view = inflater.inflate(R.layout.weather_pager_item, null);
        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(container.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        try {
            WeatherAdapter adapter = new WeatherAsyncTask(zipcodes.get(position), recyclerView).execute().get();
            recyclerView.setAdapter(adapter);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        String hi = "hi";

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public void setAdapter(WeatherAdapter adapter){
        recyclerView.setAdapter(adapter);
    }

    public class WeatherAsyncTask extends AsyncTask<Object[], Object, WeatherAdapter> {
        private String zip;
        private RecyclerView recyclerView;
        private TodayWeather today;
        private CurrentWeather current;

        public WeatherAsyncTask(String zip, RecyclerView recyclerView){
            this.zip = zip;
            this.recyclerView = recyclerView;
        }

        @Override
        protected void onPostExecute(WeatherAdapter adapter) {
            recyclerView.setAdapter(adapter);
        }

        @Override
        protected WeatherAdapter doInBackground(Object[]... params) {
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

            return new WeatherAdapter(today, current);
        }
    }

}
