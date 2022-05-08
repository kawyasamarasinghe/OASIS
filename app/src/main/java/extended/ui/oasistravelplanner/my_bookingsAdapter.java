package extended.ui.oasistravelplanner;


import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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

public class my_bookingsAdapter extends FirebaseRecyclerAdapter<my_bookingsModel,my_bookingsAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public my_bookingsAdapter(@NonNull FirebaseRecyclerOptions<my_bookingsModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull my_bookingsModel model) {

        holder.restaurantName.setText(model.getRestaurantName());
        holder.meal.setText(model.getMeal());
        holder.type.setText(model.getType());
        holder.date.setText(model.getDate());
        holder.time.setText(model.getTime());
        holder.table.setText(model.getTable());

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final  DialogPlus dialogPlus = DialogPlus.newDialog(holder.date.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_res_popup))
                        .setExpanded(true,1800)
                        .create();

                View view = dialogPlus.getHolderView();

                EditText restaurantName = view.findViewById(R.id.txtrname);
                EditText meal = view.findViewById(R.id.txtrmeal);
                EditText type = view.findViewById(R.id.txtrtype);
                EditText date = view.findViewById(R.id.txtrdate);
                EditText time = view.findViewById(R.id.txtrtime);
                EditText table = view.findViewById(R.id.txtrtables);

                Button btn_r_edit = view.findViewById(R.id.btnredit);

                restaurantName.setText(model.getRestaurantName());
                meal.setText(model.getMeal());
                type.setText(model.getType());
                date.setText(model.getDate());
                time.setText(model.getTime());
                table.setText(model.getTable());

                dialogPlus.show();

                btn_r_edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("restaurantName",restaurantName.getText().toString());
                        map.put(" meal", meal.getText().toString());
                        map.put("type", type.getText().toString());
                        map.put("date",date.getText().toString());
                        map.put("time",time.getText().toString());
                        map.put("table",table.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("RestaurantBookings")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.restaurantName.getContext(), "Data Updated successfully", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(Exception e) {
                                        Toast.makeText(holder.restaurantName.getContext(), "Error while Editing", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.restaurantName.getContext());
                builder.setTitle("Are you sure?");
                builder.setMessage("Deleted data can't be undo");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("RestaurantBookings")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.restaurantName.getContext(), "Canceled", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.show();
            }
        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        TextView restaurantName,meal,type,date,time,table;

        Button btnEdit,btnDelete;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            restaurantName = (TextView)itemView.findViewById(R.id.name_r_text);
            meal = (TextView)itemView.findViewById(R.id.meal_r_text);
            type = (TextView)itemView.findViewById(R.id.meal_t_text);
            date = (TextView)itemView.findViewById(R.id.date_r_text);
            time = (TextView)itemView.findViewById(R.id.time_r_text);
            table = (TextView)itemView.findViewById(R.id.table_r_text);

            btnEdit = (Button)itemView.findViewById(R.id.rb_edit);
            btnDelete = (Button)itemView.findViewById(R.id.rb_delete);
        }
    }

}
