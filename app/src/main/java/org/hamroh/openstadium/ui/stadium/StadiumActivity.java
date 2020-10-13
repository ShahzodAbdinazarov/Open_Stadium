package org.hamroh.openstadium.ui.stadium;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.hamroh.openstadium.R;
import org.hamroh.openstadium.model.Order;
import org.hamroh.openstadium.ui.stadium.adapters.OrderAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class StadiumActivity extends AppCompatActivity {

    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stadium);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        CollapsingToolbarLayout toolBarLayout = findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle("Stadion nomi");

        ImageView colImage = findViewById(R.id.colImage);
        TextView colLikes = findViewById(R.id.colLikes);
        TextView colPrice = findViewById(R.id.colPrice);
        TextView colDistance = findViewById(R.id.colDistance);

        Bundle extras = getIntent().getExtras();

        colImage.setImageResource(Integer.parseInt(extras.getString("image")));
        colDistance.setText(extras.getString("distance"));
        colPrice.setText(extras.getString("price"));
        colLikes.setText(extras.getString("likes"));
        toolBarLayout.setTitle(extras.getString("name"));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> fab.setImageResource((i++ % 2 == 1) ? R.drawable.ic_liked : R.drawable.ic_like));

        RecyclerView listOrder = findViewById(R.id.listOrder);
        listOrder.setLayoutManager(new LinearLayoutManager(this));

        List<Order> data = new ArrayList<>();
        long time = Calendar.getInstance().getTimeInMillis();
        for (int i = 0; i < 20; i++) data.add(new Order(i, "Lorem ipsum", time, time, i % 3));
        OrderAdapter adapter = new OrderAdapter(listOrder, this, data);
        listOrder.setAdapter(adapter);

    }
}