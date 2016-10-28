package playlistteste.mentoria.com.playlisttest.control;

import android.os.SystemClock;
import android.text.format.DateUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import playlistteste.mentoria.com.playlisttest.model.Estilo;
import playlistteste.mentoria.com.playlisttest.model.Musica;
import playlistteste.mentoria.com.playlisttest.model.PlayList;


public class PlaylistControl {
    private final Map<Long, PlayList> lista = new HashMap<>();

    public PlaylistControl() {
    }

    public void addPlaylist(PlayList playList) {
        lista.put(playList.getId(), playList);
    }

    public List<PlayList> retrieveLista() {
        if (lista.isEmpty()) {
            List<PlayList> playLists = carregarLista();
            if (playLists != null) {
                for(PlayList pl : playLists) {
                    lista.put(pl.getId(), pl);
                }
            }
        }
        return new ArrayList<>(lista.values());
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
        musicas.add(new Musica(System.nanoTime(), "Z-NomeMpb1", "A-AutorMpb1"));
        musicas.add(new Musica(System.nanoTime(), "T-NomeMpb3", "C-AutorMpb3"));
        musicas.add(new Musica(System.nanoTime(), "S-NomeMpb2", "E-AutorMpb2"));
        musicas.add(new Musica(System.nanoTime(), "B-NomeMpb1", "X-AutorMpb1"));

        PlayList.Builder builder = new PlayList.Builder(System.nanoTime(),"Música Popular")
                .setEstilo(Estilo.MPB)
                .setMusicas(musicas);
        return builder.build();
    }

    public  PlayList newRock() {
        List<Musica> musicas = new ArrayList<>();
        musicas.add(new Musica(System.nanoTime(), "Z-NomeRock1", "Z-AutorRock1"));
        musicas.add(new Musica(System.nanoTime(), "C-NomeRock3", "C-AutorRock3"));
        musicas.add(new Musica(System.nanoTime(), "E-NomeRock2", "E-AutorRock2"));
        musicas.add(new Musica(System.nanoTime(), "X-NomeRock1", "X-AutorRock1"));

        PlayList.Builder builder = new PlayList.Builder(System.nanoTime(),"Roqueiras Punk")
                .setEstilo(Estilo.ROCK)
                .setMusicas(musicas);
        return builder.build();
    }

    public  PlayList newSertanejo() {
        List<Musica> musicas = new ArrayList<>();
        musicas.add(new Musica(System.nanoTime(), "A-NomeSertanejo1", "A-AutorSertanejo1"));
        musicas.add(new Musica(System.nanoTime(), "C-NomeSertanejo3", "C-AutorSertanejo3"));
        musicas.add(new Musica(System.nanoTime(), "E-NomeSertanejo2", "E-AutorSertanejo2"));
        musicas.add(new Musica(System.nanoTime(), "B-NomeSertanejo1", "B-AutorSertanejo1"));

        PlayList.Builder builder = new PlayList.Builder(System.nanoTime(), "Sertanejas UAI")
                .setEstilo(Estilo.SERTANEJO)
                .setMusicas(musicas);
        return builder.build();
    }

    public PlayList newPlaylist(String nome) {
        List<Musica> musicas = new ArrayList<>();
        musicas.add(new Musica(System.nanoTime(), "A-"+nome, "A-Autor1"));
        musicas.add(new Musica(System.nanoTime(), "C-"+nome, "C-Autor3"));
        musicas.add(new Musica(System.nanoTime(), "E-"+nome, "E-Autor2"));
        musicas.add(new Musica(System.nanoTime(), "B-"+nome, "B-Autor1"));

        PlayList.Builder builder = new PlayList.Builder(System.nanoTime(), nome)
                .setEstilo(Estilo.OUTROS)
                .setMusicas(musicas);
        return builder.build();
    }

    public PlayList getPlayListPorId(Long id) {
        return lista.get(id);
    }
}
