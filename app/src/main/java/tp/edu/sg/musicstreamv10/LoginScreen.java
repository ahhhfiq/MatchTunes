package tp.edu.sg.musicstreamv10;


/******************************
//Create an account or use    \\
//Email:    admin@gmail.com    \\
//Password: password            \\
********************************/


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
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

public class LoginScreen extends AppCompatActivity {
    private FirebaseAuth auth;
    private ProgressDialog progressDialog;
    private EditText editTextemail;
    private EditText editTextpassword;
    private TextView quotes;
    public int change = 0;
    private int devchange = 0;



    String [] qotd = {"Watch it count down to the end of the day", "The clock ticks life away","I had to fall\n" +
            "To lose it all","In the end it doesn't even matter","Breaking the habit tonight"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        Typeface logoDesign = Typeface.createFromAsset(getApplicationContext().getAssets(),"ARCENA.ttf");
        TextView logoSign = (TextView) findViewById(R.id.devMode);
        logoSign.setTypeface(logoDesign);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        //ActionBar actionBar = getSupportActionBar();

        //actionBar.hide();

        devMode();
        onEnter();
        progressDialog = new ProgressDialog(this);

        auth = FirebaseAuth.getInstance();

        if(auth.getCurrentUser() != null){
            //close this activity
            finish();
            //opening profile activity
            startActivity(new Intent(getApplicationContext(), home.class));
        }

        Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(4000);//TODO: Change to 5000, 2000 for test purposes
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // update TextView here!
                                quotes = (TextView) findViewById(R.id.qotd);
                                quotes.setText(qotd[change]);
                                change++;

                                if(change == 4){
                                    change = 0;

                                }
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        t.start();
    }

    private void devMode(){

        final TextView developer = (TextView)findViewById(R.id.devMode);

        developer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                devchange++;
                System.out.println(devchange);

                if(devchange==5){
                    Toast.makeText(getApplicationContext(), "2 more steps to developer mode", Toast.LENGTH_LONG).show();
                }
                if(devchange==7){
                    Toast.makeText(getApplicationContext(), "Entering developer mode", Toast.LENGTH_LONG).show();

                    startActivity(new Intent(getApplicationContext(), setting.class));
                }
            }
        });




    }

    public void userLogin(){
    //Get login info
        editTextemail = (EditText) findViewById(R.id.email);
        editTextpassword = (EditText) findViewById(R.id.password);

        String email = editTextemail.getText().toString().trim();
        String password  = editTextpassword.getText().toString().trim();


        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }


        progressDialog.setMessage("Logging in please Wait...");
        progressDialog.show();

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if (task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(), home.class));
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Error 404: Problems with logging in", Toast.LENGTH_LONG).show();
                        }

                    }
                });


    }

    public void handleRegistration(View view) { //Register screen
        Intent register = new Intent(this, RegisterScreen.class);
        startActivity(register);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        System.out.println("Registration");
    }


    public void onClick(View view) {
        userLogin();
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }


    public void onEnter(){
        editTextpassword = (EditText) findViewById(R.id.password);
        editTextpassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_ENTER:
                            userLogin();
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


    public void viewPass(View view){

        final EditText passwordText = (EditText)findViewById(R.id.password);
        final Button  viewPassword  = (Button)findViewById(R.id.passViewer);


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



}
