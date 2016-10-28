package playlistteste.mentoria.com.playlisttest.application;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import playlistteste.mentoria.com.playlisttest.control.MusicasControl;
import playlistteste.mentoria.com.playlisttest.control.PlaylistControl;
import playlistteste.mentoria.com.playlisttest.model.PlayList;

public class CustomApplication extends Application{

    private PlaylistControl playlistControl;
    private MusicasControl musicasControl;

    @Override
    public void onCreate() {
        super.onCreate();

        playlistControl = new PlaylistControl();
        musicasControl = new MusicasControl();

    }
    public PlaylistControl getPlaylistControl() {
        return playlistControl;
    }

    public MusicasControl getMusicasControl() { return musicasControl; }

}
