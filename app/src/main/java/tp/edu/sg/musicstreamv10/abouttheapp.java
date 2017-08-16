package tp.edu.sg.musicstreamv10;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class abouttheapp extends AppCompatActivity {

    private ImageView git;
    private ImageView fb;
    private ImageView sO;
    private ImageView Fi;
    private ImageView i8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abouttheapp);
        goToWeb();
    }

    private void goToWeb(){
        git = (ImageView)findViewById(R.id.git);
        fb  = (ImageView)findViewById(R.id.firebase);
        sO  = (ImageView)findViewById(R.id.stackover);
        Fi  = (ImageView)findViewById(R.id.flat);
        i8  = (ImageView)findViewById(R.id.icons8);

        git.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://github.com/ahhhfiq/MatchTunes/"));
                startActivity(intent);
            }
        });

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://firebase.google.com/"));
                startActivity(intent);
            }
        });

        i8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://icons8.com/"));
                startActivity(intent);}
        });


        sO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://stackoverflow.com/"));
                startActivity(intent);}
        });

        Fi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.flaticon.com/"));
                startActivity(intent);
            }
        });

    }

}
