package extended.ui.oasistravelplanner.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import extended.ui.oasistravelplanner.R;
import extended.ui.oasistravelplanner.model.MemoriesModel;

public class My_Memories_Adapter extends FirebaseRecyclerAdapter<MemoriesModel, My_Memories_Adapter.myViewHolder> {

    public My_Memories_Adapter(@NonNull FirebaseRecyclerOptions<MemoriesModel> options){
        super(options);
    }

    @Override
    //added the final keyword
    protected void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") final int position, @NonNull MemoriesModel model){
        holder.title.setText(model.getTitle());
        holder.place.setText(model.getPlace());
        holder.date.setText(model.getDate());
        holder.description.setText(model.getDescription());

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.title.getContext())
                        .setContentHolder(new ViewHolder(R.layout.activity_memories_update))
                        .setExpanded(true, 1150)
                        .create();

                //dialogPlus.show();

                View view1 = dialogPlus.getHolderView();

                EditText title = view1.findViewById(R.id.editTitleTxt);
                EditText place = view1.findViewById(R.id.editPlaceTxt);
                EditText date = view1.findViewById(R.id.editDateTxt);
                EditText description = view1.findViewById(R.id.editDescriptionTxt);

                Button btnUpdate = view.findViewById(R.id.edit_view);

                //title.setText(model)
                title.setText(model.getTitle());
                place.setText(model.getPlace());
                date.setText(model.getDate());
                description.setText(model.getDescription());

                dialogPlus.show();

              /*  btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String, Object> map=new HashMap<>();
                        map.put("title", title.getText().toString());
                        map.put("place", place.getText().toString());
                        map.put("date", date.getText().toString());
                        map.put("description", description.getText().toString());


                        FirebaseDatabase.getInstance().getReference().child("Memories")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.title.getContext(), "Updated Successfully", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })

                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(Exception e) {
                                        Toast.makeText(holder.title.getContext(), "Error in Updating", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });*/
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.title.getContext());
                builder.setTitle("Are You Sure?");
                builder.setMessage("Deleted data can't be Undo");

                builder.setPositiveButton("Delete",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        FirebaseDatabase.getInstance().getReference().child("Memories")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });

                builder.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        Toast.makeText(holder.title.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.show();
            }
        });

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.memories_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        TextView title, place, date, description;
        Button btnEdit, btnDelete;

        public  myViewHolder(@NonNull View itemView){
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.viewTitletxt);
            place = (TextView) itemView.findViewById(R.id.viewPlacetxt);
            date = (TextView) itemView.findViewById(R.id.viewDatetxt);
            description = (TextView) itemView.findViewById(R.id.viewDesctxt);

            btnEdit = (Button) itemView.findViewById(R.id.edit_view);
            btnDelete = (Button) itemView.findViewById(R.id.delete_view);

        }
    }
}
