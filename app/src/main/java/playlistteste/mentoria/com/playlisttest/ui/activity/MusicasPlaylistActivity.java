package playlistteste.mentoria.com.playlisttest.ui.activity;

import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import playlistteste.mentoria.com.playlisttest.R;
import playlistteste.mentoria.com.playlisttest.application.CustomApplication;
import playlistteste.mentoria.com.playlisttest.control.MusicaComparator;
import playlistteste.mentoria.com.playlisttest.control.PlaylistControl;
import playlistteste.mentoria.com.playlisttest.model.Musica;
import playlistteste.mentoria.com.playlisttest.model.PlayList;
import playlistteste.mentoria.com.playlisttest.ui.adapter.*;

public class MusicasPlaylistActivity extends BasicActivity {
    private final MusicasRecyclerAdapter adapter = new MusicasRecyclerAdapter(this);
    private TextView tituloPlaylist;
    private Button ordemAlfabetica;
    private List<Musica> copiaMusicas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musicas);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        final CustomApplication customApplication = (CustomApplication) getApplicationContext();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setLayoutManager(layoutManager);

        final Long id = getIntent().getLongExtra("id", -1);
        recyclerView.setAdapter(adapter);



        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        new AsyncTask<Long, Void, PlayList>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //progressView.setVisibility(View.VISIBLE);
            }

            @Override
            public PlayList doInBackground(Long... ids) {
                return customApplication.getPlaylistControl().getPlayListPorId(ids[0]);
            }

            @Override
            protected void onPostExecute(PlayList playList) {
                super.onPostExecute(playList);
                //progressView.setVisibility(View.GONE);
                adapter.setItems(playList.getMusicas());
            }
        }.execute(id);

        ordemAlfabetica = (Button) findViewById(R.id.ordemAlfabetica);

        ordemAlfabetica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                copiaMusicas = new ArrayList<Musica>(customApplication.getPlaylistControl().getPlayListPorId(id).getMusicas());
                MusicaComparator comparator = new MusicaComparator();
                Collections.sort(copiaMusicas, comparator);

                adapter.setItems(copiaMusicas);
                recyclerView.setAdapter(adapter);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_musicas;
    }
}
