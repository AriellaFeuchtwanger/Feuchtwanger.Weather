package feuchtwanger.feuchtwangerweather;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> zipcodes;
    private Context activityContext;
    private SharedPreferences preferences;
    private WeatherPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.activityContext = getApplicationContext();
        preferences = this.getSharedPreferences("DEFAULT", MODE_PRIVATE);

        zipcodes = new ArrayList<String>();

        ViewPager pager = (ViewPager) findViewById(R.id.viewPager);
        pagerAdapter = new WeatherPagerAdapter(zipcodes, this);
        pager.setAdapter(pagerAdapter);
    }


    @Override
    protected void onPause() {
        super.onPause();

        StringBuilder builder = new StringBuilder();

        for(String s : zipcodes){
            builder.append(s + " ");
        }
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Zipcodes", builder.toString());
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();

        String zipcodeString = preferences.getString("Zipcodes", "11210");
        String[] zipList = zipcodeString.split(" ");

        for(String s : zipList){
            zipcodes.add(s);
            pagerAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
