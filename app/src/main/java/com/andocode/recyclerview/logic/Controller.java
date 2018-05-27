package com.andocode.recyclerview.logic;

import com.andocode.recyclerview.data.DataSourceInterface;
import com.andocode.recyclerview.data.ListItem;
import com.andocode.recyclerview.view.ViewInterface;

import java.util.List;

public class Controller {
    private ViewInterface view;
    private DataSourceInterface dataSource;

    public Controller(ViewInterface view, DataSourceInterface dataSource) {
        this.view = view;
        this.dataSource = dataSource;
        getListFromDatasource();
    }
    void getListFromDatasource(){
        view.setUpAdapterAndView(
                dataSource.getListOfData()
        );

    }
    public void onListItemClicked(ListItem item){
        view.startDetailActivity(
                item.getDateAndTime(),
                item.getMessage(),
                item.getColorResource()
        );


    }
}
