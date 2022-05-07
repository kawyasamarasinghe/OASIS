package extended.ui.oasistravelplanner;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HotelDiscountCalculator extends AppCompatActivity {

    EditText etdischotel;
    Button btndisccalculate;
    RadioButton rdbtnfam,rdbtnholiday;
    TextView tvdiscount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_discount_calculator);

        etdischotel = findViewById(R.id.et_dischotel);
        btndisccalculate = findViewById(R.id.btn_disccalculate);
        rdbtnfam = findViewById(R.id.rd_btn_fam);
        rdbtnholiday = findViewById(R.id.rd_btn_holiday);
        tvdiscount = findViewById(R.id.tv_discount);
    }
    @Override
    protected void onResume() {
        super.onResume();
        btndisccalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateDiscount();
            }
        });
}
    public void calculateDiscount(){
        CalculationsDiscount cal = new CalculationsDiscount();
        String value = etdischotel.getText().toString();

        if(TextUtils.isEmpty(value)){
            Toast.makeText(this, "Please enter the Hotel price", Toast.LENGTH_SHORT).show();
        } else {

            Float disc = Float.parseFloat(value);
            if(rdbtnfam.isChecked()){
                disc = cal.calcfamilydiscount(disc);
            }else if(rdbtnholiday.isChecked()){
                disc = cal.calcholidaydiscount(disc);
            } else{
                Toast.makeText(this, "Select the Discount type", Toast.LENGTH_SHORT).show();
                disc = 0.0f;
            }
            tvdiscount.setText(new Float(disc).toString());
        }
    }
}