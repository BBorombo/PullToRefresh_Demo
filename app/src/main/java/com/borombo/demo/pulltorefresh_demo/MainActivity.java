package com.borombo.demo.pulltorefresh_demo;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    private List strings = new ArrayList();
    private ListView list;
    private ArrayAdapter adapter;
    private SwipeRefreshLayout swipe;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
        swipe.setOnRefreshListener(this);
        swipe.setColorSchemeColors(Color.RED);

        list = (ListView) findViewById(R.id.listView);
        for (int i =0; i <20; i++)
            strings.add("Element " + i);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, strings);
        list.setAdapter(adapter);
    }

    @Override
    public void onRefresh() {
        swipe.postDelayed(new Runnable() {
            @Override
            public void run() {

                strings.clear();
                for (int i =0; i <20; i++)
                    strings.add("New Element " + i);

                adapter.notifyDataSetChanged();
                swipe.setRefreshing(false);
            }
        }, 2000);
    }
}
