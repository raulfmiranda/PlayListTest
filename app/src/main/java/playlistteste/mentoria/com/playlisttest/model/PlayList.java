package playlistteste.mentoria.com.playlisttest.model;

import java.util.ArrayList;
import java.util.List;


public class PlayList {
    private Long id;
    private List<Musica> musicas;
    private String nome;
    private Estilo estilo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Estilo getEstilo() {
        return estilo;
    }

    public void setEstilo(Estilo estilo) {
        this.estilo = estilo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Musica> getMusicas() {
        return new ArrayList<>(musicas);
        //return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayList playList = (PlayList) o;

        return id != null ? id.equals(playList.id) : playList.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public static class Builder {
        private Long id;
        private List<Musica> musicas;
        private String nome;
        private Estilo estilo;

        public Builder(Long id, String nome) {
            this.id  = id;
            this.nome = nome;
        }

        public Builder setEstilo(Estilo estilo) {
            this.estilo = estilo;
            return this;
        }

        public Builder setMusicas(List<Musica> musicas) {
            this.musicas = musicas;
            return this;
        }

        public PlayList build() {
            PlayList playList = new PlayList();
            playList.setId(id);
            playList.setEstilo(estilo);
            playList.setNome(nome);
            playList.setMusicas(this.musicas);
            return playList;
        }

    }
}
