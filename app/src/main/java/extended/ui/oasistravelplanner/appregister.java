package extended.ui.oasistravelplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;




public class appregister extends AppCompatActivity {

    EditText  txt_nameinput, txt_emailinput, txt_pwdinput;
    Button btnsignup,btnsignin;

    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appregister);

        txt_nameinput = findViewById(R.id.txt_hnameinput);
        txt_emailinput = findViewById(R.id.txt_checkdateinput);
        txt_pwdinput = findViewById(R.id.txt_countinput);

        btnsignup = findViewById(R.id.btn_bookinghotelr);
        btnsignin = findViewById(R.id.btn_signin);

        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

       btnsignup.setOnClickListener((view )-> {
           Intent intent = new Intent(appregister.this,login.class);
           startActivity(intent);
       });

       btnsignup.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String txt_nameinputString = txt_nameinput.getText().toString();
               String txt_emailinputString = txt_emailinput.getText().toString();
               String txt_pwdinputString = txt_pwdinput.getText().toString();

               if (TextUtils.isEmpty(txt_nameinputString)){
                   txt_nameinput.setError("Name is required!");
               }
               if (TextUtils.isEmpty(txt_emailinputString)){
                   txt_emailinput.setError("Email is required!");
               }
               if (TextUtils.isEmpty(txt_pwdinputString)){
                   txt_pwdinput.setError("Password is required!");
               }
               else{

                   progressDialog.setMessage("Registration in progress");
                   progressDialog.setCanceledOnTouchOutside(false);
                   progressDialog.show();

                   mAuth.createUserWithEmailAndPassword(txt_emailinputString,txt_pwdinputString).addOnCompleteListener((task) -> {
                       if(task.isSuccessful()){
                           Intent intent = new Intent(appregister.this,login.class);
                           startActivity(intent);
                           finish();
                           progressDialog.dismiss();
                       }else{
                           Toast.makeText(appregister.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                           progressDialog.dismiss();
                       }
                   });
               }
           }
       });

        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(appregister.this,login.class);
                startActivity(intent);
            }
        });
    }


}
