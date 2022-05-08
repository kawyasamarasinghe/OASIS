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
