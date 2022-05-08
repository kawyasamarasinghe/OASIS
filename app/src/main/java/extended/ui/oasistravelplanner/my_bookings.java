package extended.ui.oasistravelplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class my_bookings extends AppCompatActivity {

    RecyclerView recyclerView;
    my_bookingsAdapter myBookingsAdapter;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bookings);

        recyclerView = (RecyclerView)findViewById(R.id.r_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<my_bookingsModel> options =
                new FirebaseRecyclerOptions.Builder<my_bookingsModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("RestaurantBookings"), my_bookingsModel.class)
                        .build();

        myBookingsAdapter = new my_bookingsAdapter(options);
        recyclerView.setAdapter(myBookingsAdapter);

        floatingActionButton = (FloatingActionButton)findViewById(R.id.b_fabtn) ;
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),make_a_booking.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        myBookingsAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        myBookingsAdapter.stopListening();
    }

}