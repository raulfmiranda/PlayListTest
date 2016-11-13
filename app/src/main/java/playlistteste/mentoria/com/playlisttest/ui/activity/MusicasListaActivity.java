package playlistteste.mentoria.com.playlisttest.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;
import java.util.Set;

import playlistteste.mentoria.com.playlisttest.R;
import playlistteste.mentoria.com.playlisttest.application.CustomApplication;
import playlistteste.mentoria.com.playlisttest.control.PlaylistControl;
import playlistteste.mentoria.com.playlisttest.model.Musica;
import playlistteste.mentoria.com.playlisttest.ui.adapter.MusicasAdapter;
import playlistteste.mentoria.com.playlisttest.ui.adapter.MusicasRecyclerAdapter;

public class MusicasListaActivity extends BasicActivity {
    private final MusicasRecyclerAdapter adapter = new MusicasRecyclerAdapter(this);
    private View progressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musicas_lista);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        final CustomApplication customApplication = (CustomApplication) getApplicationContext();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

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
                recyclerView.setAdapter(adapter);
            }
        }.execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.select, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean showMenu = !adapter.getSelectedIds().isEmpty();
        menu.findItem(R.id.action_done).setVisible(showMenu);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_done) {
            Set<Long> selectedIds = adapter.getSelectedIds();
            Intent data = new Intent();
            long[] ids = new long[selectedIds.size()];
            int i=0;
            for(Long id:selectedIds) {
                ids[i++]=id;
            }
            data.putExtra("musicas_ids", ids);

            setResult(Activity.RESULT_OK, data);


            finish();
        } else if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_musicas_lista;
    }
}
