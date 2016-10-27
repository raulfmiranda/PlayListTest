package playlistteste.mentoria.com.playlisttest.ui.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import playlistteste.mentoria.com.playlisttest.R;
import playlistteste.mentoria.com.playlisttest.application.CustomApplication;
import playlistteste.mentoria.com.playlisttest.model.PlayList;
import playlistteste.mentoria.com.playlisttest.ui.adapter.PlaylistAdapter;

public class MainActivity extends AppCompatActivity {
    private View progressView;
    private Button criarPlaylist;
    private final PlaylistAdapter adapter = new PlaylistAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressView = findViewById(R.id.progress);
        criarPlaylist = (Button) findViewById(R.id.criarplaylist);

        final CustomApplication customApplication = (CustomApplication) getApplicationContext();

        final ListView listView;
        listView = (ListView) findViewById(R.id.listView);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, MusicasActivity.class);
                PlayList item = (PlayList) adapter.getItem(i);
                intent.putExtra("id", item.getId());
                startActivity(intent);
            }
        });


        criarPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CadastraPlaylistActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        final CustomApplication customApplication = (CustomApplication) getApplicationContext();

        new AsyncTask<Void, Void, List<PlayList>>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressView.setVisibility(View.VISIBLE);
            }

            @Override
            public List<PlayList> doInBackground(Void... voids) {
                List<PlayList> playLists = customApplication.getPlaylistControl().retrieveLista();
                return playLists;
            }

            @Override
            protected void onPostExecute(List<PlayList> playLists) {
                super.onPostExecute(playLists);
                progressView.setVisibility(View.GONE);
                adapter.setItems(playLists);
            }
        }.execute();
    }
}
