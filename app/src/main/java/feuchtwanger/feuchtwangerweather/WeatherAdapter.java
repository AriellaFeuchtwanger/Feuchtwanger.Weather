package feuchtwanger.feuchtwangerweather;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Ariella on 1/12/2016.
 */
public class WeatherAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private CurrentWeather current;
    private TodayWeather today;
    private Context context;

    public WeatherAdapter(TodayWeather today, CurrentWeather current){
        this.today = today;
        this.current = current;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch(viewType){
            case 0:
                View view1 = inflater.inflate(R.layout.today_weather_pic, parent, false);
                viewHolder = new TodayWeatherPicViewHolder(view1);
                break;
            case 1:
                View view2 = inflater.inflate(R.layout.today_temp, parent, false);
                viewHolder = new TodayTempViewholder(view2);
                break;
            case 2:
                View view3 = inflater.inflate(R.layout.current_temperature, parent, false);
                viewHolder = new CurrentTemperatureViewholder(view3);
                break;
            case 3:
                View view4 = inflater.inflate(R.layout.daily_weather, parent, false);
                viewHolder = new DailyWeatherViewHolder(view4);
                break;
            case 4:
                View view5 = inflater.inflate(R.layout.weather_detail, parent, false);
                viewHolder = new WeatherDetailViewHolder(view5);
                break;
            default:
                View v = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
                viewHolder = new CurrentTemperatureViewholder(v);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch(holder.getItemViewType()){
            case 0:
                TodayWeatherPicViewHolder viewHolder = (TodayWeatherPicViewHolder) holder;
                viewHolder.bind(today, context);
                break;
            case 1:
                TodayTempViewholder tempHolder = (TodayTempViewholder) holder;
                tempHolder.bind(today);
                break;
            case 2:
                CurrentTemperatureViewholder currentTempHolder = (CurrentTemperatureViewholder) holder;
                currentTempHolder.bind(today);
                break;
            case 3:
                DailyWeatherViewHolder dailyHolder = (DailyWeatherViewHolder) holder;
                dailyHolder.bind(current.getWeatherList(position-3), context);
                break;
            case 4:
                WeatherDetailViewHolder detailHolder = (WeatherDetailViewHolder) holder;
                detailHolder.bind(today);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return 0;
        } else if(position == 1){
            return 1;
        } else if(position == 2){
            return 2;
        } else if(position >= 3 && position <= 12){
            return 3;
        } else{
            return 4;
        }
    }

    @Override
    public int getItemCount() {
        return 14;
    }
}
