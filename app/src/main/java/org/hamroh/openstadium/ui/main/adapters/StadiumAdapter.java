package org.hamroh.openstadium.ui.main.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.hamroh.openstadium.R;
import org.hamroh.openstadium.model.Stadium;
import org.hamroh.openstadium.ui.stadium.StadiumActivity;

import java.util.List;

public class StadiumAdapter extends BaseAdapter {

    private Activity activity;
    private List<Stadium> data;
    private LayoutInflater inflater;

    public StadiumAdapter(Activity activity, List<Stadium> data) {
        this.activity = activity;
        this.data = data;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getId();
    }

    @SuppressLint({"InflateParams", "ViewHolder", "SetTextI18n"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_stadium, null);

        ImageView imgStadium = view.findViewById(R.id.imgStadium);
        TextView txtName = view.findViewById(R.id.nameStadium);
        TextView txtDistance = view.findViewById(R.id.txtDistance);
        TextView txtLikes = view.findViewById(R.id.txtLikes);
        TextView txtPrice = view.findViewById(R.id.txtPrice);

        imgStadium.setImageResource(Integer.parseInt(data.get(position).getImage()));
        txtName.setText(data.get(position).getName());
        txtDistance.setText(data.get(position).getDistance() + "");
        txtLikes.setText(data.get(position).getLikes() + "");
        txtPrice.setText(data.get(position).getPrice() + "");

        view.setOnClickListener(l ->
                activity.startActivity(new Intent(activity, StadiumActivity.class)
                        .putExtra("name", data.get(position).getName())
                        .putExtra("distance", data.get(position).getDistance())
                        .putExtra("price", data.get(position).getPrice())
                        .putExtra("likes", data.get(position).getLikes())
                        .putExtra("image", data.get(position).getImage())));

        return view;
    }
}
