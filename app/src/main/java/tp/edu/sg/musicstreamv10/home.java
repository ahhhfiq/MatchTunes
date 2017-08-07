package tp.edu.sg.musicstreamv10;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.SpannableStringBuilder;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class home extends AppCompatActivity {

    private int iSwitch = 1;
    private FirebaseAuth auth;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private ActionBar actionBar;
    private Toolbar mToolbar;
    // TODO: 13/7/2017 :
    //Design home screen
    //Add profile class & library class
    //implement settings
    //add music by 21/7/2017
    //maps screen by 23/7/17
    //Remove redundant items


    private SliderLayout sliderShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView toolTitle  = (TextView)findViewById(R.id.toolbar_title);
        Typeface logoDesign = Typeface.createFromAsset(getApplicationContext().getAssets(),"ARCENA.ttf");

        toolTitle.setTypeface(logoDesign);



        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        auth = FirebaseAuth.getInstance();

        //if the user is not logged in
        //that means current user will return null
        if (auth.getCurrentUser() == null) {
            //closing this activity
            finish();
            //start login activity
            startActivity(new Intent(this, LoginScreen.class));
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        }
        FirebaseUser user = auth.getCurrentUser();



        //For iframe
        mToolbar =(Toolbar)findViewById(R.id.toolbar);


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToolbar =(Toolbar) findViewById(R.id.toolbar);
        mToggle = new ActionBarDrawerToggle(this,  mDrawerLayout, mToolbar, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        if(getActionBar() != null){
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mToggle.syncState();

        imageSwitching();
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


    private void imageSwitching(){


    }

    //TODO: Change to Random class
    public void libraryClicker(View view){

        Intent intent = new Intent(this, library.class);
        startActivity(intent);

    }
}
