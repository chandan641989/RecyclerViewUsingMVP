package com.andocode.recyclerview.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andocode.recyclerview.R;
import com.andocode.recyclerview.data.DataSourceInterface;
import com.andocode.recyclerview.data.FakeDataSource;
import com.andocode.recyclerview.data.ListItem;
import com.andocode.recyclerview.logic.Controller;

import java.util.List;
import java.util.ResourceBundle;
import java.util.zip.Inflater;

public class ListActivity extends AppCompatActivity  implements ViewInterface{

    public static final String EXTRA_DATE_AND_TIME = "EXTRA_DATE_AND_TIME ";
    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    public static final String EXTRA_COLOR = "EXTRA_COLOR ";
    private List<ListItem> listOfData;
    private LayoutInflater layoutInflater;
    private RecyclerView recyclerView;
    private CustomAdapter customAdapter;
    private Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        layoutInflater = getLayoutInflater();
        controller = new Controller(this, new FakeDataSource());
    }

    @Override
    public void startDetailActivity(String dateTime, String message, int color) {
        Intent intent  = new Intent(this,DetailActivity.class);
        intent.putExtra(EXTRA_DATE_AND_TIME,dateTime);
        intent.putExtra(EXTRA_MESSAGE,message);
        intent.putExtra(EXTRA_COLOR,color);
        startActivity(intent);
    }

    @Override
    public void setUpAdapterAndView(List<ListItem> list) {
        this.listOfData = list;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        customAdapter = new CustomAdapter();
        recyclerView.setAdapter(customAdapter);

    }
    private class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder>{

        @NonNull
        @Override
        public CustomAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = layoutInflater.inflate(R.layout.item_data,parent, false);

            return new CustomViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CustomAdapter.CustomViewHolder holder, int position) {
            ListItem item = listOfData.get(position);
            holder.dateandtime.setText(item.getDateAndTime());
            holder.message.setText(item.getMessage());
            holder.coloredCircle.setBackgroundResource(item.getColorResource());
        }

        @Override
        public int getItemCount() {
            return listOfData.size();
        }

        public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            private View coloredCircle;
            private TextView dateandtime;
            private TextView message;
            private ViewGroup container;

            public CustomViewHolder(View itemView) {
                super(itemView);
                this.coloredCircle = itemView.findViewById(R.id.imv_list_item_circle);
                this.dateandtime = (TextView) itemView.findViewById(R.id.tv_date_and_time);
                this.message = (TextView) itemView.findViewById(R.id.tv_msg);
                this.container = (ViewGroup) itemView.findViewById(R.id.root_list_item);
                this.container.setOnClickListener(this);

            }

            @Override
            public void onClick(View v) {
                ListItem listItem = listOfData.get(
                  this.getAdapterPosition()
                );
                controller.onListItemClicked(listItem);

            }
        }
    }
}
