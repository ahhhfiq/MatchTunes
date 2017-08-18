package tp.edu.sg.musicstreamv10;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import tp.edu.sg.musicstreamv10.util.AppUtil;

public class tbt extends AppCompatActivity {

    private Toolbar mToolbar;

    private String[] fallforyou  = new String[6];
    private String[] apologize = new String[6];
    private Object[] songs={fallforyou, apologize};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tbt);


        fallforyou[0] = "S1001";
        fallforyou[1] = "Fall For You";
        fallforyou[2] = "Secondhand Serenade";
        fallforyou[3] = "60646c4a7ceae434006b8e2fa3fd977b6beddd95?cid=2afe87a64b0042dabf51f37318616965";
        fallforyou[4] = "3.06";
        fallforyou[5] = "fallforyou";


        apologize[0] = "S1002";
        apologize[1] = "Apologize";
        apologize[2] = "OneRepublic";
        apologize[3] = "c8b8ba32830c4af5030c609294e8d2375bf2fee2?cid=2afe87a64b0042dabf51f37318616965";
        apologize[4] = "3.47";
        apologize[5] = "apologize";

        mToolbar = (Toolbar)findViewById(R.id.toolbar3);

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
        Intent intent = new Intent(this, play_music2.class);

        intent.putExtra("id", songs[0]);
        intent.putExtra("title",songs[1]);
        intent.putExtra("artist", songs[2]);
        intent.putExtra("fileLink", songs[3]);
        intent.putExtra("coverArt", songs[5]);
        startActivity(intent);
    }



}
