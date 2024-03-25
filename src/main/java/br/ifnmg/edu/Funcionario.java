package br.ifnmg.edu;

public class Funcionario {

    private String nome;
    private String cargo;
    private String ramal;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getRamal() {
        return ramal;
    }

    public void setRamal(String ramal) {
        this.ramal = ramal;
    }

    public Campus criaCampus(){

        return new Campus();
    }

    public Funcionario() {
    }

    public Funcionario(String nome, String cargo, String ramal) {
        this.nome = nome;
        this.cargo = cargo;
        this.ramal = ramal;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "nome='" + nome + '\'' +
                ", cargo='" + cargo + '\'' +
                ", ramal='" + ramal + '\'' +
                '}';
    }
}
