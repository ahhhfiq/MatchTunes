package tp.edu.sg.musicstreamv10;

/**
 * Created by Ahhhfiq on 10/8/2017.
 */

public class songList {
    private String singer;
    private String song;


    public songList(){

    }

    public songList(String si, String so){
        this.singer = si;
        this.song = so;

    }
    public String getSinger(){
        return singer;
    }
    public void setSinger(String singer){
        this.singer = singer;
    }
    public String getSong(){
        return song;
    }
    public void setSong(String song){
        this.song = song;
    }

}

