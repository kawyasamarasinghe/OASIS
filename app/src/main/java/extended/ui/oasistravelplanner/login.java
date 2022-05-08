package extended.ui.oasistravelplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    private EditText i_mail;
    private EditText i_pw;
    private Button b_sign;
    private Button b_l_signup;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        i_mail = findViewById(R.id.i_mail);
        i_pw = findViewById(R.id.i_pw);
        b_sign = findViewById(R.id.b_sign);
        b_l_signup = findViewById(R.id.logsup);

        auth = FirebaseAuth.getInstance();

        b_l_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this,appregister.class);
                startActivity(intent);
            }
        });

        b_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_i_mail = i_mail.getText().toString();
                String txt_i_pw = i_pw.getText().toString();
                loginUser(txt_i_mail,txt_i_pw);
            }
        });
    }

    private void loginUser(String i_mail, String i_pw) {
        auth.signInWithEmailAndPassword(i_mail,i_pw).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(login.this,"login successful",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(login.this, MainActivity.class));
                finish();
            }
        });
    }
}