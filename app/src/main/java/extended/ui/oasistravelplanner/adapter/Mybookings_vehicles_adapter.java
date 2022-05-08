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
import extended.ui.oasistravelplanner.model.Mybookings_vehicles_model;


public class Mybookings_vehicles_adapter extends FirebaseRecyclerAdapter<Mybookings_vehicles_model,Mybookings_vehicles_adapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public Mybookings_vehicles_adapter(@NonNull FirebaseRecyclerOptions<Mybookings_vehicles_model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") final int position, @NonNull Mybookings_vehicles_model model) {
        holder.vehicleName.setText(model.getVehicleName());
        holder.startingLocation.setText(model.getStartingLocation());
        holder.arrivalLocation.setText(model.getArrivalLocation());
        holder.date.setText(model.getDate());
        holder.noOfPassengers.setText(model.getNoOfPassengers());


        holder.editvehiclebtn_mybookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.arrivalLocation.getContext())
                        .setContentHolder(new ViewHolder(R.layout.updatevehicle_popup))
                        .setExpanded(true,1200)
                        .create();

                //dialogPlus.show();

               View view =  dialogPlus.getHolderView();

                EditText vehicleName = view.findViewById(R.id.updatevehiclecon0);
                EditText startingLocation = view.findViewById(R.id.updatevehiclecon1);
                EditText arrivalLocation = view.findViewById(R.id.updatevehiclecon2);
                EditText date = view.findViewById(R.id.updatevehiclecon3);
                EditText noOfPassengers = view.findViewById(R.id.updatevehiclecon4);

                Button updatelbooking_vehicle_k2 = view.findViewById(R.id.updatelbooking_vehicle_k2);

                vehicleName.setText(model.getVehicleName());
                startingLocation.setText(model.getStartingLocation());
                arrivalLocation.setText(model.getArrivalLocation());
                date.setText(model.getDate());
                noOfPassengers.setText(model.getNoOfPassengers());

                dialogPlus.show();

                updatelbooking_vehicle_k2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("vehicleName",vehicleName.getText().toString());
                        map.put("startingLocation",startingLocation.getText().toString());
                        map.put("arrivalLocation",arrivalLocation.getText().toString());
                        map.put("date",date.getText().toString());
                        map.put("noOfPassengers",noOfPassengers.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("VehicleBooking")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.vehicleName.getContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(Exception e) {
                                        Toast.makeText(holder.vehicleName.getContext(), "Error While Updating", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });
            }
        });

        holder.deletevehiclebtn_mybookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.vehicleName.getContext());
                builder.setTitle("Are You Sure?");
                builder.setMessage("Deleted data can't be Undo");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        FirebaseDatabase.getInstance().getReference().child("VehicleBooking")
                                .child(getRef(position).getKey()).removeValue();

                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        Toast.makeText(holder.vehicleName.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mybookings_vehicles_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        TextView vehicleName, startingLocation, arrivalLocation, date, noOfPassengers;

        Button editvehiclebtn_mybookings,deletevehiclebtn_mybookings;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            vehicleName = (TextView)itemView.findViewById(R.id.vehicleNametxt);
            startingLocation = (TextView)itemView.findViewById(R.id.startingLocationtxt);
            arrivalLocation = (TextView)itemView.findViewById(R.id.arrivalLocationtxt);
            date = (TextView)itemView.findViewById(R.id.datetxt);
            noOfPassengers = (TextView)itemView.findViewById(R.id.noOfPassengerstxt);

            editvehiclebtn_mybookings = (Button)itemView.findViewById(R.id.editvehiclebtn_mybookings);
            deletevehiclebtn_mybookings = (Button)itemView.findViewById(R.id.deletevehiclebtn_mybookings);


        }
    }
}
