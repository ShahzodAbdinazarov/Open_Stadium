package org.hamroh.openstadium.ui.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.broooapps.otpedittext2.OtpEditText;

import org.hamroh.openstadium.R;
import org.hamroh.openstadium.service.Session;
import org.hamroh.openstadium.ui.main.MainActivity;

import java.text.SimpleDateFormat;
import java.util.Objects;

@SuppressLint("SetTextI18n, SimpleDateFormat")
public class VerifyActivity extends AppCompatActivity {

    private TextView msgTimer;
    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);

        setTitle(getString(R.string.title_verify));

        timer = new CountDownTimer(120000, 1000) {
            @Override
            public void onTick(long l) {
                SimpleDateFormat timeFormat = new SimpleDateFormat("mm:ss");
                msgTimer.setText("Open Stadium sizga " + timeFormat.format(l) + " dan keyin chaqiruvni amalga oshiradi!");
            }

            @Override
            public void onFinish() {
                startActivity(new Intent(VerifyActivity.this, LoginActivity.class));
                finish();
            }
        }.start();

        TextView msgVerify = findViewById(R.id.msgVerify);
        msgTimer = findViewById(R.id.msgTimer);
        OtpEditText otpEditText = findViewById(R.id.codeVerify);

        String phone = Objects.requireNonNull(getIntent().getExtras()).getString("phone");

        msgVerify.setText("Biz +998 " + phone + " telefon raqamingizga SMS orqali faollashtirish kodini yubordik.");

        otpEditText.setOnCompleteListener(value -> {
            if (value.equals("12345")) {
                timer.cancel();
                new Session(this).logIn(phone);
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else {
                otpEditText.setError("Kod xato");
            }
        });
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            timer.cancel();
            startActivity(new Intent(VerifyActivity.this, LoginActivity.class));
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}