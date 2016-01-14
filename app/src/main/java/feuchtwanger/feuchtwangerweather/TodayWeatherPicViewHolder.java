package feuchtwanger.feuchtwangerweather;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Ariella on 1/12/2016.
 */
public class TodayWeatherPicViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.image)
    ImageView image;
    @Bind(R.id.name)
    TextView name;

    public TodayWeatherPicViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(TodayWeather today, Context context) {
        name.setText(today.getMain());
        Picasso.with(context).load("http://openweathermap.org/img/w/" + today.getIcon() + ".png").into(image);
    }
}
