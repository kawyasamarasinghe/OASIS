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

public class Memories_Create extends AppCompatActivity {

    EditText date, description,title,place;
    Button add, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memories_create);

        date = (EditText)findViewById(R.id.editTextDate);
        title = (EditText)findViewById(R.id.editText7);
        place = (EditText)findViewById(R.id.textView19);
        description = (EditText)findViewById(R.id.editTextTextMultiLine2);

        add = (Button)findViewById(R.id.btnAdd);
        back = (Button)findViewById(R.id.btnBack);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
                clearAll();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void insertData(){
        Map<String,Object> map = new HashMap<>();
        map.put("date", date.getText().toString());
        map.put("title", title.getText().toString());
        map.put("place", place.getText().toString());
        map.put("description", description.getText().toString());


        FirebaseDatabase.getInstance().getReference().child("Memories").push()
                .setValue(map)

                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(Memories_Create.this, "Data inserted Successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(Memories_Create.this, "Error while insertion", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void clearAll(){
        date.setText("");
        title.setText("");
        place.setText("");
        description.setText("");

    }
}