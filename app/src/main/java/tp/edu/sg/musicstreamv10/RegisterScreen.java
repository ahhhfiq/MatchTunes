package tp.edu.sg.musicstreamv10;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterScreen extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText inputEmail;
    private EditText inputPass;
    private Button btnSubmit;
    private EditText reputPass;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        //ActionBar actionBar = getSupportActionBar();

        //actionBar.hide();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Typeface logoDesign = Typeface.createFromAsset(getApplicationContext().getAssets(), "ARCENA.ttf");
        TextView logoSign = (TextView) findViewById(R.id.logo);
        logoSign.setTypeface(logoDesign);

        auth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        onEnter();
    }

    private void createAcc(){

        inputEmail = (EditText) findViewById(R.id.email);
        inputPass = (EditText) findViewById(R.id.password);
        reputPass = (EditText) findViewById(R.id.retypePassword);

        btnSubmit = (Button) findViewById(R.id.submit);


        String email    = inputEmail.getText().toString().trim();
        String password = inputPass.getText().toString().trim();
        String retype   = reputPass.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(),"Enter password!",Toast.LENGTH_LONG).show();
            return;
        }
        if(password.length() < 6){
            Toast.makeText(getApplicationContext(), "Password too short", Toast.LENGTH_LONG).show();
            return;
        }
        if(!password.equals(retype)){
            Toast.makeText(getApplicationContext(),"Password does not match", Toast.LENGTH_LONG).show();
            System.out.println(retype);
            System.out.println(password);
            return;
        }

        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>(){
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterScreen.this,"Successfully registered",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(),home.class));
                            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        }
                        else {
                            Toast.makeText(RegisterScreen.this,"Registration Error",Toast.LENGTH_LONG).show();
                            Log.i("Response","Failed to create user:"+task.getException().getMessage());
                        }
                        progressDialog.dismiss();
                    }
                });
    }

    public void onEnter(){

        reputPass = (EditText) findViewById(R.id.retypePassword);

        reputPass.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if(event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_ENTER:
                            createAcc();
                            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                            return true;
                        default:
                            break;

                    }
                }

                return false;
            }
        });

    }

    public void handleRegistration(View view) {
        createAcc();
        System.out.println("Clicked");
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }

    public void handleLogin(View view) {
        Intent logScreen = new Intent(this, LoginScreen.class);
        startActivity(logScreen);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }


    public void viewPass(View view) {//TODO: Redo viewPass n reviewPass and separate it

        final EditText passwordText = (EditText)findViewById(R.id.retypePassword);
        final Button  viewPassword  = (Button)findViewById(R.id.rePassViewer);


        viewPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN: {
                        passwordText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
                        viewPassword.setBackgroundResource(R.drawable.eyeopen);
                        return true;
                    }
                    case MotionEvent.ACTION_CANCEL:
                        passwordText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        viewPassword.setBackgroundResource(R.drawable.eyeclose);

                    case MotionEvent.ACTION_UP: {
                        passwordText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        viewPassword.setBackgroundResource(R.drawable.eyeclose);

                        return true;
                    }
                    default:
                        return false;
                }
            }

        });
    }



    public void reviewPass(View view) {
        final EditText retypePass   = (EditText)findViewById(R.id.password);
        final Button  reviewPassword  = (Button)findViewById(R.id.passViewer);


        reviewPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN: {
                        retypePass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
                        reviewPassword.setBackgroundResource(R.drawable.eyeopen);
                        return true;
                    }
                    case MotionEvent.ACTION_CANCEL:
                        retypePass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        reviewPassword.setBackgroundResource(R.drawable.eyeclose);

                    case MotionEvent.ACTION_UP: {
                        retypePass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        reviewPassword.setBackgroundResource(R.drawable.eyeclose);

                        return true;
                    }
                    default:
                        return false;
                }
            }

        });
    }
}
