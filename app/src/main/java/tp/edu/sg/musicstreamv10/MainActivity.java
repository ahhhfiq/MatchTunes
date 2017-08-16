package tp.edu.sg.musicstreamv10;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.*;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    //TODO: Remove redundant stuff

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ActionBar actionBar = getSupportActionBar();

        //actionBar.hide();


        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//Set to portrait only mode

        //Changing the font

        Typeface logoDesign = Typeface.createFromAsset(getApplicationContext().getAssets(),"ARCENA.ttf");
        TextView logoSign = (TextView) findViewById(R.id.textView2);
        logoSign.setTypeface(logoDesign);

        loadScreen();
        auth = FirebaseAuth.getInstance();

    }

    public void loadScreen() {

        new CountDownTimer(5000 ,1000){
            public void onTick(long millisUntilFinished) {

                System.out.println("Loading...");
            }
            public void onFinish(){
                checkLogin();
            }
        }.start();

    }



    private void checkLogin(){
        auth = FirebaseAuth.getInstance();

        final ProgressBar circle = (ProgressBar) findViewById(R.id.progressBar2);

        if(auth.getCurrentUser() != null){
            //close this activity
            finish();
            //opening profile activity
            startActivity(new Intent(getApplicationContext(), home.class));
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        }
        else {
            startActivity(new Intent(getApplicationContext(), LoginScreen.class));
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        }
    }
}