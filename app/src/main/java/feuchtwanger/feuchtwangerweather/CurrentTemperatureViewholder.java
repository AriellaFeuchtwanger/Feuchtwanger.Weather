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

    public CurrentTemperatureViewholder(View itemView) {
        super(itemView);
        ButterKnife.bind(itemView);
    }

    public void bind(TodayWeather today){
        temperature.setText(today.getTemp() + "");
    }
}
