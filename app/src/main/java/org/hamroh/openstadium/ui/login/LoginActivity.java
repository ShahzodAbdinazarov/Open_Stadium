package org.hamroh.openstadium.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.hamroh.openstadium.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle(getString(R.string.title_login));

        EditText edtPhone = findViewById(R.id.edtPhone);

        FloatingActionButton fab = findViewById(R.id.fabLogin);
        fab.setOnClickListener(l -> {
            if (edtPhone.getText().length() != 9) {
                edtPhone.setError("Telefon raqam noto'g'ri");
            } else {
                startActivity(new Intent(this, VerifyActivity.class)
                        .putExtra("phone", edtPhone.getText().toString()));
                finish();
            }
        });

    }
}