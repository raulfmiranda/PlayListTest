package playlistteste.mentoria.com.playlisttest.ui.activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import playlistteste.mentoria.com.playlisttest.R;
import playlistteste.mentoria.com.playlisttest.application.CustomApplication;
import playlistteste.mentoria.com.playlisttest.model.Musica;
import playlistteste.mentoria.com.playlisttest.model.PlayList;
import playlistteste.mentoria.com.playlisttest.ui.adapter.MusicasAdapter;

import static playlistteste.mentoria.com.playlisttest.R.id.tituloPlaylist;

public class CadastraPlaylistActivity extends AppCompatActivity {
    private Button cadastrar;
    private EditText nomePlaylist;
    private ListView listView;
    private View progressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_playlist);

        final CustomApplication customApplication = (CustomApplication) getApplicationContext();
        final MusicasAdapter adapter = new MusicasAdapter(this);

        cadastrar = (Button) findViewById(R.id.cadastrar);
        nomePlaylist = (EditText) findViewById(R.id.editText);
        listView = (ListView) findViewById(R.id.listView);
        progressView = findViewById(R.id.progress);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customApplication.getPlaylistControl()
                        .addPlaylist(customApplication.getPlaylistControl().newPlaylist(nomePlaylist.getText().toString()));

                Toast.makeText(getApplicationContext(), "PlayList Cadastrada com Sucesso", Toast.LENGTH_SHORT).show();
                nomePlaylist.setText("");
            }
        });


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
