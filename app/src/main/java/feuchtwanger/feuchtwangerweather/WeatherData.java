package feuchtwanger.feuchtwangerweather;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Ariella on 1/9/2016.
 */
public class WeatherData {
    private RecyclerView recyclerView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.weather_data, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        //set up how the recycler view looks
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PresidentsService service = retrofit.create(PresidentsService.class);
        Call<List<Presidents>> call = service.listPresidents();
        call.enqueue(new Callback<List<Presidents>>() {
                         @Override
                         public void onResponse(Response<List<Presidents>> response) {
                             List<Presidents> list = response.body();

                             OnPresidentSelectedListener listener = (OnPresidentSelectedListener) getActivity();
                             PresidentAdaptor adaptor = new PresidentAdaptor(presidents, pics, listener);
                             recyclerView.setAdapter(adaptor);
                         }

                         @Override
                         public void onFailure(Throwable t) {
                             t.printStackTrace();
                         }
                     }
        );
    }
}
