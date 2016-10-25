package playlistteste.mentoria.com.playlisttest.control;

import java.util.Comparator;

import playlistteste.mentoria.com.playlisttest.model.Musica;

public class MusicaComparator implements Comparator<Musica>{
    public int compare(Musica musica1, Musica musica2) {
        return musica1.getNome().compareTo(musica2.getNome());
    }
}
