package playlistteste.mentoria.com.playlisttest.ui.activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import playlistteste.mentoria.com.playlisttest.R;
import playlistteste.mentoria.com.playlisttest.application.CustomApplication;
import playlistteste.mentoria.com.playlisttest.model.Musica;
import playlistteste.mentoria.com.playlisttest.ui.adapter.MusicasAdapter;

public class MusicasListaActivity extends AppCompatActivity {
    private ListView listView;
    private View progressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musicas_lista);

        final CustomApplication customApplication = (CustomApplication) getApplicationContext();
        final MusicasAdapter adapter = new MusicasAdapter(this);

        listView = (ListView) findViewById(R.id.listView);
        progressView = findViewById(R.id.progress);

        new AsyncTask<Void, Void, List<Musica>>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressView.setVisibility(View.VISIBLE);
            }

            @Override
            public List<Musica> doInBackground(Void... voids) {
                return customApplication.getMusicasControl().retrieveMusicas();
            }

            @Override
            protected void onPostExecute(List<Musica> musicas) {
                super.onPostExecute(musicas);
                progressView.setVisibility(View.GONE);
                adapter.setItems(musicas);
                listView.setAdapter(adapter);
            }
        }.execute();
    }
}
