package playlistteste.mentoria.com.playlisttest.ui.adapter;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import playlistteste.mentoria.com.playlisttest.R;
import playlistteste.mentoria.com.playlisttest.model.Musica;
import playlistteste.mentoria.com.playlisttest.ui.activity.MusicasListaActivity;


public class MusicasAdapter extends BaseAdapter{
    private final List<Musica> items = new ArrayList<>();
    private final AppCompatActivity activity;
    private final Set<Long> selectedIds = new HashSet<>();

    public MusicasAdapter(AppCompatActivity activity) {
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
        selectedIds.clear();
        items.addAll(novaLista);

        notifyDataSetChanged();
    }

    public Set<Long> getSelectedIds() {
        return selectedIds;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView == null ?
                activity.getLayoutInflater().inflate(R.layout.row_playlist, parent, false)
                : convertView;

        TextView nomeTextView = (TextView) view.findViewById(R.id.playlist_nome);

        final Musica item = (Musica) getItem(position);
        nomeTextView.setText(item.getNome());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Long id = item.getId();
                if (selectedIds.contains(id)) {
                    selectedIds.remove(id);
                } else {
                    selectedIds.add(id);
                }

                notifyDataSetChanged();
                activity.supportInvalidateOptionsMenu();
            }
        });
        boolean isSelected = selectedIds.contains(item.getId());
        nomeTextView.setTextColor(isSelected ? activity.getColor(R.color.colorPrimaryDark) :  activity.getColor(R.color.colorAccent));

        return view;
    }
}


