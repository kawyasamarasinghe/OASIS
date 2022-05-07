package extended.ui.oasistravelplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class tip_calculator extends AppCompatActivity {

    EditText txt_amount;
    TextView txt_percent,t_tip,t_total;
    SeekBar s_percent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculator);

        txt_amount = findViewById(R.id.txt_amount);
        txt_percent = findViewById(R.id.txt_percent);
        t_tip = findViewById(R.id.t_tip);
        t_total = findViewById(R.id.t_total);
        s_percent = findViewById(R.id.s_percent);

        s_percent.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int percent = i;
                txt_percent.setText(String.valueOf(percent)+"%");
                calculate();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        txt_amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                calculate();
            }
        });
    }

    private void calculate(){
        if(txt_amount.length()==0){
            txt_amount.requestFocus();
            txt_amount.setError("Enter Amount");
        }else{
            double amount = Double.parseDouble(txt_amount.getText().toString());
            int percent = s_percent.getProgress();
            double tip = amount*percent/100.0;
            double total = amount + tip;
            t_tip.setText(String.valueOf(tip));
            t_total.setText(String.valueOf(total));
        }
    }
}