package playlistteste.mentoria.com.playlisttest.control;

import android.os.SystemClock;
import android.text.format.DateUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import playlistteste.mentoria.com.playlisttest.model.Musica;

public class MusicasControl {
    private Map<Long, Musica> mapMusicas = new HashMap<>();

    public List<Musica> retrieveMusicas() {
        if(mapMusicas.isEmpty()) {
            List<Musica> listaMusicas = carregarMusicas();
            if(listaMusicas != null) {
                for(Musica musica : listaMusicas) {
                    mapMusicas.put(musica.getId(), musica);
                }
            }
        }
        return new ArrayList<>(mapMusicas.values());
    }

    private List<Musica> carregarMusicas() {
        SystemClock.sleep(2 * DateUtils.SECOND_IN_MILLIS);

        List<Musica> musicasCarregadas = new ArrayList<>();
        musicasCarregadas.add(new Musica(System.nanoTime(), "Felicidade", "Marcelo Jeneci"));
        musicasCarregadas.add(new Musica(System.nanoTime(), "Como diria Blavatsky", "Jorge Vercilo"));
        musicasCarregadas.add(new Musica(System.nanoTime(), "Índios", "Legião Urbana"));
        musicasCarregadas.add(new Musica(System.nanoTime(), "Hey Jude", "The Beatles"));

        return musicasCarregadas;
    }

    public void addMusica(Musica musica) {
        mapMusicas.put(musica.getId(), musica);
    }

    public Musica findMusicaById(Long id) {
        return mapMusicas.get(id);
    }
}
