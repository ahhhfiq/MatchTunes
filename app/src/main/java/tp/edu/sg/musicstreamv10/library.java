package tp.edu.sg.musicstreamv10;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.databinding.DataBindingUtil;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


import tp.edu.sg.musicstreamv10.util.AppUtil;


public class library extends AppCompatActivity {


    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private ActionBar actionBar;
    private Toolbar mToolbar;



    private String[] lostStars = new String[6];
    private String[] photograph = new String[6];
    private String[] roar = new String[6];
    private String[] whatIveDone = new String[6];
    private String[] saveMeNow = new String[6];
    private String[] talkingToMyself = new String[6];
    private String[] heavy = new String[6];
    private String[] galway = new String[6];
    private Object[] songs = {lostStars, roar, photograph, whatIveDone, saveMeNow, talkingToMyself, heavy, galway};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);


        lostStars[0] = "S1001";
        lostStars[1] = "Lost Stars";
        lostStars[2] = "Adam Levine";
        lostStars[3] = "4e855af04636ec04d2ce5fc48270b25fb7784433?cid=2afe87a64b0042dabf51f37318616965";
        lostStars[4] = "4.3";
        lostStars[5] = "lost_stars";

        roar[0] ="S1002";
        roar[1] ="Roar";
        roar[2] ="Katy Perry";
        roar[3] ="f99e43444f69a4af6dd79ebc3fe6c0e18cfabbd9?cid=2afe87a64b0042dabf51f37318616965";
        roar[4] ="3.73";
        roar[5] ="roar";

        photograph[0] = "S1003";
        photograph[1] = "Photograph";
        photograph[2] = "Ed Sheeran";
        photograph[3] = "097c7b735ceb410943cbd507a6e1dfda272fd8a8?cid=null";
        photograph[4] = "4.32";
        photograph[5] = "photograph";

        whatIveDone[0]="S1004";
        whatIveDone[1]="What I've Done";
        whatIveDone[2]="Linkin Park";
        whatIveDone[3]="b2bdf31db7c3a90dfa8f5c7539f8832c4089afe8?cid=2afe87a64b0042dabf51f37318616965";
        whatIveDone[4]="3.43";
        whatIveDone[5]="linkin_park";

        saveMeNow[0]="S1005";
        saveMeNow[1]="Nobody Can Save Me";
        saveMeNow[2]="Linkin Park";
        saveMeNow[3]="68c7d43f0d89a47e90862456371e92993ac66acd?cid=2afe87a64b0042dabf51f37318616965";
        saveMeNow[4]="3.76";
        saveMeNow[5]="linkin_park_2";

        talkingToMyself[0]="S1006";
        talkingToMyself[1]="Talking To Myself";
        talkingToMyself[2]="Linkin Park";
        talkingToMyself[3]="3b4495d1092ad4675bf5abb8dfb840619a232122?cid=2afe87a64b0042dabf51f37318616965";
        talkingToMyself[4]="3.86";
        talkingToMyself[5]="linkin_park_2";

        heavy[0]="S1007";
        heavy[1]="Heavy";
        heavy[2]="Linkin Park FEAT Kiiara";
        heavy[3]="54f76a271f9d9995eafc92cab90e1f7e021ebc14?cid=2afe87a64b0042dabf51f37318616965";
        heavy[4]="2.83";
        heavy[5]="linkin_park_2";

        galway[0]="S1008";
        galway[1]="Galway Girl";
        galway[2]="Ed Sheeran"; //She ran away
        galway[3]="cec1fc40a0220f20d3b91dd28d8e1141ad5e7e25?cid=2afe87a64b0042dabf51f37318616965";
        galway[4]="2.85";
        galway[5]="divide";


        //For iframe
        mToolbar =(Toolbar)findViewById(R.id.toolbar3);


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToolbar =(Toolbar) findViewById(R.id.toolbar3);
        mToggle = new ActionBarDrawerToggle(this,  mDrawerLayout, mToolbar, R.string.open, R.string.close);

        mToolbar.setTitle("Library");
        mToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mToolbar);
        mDrawerLayout.addDrawerListener(mToggle);
        if(getActionBar() != null){
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mToggle.syncState();

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

    public void handleSelection(View view) {

        String resourceID = AppUtil.getResourceId(this,view);
        System.out.println("Button Clicked for :" + resourceID);
        String[] selectedSong = searchById(resourceID);
        AppUtil.popMessage(this,"Streaming song: "+ selectedSong[1]);
        sendDataToActivity1(selectedSong);

    }
    public String[] searchById(String id)
    {
        String[] song = null;

        for(int index=0;index<songs.length;index++)
        {
            song= (String[]) songs[index];
            if(song[0].equals(id))
            {
                System.out.println(song[0]);
                return song;
            }
        }

        //return song;
        return null;
    }
    public void sendDataToActivity1(String[] songs)
    {
        Intent intent = new Intent(this, playMusic.class);

        intent.putExtra("id", songs[0]);
        intent.putExtra("title",songs[1]);
        intent.putExtra("artist", songs[2]);
        intent.putExtra("fileLink", songs[3]);
        intent.putExtra("coverArt", songs[5]);
        startActivity(intent);
    }

    public void backButton(View view) {
        System.out.println("back");
        // Intent intent = new Intent(this, home.class);
        super.onBackPressed();
        //startActivity(intent);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.searchmenu, menu);
        MenuItem item = menu.findItem(R.id.menuSearch);
        SearchView searchView = (SearchView)item.getActionView();
        SearchManager searchManager = (SearchManager) library.this.getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        if(searchView.isIconified()){
            searchView.setQuery("", false);
            searchView.clearFocus();
        }
        else{
            searchView.setQueryHint("Search songs");
        }


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            public boolean onQueryTextChange(String newText) {
                return false;

            }
        });
        return super.onCreateOptionsMenu(menu);
    }

}
