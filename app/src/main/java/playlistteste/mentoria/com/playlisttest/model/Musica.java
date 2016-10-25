package playlistteste.mentoria.com.playlisttest.model;

import java.util.Comparator;

public class Musica {
    private String nome;
    private String autor;

    public Musica(String nome, String autor) {
        this.nome = nome;
        this.autor = autor;
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

        if (nome != null ? !nome.equals(musica.nome) : musica.nome != null) return false;
        return autor != null ? autor.equals(musica.autor) : musica.autor == null;

    }

    @Override
    public int hashCode() {
        int result = nome != null ? nome.hashCode() : 0;
        result = 31 * result + (autor != null ? autor.hashCode() : 0);
        return result;
    }

}
