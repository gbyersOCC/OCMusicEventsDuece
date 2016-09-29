package edu.orangecoastcollege.cs273.gbyers.ocmusicevents;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

import static edu.orangecoastcollege.cs273.gbyers.ocmusicevents.R.id.eventTitleTextView;

/**
 * Created by gbyers on 9/27/2016.
 */

public class EventDetailsActivity extends AppCompatActivity {

    private ImageView eventImageView;

    private Context context= this;

    private TextView eventTitle;
    private TextView eventDate;
    private TextView eventTime;
    private TextView eventLocation;
    private TextView eventAddressOne;
    private TextView eventAddressTwo;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);


        Intent recieved = getIntent();

        String titleText = recieved.getStringExtra("Title");
        String dateText = recieved.getStringExtra("Date");
        String timeText = recieved.getStringExtra("Time");
        String locationText = recieved.getStringExtra("Location");
        String addressTextOne = recieved.getStringExtra("ADD1");
        String addressTextTwo = recieved.getStringExtra("ADD2");

        String imageFileName = titleText.replace(" ","")+".jpeg";

        eventTitle = (TextView) findViewById(eventTitleTextView);
        eventDate = (TextView) findViewById(R.id.eventDateDayTextView);
        eventTime = (TextView) findViewById(R.id.eventTimeTextView);
        eventLocation = (TextView) findViewById(R.id.eventLocationTextView);
        eventAddressOne = (TextView) findViewById(R.id.eventAddressOneTextView);
        eventAddressTwo = (TextView) findViewById(R.id.eventAddressTwoTextView);

        eventImageView = (ImageView) findViewById(R.id.eventImageView);

        //connect AssetManager
        AssetManager am = context.getAssets();

        try{

            InputStream stream = am.open(imageFileName);
            Drawable draImage =  Drawable.createFromStream(stream, titleText);
            eventImageView.setImageDrawable(draImage);

        }catch(IOException e){
            Log.e("OC Music Events", "CANNOT lOAD EVENT"+e.getMessage());
        }


        //set text for corresponding textViews
        eventTitle.setText(titleText);
        eventDate.setText(dateText);
        eventTime.setText(timeText);
        eventLocation.setText(locationText);
        eventAddressOne.setText(addressTextOne);
        eventAddressTwo.setText(addressTextTwo);




    }


}
