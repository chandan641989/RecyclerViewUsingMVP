package com.andocode.recyclerview.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.andocode.recyclerview.R;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_DATE_AND_TIME = "EXTRA_DATE_AND_TIME ";
    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    public static final String EXTRA_COLOR = "EXTRA_COLOR ";
    private View coloredCircle;
    private TextView dateandtime;
    private TextView mesg;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        String dateTime =  getIntent().getExtras().getString(EXTRA_DATE_AND_TIME);
        String message =  getIntent().getExtras().getString(EXTRA_MESSAGE);
        int color = getIntent().getExtras().getInt(EXTRA_COLOR);

        dateandtime = (TextView)findViewById(R.id.tv_time_date_header);
        mesg  = (TextView)findViewById(R.id.messageBody);
        coloredCircle = (View)findViewById(R.id.container_color_background);
        dateandtime.setText(dateTime);
        mesg.setText(message);
        coloredCircle.setBackgroundResource(color);
    }
}

