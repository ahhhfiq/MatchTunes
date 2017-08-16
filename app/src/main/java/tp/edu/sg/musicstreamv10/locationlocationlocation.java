package tp.edu.sg.musicstreamv10;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import java.util.Random;

public class locationlocationlocation extends AppCompatActivity {


    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private ActionBar actionBar;
    private Toolbar mToolbar;
    private ToggleButton locateToggle;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locationlocationlocation);

        progressDialog = new ProgressDialog(this);

        mToolbar =(Toolbar)findViewById(R.id.toolbar3);
        locateToggle = (ToggleButton)findViewById(R.id.locationSwitch);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToolbar =(Toolbar) findViewById(R.id.toolbar3);
        mToggle = new ActionBarDrawerToggle(this,  mDrawerLayout, mToolbar, R.string.open, R.string.close);


        mDrawerLayout.addDrawerListener(mToggle);
        if(getActionBar() != null){
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mToggle.syncState();

        locationfinder();

    }

    public boolean onNavigationSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.home){
            Intent intent = new Intent(this, home.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        }
        else if (id == R.id.profile){
            Intent intent = new Intent(this, profile.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        }
        else if (id == R.id.chat){
            Intent intent = new Intent(this, chat.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        }
        else if (id == R.id.library){
            Intent intent = new Intent(this, library.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        }
        else if (id == R.id.playlist){
            Intent intent = new Intent(this, playlist.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        }
        else if (id == R.id.location){

            Intent intent = new Intent(this, locationlocationlocation.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        }
        else if (id == R.id.settings){
            Intent intent = new Intent(this, setting.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        }
        return super.onOptionsItemSelected(item);
    }


    public void locationfinder(){

        locateToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    new CountDownTimer(3000 ,1000){
                        public void onTick(long millisUntilFinished) {

                            System.out.println("Loading...");
                            progressDialog.setMessage("Finding users near you");
                            progressDialog.show();

                        }
                        public void onFinish(){
                            progressDialog.dismiss();
                        }
                    }.start();

                }

                else{
                    new CountDownTimer(4000 ,1000){
                        public void onTick(long millisUntilFinished) {

                            System.out.println("Loading...");
                            progressDialog.setMessage("Turning off");
                            progressDialog.show();

                        }
                        public void onFinish(){
                            progressDialog.dismiss();
                        }
                    }.start();

                }

            }
        });

    }


}
