package br.ifnmg.edu.dominio;

import java.util.Objects;

public class SalaReuniao {
    private Integer numero;
    private Integer qtdLugares;
    private Predio predio;

    public SalaReuniao() {
    }

    public SalaReuniao(Integer numero, Integer qtdLugares, Predio predio) {
        this.numero = numero;
        this.qtdLugares = qtdLugares;
        this.predio = predio;
    }


    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getQtdLugares() {
        return qtdLugares;
    }

    public void setQtdLugares(Integer qtdLugares) {
        this.qtdLugares = qtdLugares;
    }

    public Predio getPredio() {
        return predio;
    }

    public void setPredio(Predio predio) {
        this.predio = predio;
    }

    @Override
    public String toString() {
        return "SalaReuniao{" +
                "numero=" + numero +
                ", qtdLugares=" + qtdLugares +
                ", predio=" + predio.getNome() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return this.hashCode() == o.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, qtdLugares, predio);
    }
}
