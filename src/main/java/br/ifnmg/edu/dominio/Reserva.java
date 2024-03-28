package br.ifnmg.edu.dominio;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Reserva {

    private LocalDate dataAlocacao;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private String assunto;
    private List<Equipamento> equipamentos = new ArrayList<>();
    private SalaReuniao sala;
    private Funcionario locador;

    public Reserva() {
    }

    public Reserva(LocalDate dataAlocacao,
                   LocalTime horaInicio,
                   LocalTime horaFim,
                   String assunto,
                   SalaReuniao sala) {
        this.dataAlocacao = dataAlocacao;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.assunto = assunto;
        this.sala = sala;
    }

    public Equipamento criaEquipamento() {

        return new Equipamento();
    }

    public Funcionario criaFuncionario(){
        return new Funcionario();
    }

    public SalaReuniao criaSala(){
        return new SalaReuniao();
    }

    public LocalDate getDataAlocacao() {
        return dataAlocacao;
    }

    public void setDataAlocacao(LocalDate dataAlocacao) {
        this.dataAlocacao = dataAlocacao;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(LocalTime horaFim) {
        this.horaFim = horaFim;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }

    public SalaReuniao getSala() {
        return sala;
    }

    public void setSala(SalaReuniao sala) {
        this.sala = sala;
    }

    public Funcionario getLocador() {
        return locador;
    }

    public void setLocador(Funcionario locador) {
        this.locador = locador;
    }

    public void adicionarEquipamento(Equipamento e){
        equipamentos.add(e);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        return this.hashCode() == o.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataAlocacao, horaInicio, horaFim, assunto, equipamentos, sala, locador);
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "dataAlocacao=" + dataAlocacao +
                ", horaInicio=" + horaInicio +
                ", horaFim=" + horaFim +
                ", assunto='" + assunto + '\'' +
                ", equipamentos=" + equipamentos +
                ", sala=" + sala +
                '}';
    }
}
