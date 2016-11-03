package playlistteste.mentoria.com.playlisttest.ui.activity;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import playlistteste.mentoria.com.playlisttest.R;
import playlistteste.mentoria.com.playlisttest.application.CustomApplication;
import playlistteste.mentoria.com.playlisttest.model.Musica;

public class MusicaAdicionarActivity extends BasicActivity {
    private EditText nomeMusica;
    private EditText autorMusica;
    private Button botaoCadastrarMusica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musica_adicionar);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        final CustomApplication customApplication = (CustomApplication) getApplicationContext();

        nomeMusica = (EditText) findViewById(R.id.nomeEditText);
        autorMusica = (EditText) findViewById(R.id.autorEditText);
        botaoCadastrarMusica = (Button) findViewById(R.id.botaoCadastrarMusica);

        botaoCadastrarMusica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = "";
                String autor = "";

                if(nomeMusica.getText() != null && autorMusica.getText() != null) {
                    nome = nomeMusica.getText().toString();
                    autor = autorMusica.getText().toString();
                }

                if(!(nome.isEmpty()) && !(autor.isEmpty())) {
                    Musica musica = new Musica(System.nanoTime(), nome, autor);
                    customApplication.getMusicasControl().addMusica(musica);
                    nomeMusica.setText("");
                    autorMusica.setText("");
                    //Toast.makeText(customApplication, "Música Cadastrada com Sucesso!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_musica_adicionar;
    }
}
