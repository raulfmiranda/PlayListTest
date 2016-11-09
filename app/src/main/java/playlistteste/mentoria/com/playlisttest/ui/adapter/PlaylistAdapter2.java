package playlistteste.mentoria.com.playlisttest.ui.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import playlistteste.mentoria.com.playlisttest.R;
import playlistteste.mentoria.com.playlisttest.model.Musica;
import playlistteste.mentoria.com.playlisttest.model.PlayList;

public class PlaylistAdapter2 extends BaseAdapter {

    private static final int MUSIC = 0;
    private static final int PLAYLIST = 1;
    private static final int ROW_TYPES = PLAYLIST + 1;

    private final List<Object> items = new ArrayList<>();
    private final Activity activity;

    public PlaylistAdapter2(Activity activity) {
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

    @Override
    public int getItemViewType(int position) {
        return getItem(position) instanceof PlayList ? PLAYLIST : MUSIC;
    }

    @Override
    public int getViewTypeCount() {
        return ROW_TYPES;
    }

    public void setItems(List<PlayList> novaLista) {
        items.clear();
        List<Musica> musicas;

        for (PlayList playlist: novaLista) {
            items.add(playlist);
        }
        notifyDataSetChanged();
    }

    public void setItems(List<PlayList> novaLista, Set<Long> ids) {
        items.clear();
        List<Musica> musicas;

        for (PlayList playlist: novaLista) {
            items.add(playlist);

            if(ids.contains(playlist.getId())) {
                musicas = playlist.getMusicas();
                for (Musica musica: musicas) {
                    items.add(musica);
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        TextView nomeTextView;
        View view;

        if(convertView == null) {
            view = type == PLAYLIST ? activity.getLayoutInflater().inflate(R.layout.row_playlist, parent, false)
                    : activity.getLayoutInflater().inflate(R.layout.row_musica, parent, false);
        } else {
            view = convertView;
        }

        nomeTextView = type == PLAYLIST ? (TextView) view.findViewById(R.id.playlist_nome)
                : (TextView) view.findViewById(R.id.musica_nome);

        Object item = getItem(position);

        if(item instanceof PlayList) {
            nomeTextView.setText(((PlayList)item).getNome());
        } else {
            nomeTextView.setText("      "+((Musica)item).getNome());
        }

        return view;
    }
}
