package extended.ui.oasistravelplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Temperature_Calculator extends AppCompatActivity {

    Button ansBtn, againBtn;
    TextView showTemperature;
    EditText getVal;
    RadioButton c2f,f2c;
    Double x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_calculator);

        ansBtn = (Button) findViewById(R.id.ansBtn);
        //showAns
        showTemperature = (TextView) findViewById(R.id.showAns);
        getVal = (EditText) findViewById(R.id.getval);
        c2f = (RadioButton) findViewById(R.id.c2f);
        f2c = (RadioButton) findViewById(R.id.f2c);
        againBtn = (Button) findViewById(R.id.againBtn);
        ansBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                if (getVal.getText().toString().isEmpty()){
                    showTemperature.setText("Please give temperature");
                }
                else{
                    x = Double.parseDouble(String.valueOf(getVal.getText()));

                    if(c2f.isChecked()){
                        x = (x*9)/5+32;
                        //this is Equation
                        x = Double.parseDouble(new DecimalFormat("##.###").format(x));
                        showTemperature.setText(String.valueOf(x) + "Degree F");
                    }

                    else if (f2c.isChecked()){
                        x = (x-32)*5/9;
                        x = Double.parseDouble(new DecimalFormat("##.###").format(x));
                        showTemperature.setText(String.valueOf(x) + "Degee C");
                    }

                    else{
                        showTemperature.setText("Please select an option !");
                    }
                }
            }
        });

        againBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTemperature.setText("0.0");
                getVal.setText("");
                c2f.setChecked(false);
                f2c.setChecked(false);
            }
        });
    }
}