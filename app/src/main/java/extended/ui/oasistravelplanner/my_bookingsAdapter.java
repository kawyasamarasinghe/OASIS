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
