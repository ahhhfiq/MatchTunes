package tp.edu.sg.musicstreamv10;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class instrumental extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrumental);


        mToolbar = (Toolbar)findViewById(R.id.toolbar3);

        mToolbar.setTitle("Instrumental");
        mToolbar.setTitleTextColor(Color.WHITE);
    }




}
