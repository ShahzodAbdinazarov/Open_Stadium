package org.hamroh.openstadium.ui.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import org.hamroh.openstadium.R;
import org.hamroh.openstadium.service.Session;
import org.hamroh.openstadium.ui.login.LoginActivity;
import org.hamroh.openstadium.ui.login.VerifyActivity;
import org.hamroh.openstadium.ui.main.MainActivity;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle("Profil");

        TextView btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(l -> new Session(this).logOut(this));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}