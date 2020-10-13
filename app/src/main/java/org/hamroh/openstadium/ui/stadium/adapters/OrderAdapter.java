package org.hamroh.openstadium.ui.stadium.adapters;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.hamroh.openstadium.R;
import org.hamroh.openstadium.model.Order;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

class OrderViewHolder extends RecyclerView.ViewHolder {

    public ImageView image;
    public TextView time, day;
    public LinearLayout layout;

    public OrderViewHolder(@NonNull View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.image);
        time = itemView.findViewById(R.id.time);
        day = itemView.findViewById(R.id.day);
        layout = itemView.findViewById(R.id.layout);
    }
}

@SuppressLint("SimpleDateFormat")
public class OrderAdapter extends ListAdapter<Order, RecyclerView.ViewHolder> implements
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private static final DiffUtil.ItemCallback<Order> DIFF_CALLBACK = new DiffUtil.ItemCallback<Order>() {
        @Override
        public boolean areItemsTheSame(@NonNull Order oldItem, @NonNull Order newItem) {
            return oldItem.equals(newItem);
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Order oldItem, @NonNull Order newItem) {
            return oldItem.equals(newItem);
        }
    };

    private static final String TAG = "OrderAdapter";
    Activity activity;
    List<Order> items;
    int lastVisibleItem, totalItemCount;
    private long finishTime, startTime;
    private int year, month, day;
    private TextView setText, txtStart, txtFinish;

    public OrderAdapter(RecyclerView recyclerView, Activity activity, List<Order> items) {
        super(DIFF_CALLBACK);
        this.activity = activity;
        this.items = items;
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                assert linearLayoutManager != null;
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
            }
        });
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_order, parent, false);

        view.setOnClickListener(l -> bottomDialog(1));

        return new OrderViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        OrderViewHolder viewHolder = (OrderViewHolder) holder;
        Order order = items.get(position);

        SimpleDateFormat dayFormat = new SimpleDateFormat("dd-MMMM, YYYY");
        SimpleDateFormat hourFormat = new SimpleDateFormat("hh:mm");

        viewHolder.time.setText(hourFormat.format(order.getFirst()) + " - " + hourFormat.format(order.getLast()));
        viewHolder.day.setText(dayFormat.format(order.getFirst()));

        switch (order.getStatus()) {
            case 0:
                viewHolder.image.setImageResource(R.drawable.ic_outline_free);
                break;
            case 1:
                viewHolder.image.setImageResource(R.drawable.ic_outline_busy);
                break;
            case 2:
                viewHolder.image.setImageResource(R.drawable.ic_outline_wait);
                break;
            default:
                break;
        }

        viewHolder.layout.setOnClickListener(l -> bottomDialog(position));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private void bottomDialog(int pos) {
        Log.e(TAG, "bottomDialog: position = " + pos);
        BottomSheetDialog dialog = new BottomSheetDialog(activity, R.style.BottomSheetDialogTheme);
        View root = LayoutInflater.from(activity)
                .inflate(R.layout.layout_bottom_sheet, activity.findViewById(R.id.bottomSheetContainer));
        dialog.setContentView(root);

        ImageButton btnClose = root.findViewById(R.id.btnClose);
        TextView btnCheck = root.findViewById(R.id.btnCheck);
        txtStart = root.findViewById(R.id.txtStart);
        txtFinish = root.findViewById(R.id.txtFinish);

        txtStart.setOnClickListener(l -> {
            txtStart.setTextColor(Color.parseColor("#000000"));
            setSetText(txtStart);
            setTime();
        });

        txtFinish.setOnClickListener(l -> {
            txtFinish.setTextColor(Color.parseColor("#000000"));
            setSetText(txtFinish);
            setTime();
        });

        btnCheck.setOnClickListener(l -> {

            if (txtStart.getText().toString().equals(activity.getString(R.string.start_time))) {
                txtStart.setTextColor(Color.parseColor("#FF0000"));
                Toast.makeText(activity, "Vaqtni kiriting!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (txtFinish.getText().toString().equals(activity.getString(R.string.finish_time))) {
                txtFinish.setTextColor(Color.parseColor("#FF0000"));
                Toast.makeText(activity, "Vaqtni kiriting!", Toast.LENGTH_SHORT).show();
                return;
            }

            Order task = new Order();
            task.setFirst(startTime);
            task.setLast(finishTime);

            dialog.dismiss();
        });

        btnClose.setOnClickListener(l -> dialog.dismiss());

        dialog.show();
    }

    private void setTime() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(activity, this, year, month, day).show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        this.year = year;
        this.month = month;
        this.day = dayOfMonth;
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        new TimePickerDialog(activity, this, hour, minute, true).show();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, hourOfDay, minute);
        long selectTime = calendar.getTimeInMillis();
        String time = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(new Date(selectTime));
        setText.setText(time);
        if (setText == txtStart) {
            startTime = selectTime;
        } else if (setText == txtFinish) {
            finishTime = selectTime;
        }
    }

    public void setSetText(TextView setText) {
        this.setText = setText;
    }


}
