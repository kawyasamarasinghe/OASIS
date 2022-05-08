package extended.ui.oasistravelplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class DistanceCalculator extends AppCompatActivity {

    EditText etSource , etDestination;
    TextView tvDistance;
    String sType;
    double lat1 = 0, long1 = 0, lat2 = 0, long2 = 0;
    int flag = 0;
    double distance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance_calculator);

        //Assign variable
        etSource = findViewById(R.id.et_temp);
        etDestination = findViewById(R.id.et_destLocation);
        tvDistance = findViewById(R.id.tv_calculatedTemp);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("DistanceCalculator");

//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setDisplayShowHomeEnabled(true);

        //Initialize places
        Places.initialize(getApplicationContext(), "AIzaSyBIRyqmeAxiGxSxZQdiyz_mXAkwAQA60kc");

        //Set edit text non focusable
        etSource.setFocusable(false);
        etSource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Define type
                sType = "source";
                //Initialize place field list
                List<Place.Field> fields = Arrays.asList(Place.Field.ADDRESS
                        , Place.Field.LAT_LNG);

                //Create intent
                Intent intent = new Autocomplete.IntentBuilder(
                        AutocompleteActivityMode.OVERLAY, fields
                ).build(DistanceCalculator.this);

                //Start activity result
                startActivityForResult(intent, 100);
            }
        });

        //Set edit text non focusable
        etDestination.setFocusable(false);
        etDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Define type
                sType = "destination";
                //Initialize place field list
                List<Place.Field> fields = Arrays.asList(Place.Field.ADDRESS
                        , Place.Field.LAT_LNG);

                //Create intent
                Intent intent = new Autocomplete.IntentBuilder(
                        AutocompleteActivityMode.OVERLAY, fields
                ).build(DistanceCalculator.this);

                //Start activity result
                startActivityForResult(intent, 100);
            }
        });

        //set text on text view
        tvDistance.setText("0.0 Kilometers");

//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                tvDistance.setText(String.format(Locale.US, "%2f Kilometers", distance));
//            }
//        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        //Check condition
        if(requestCode == 100 && resultCode == RESULT_OK){
            //When success
            //Initialize place
            Place place = Autocomplete.getPlaceFromIntent(data);
            //Check condition
            if (sType.equals("source")){
                //When type is source
                //Increase flag value
                flag++;
                //Set address on edit text
                etSource.setText(place.getAddress());
                //Get latitude and longitude
                String sSource = String.valueOf(place.getLatLng());
                sSource = sSource.replaceAll("lat/lng:", "");
                sSource = sSource.replace("(", "");
                sSource = sSource.replace(")", "");
                String [] split = sSource.split(",");
                lat1 = Double.parseDouble(split[0]);
                long1 = Double.parseDouble(split[1]);
            }else{
                //When type is destination
                //Increase flag value
                flag++;
                //Set address on edit text
                etDestination.setText(place.getAddress());
                //Get latitude and longitude
                String sDestination = String.valueOf(place.getLatLng());
                sDestination = sDestination.replaceAll("lat/lng:", "");
                sDestination = sDestination.replace("(", "");
                sDestination = sDestination.replace(")", "");
                String [] split = sDestination.split(",");
                lat2 = Double.parseDouble(split[0]);
                long2 = Double.parseDouble(split[1]);
            }

            //Check condition
            if(flag >=2){
                //When flag is greater than and equal to 2
                //Calculate distance
                distance(lat1,long1, lat2,long2);
            }
        }else if (requestCode == AutocompleteActivity.RESULT_ERROR){
            //When error
            //Initialize status
            Status status = Autocomplete.getStatusFromIntent(data);
            Toast.makeText(getApplicationContext(), status.getStatusMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void distance(double lat1, double long1, double lat2, double long2) {
        DistanceCalculations calculations = new DistanceCalculations();
        //Calculate longitude difference
        double longDiff = long1 - long2;
        //Calculate distance
        double distance = Math.sin(calculations.deg2rad(lat1))
                * Math.sin(calculations.deg2rad(lat2))
                + Math.cos(calculations.deg2rad(lat1))
                * Math.cos(calculations.deg2rad(lat2))
                * Math.cos(calculations.deg2rad(longDiff));
        distance = Math.acos(distance);
        //Convert distance radian to degree
        distance = rad2deg(distance);
        //Distance in miles
        distance= distance * 60 * 1.1515;
        //Distance in kilometers
        distance = distance * 1.609344;
        //Set distance on text view
        tvDistance.setText(String.format(Locale.US, "%2f Kilometers", distance));

    }

    private double rad2deg(double distance) {
        return(distance * 180.0 / Math.PI);
    }


}