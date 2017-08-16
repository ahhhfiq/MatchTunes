package tp.edu.sg.musicstreamv10;

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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

public class searchme extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private ActionBar actionBar;
    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchme);


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToolbar =(Toolbar) findViewById(R.id.searchmeToolbar);
        mToggle = new ActionBarDrawerToggle(this,  mDrawerLayout, mToolbar, R.string.open, R.string.close);


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

   public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.searchmenu, menu);
        MenuItem item = menu.findItem(R.id.menuSearch);
        SearchView searchView = (SearchView)item.getActionView();
        SearchManager searchManager = (SearchManager) searchme.this.getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setIconifiedByDefault(false);




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
