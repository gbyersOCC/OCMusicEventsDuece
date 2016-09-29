package edu.orangecoastcollege.cs273.gbyers.ocmusicevents;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class EventListActivity extends ListActivity {

    private ListView eventListView;
    private Context context = this;
    private ArrayList<MusicEvent> allMusicEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventListView = (ListView) findViewById(R.id.eventsListView);

        try{
            allMusicEvents = JSONLoader.loadJSONFromAsset(context);
        }catch(IOException ex){
            Log.e("MusicEvent.java"," "+ex.getMessage());

        }
        //setListAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, MusicEvent.titles));
        //dont inflate layot like normal, adapter will do it when using adapter
    setListAdapter(new MusicEventAdapter(context,R.layout.music_event_list_item_layout, allMusicEvents));
        //setContentView(R.layout.activity_event_list);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int pos, long id)
    {

        //get the position
        Intent detailsIntent = new Intent(this, EventDetailsActivity.class);
        MusicEvent clickedEvent = allMusicEvents.get(pos);
        String title = clickedEvent.getTitle();
        String date = clickedEvent.getDate();
        String time = clickedEvent.getTime();
        String location = clickedEvent.getLocation();
        String address1 = clickedEvent.getAddress1();
        String address2 = clickedEvent.getAddress2();




        detailsIntent.putExtra("Title", title);
        detailsIntent.putExtra("Date", date);
        detailsIntent.putExtra("Time", time);
        detailsIntent.putExtra("Location",location);
        detailsIntent.putExtra("ADD1", address1);
        detailsIntent.putExtra("ADD2", address2);

        startActivity(detailsIntent);

    }
}
