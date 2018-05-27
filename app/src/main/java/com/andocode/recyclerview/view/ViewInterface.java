package com.andocode.recyclerview.view;

import com.andocode.recyclerview.data.ListItem;

import java.util.List;

public interface ViewInterface {
    void startDetailActivity(String dateTime,String message,int color);
    void setUpAdapterAndView(List<ListItem> list);
}
