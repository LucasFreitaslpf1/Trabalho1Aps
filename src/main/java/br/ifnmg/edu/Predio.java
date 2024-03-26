package br.ifnmg.edu;

import java.util.ArrayList;
import java.util.List;

public class Predio {
    private String nome;

    private Campus campus;

    private List<SalaReuniao> salas = new ArrayList<>();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    public Predio() {
    }

    public Predio(String nome, Campus campus) {
        this.nome = nome;
        this.campus = campus;
    }

    public void adicionarSala(SalaReuniao sala){
        salas.add(sala);
    }

    @Override
    public String toString() {
        return "Predio{" +
                "nome='" + nome + '\'' +
                ", campus=" + campus.getNome() +
                '}';
    }
}
