package extended.ui.oasistravelplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class mybookings extends AppCompatActivity {

    Button btn_addbookings,btn_hotelbookingsall,btn_calc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mybookings);

        btn_addbookings = findViewById(R.id.btn_addbookings);
        btn_hotelbookingsall = findViewById(R.id.btn_hotelbookingsall);
        btn_calc = findViewById(R.id.btn_calc);

        btn_addbookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mybookings.this,hotelbookmain.class);
                startActivity(intent);
            }
        });

        btn_hotelbookingsall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mybookings.this,ViewHotelBookings.class);
                startActivity(intent);
            }
        });

        btn_calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mybookings.this,HotelDiscountCalculator.class);
                startActivity(intent);
            }
        });
    }
}