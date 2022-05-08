package extended.ui.oasistravelplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class home_booking extends AppCompatActivity {

    Button b_res_book,rmybookings,btipcal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_booking);

        b_res_book = findViewById(R.id.b1);
        rmybookings = findViewById(R.id.btn_bookings);
        btipcal = findViewById(R.id.bTip);

        btipcal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(home_booking.this,tip_calculator.class);
                startActivity(intent);
            }
        });

        b_res_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(home_booking.this,make_a_booking.class);
                startActivity(intent);
            }
        });

        rmybookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(home_booking.this,my_bookings.class);
                startActivity(intent);
            }
        });
    }
}