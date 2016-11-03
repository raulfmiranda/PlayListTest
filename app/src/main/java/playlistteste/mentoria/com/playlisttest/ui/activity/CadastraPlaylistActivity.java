package playlistteste.mentoria.com.playlisttest.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import playlistteste.mentoria.com.playlisttest.R;
import playlistteste.mentoria.com.playlisttest.application.CustomApplication;
import playlistteste.mentoria.com.playlisttest.control.PlaylistControl;
import playlistteste.mentoria.com.playlisttest.model.Musica;
import playlistteste.mentoria.com.playlisttest.ui.adapter.MusicasAdapter;

public class CadastraPlaylistActivity extends BasicActivity {
    private final static  int REQUEST_CODE_SELECIONA_MUSICA = 1;
    private Button cadastrar;
    private EditText nomePlaylist;
    private ListView listView;
    private View progressView;
    private MusicasAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_playlist);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        final CustomApplication customApplication = getCustomApplication();
        adapter = new MusicasAdapter(this);

        cadastrar = (Button) findViewById(R.id.cadastrar);
        nomePlaylist = (EditText) findViewById(R.id.editText);

        nomePlaylist.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                supportInvalidateOptionsMenu();
            }
        });

        listView = (ListView) findViewById(R.id.listView);
        progressView = findViewById(R.id.progress);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivityForResult(new Intent(CadastraPlaylistActivity.this, MusicasListaActivity.class), REQUEST_CODE_SELECIONA_MUSICA);
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

    protected CustomApplication getCustomApplication() {
        return (CustomApplication) getApplicationContext();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_cadastra_playlist;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.criar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean showMenu = !(nomePlaylist.getText().toString().trim().isEmpty());
        menu.findItem(R.id.action_save).setVisible(showMenu);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_save) {
            PlaylistControl playlistControl = getPlaylistControl();
            playlistControl.addPlaylist(playlistControl.newPlaylist(nomePlaylist.getText().toString()));

            Toast.makeText(getApplicationContext(), "PlayList Cadastrada com Sucesso", Toast.LENGTH_SHORT).show();

            finish();
        } else if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private PlaylistControl getPlaylistControl() {
        CustomApplication customApplication = getCustomApplication();
        return customApplication.getPlaylistControl();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SELECIONA_MUSICA) {
            if (resultCode == Activity.RESULT_OK) {
                long[] ids = data.getLongArrayExtra("musicas_ids");
                Toast.makeText(this, "Lista --> "+Arrays.toString(ids), Toast.LENGTH_SHORT).show();
                List<Musica> novaLista = new ArrayList<>();
                for (Long id: ids) {
                    Musica musica = getCustomApplication().getMusicasControl().findMusicaById(id);
                    novaLista.add(musica);
                }
                adapter.setItems(novaLista);
            } else {
                Toast.makeText(this, "Operacao Cancelada!", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
