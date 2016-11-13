package playlistteste.mentoria.com.playlisttest.ui.adapter;

import android.annotation.TargetApi;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import playlistteste.mentoria.com.playlisttest.R;
import playlistteste.mentoria.com.playlisttest.model.Musica;
import playlistteste.mentoria.com.playlisttest.model.PlayList;
import playlistteste.mentoria.com.playlisttest.ui.activity.MusicasListaActivity;
import playlistteste.mentoria.com.playlisttest.ui.adapter.viewholder.MusicaViewHolder;
import playlistteste.mentoria.com.playlisttest.ui.adapter.viewholder.PlayListViewHolder;

public class MusicasRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<Musica> items = new ArrayList<>();
    private final AppCompatActivity activity;
    private final Set<Long> selectedIds = new HashSet<>();

    public MusicasRecyclerAdapter(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    // primeira parte do getView
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();

        View view = layoutInflater.inflate(R.layout.row_musica, parent, false);
        return new MusicaViewHolder(view);
    }

    // segunda parte do getView
    @TargetApi(24)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Musica item = (Musica) getItem(position);
        ((MusicaViewHolder)holder).getNomeTextView().setText(item.getNome());

        ((MusicaViewHolder)holder).itemView.setOnClickListener(new View.OnClickListener() {
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

        if(activity instanceof MusicasListaActivity) {
            boolean isSelected = selectedIds.contains(item.getId());
            ((MusicaViewHolder)holder).getNomeTextView().setTextColor(isSelected ? activity.getColor(R.color.colorPrimaryDark) :  activity.getColor(R.color.colorAccent));
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public Object getItem(int position) {
        return items.get(position);
    }


    public void setItems(List<Musica> novaLista) {
        items.clear();
        items.addAll(novaLista);

        notifyDataSetChanged();
    }

    public Set<Long> getSelectedIds() {
        return selectedIds;
    }

}
