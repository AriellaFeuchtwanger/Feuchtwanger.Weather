package feuchtwanger.feuchtwangerweather;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.HashSet;


public class DetailActivity extends AppCompatActivity{
    private HashSet<String> zipcodes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();

        zipcodes = (HashSet<String>) intent.getSerializableExtra("Zipcodes");

    }
}
