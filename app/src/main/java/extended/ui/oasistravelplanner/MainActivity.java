package extended.ui.oasistravelplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Interpolator;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.currencycon:
                        startActivity(new Intent(getApplicationContext()
                                ,Currency_Convertor.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.compass:
                        startActivity(new Intent(getApplicationContext()
                                ,Compass.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.tempcal:
                        startActivity(new Intent(getApplicationContext()
                                ,Temperature_Calculator.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        return true;

                }
                return false;
            }
        });



        logout = findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(MainActivity.this,"logged Out",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, login.class));
            }
        });

    }

    public void openHotel(View view) {
        Intent intent = new Intent(this, SearchLocation.class);
        startActivity(intent);
    }
    public void openMemories(View view) {
        Intent intent = new Intent(this, Memories_Create.class);
        startActivity(intent);
    }
    public void openRestuarants(View view) {
        Intent intent = new Intent(this, home_booking.class);
        startActivity(intent);
    }
    public void openTravelway(View view) {
        Intent intent = new Intent(this, MyVehicles.class);
        startActivity(intent);
    }
}