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

public class HotelAdapter extends FirebaseRecyclerAdapter<HotelModel,HotelAdapter.hotelViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public HotelAdapter(@NonNull FirebaseRecyclerOptions<HotelModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull hotelViewHolder holder, @SuppressLint("RecyclerView")  int position, @NonNull HotelModel model) {
        holder.HotelName.setText(model.getHotelName());

        holder.btnEdithotelrr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.HotelName.getContext())
                        .setContentHolder(new ViewHolder(R.layout.updatehotel_popup))
                        .setExpanded(true,1200)
                        .create();

                //dialogPlus.show();

                View view =dialogPlus.getHolderView();

                EditText HotelName = view.findViewById(R.id.txthname);
                EditText CheckInDate = view.findViewById(R.id.txtcheckinhotel);
                EditText CheckOutDate = view.findViewById(R.id.txtcheckouthotel);
                EditText Price = view.findViewById(R.id.txtpricehotel);

                Button btnupdatehotel = view.findViewById(R.id.btnupdatehotel);

                HotelName.setText(model.getHotelName());
                CheckInDate.setText(model.getCheckInDate());
                CheckOutDate.setText(model.getCheckOutDate());
                Price.setText(model.getPrice());

                dialogPlus.show();


                btnupdatehotel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map=new HashMap<>();
                        map.put("HotelName",HotelName.getText().toString());
                        map.put("CheckInDate",CheckInDate.getText().toString());
                        map.put("CheckOutDate",CheckOutDate.getText().toString());
                        map.put("Price",Price.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("HotelBookings")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.HotelName.getContext(), "Updated Successfully!", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.HotelName.getContext(), "Error While Updating!", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });

            }
        });

        holder.btnDeletehotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder((holder.HotelName.getContext()));
                builder.setTitle("Are you sure?");
                builder.setMessage("Deleted booking can't be undo.");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("HotelBookings")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.HotelName.getContext(), "cancelled.", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });



    }

    @NonNull
    @Override
    public hotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_item,parent,false);
        return new hotelViewHolder(view);
    }

    class hotelViewHolder extends RecyclerView.ViewHolder{
        TextView HotelName,CheckInDate,CheckOutDate,Price;

        Button btnEdithotelrr,btnDeletehotel;

        public hotelViewHolder(@NonNull View itemView) {
            super(itemView);

            HotelName = (TextView)itemView.findViewById(R.id.hotelnameview);
            CheckInDate = (TextView)itemView.findViewById(R.id.checkinview);
            CheckOutDate = (TextView)itemView.findViewById(R.id.checkoutview);
            Price = (TextView)itemView.findViewById(R.id.priceview);


            btnEdithotelrr = (Button)itemView.findViewById(R.id.btnEdithotelrr);
            btnDeletehotel = (Button)itemView.findViewById(R.id.btnDeletehotel);

        }
    }
}
