package extended.ui.oasistravelplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Currency_Convertor extends AppCompatActivity {

    Spinner from_currency, to_currency;
    EditText currency, result;
    Button convertBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_convertor);

        currency = (EditText) findViewById(R.id.textxField1);
        from_currency = (Spinner) findViewById(R.id.from_currency);
        to_currency = (Spinner) findViewById(R.id.to_currency);
        convertBtn = (Button) findViewById(R.id.convert);
        result = (EditText) findViewById(R.id.result);


        String[] from = {"USD"};
        ArrayAdapter ad = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,from);
        from_currency.setAdapter(ad);

        String[] to = {"Indian Rupees","Sri Lankan Rupees"};
        ArrayAdapter ad1 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,to);
        to_currency.setAdapter(ad1);

        convertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void  onClick(View v){
                Double tot;
                Double amount = Double.parseDouble(currency.getText().toString());
                if(from_currency.getSelectedItem().toString() .equals("USD" ) && to_currency.getSelectedItem().toString().equals( "Indian Rupees"))
                {
                    tot = amount * 70.0;
                    result.setText(String.format("Answer: %.2f", tot));
                    Toast.makeText(getApplicationContext(),tot.toString(),Toast.LENGTH_LONG).show();
                }

                else if(from_currency.getSelectedItem().toString() .equals("USD")  && to_currency.getSelectedItem().toString().equals("Sri Lankan Rupees"))
                {
                    tot = amount * 180.0;
                    result.setText(String.format("Answer: %.2f", tot));
                    Toast.makeText(getApplicationContext(),tot.toString(),Toast.LENGTH_LONG).show();
                }

            }

        });

    }
}