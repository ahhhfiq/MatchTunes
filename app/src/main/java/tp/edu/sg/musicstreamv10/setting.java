package tp.edu.sg.musicstreamv10;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class setting extends AppCompatActivity {

    private FirebaseAuth auth;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private ActionBar actionBar;
    private Toolbar mToolbar;
    private Button BtnLogout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        auth = FirebaseAuth.getInstance();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToolbar =(Toolbar) findViewById(R.id.toolbarSettings);
        mToggle = new ActionBarDrawerToggle(this,  mDrawerLayout, mToolbar, R.string.open, R.string.close);


        mDrawerLayout.addDrawerListener(mToggle);
        if(getActionBar() != null){
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mToggle.syncState();
        logout();
    }


    public void logout(){
        BtnLogout = (Button) findViewById(R.id.logout);


        BtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(setting.this);

                builder.setMessage("Do you want to logout?");
                builder.setCancelable(true);

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //logging out the user
                        auth.signOut();
                        //closing activity
                        finish();
                        //starting login activity
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        dialog.cancel();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });


                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

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
        else if (id==R.id.search){
            Intent intent = new Intent(this, searchme.class);
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

    public void myAcc(View view) {
        Intent intent = new Intent(this, myAccount.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }

    public void notif(View view) {
        Intent intent = new Intent(this, notif.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }

    public void about(View view) {
        Intent intent = new Intent(this, abouttheapp.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }



}
