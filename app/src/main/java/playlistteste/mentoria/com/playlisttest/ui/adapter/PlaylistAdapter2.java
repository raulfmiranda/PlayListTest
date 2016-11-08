package playlistteste.mentoria.com.playlisttest.ui.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import playlistteste.mentoria.com.playlisttest.R;
import playlistteste.mentoria.com.playlisttest.model.Musica;
import playlistteste.mentoria.com.playlisttest.model.PlayList;

public class PlaylistAdapter2 extends BaseAdapter {

    private static final int TYPE_PLAYLIST = 1;
    private static final int TYPE_MUSICA = 2;
    private List<Integer> posicoesPlaylists = new ArrayList<>();

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
        return posicoesPlaylists.contains(position) ? TYPE_PLAYLIST : TYPE_MUSICA;
    }

    public void setItems(List<PlayList> novaLista) {
        items.clear();
        List<Musica> musicas;

        for (PlayList playlist: novaLista) {
            items.add(playlist);
            posicoesPlaylists.add(items.size() - 1);

            musicas = playlist.getMusicas();
            for (Musica musica: musicas) {
                items.add(musica);
            }
        }

        notifyDataSetChanged();
    }

    public void setItems(List<PlayList> novaLista, List<Integer> positions) {
        items.clear();
        List<Musica> musicas;

        for (PlayList playlist: novaLista) {
            items.add(playlist);
            posicoesPlaylists.add(items.size() - 1);

            if(!positions.contains(items.size()-1)) {
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

        View view = convertView == null ?
                activity.getLayoutInflater().inflate(R.layout.row_playlist, parent, false)
                : convertView;

        TextView nomeTextView = (TextView) view.findViewById(R.id.playlist_nome);
        Object item = getItem(position);

        if(item instanceof PlayList) {
            nomeTextView.setText(((PlayList)item).getNome());
        } else {
            nomeTextView.setText(" - "+((Musica)item).getNome());
        }

        return view;
    }
}
