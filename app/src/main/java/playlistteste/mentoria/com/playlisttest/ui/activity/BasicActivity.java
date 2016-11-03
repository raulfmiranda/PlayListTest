package playlistteste.mentoria.com.playlisttest.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import playlistteste.mentoria.com.playlisttest.R;
import playlistteste.mentoria.com.playlisttest.application.CustomApplication;


public abstract class BasicActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    protected CustomApplication getCustomApplication() {
        return (CustomApplication) getApplicationContext();
    }

    protected abstract int getLayoutResId();
}
