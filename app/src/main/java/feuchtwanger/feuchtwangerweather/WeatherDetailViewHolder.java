package feuchtwanger.feuchtwangerweather;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Ariella on 1/12/2016.
 */
public class WeatherDetailViewHolder extends RecyclerView.ViewHolder{
    @Bind(R.id.wind)
    TextView wind;
    @Bind(R.id.pressure)
    TextView pressure;
    @Bind(R.id.humidity)
    TextView humidity;

    public WeatherDetailViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(itemView);
    }

    public void bind(TodayWeather today){
        pressure.setText("Pressure:\t" + today.getPressure());
        humidity.setText("Humidity:\t" + today.getHumidity());

        String windDirection;
        double degree = today.getWindDegree();

        if(degree >= 11.25 && degree < 33.75){
            windDirection = "NNE";
        } else if(degree >= 33.75 && degree < 56.25){
            windDirection = "NE";
        } else if(degree >= 56.25 && degree < 78.75){
            windDirection = "ENE";
        } else if(degree >= 78.75 && degree < 101.25){
            windDirection = "E";
        } else if(degree >= 101.25 && degree < 123.75){
            windDirection = "ESE";
        } else if(degree >= 123.75 && degree < 146.25){
            windDirection = "SE";
        } else if(degree >= 146.75 && degree < 168.75){
            windDirection = "SSE";
        } else if(degree >= 168.75 && degree < 191.25){
            windDirection = "S";
        } else if(degree >= 191.25 && degree < 213.75){
            windDirection = "SSW";
        } else if(degree >= 213.75 && degree < 236.25){
            windDirection = "SW";
        } else if(degree >= 236.25 && degree < 258.75){
            windDirection = "WSW";
        } else if(degree >= 258.75 && degree < 281.25){
            windDirection = "W";
        } else if(degree >= 281.25 && degree < 303.75){
            windDirection = "WNW";
        } else if(degree >= 303.75 && degree < 326.25){
            windDirection = "NW";
        } else if(degree >= 326.25 && degree < 348.75){
            windDirection = "NNW";
        } else{
            windDirection = "N";
        }

        wind.setText("Wind:\t" + today.getWindSpeed() + "mph" + windDirection);
    }
}
