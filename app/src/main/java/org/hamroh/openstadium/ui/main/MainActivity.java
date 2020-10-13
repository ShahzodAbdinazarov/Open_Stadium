package org.hamroh.openstadium.ui.main;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import org.hamroh.openstadium.R;
import org.hamroh.openstadium.model.Stadium;
import org.hamroh.openstadium.service.Session;
import org.hamroh.openstadium.ui.login.LoginActivity;
import org.hamroh.openstadium.ui.main.adapters.StadiumAdapter;
import org.hamroh.openstadium.ui.profile.ProfileActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!new Session(this).isLoggedIn()) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
        setContentView(R.layout.activity_main);

        ListView listStadium = findViewById(R.id.listStadium);
        List<Stadium> data = new ArrayList<>();

        data.add(new Stadium(0, "Barselona", String.valueOf(R.drawable.std), "90 000", "1.5k", "1.1 km"));
        data.add(new Stadium(1, "Camp Nou", String.valueOf(R.drawable.std1), "70 000", "0.9k", "1.3 km"));
        data.add(new Stadium(2, "Santyago Bernabeu", String.valueOf(R.drawable.std2), "80 000", "1.2k", "1.5 km"));
        data.add(new Stadium(3, "Wambly", String.valueOf(R.drawable.std3), "60 000", "0.5k", "1.7km"));
        data.add(new Stadium(4, "Yuventus", String.valueOf(R.drawable.std4), "100 000", "1.9k", "1.9 km"));
        data.add(new Stadium(5, "Etihad", String.valueOf(R.drawable.std5), "90 000", "1.4k", "2.1 km"));
        data.add(new Stadium(6, "Liverpool", String.valueOf(R.drawable.std6), "70 000", "1.1k", "2.3 km"));
        data.add(new Stadium(7, "Arsenal", String.valueOf(R.drawable.std7), "80 000", "1.3k", "2.5 km"));
        data.add(new Stadium(8, "Manchester", String.valueOf(R.drawable.std8), "90 000", "1.3k", "2.7 km"));
        data.add(new Stadium(9, "Chelsea", String.valueOf(R.drawable.std9), "70 000", "1.2k", "3.2 km"));

        StadiumAdapter adapter = new StadiumAdapter(this, data);
        listStadium.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) MainActivity.this.getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = null;
        if (searchItem != null) searchView = (SearchView) searchItem.getActionView();

        if (searchView != null)
            searchView.setSearchableInfo(searchManager.getSearchableInfo(MainActivity.this.getComponentName()));

        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_search:
                return true;
            case R.id.action_profile:
                startActivity(new Intent(this, ProfileActivity.class));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}