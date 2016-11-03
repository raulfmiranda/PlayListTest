package playlistteste.mentoria.com.playlisttest.ui.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import playlistteste.mentoria.com.playlisttest.R;
import playlistteste.mentoria.com.playlisttest.application.CustomApplication;
import playlistteste.mentoria.com.playlisttest.model.PlayList;
import playlistteste.mentoria.com.playlisttest.ui.adapter.PlaylistAdapter;

public class MainActivity extends BasicActivity {
    private View progressView;
    private final PlaylistAdapter adapter = new PlaylistAdapter(this);
    private List<PlayList> playlists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        progressView = findViewById(R.id.progress);

        final ListView listView;
        listView = (ListView) findViewById(R.id.listView);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, MusicasPlaylistActivity.class);
                PlayList item = (PlayList) adapter.getItem(i);
                intent.putExtra("id", item.getId());
                startActivity(intent);
            }
        });
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onResume() {
        super.onResume();

        final CustomApplication customApplication = getCustomApplication();

        new AsyncTask<Void, Void, List<PlayList>>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressView.setVisibility(View.VISIBLE);
            }

            @Override
            public List<PlayList> doInBackground(Void... voids) {
                List<PlayList> playLists = customApplication.getPlaylistControl().retrieveLista();
                customApplication.getMusicasControl().retrieveMusicas();
                return playLists;
            }

            @Override
            protected void onPostExecute(List<PlayList> playLists) {
                super.onPostExecute(playLists);
                MainActivity.this.playlists = playLists;
                progressView.setVisibility(View.GONE);
                adapter.setItems(playLists);

                supportInvalidateOptionsMenu();
            }
        }.execute();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean showMenu = playlists != null;
        menu.findItem(R.id.action_add).setVisible(showMenu);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            Intent intent = new Intent(MainActivity.this, CadastraPlaylistActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
