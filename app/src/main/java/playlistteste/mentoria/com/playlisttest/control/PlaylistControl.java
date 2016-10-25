package playlistteste.mentoria.com.playlisttest.control;

import android.os.SystemClock;
import android.text.format.DateUtils;

import java.util.ArrayList;
import java.util.List;

import playlistteste.mentoria.com.playlisttest.model.Estilo;
import playlistteste.mentoria.com.playlisttest.model.Musica;
import playlistteste.mentoria.com.playlisttest.model.PlayList;


public class PlaylistControl {
    private final List<PlayList> lista = new ArrayList<PlayList>();

    public PlaylistControl() {
    }

    public void addPlaylist(PlayList playList) {
        lista.add(playList);
    }

    public List<PlayList> retrieveLista() {
        if (lista.isEmpty()) {
            List<PlayList> playLists = carregarLista();
            if (playLists != null) {
                lista.addAll(playLists);
            }
        }
        return new ArrayList<>(lista);
    }


    private List<PlayList> carregarLista() {
        SystemClock.sleep(5 * DateUtils.SECOND_IN_MILLIS);
        List<PlayList> listCarregada = new ArrayList<>();
        listCarregada.add(newSertanejo());
        listCarregada.add(newRock());
        listCarregada.add(newMpb());
        return listCarregada;
    }

    public PlayList newMpb() {
        List<Musica> musicas = new ArrayList<>();
        musicas.add(new Musica("Z-NomeMpb1", "A-AutorMpb1"));
        musicas.add(new Musica("T-NomeMpb3", "C-AutorMpb3"));
        musicas.add(new Musica("S-NomeMpb2", "E-AutorMpb2"));
        musicas.add(new Musica("B-NomeMpb1", "X-AutorMpb1"));

        PlayList.Builder builder = new PlayList.Builder(System.nanoTime(),"Música Popular")
                .setEstilo(Estilo.MPB)
                .setMusicas(musicas);
        return builder.build();
    }

    public  PlayList newRock() {
        List<Musica> musicas = new ArrayList<>();
        musicas.add(new Musica("Z-NomeRock1", "Z-AutorRock1"));
        musicas.add(new Musica("C-NomeRock3", "C-AutorRock3"));
        musicas.add(new Musica("E-NomeRock2", "E-AutorRock2"));
        musicas.add(new Musica("X-NomeRock1", "X-AutorRock1"));

        PlayList.Builder builder = new PlayList.Builder(System.nanoTime(),"Roqueiras Punk")
                .setEstilo(Estilo.ROCK)
                .setMusicas(musicas);
        return builder.build();
    }

    public  PlayList newSertanejo() {
        List<Musica> musicas = new ArrayList<>();
        musicas.add(new Musica("A-NomeSertanejo1", "A-AutorSertanejo1"));
        musicas.add(new Musica("C-NomeSertanejo3", "C-AutorSertanejo3"));
        musicas.add(new Musica("E-NomeSertanejo2", "E-AutorSertanejo2"));
        musicas.add(new Musica("B-NomeSertanejo1", "B-AutorSertanejo1"));

        PlayList.Builder builder = new PlayList.Builder(System.nanoTime(), "Sertanejas UAI")
                .setEstilo(Estilo.SERTANEJO)
                .setMusicas(musicas);
        return builder.build();
    }

    public PlayList newPlaylist(String nome) {
        List<Musica> musicas = new ArrayList<>();
        musicas.add(new Musica("A-Nome1", "A-Autor1"));
        musicas.add(new Musica("C-Nome3", "C-Autor3"));
        musicas.add(new Musica("E-Nome2", "E-Autor2"));
        musicas.add(new Musica("B-Nome1", "B-Autor1"));

        PlayList.Builder builder = new PlayList.Builder(System.nanoTime(), nome)
                .setEstilo(Estilo.OUTROS)
                .setMusicas(musicas);
        return builder.build();
    }

    public PlayList getPlayListPorId(Long id) {
        List<PlayList> playLists = retrieveLista();
        PlayList playlist = null;
        for (PlayList pl: playLists) {
            if (pl.getId().equals(id)) {
                playlist = pl;
                break;
            }
        }
        return playlist;
    }
}
