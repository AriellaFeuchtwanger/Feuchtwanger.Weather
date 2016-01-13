package feuchtwanger.feuchtwangerweather;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Ariella on 1/12/2016.
 */
public class TodayTempViewholder extends RecyclerView.ViewHolder{

    @Bind(R.id.tempLow)
    TextView tempLow;
    @Bind(R.id.tempHigh)
    TextView tempHigh;

    public TodayTempViewholder(View itemView) {
        super(itemView);
        ButterKnife.bind(itemView);
    }

    public void bind(TodayWeather today){
        tempLow.setText(today.getTempMin() + " ");
        tempHigh.setText(today.getTempMax() + " ");
    }
}
