package feuchtwanger.feuchtwangerweather;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CurrentTemperatureViewholder extends RecyclerView.ViewHolder{
    @Bind(R.id.temperature)
    TextView temperature;
    @Bind(R.id.forecast)
    TextView forecast;

    public CurrentTemperatureViewholder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(TodayWeather today){
        forecast.setText("Forecast:");
        temperature.setText(today.getTemp() + "");
    }
}
