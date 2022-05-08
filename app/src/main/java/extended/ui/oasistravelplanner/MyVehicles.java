package extended.ui.oasistravelplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MyVehicles extends AppCompatActivity {

    Button bookvehiclebutton, mybookings_k, distancecalcu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_vehicles);

        bookvehiclebutton = (Button) findViewById(R.id.bookvehiclebutton);
        mybookings_k = (Button) findViewById(R.id.mybookings_k);
        distancecalcu = (Button) findViewById(R.id.distancecalcu);


        bookvehiclebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MyVehicles.this, BookVehicle.class);
                startActivity(i);
                finish();
            }
        });

        mybookings_k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MyVehicles.this, MybookingsVehicles.class);
                startActivity(i);
            }
        });
        distancecalcu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MyVehicles.this, DistanceCalculator.class);
                startActivity(i);
            }
        });


    }
}