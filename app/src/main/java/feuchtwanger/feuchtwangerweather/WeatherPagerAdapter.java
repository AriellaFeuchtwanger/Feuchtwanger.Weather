package feuchtwanger.feuchtwangerweather;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

import static feuchtwanger.feuchtwangerweather.R.drawable.weatherimage;

/**
 * Created by Ariella on 1/13/2016.
 */
public class WeatherPagerAdapter extends PagerAdapter {
    private ArrayList<String> zipcodes;
    private RecyclerView recyclerView;
    private Context context;

    public WeatherPagerAdapter(ArrayList<String> zipcodes, Context context) {
        this.zipcodes = zipcodes;
        this.context = context;
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
        recyclerView.setBackgroundResource(R.drawable.backgroundtwo);

        Button zip = (Button) view.findViewById(R.id.zip);
        final Dialog dialog = new Dialog(context);
        zip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater dialogInflater = LayoutInflater.from(context);
                View dialogView = dialogInflater.inflate(R.layout.dialog, null);
                dialog.setContentView(dialogView);
                dialog.setTitle("Add Zip Codes");

                final EditText text = (EditText) dialog.findViewById(R.id.enterZips);

                Button zips = (Button) dialog.findViewById(R.id.zips);
                zips.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        zipcodes.add(text.getText().toString());
                        notifyDataSetChanged();
                    }
                });

                Button cancel = (Button) dialog.findViewById(R.id.cancel);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog.cancel();
                    }
                });
                dialog.show();

            }
        });

        new WeatherAsyncTask(zipcodes.get(position), recyclerView).execute();

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public class WeatherAsyncTask extends AsyncTask<Object[], Object, WeatherAdapter> {
        private String zip;
        private RecyclerView recyclerView;
        private TodayWeather today;
        private CurrentWeather current;

        public WeatherAsyncTask(String zip, RecyclerView recyclerView) {
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
            WeatherService service = retrofit.create(WeatherService.class);

            Call<TodayWeather> callToday = service.getTodayWeather(zip);
            try {
                today = callToday.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }


            Call<CurrentWeather> call = service.getWeather(zip);

            try {
                current = call.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return new WeatherAdapter(today, current);
        }
    }

}
