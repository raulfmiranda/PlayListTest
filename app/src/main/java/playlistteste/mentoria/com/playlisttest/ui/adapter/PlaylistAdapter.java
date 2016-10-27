package playlistteste.mentoria.com.playlisttest.ui.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import playlistteste.mentoria.com.playlisttest.R;
import playlistteste.mentoria.com.playlisttest.model.PlayList;


public class PlaylistAdapter extends BaseAdapter{
    private final List<PlayList> items = new ArrayList<>();
    private final Activity activity;

    public PlaylistAdapter(Activity activity) {
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

    public void setItems(List<PlayList> novaLista) {
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

        PlayList item = (PlayList) getItem(position);
        nomeTextView.setText(item.getNome());

        return view;
    }
}


