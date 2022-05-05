package extended.ui.oasistravelplanner;

import androidx.annotation.NonNull;
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


public class hotelbookmain extends AppCompatActivity {

    EditText txt_hnameinput,txt_checkdateinput,txt_checkoutinput,txt_countinput;
    Button btn_bookinghotelr,btn_cancelupdater;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotelbookmain);

        txt_hnameinput = findViewById(R.id.txt_hnameinput);
        txt_checkdateinput = findViewById(R.id.txt_checkdateinput);
        txt_checkoutinput = findViewById(R.id.txt_checkoutinput);
        txt_countinput = findViewById(R.id.txt_countinput);

        btn_bookinghotelr = findViewById(R.id.btn_bookinghotelr);
        btn_cancelupdater = findViewById(R.id.btn_cancelupdater);

        btn_bookinghotelr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
                clearAll();
            }
        });

        btn_cancelupdater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void insertData()
    {
        Map<String,Object> map = new HashMap<>();
        map.put("HotelName",txt_hnameinput.getText().toString());
        map.put("CheckInDate ",txt_checkdateinput.getText().toString());
        map.put("CheckOutDate",txt_checkoutinput.getText().toString());
        map.put("Price", txt_countinput.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("HotelBookings").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(hotelbookmain.this, "Hotel booking added successfully.", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(hotelbookmain.this, "Error while insertion!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void clearAll()
    {
        txt_hnameinput.setText("");
        txt_checkdateinput.setText("");
        txt_checkoutinput.setText("");
        txt_countinput.setText("");
    }
}