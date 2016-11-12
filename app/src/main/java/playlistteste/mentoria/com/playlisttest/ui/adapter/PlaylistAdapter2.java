package playlistteste.mentoria.com.playlisttest.ui.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
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
        if (getItem(position) instanceof PlayList) {
            return PLAYLIST;
        } else if (getItem(position) instanceof Musica) {
            return MUSIC;
        } else {
            throw new IllegalStateException("View Type não suportado");
        }
    }

    @Override
    public int getViewTypeCount() {
        return ROW_TYPES;
    }

    public void setItems(List<PlayList> novaLista) {
        setItems(novaLista, null);
    }

    public void setItems(List<PlayList> novaLista, Set<Long> selectedPlayListIds) {
        items.clear();

        for (PlayList playlist : novaLista) {
            items.add(playlist);
            if (selectedPlayListIds != null && selectedPlayListIds.contains(playlist.getId())) {
                List<Musica> musicas = playlist.getMusicas();
                for (Musica musica : musicas) {
                    items.add(musica);
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        View view;
        if (convertView == null) {
            LayoutInflater layoutInflater = activity.getLayoutInflater();
            if (type == PLAYLIST) {
                view = layoutInflater.inflate(R.layout.row_playlist, parent, false);
                PlayListViewHolder viewHolder = (PlayListViewHolder) view.getTag();
                if (viewHolder == null) {
                    viewHolder = new PlayListViewHolder();
                    viewHolder.nomeTextView = (TextView) view.findViewById(R.id.playlist_nome);
                    view.setTag(viewHolder);
                }
            } else if (type == MUSIC) {
                view = layoutInflater.inflate(R.layout.row_musica, parent, false);
                MusicaViewHolder viewHolder = (MusicaViewHolder) view.getTag();
                if (viewHolder == null) {
                    viewHolder = new MusicaViewHolder();
                    viewHolder.nomeTextView = (TextView) view.findViewById(R.id.musica_nome);
                    view.setTag(viewHolder);
                }
            } else {
                throw new IllegalStateException("View Type não suportado");
            }
        } else {
            view = convertView;
        }

        Object item = getItem(position);
        if (item instanceof PlayList) {
            PlayListViewHolder playListViewHolder = (PlayListViewHolder) view.getTag();
            playListViewHolder.nomeTextView.setText(((PlayList)item).getNome());
        } else if (item instanceof Musica) {
            MusicaViewHolder musicaViewHolder = (MusicaViewHolder) view.getTag();
            musicaViewHolder.nomeTextView.setText(((Musica)item).getNome());
        }

        return view;
    }

    public static final class PlayListViewHolder {
        TextView nomeTextView;
    }

    public static final class MusicaViewHolder {
        TextView nomeTextView;
    }
}
