package br.ifnmg.edu.outros;

import br.ifnmg.edu.dominio.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Controlador {

    public Controlador() {
    }

    public void criaEquipamento(String nome, Integer patrimonio) {
        Equipamento e = new Reserva().criaEquipamento();
        e.setNome(nome);
        e.setPatrimonio(patrimonio);
        Dados.salvarEquipamento(e);
    }

    public List<String> getEquipamentos() {
        return Dados.getEquipamentos();
    }

    public void criaCampus(String nome, String rua, Integer numero, String bairro, String cidade) {
        Campus c = new Reserva().criaFuncionario().criaCampus();
        c.setNome(nome);
        c.setRua(rua);
        c.setNumero(numero);
        c.setBairro(bairro);
        c.setCidade(cidade);
        Dados.salvarCampus(c);
    }


    public List<String> listarCampi() {
        return Dados.getCampi();

    }

    public void criaFuncionario(String nome, String cargo, String ramal) {
        Funcionario f = new Reserva().criaFuncionario();
        f.setNome(nome);
        f.setRamal(ramal);
        f.setCargo(cargo);
        Dados.salvarFuncionario(f);
    }

    public List<String> getFuncionarios() {
        return Dados.getFuncionarios();
    }

    public Boolean temCampus() {
        return !Dados.getCampi().isEmpty();
    }

    public Boolean temPredio() {
        return !Dados.getPredios().isEmpty();
    }

    public void criaPredio(String nome, Integer numeroCampus) {
        Predio p = new Reserva().criaFuncionario().criaCampus().criaPredio();
        p.setNome(nome);
        p.setCampus(Dados.getCampus(numeroCampus));
        Dados.salvarPredio(p);
    }

    public List<String> getPredios() {
        return Dados.getPredios();
    }

    public void criarSala(Integer numero, Integer qtdLugares, Integer numPredio) {
        SalaReuniao s = new Reserva().criaSala();
        s.setQtdLugares(qtdLugares);
        s.setNumero(numero);
        s.setPredio(Dados.getPredio(numPredio));
        Dados.salvarSala(s);
    }

    public List<String> getSalas() {
        return Dados.getSalas();
    }

    public Boolean temSala() {
        return !Dados.getSalas().isEmpty();
    }

    public void criaReserva(String data, String horaInicio, String horaFim,
                            String assunto, ArrayList<Integer> equipamentos, Integer numSala) {

        Reserva r = new Reserva();
        r.setAssunto(assunto);
        r.setDataAlocacao(LocalDate.parse(data));

        r.setHoraInicio(LocalTime.parse(horaInicio));
        r.setHoraFim(LocalTime.parse(horaFim));
        r.setSala(Dados.getSala(numSala));
        for (Integer e : equipamentos) {
            r.adicionarEquipamento(Dados.getEquipamento(e));
        }

        Dados.salvarReserva(r);
    }

    public List<String> getReservas() {
        return Dados.getReservas();
    }

    public List<String> getSalasOcupadasPeriodo(String dataInicio, String dataFim) {
        LocalDate inicio = LocalDate.parse(dataInicio);
        LocalDate fim = LocalDate.parse(dataFim);

        return Dados.getSalasPeriodo(inicio,fim);
    }

    public List<String> getSalasOrdemCronologica(Integer escolha) {
        return Dados.listaSalasOrdemCronologica(escolha);
    }

    public List<String> getSalasLivres(String data, String horaInicio, String horaFim) {

        LocalDate dataDesejada = LocalDate.parse(data);
        LocalTime inicio = LocalTime.parse(horaInicio);
        LocalTime fim = LocalTime.parse(horaFim);

        return Dados.getSalasLivres(dataDesejada,inicio,fim);
    }
}