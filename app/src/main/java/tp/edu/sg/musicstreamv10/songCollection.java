package tp.edu.sg.musicstreamv10;

/**
 * Created by School on 12/8/2017.
 */

public class songCollection {

    private Songs[] songs = new Songs[2];
    public songCollection() {
        prepareSongs();
    }


    public void prepareSongs() {
        Songs theWayYouLookTonight = new Songs(
                "S1001",
                "The Way You Look Tonight",
                "Michael Buble",
                "a5b8972e764025020625bbf9c1c2bbb06e394a60?cid=null",
                4.39,
                "michael_buble_collection");



        songs[0] = theWayYouLookTonight;

    }

    public Songs searchById(String id)
    {
        Songs song = null;

        for(int index = 0; index < songs.length; index++)
        {
            song = songs[index];
            if(song.getId().equals(id))
            {
                return song;
            }
        }

        return song;
    }

    public Songs getNextSong(String currentSongId)
    {
        Songs song = null;

        for(int i = 0; i < songs.length; i++)
        {

            if(songs[i].getId().equals(currentSongId) && (i < songs.length -1))
            {
                song = songs[i+1];
                break;


            }
        }
        return song;
    }

    public Songs getPrevSong(String currentSongId)
    {
        Songs song = null;

        for(int i = 0; i < songs.length; i++)
        {
            if(songs[i].getId().equals(currentSongId) && (i != 0))
            {
                song = songs[i-1];
                break;
            }
        }

        return song;
    }
}
