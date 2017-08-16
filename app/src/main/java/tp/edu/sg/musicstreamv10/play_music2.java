package tp.edu.sg.musicstreamv10;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.io.IOException;
import java.util.Random;

import tp.edu.sg.musicstreamv10.util.AppUtil;

public class play_music2 extends AppCompatActivity {


    private MediaPlayer player = null;
    private int musicPosition = 0;
    private Button btnPlayPause = null;
    private ToggleButton volToggle;



    private static final String BASE_URL = "http://p.scdn.co/mp3-preview/";
    private String songId  ="";
    private String title   ="";
    private String artist  ="";
    private String fileLink="";
    private String coverArt="";
    private String url     ="";

    private SeekBar volumeSeekbar = null;
    private AudioManager audioManager = null;

    private boolean shuffleAct = false;
    private boolean loopAct    = false;

    private int timeElapsed;
    private SeekBar seekBar;
    private Handler seekHandler;

    private String[] fallforyou  = new String[6];
    private String[] apologize = new String[6];
    private Object[] songs={fallforyou, apologize};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music2);

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

        loopFunc();
        shuffleFunc();
        retrievedData();
        displaySong(title, artist, coverArt);
        autoPlayMusic();
        initControls();
    }

    private void retrievedData() {
        Bundle songData = this.getIntent().getExtras();
        songId   = songData.getString("id");
        title    = songData.getString("title");
        artist   = songData.getString("artist");
        fileLink = songData.getString("fileLink");
        coverArt = songData.getString("coverArt");

        url = BASE_URL + fileLink;
        System.out.println(url);
    }

    private void displaySong(String title, String artist, String coverArt)
    {
        // This is to retrieve the song title TextView from the UI screen.
        TextView txtTitle = (TextView) findViewById(R.id.txtSongTitle);


        TextView musicTitle =(TextView)findViewById(R.id.toolTitle);
        musicTitle.setText(title);

        // This is to set the text of the song title TextView to the selected title.
        txtTitle.setText(title);

        // This is to retrieve the artist TextView from the UI screen.
        TextView txtArtist = (TextView) findViewById(R.id.txtSongArtist);

        // This is to set the text of the artist TextView to the selected artist name.
        txtArtist.setText(artist);

        // This is to get the ID of the cover art from the drawable folder.
        int imageId = AppUtil.getImageIdFromDrawable(this, coverArt);

        // This is to retrieve the cover art ImageView from the UI screen.
        ImageView ivCoverArt = (ImageView) findViewById(R.id.imgCoverArt);

        // This is to set the selected cover art image to the ImageView in the screen.
        ivCoverArt.setImageResource(imageId);

    }

    public void autoPlayMusic(){

        if (player == null)
            preparePlayer();

        player.start();
        seekBar = (SeekBar) findViewById(R.id.seekBar1);
        seekBar.setMax(player.getDuration());
        seekHandler = new Handler();
        findViewById(R.id.playNpause).setBackgroundResource(R.drawable.ic_pause_black_24dp);

        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                String[] nextSong = getNextSong(songId);
                String[] nextLoopSong = nextLoopSong(songId);
                if (loopAct){
                    System.out.println("Next");

                    if (nextLoopSong!=null) {

                        songId = nextLoopSong[0];
                        title = nextLoopSong[1];
                        artist = nextLoopSong[2];
                        fileLink = nextLoopSong[3];
                        coverArt = nextLoopSong[5];

                        System.out.println(songId);

                        url = BASE_URL + fileLink;

                        displaySong(title, artist, coverArt);

                        stopActivities();

                        autoPlayMusic();

                    }
                }
                else if (shuffleAct)
                {
                    if (nextSong!=null)
                    {
                        System.out.println("shuffle random");
                        Random random = new Random();

                        int n = random.nextInt(1)+1;

                        System.out.println(n);
                        nextSong=(String[])songs[n];
                        songId=nextSong[0];
                        title=nextSong[1];
                        artist=nextSong[2];
                        fileLink=nextSong[3];
                        coverArt=nextSong[5];

                        System.out.println(songId);

                        url = BASE_URL + fileLink;

                        displaySong(title,artist,coverArt);

                        stopActivities();

                        autoPlayMusic();

                    }
                }
                else{
                    if(nextSong != null)
                    {

                        songId=nextSong[0];
                        title=nextSong[1];
                        artist=nextSong[2];
                        fileLink=nextSong[3];
                        coverArt=nextSong[5];

                        url = BASE_URL + fileLink;

                        displaySong(title,artist,coverArt);

                        stopActivities();

                        autoPlayMusic();

                    }
                }
            }
        });

        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if (player!=null) {
                    timeElapsed = player.getCurrentPosition();
                }
                seekBar.setProgress(timeElapsed);

                seekHandler.postDelayed(this, 100);
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(player!=null&&fromUser)
                    player.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void playOrPauseMusic(View view)
    {
        System.out.println("click");
        if (player == null)
            preparePlayer();

        if (!player.isPlaying())
        {
            System.out.println("in");
            if (musicPosition > 0)
            {
                player.seekTo(musicPosition);
            }
            player.start();
            findViewById(R.id.playNpause).setBackgroundResource(R.drawable.ic_pause_black_24dp);

            setTitle("Now Playing: " + title + " - " + artist);

            gracefullyStopWhenMusicEnds();
        }
        else
        {
            System.out.println("pause");
            pauseMusic();
        }
    }

    public void preparePlayer()
    {
        player = new MediaPlayer();
        System.out.println("prepare");
        try
        {
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);

            player.setDataSource(url);

            player.prepare();
            System.out.println("try");
        }
        catch (IOException e)
        {
            System.out.println("fail");
            e.printStackTrace();
        }
    }

    public void gracefullyStopWhenMusicEnds()
    {
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
        {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer)
            {
                stopActivities();
            }
        });
    }

    public void stopActivities()
    {
        if (player != null)
        {
            findViewById(R.id.playNpause).setBackgroundResource(R.drawable.ic_pause_black_24dp);
            musicPosition = 0;
            setTitle("");
            player.stop();
            player.reset();
            player.release();
            player = null;
        }
    }

    public void pauseMusic()
    {
// 1. Pause the player.
        player.pause();
// 2. Get the current position of the music that is playing.
        musicPosition = player.getCurrentPosition();
// 3. Set the image on the button back to “PLAY”
        findViewById(R.id.playNpause).setBackgroundResource(R.drawable.ic_play_arrow_black_24dp);
    }


    public String[] getNextSong(String currentSongId) {
//Create a temporary empty string
        String[] song = null;
//starting from index 0 of the songs array to the last one.
//Loop through every song item. Increment the index by one after every loop
        for (int index=0; index < songs.length;index++)
        {
//create a temporary String Array to store each item in the songs array
            String[] tempSong = (String[]) songs[index];
//if we are able to find the current song from the array - which we should...
//we check if that song position in the array is NOT the last item so that we can go next item
            if (tempSong[0].equals(currentSongId) && (index < songs.length-1))
            {
                song = (String[]) songs[index+1];
//break away from the For loop since we are able to return the next song item
                break;
            }
        }
//return the song item to the caller
        return song;
    }

    public String[] getPrevSong(String currentSongId) {
//Create a temporary empty string
        String[] song = null;
//starting from index 0 of the songs array to the last one.
//Loop through every song item. Increment the index by one after every loop
        for (int index=0; index < songs.length;index++)
        {
//create a temporary String Array to store each item in the songs array
            String[] tempSong = (String[]) songs[index];
//if we are able to find the current song from the array - which we should...
//we check if that song position in the array is NOT the first item so that we can get the item before it
            if (tempSong[0].equals(currentSongId) && (index >= 1))
            {
                song = (String[]) songs[index-1];
//break away from the For loop since we are able to return the previous song item
                break;
            }
        }
        return song;
    }

    public String[] nextLoopSong(String currentSongId){
//Create a temporary empty string
        String[] song = null;
//starting from index 0 of the songs array to the last one.
//Loop through every song item. Increment the index by one after every loop

        for (int index=0; index < songs.length;index++)
        {
//create a temporary String Array to store each item in the songs array
            String[] tempSong = (String[]) songs[index];
//if we are able to find the current song from the array - which we should...
//we check if that song position in the array is NOT the last item so that we can go next item
            if (tempSong[0].equals(currentSongId) && (index < songs.length-1))
            {
                song = (String[]) songs[index+1];
//break away from the For loop since we are able to return the next song item
                break;
            }
            else
            {
                song =(String[]) songs[0];
            }
        }
//return the song item to the caller
        return song;
    }
    public String[] prevLoopSong(String currentSongId){

//Create a temporary empty string
        String[] song = null;

//starting from index 0 of the songs array to the last one.
//Loop through every song item. Increment the index by one after every loop
        for (int index=0; index < songs.length;index++)
        {
//create a temporary String Array to store each item in the songs array
            String[] tempSong = (String[]) songs[index];

//if we are able to find the current song from the array - which we should...
//we check if that song position in the array is NOT the first item so that we can get the item before it
            if (tempSong[0].equals(currentSongId) && (index >= 1))
            {
                song = (String[]) songs[index-1];
//break away from the For loop since we are able to return the previous song item
                break;
            }
            else{
                song =(String[])songs[songs.length-1];
            }

        }
//return the song item to the caller
        return song;
    }



    public void shuffleFunc(){
        final ToggleButton btnShuffle = (ToggleButton) findViewById(R.id.shuffleButton);
        Button playNext = (Button) findViewById(R.id.playNext);
        Button playPrev = (Button) findViewById(R.id.playPrev);


        btnShuffle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    shuffleAct = true;
                    System.out.println("Shuffle " + shuffleAct);
                    btnShuffle.setBackgroundResource(R.drawable.shuffleon);

                }
                else{
                    btnShuffle.setBackgroundResource(R.drawable.shuffleoff);
                    shuffleAct = false;
                }
            }
        });



        playNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[]  nextSong=nextLoopSong(songId);
                System.out.println("next "+shuffleAct);
                if(shuffleAct) {

                    System.out.println("Next");
                    if (nextSong!=null)
                    {
                        System.out.println("shuffle random");
                        Random random = new Random();

                        int n = random.nextInt(1)+1;

                        System.out.println(n);
                        nextSong= (String[])songs[n];
                        songId =nextSong[0];
                        title=nextSong[1];
                        artist=nextSong[2];
                        fileLink=nextSong[3];
                        coverArt=nextSong[5];

                        System.out.println(songId);

                        url = BASE_URL + fileLink;

                        displaySong(title,artist,coverArt);

                        stopActivities();

                        autoPlayMusic();

                    }
                }
                else{
                    System.out.println("null");

                    if(nextSong != null)
                    {

                        songId=nextSong[0];
                        title=nextSong[1];
                        artist=nextSong[2];
                        fileLink=nextSong[3];
                        coverArt=nextSong[5];

                        url = BASE_URL + fileLink;

                        displaySong(title,artist,coverArt);

                        stopActivities();

                        autoPlayMusic();

                    }

                }
            }
        });

        playPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[]  prevSong=prevLoopSong(songId);
                if (shuffleAct) {
                    if (prevSong != null) {
                        Random random = new Random();

                        int n = random.nextInt(1)+1;
                        prevSong = (String[]) songs[n];
                        System.out.println(songId);

                        songId = prevSong[0];
                        title = prevSong[1];
                        artist = prevSong[2];
                        fileLink = prevSong[3];
                        coverArt = prevSong[5];
                        url = BASE_URL + fileLink;

                        displaySong(title, artist, coverArt);

                        stopActivities();

                        autoPlayMusic();
                    }
                }
                else{

                    if(prevSong != null){
                        songId=prevSong[0];
                        title=prevSong[1];
                        artist=prevSong[2];
                        fileLink=prevSong[3];
                        coverArt=prevSong[5];

                        url = BASE_URL + fileLink;

                        displaySong(title,artist,coverArt);

                        stopActivities();

                        autoPlayMusic();
                    }
                }
            }
        });

    }


    public void loopFunc(){
        final ToggleButton btnLoop = (ToggleButton)findViewById(R.id.loopButton);
        Button playNext = (Button) findViewById(R.id.playNext);
        Button playPrev = (Button) findViewById(R.id.playPrev);

        btnLoop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    btnLoop.setBackgroundResource(R.drawable.loopon);
                    loopAct = true;
                    System.out.println("Looping");
                }
                else {
                    btnLoop.setBackgroundResource(R.drawable.loopoff);
                }
            }
        });


        playNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] nextLoopSong = nextLoopSong(songId);
                if (loopAct){
                    System.out.println("Next");



                    if (nextLoopSong!=null) {

                        songId = nextLoopSong[0];
                        title = nextLoopSong[1];
                        artist = nextLoopSong[2];
                        fileLink = nextLoopSong[3];
                        coverArt = nextLoopSong[5];
                        System.out.println(songId);
                        url = BASE_URL + fileLink;
                        displaySong(title, artist, coverArt);
                        stopActivities();
                        autoPlayMusic();

                    }
                }

            }
        });

        playPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[]  prevLoopSong=prevLoopSong(songId);
                if (loopAct) {

                    if (prevLoopSong != null) {
                        songId = prevLoopSong[0];
                        title = prevLoopSong[1];
                        artist = prevLoopSong[2];
                        fileLink = prevLoopSong[3];
                        coverArt = prevLoopSong[5];
                        url = BASE_URL + fileLink;

                        displaySong(title, artist, coverArt);

                        stopActivities();

                        autoPlayMusic();

                    }
                }

            }
        });
    }


    private void initControls(){

        volToggle = (ToggleButton)findViewById(R.id.volumeToggle);
        volumeSeekbar = (SeekBar)findViewById(R.id.volBar);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);


        volToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    volumeSeekbar.setVisibility(View.VISIBLE);
                }
                else{
                    volumeSeekbar.setVisibility(View.INVISIBLE);
                }
            }
        });

        try{
            volumeSeekbar.setMax(audioManager
                    .getStreamMaxVolume(AudioManager.STREAM_MUSIC));
            volumeSeekbar.setProgress(audioManager
                    .getStreamVolume(AudioManager.STREAM_MUSIC));


            volumeSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
            {
                @Override
                public void onStopTrackingTouch(SeekBar arg0){
                }

                @Override
                public void onStartTrackingTouch(SeekBar arg0) {
                }

                @Override
                public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                            progress, 0);
                }
            });
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }



    @Override
    public void onBackPressed(){
        stopActivities();
        finish();
        super.onBackPressed();
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }


    public void backButton(View view) {
        System.out.println("back");
        stopActivities();
        finish();
        onBackPressed();
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);

    }

}
