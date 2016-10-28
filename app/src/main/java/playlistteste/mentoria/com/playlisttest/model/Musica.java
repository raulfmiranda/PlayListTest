package playlistteste.mentoria.com.playlisttest.model;

import java.util.Comparator;

public class Musica {
    private Long id;
    private String nome;
    private String autor;

    public Musica(Long id, String nome, String autor) {
        this.id = id;
        this.nome = nome;
        this.autor = autor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Musica musica = (Musica) o;

        return id != null ? id.equals(musica.id) : musica.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
