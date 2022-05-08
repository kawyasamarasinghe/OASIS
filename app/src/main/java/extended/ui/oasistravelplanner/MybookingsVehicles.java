package extended.ui.oasistravelplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import extended.ui.oasistravelplanner.adapter.Mybookings_vehicles_adapter;
import extended.ui.oasistravelplanner.model.Mybookings_vehicles_model;

public class MybookingsVehicles extends AppCompatActivity {

    RecyclerView recyclerView;
    Mybookings_vehicles_adapter mybookings_vehicles_adapter;
    Button addnewbookingbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mybookings_vehicles);

        addnewbookingbtn = (Button) findViewById(R.id.addnewbookingbtn);

        addnewbookingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MybookingsVehicles.this, MyVehicles.class);
                startActivity(i);
            }
        });

        recyclerView = (RecyclerView)findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Mybookings_vehicles_model> options =
                new FirebaseRecyclerOptions.Builder<Mybookings_vehicles_model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("VehicleBooking"), Mybookings_vehicles_model.class)
                        .build();

        mybookings_vehicles_adapter = new Mybookings_vehicles_adapter(options);
        recyclerView.setAdapter(mybookings_vehicles_adapter);

    }

    public MybookingsVehicles() {
        super();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mybookings_vehicles_adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mybookings_vehicles_adapter.stopListening();
    }
}