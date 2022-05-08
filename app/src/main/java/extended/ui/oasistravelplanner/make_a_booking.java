package extended.ui.oasistravelplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class make_a_booking extends AppCompatActivity {

    EditText restaurantName,date,time,type,meal,table;
    Button btnAdd,btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_abooking);

        restaurantName = (EditText)findViewById(R.id.r_name);
        date = (EditText)findViewById(R.id.r_date);
        time = (EditText)findViewById(R.id.r_time);
        meal = (EditText)findViewById(R.id.r_meal);
        type = (EditText)findViewById(R.id.r_t_meal);
        table = (EditText)findViewById(R.id.r_tables);

        btnAdd = (Button)findViewById(R.id.b_r_create) ;
        btnBack = (Button)findViewById(R.id.b_r_back);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
                clearAll();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void insertData(){
        Map<String,Object> map = new HashMap<>();
        map.put("restaurantName",restaurantName.getText().toString());
        map.put("date",date.getText().toString());
        map.put("time",time.getText().toString());
        map.put("meal",meal.getText().toString());
        map.put("type",type.getText().toString());
        map.put("table",table.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("RestaurantBookings").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(make_a_booking.this, "Data Inserted successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(make_a_booking.this, "Error while Insertion", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void clearAll(){
        restaurantName.setText("");
        date.setText("");
        time.setText("");
        meal.setText("");
        type.setText("");
        table.setText("");

    }
}