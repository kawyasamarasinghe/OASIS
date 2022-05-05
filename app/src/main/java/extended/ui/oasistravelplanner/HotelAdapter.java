package extended.ui.oasistravelplanner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

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
    protected void onBindViewHolder(@NonNull hotelViewHolder holder, int position, @NonNull HotelModel model) {
        holder.HotelName.setText(model.getHotelName());


    }

    @NonNull
    @Override
    public hotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_item,parent,false);
        return new hotelViewHolder(view);
    }

    class hotelViewHolder extends RecyclerView.ViewHolder{
        TextView HotelName;

        public hotelViewHolder(@NonNull View itemView) {
            super(itemView);

            HotelName = (TextView)itemView.findViewById(R.id.hotelnameview);

        }
    }
}
