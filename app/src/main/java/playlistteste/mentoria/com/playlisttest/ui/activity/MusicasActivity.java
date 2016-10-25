package playlistteste.mentoria.com.playlisttest.ui.activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import playlistteste.mentoria.com.playlisttest.R;
import playlistteste.mentoria.com.playlisttest.application.CustomApplication;
import playlistteste.mentoria.com.playlisttest.control.MusicaComparator;
import playlistteste.mentoria.com.playlisttest.model.Musica;
import playlistteste.mentoria.com.playlisttest.model.PlayList;
import playlistteste.mentoria.com.playlisttest.ui.adapter.*;

public class MusicasActivity extends AppCompatActivity {
    private TextView tituloPlaylist;
    private Button ordemAlfabetica;
    private List<Musica> copiaMusicas;
    private ListView listView;
    private View progressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musicas);
        progressView = findViewById(R.id.progress);
        final CustomApplication customApplication = (CustomApplication) getApplicationContext();
        tituloPlaylist = (TextView) findViewById(R.id.tituloPlaylist);

        listView = (ListView) findViewById(R.id.listViewMusicas);
        final MusicasAdapter adapter = new MusicasAdapter(this);

        Long id = getIntent().getLongExtra("id", -1);
            listView.setAdapter(adapter);


        new AsyncTask<Long, Void, PlayList>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressView.setVisibility(View.VISIBLE);
            }

            @Override
            public PlayList doInBackground(Long... ids) {
                return customApplication.getPlaylistControl().getPlayListPorId(ids[0]);
            }

            @Override
            protected void onPostExecute(PlayList playList) {
                super.onPostExecute(playList);
                progressView.setVisibility(View.GONE);
                tituloPlaylist.setText(playList.getNome());
                adapter.setItems(playList.getMusicas());
            }
        }.execute(id);

        ordemAlfabetica = (Button) findViewById(R.id.ordemAlfabetica);

        ordemAlfabetica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  copiaMusicas = new ArrayList<>(customApplication.getPlaylistControl().retrieveLista().get(Integer.parseInt(posicao)).getMusicas());
                MusicaComparator comparator = new MusicaComparator();
                Collections.sort(copiaMusicas, comparator);

                adapter.setItems(copiaMusicas);
                listView.setAdapter(adapter);
            }
        });
    }
}
