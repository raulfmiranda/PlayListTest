package playlistteste.mentoria.com.playlisttest.ui.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import playlistteste.mentoria.com.playlisttest.R;
import playlistteste.mentoria.com.playlisttest.model.Musica;


public class MusicasAdapter extends BaseAdapter{
    private final List<Musica> items = new ArrayList<>();
    private final Activity activity;

    public MusicasAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setItems(List<Musica> novaLista) {
        items.clear();
        items.addAll(novaLista);

        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView == null ?
                activity.getLayoutInflater().inflate(R.layout.row_playlist, parent, false)
                : convertView;

        TextView nomeTextView = (TextView) view.findViewById(R.id.playlist_nome);

        Musica item = (Musica) getItem(position);
        nomeTextView.setText(item.getNome());

        /*
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<PlayList> copia = new ArrayList<>();

                copia.addAll(items);
                copia.addAll(items);
                setItems(copia);
            }
        });*/

        return view;
    }
}


