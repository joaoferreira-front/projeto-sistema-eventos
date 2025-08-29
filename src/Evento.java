

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Evento {
    //Atributos
    //São as características que todo evento terá.
    private String nome;
    private String endereco;
    private String categoria;
    private LocalDateTime horario;
    private String descricao;

    public Evento(String nome, String endereco, String categoria, LocalDateTime horario, String descricao) {
        this.nome = nome;
        this.endereco = endereco;
        this.categoria = categoria;
        this.horario = horario;
        this.descricao = descricao;

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String paraFormatoArquivo() {
        return nome + ";" + endereco + ";" + categoria + ";" + horario + ";" + descricao;
    }

    public static Evento fromFormatoArquivo(String linha) {
        String[] partes = linha.split(";");

        String nome = partes[0];
        String endereco = partes[1];
        String categoria = partes[2];
        LocalDateTime horario = LocalDateTime.parse(partes[3]);
        String descricao = partes[4];
        return new Evento(nome, endereco, categoria, horario, descricao);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return "Evento: " + nome +
                "\n  Local: " + endereco +
                "\n  Categoria: " + categoria +
                "\n  Horário: " + horario.format(formatter) +
                "\n  Descrição: " + descricao + "\n";

    }
}