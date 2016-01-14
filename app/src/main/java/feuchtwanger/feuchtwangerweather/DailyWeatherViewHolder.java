package feuchtwanger.feuchtwangerweather;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DailyWeatherViewHolder extends RecyclerView.ViewHolder{

    @Bind(R.id.day)
    TextView day;
    @Bind(R.id.weatherPic)
    ImageView weatherPic;
    @Bind(R.id.tempHigh)
    TextView tempHigh;
    @Bind(R.id.tempLow)
    TextView tempLow;

    public DailyWeatherViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void bind(WeatherList weather, Context context){
        Date date = new Date(weather.getDate());
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE");
        String dayName = formatter.format(date);
        tempHigh.setText(dayName);
        tempLow.setText(" " + weather.getMin());
        Picasso.with(context).load("http://openweathermap.org/img/w/" + weather.getIcon() + ".png").into(weatherPic);
    }
}
