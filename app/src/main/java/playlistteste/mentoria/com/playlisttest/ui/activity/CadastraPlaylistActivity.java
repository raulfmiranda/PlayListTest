package playlistteste.mentoria.com.playlisttest.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import playlistteste.mentoria.com.playlisttest.R;
import playlistteste.mentoria.com.playlisttest.application.CustomApplication;

public class CadastraPlaylistActivity extends AppCompatActivity {
    private Button cadastrar;
    private EditText nomePlaylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_playlist);

        final CustomApplication customApplication = (CustomApplication) getApplicationContext();
        cadastrar = (Button) findViewById(R.id.cadastrar);
        nomePlaylist = (EditText) findViewById(R.id.editText);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customApplication.getPlaylistControl()
                        .addPlaylist(customApplication.getPlaylistControl().newPlaylist(nomePlaylist.getText().toString()));

                Toast.makeText(getApplicationContext(), "PlayLista Cadastrada com Sucesso", Toast.LENGTH_SHORT).show();
                nomePlaylist.setText("");
            }
        });
    }
}
