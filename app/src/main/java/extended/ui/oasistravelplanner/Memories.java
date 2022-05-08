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

import extended.ui.oasistravelplanner.adapter.My_Memories_Adapter;
import extended.ui.oasistravelplanner.model.MemoriesModel;

public class Memories extends AppCompatActivity {

    RecyclerView recyclerView;
    My_Memories_Adapter my_memories_adapter;
    Button button21;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memories);

        button21 = (Button) findViewById(R.id.mm3);
        button21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Memories.this,Memories_Create.class);
                startActivity(i);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<MemoriesModel> options =
                new FirebaseRecyclerOptions.Builder<MemoriesModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Memories"),MemoriesModel.class)
                .build();

        my_memories_adapter = new My_Memories_Adapter(options);
        recyclerView.setAdapter(my_memories_adapter);
    }

    @Override
    protected  void onStart(){
        super.onStart();
        my_memories_adapter.startListening();
    }

    @Override
    protected  void onStop(){
        super.onStop();
        my_memories_adapter.stopListening();
    }
}