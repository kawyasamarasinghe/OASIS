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

public class BookVehicle extends AppCompatActivity {

    EditText vehicleName, startingLocation, arrivalLocation, date, noOfPassengers;
    Button bookvehiclebtn, cancelvehiclebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookvehicle);

        vehicleName = (EditText)findViewById(R.id.vehiclecon0);
        startingLocation = (EditText)findViewById(R.id.vehiclecon1);
        arrivalLocation = (EditText)findViewById(R.id.vehiclecon2);
        date = (EditText)findViewById(R.id.vehiclecon3);
        noOfPassengers = (EditText)findViewById(R.id.vehiclecon4);

        bookvehiclebtn = (Button)findViewById(R.id.addnewbookingbtn);
        cancelvehiclebtn = (Button)findViewById(R.id.cancelvehiclebtn);

        bookvehiclebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
                clearAll();
            }
        });
        cancelvehiclebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void insertData()
    {
        Map<String,Object> map = new HashMap<>();
        map.put("vehicleName", vehicleName.getText().toString());
        map.put("startingLocation", startingLocation.getText().toString());
        map.put("arrivalLocation", arrivalLocation.getText().toString());
        map.put("date", date.getText().toString());
        map.put("noOfPassengers", noOfPassengers.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("VehicleBooking").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(BookVehicle.this, "Data Added Successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(BookVehicle.this, "Error While Data Adding", Toast.LENGTH_SHORT).show();
                    }
                });

    }
    private void clearAll()
    {
        vehicleName.setText("");
        startingLocation.setText("");
        arrivalLocation.setText("");
        date.setText("");
        noOfPassengers.setText("");

    }
}