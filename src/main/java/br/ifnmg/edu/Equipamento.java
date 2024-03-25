package br.ifnmg.edu;

public class Equipamento {

    private String nome;
    private Integer patrimonio;

    public Equipamento() {
    }

    public Equipamento(String nome, Integer patrimonio) {
        this.nome = nome;
        this.patrimonio = patrimonio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(Integer patrimonio) {
        this.patrimonio = patrimonio;
    }

    @Override
    public String toString() {
        return "Equipamento{" +
                "nome='" + nome + '\'' +
                ", patrimonio=" + patrimonio +
                '}';
    }
}
